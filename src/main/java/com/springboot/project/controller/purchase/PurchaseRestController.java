package com.springboot.project.controller.purchase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
 
import com.model2.mvc.common.util.PaymentOption;
import com.model2.mvc.common.util.TranStatusCode;
import com.model2.mvc.common.util.TranStatusCodeUtil;
import com.springboot.project.controller.common.CommonController;
import com.springboot.project.controller.product.ProductController;
import com.springboot.project.service.domain.Page;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.PurchaseVO;
import com.springboot.project.service.domain.RestApiCommonVO;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.UpdateTranCodeVO;
import com.springboot.project.service.domain.UserVO;
import com.springboot.project.service.product.ProductService;
import com.springboot.project.service.purchase.PurchaseService;
import com.springboot.project.service.user.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest/purchase/*")
public class PurchaseRestController extends CommonController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@RequestMapping(value = "/listPurchase/{page}")
	public Map<String, Object> listPurchase(
			@RequestBody SearchVO search, 
			@PathVariable("page") int page,
			HttpSession session) {
		System.out.println("[PurchaseController.listPurchase()] start");
		
		// Default page = 1;
		search.setPage(page);
		if(search.getPage() == 0) {
			search.setPage(1);
		}
		search.setPageUnit(PAGE_UNIT); 
		search.setPageSize(PAGE_SIZE);
		
		// 2. Get purchaseList
		boolean isAdmin = false;
		UserVO user = (UserVO) session.getAttribute("user");
		if (user != null && user.getRole().equals("admin")) {
			isAdmin = true;
		} else {
			user = new UserVO();
			user.setUserId("test");
		}
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, user);
		
		Page resultPage	= new Page(
				search.getPage(), 
				((Integer)map.get("totalCount")).intValue(), 
				PAGE_UNIT,
				PAGE_SIZE
		);
		
		// 3. Get Enum Message
		List<PurchaseVO> list = (List<PurchaseVO>) map.get("list");
		Map<Integer, String> messageMap = new HashMap<Integer, String>();
		
		Iterator<PurchaseVO> iterator = list.iterator();
		while (iterator.hasNext()) {
			PurchaseVO purchaseResult = iterator.next();
			String tranCode = purchaseResult.getTranCode();
			String message = TranStatusCodeUtil.getMessage(tranCode, isAdmin);
			messageMap.put(purchaseResult.getTranNo(), message);
		}
		
		String url = null;
		if(isAdmin) {
			url = "forward:/purchase/listAdminPurchase.jsp";
		} else {
			url = "forward:/purchase/listUserPurchase.jsp";
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("path", url);
		resultMap.put("list", list);
		resultMap.put("resultPage",	resultPage);
		resultMap.put("search", search);
		resultMap.put("getList", "fncGetPurchaseList");
		resultMap.put("messageMap", messageMap);
		
		System.out.println("[PurchaseController.listPurchase()] end");
		
		return resultMap;
	}
	
	@RequestMapping(value = "/listAdminPurchase/{page}")
	public String listAdminPurchase(
			@RequestBody SearchVO search,
			@PathVariable("page") int page,
			HttpSession session,
			Model model) {
		System.out.println("[PurchaseController.listPurchase()] start");
		
		// Default page = 1;
		search.setPage(page);
		if(search.getPage() == 0) {
			search.setPage(1);
		}
		
		search.setPageUnit(PAGE_UNIT); 
		search.setPageSize(PAGE_SIZE);
		
		// 2. Get purchaseList
		boolean isAdmin = false;
		UserVO user = (UserVO) session.getAttribute("user");
		if (user.getRole().equals("admin")) {
			isAdmin = true;
		} else {
			// 일반 User 방지 -> index.jsp로 복귀
			return "index";
		}
		
		Map<String, Object> map = purchaseService.getPurchaseList(search, user);
		
		Page resultPage	= new Page(
				search.getPage(), 
				((Integer)map.get("totalCount")).intValue(), 
				PAGE_UNIT,
				PAGE_SIZE
		);
		
		// 3. Get Enum Message
		List<PurchaseVO> list = (List<PurchaseVO>) map.get("list");
		Map<Integer, String> messageMap = new HashMap<Integer, String>();
		
		Iterator<PurchaseVO> iterator = list.iterator();
		while (iterator.hasNext()) {
			PurchaseVO purchaseResult = iterator.next();
			String tranCode = purchaseResult.getTranCode();
			String message = TranStatusCodeUtil.getMessage(tranCode, isAdmin);
			messageMap.put(purchaseResult.getTranNo(), message);
		}
		
		String url = "purchase/listAdminPurchase";
		
		model.addAttribute("list", list);
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("getList", "fncGetPurchaseList");
		model.addAttribute("messageMap", messageMap);
		
		System.out.println("[PurchaseController.listPurchase()] end");
		
		return url;
	}
	
	@RequestMapping(value = "/getPurchase/{tranNo}")
	public Map<String, Object> getPurchase(@PathVariable("tranNo") int tranNo) {
		System.out.println("[PurchaseController.getPurchase()] start");
		
		PurchaseVO result = purchaseService.getPurchase(tranNo);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("path", "forward:/purchase/getPurchase.jsp");
		resultMap.put("purchase", result);
		
		for(PaymentOption po : PaymentOption.values()) {
			if(result.getPaymentOption().trim().equals(po.getNumber())) {
				resultMap.put("paymentOption",  po.getOption());
			}
		}	
		
		System.out.println("[PurchaseController.getPurchase()] end");
		
		return resultMap;
	}
	
	@RequestMapping(value = "/addPurchase")
	public Map<String, Object> addPurchase(
			@RequestBody RestApiCommonVO restApiCommon) {
		System.out.println("[PurchaseController.addPurchase()] start");
		
		UserVO user = restApiCommon.getUser();
		ProductVO product = restApiCommon.getProduct();
		PurchaseVO purchase = restApiCommon.getPurchase();
		
		purchase.setTranCode(TranStatusCode.PURCHASED.getNumber());
		
		UserVO userResult = userService.getUser(user.getUserId());
		purchase.setBuyer(userResult);
		
		ProductVO productResult = productService.getProduct(product.getProdNo());
		purchase.setPurchaseProd(productResult);
		
		purchaseService.addPurchase(purchase);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("purchase", purchase);
		
		System.out.println("[PurchaseController.addPurchase()] end");
		
		return result;
	}
	
	
	@PostMapping(value = "/updateTranCode/{page}")
	public Map<String, Object> updateTranCode(
			@RequestBody UpdateTranCodeVO updateTranCode,
			@PathVariable("page") int page) {
		System.out.println("[PurchaseController.updateTranCode()] start");
		
		// PurchaseVO
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranCode(updateTranCode.getTranCode());
		purchaseVO.setTranNo(updateTranCode.getTranNo());
		
		purchaseService.updateTranCode(purchaseVO);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("path", "redirect:/purchase/listPurchase/" + page);
		
		System.out.println("[PurchaseController.updateTranCode()] end");
		
		return result;
	}
	
	@RequestMapping(value = "/updatePurchase")
	public String updatePurchase(@RequestBody PurchaseVO purchase) {
		System.out.println("[PurchaseController.updatePurchase()] start");
		
		int result = purchaseService.updatePurchase(purchase);
		
		System.out.println("[PurchaseController.updatePurchase()] end");
		
		return "success";
	}
	
	@PostMapping(value = "/deletePurchase") 
	public String deletePurchase (
			@RequestBody RestApiCommonVO restApiCommon) {
		System.out.println("[PurchaseController.deletePurchase()] start");
		
		restApiCommon.getPurchase().setPurchaseProd(restApiCommon.getProduct());
		purchaseService.deletePurchase(restApiCommon.getPurchase());
		
		System.out.println("[PurchaseController.deletePurchase()] end");
		
		return "success";
	}
}
