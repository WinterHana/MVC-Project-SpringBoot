package com.springboot.project.controller.purchase;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.util.PaymentOption;
import com.mvc.common.util.TranStatusCode;
import com.mvc.common.util.TranStatusCodeUtil;
import com.springboot.project.controller.common.CommonController;
import com.springboot.project.controller.product.ProductController;
import com.springboot.project.service.domain.Page;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.PurchaseVO;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.TransactionListVO;
import com.springboot.project.service.domain.UserVO;
import com.springboot.project.service.product.ProductService;
import com.springboot.project.service.purchase.PurchaseService;
import com.springboot.project.service.user.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/purchase/*")
public class PurchaseController extends CommonController {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	// 개인 목록 확인 : role을 admin을 user로 바꿔서 검색한다.
	@RequestMapping(value = "/listPurchase/{page}")
	public String listPurchase(
			@ModelAttribute("search") SearchVO search, 
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
		
		// 잠시 Role을 user로 바꾼 다음, 나중에 다시 admin으로 바꾼다.
		Map<String, Object> map = null;
		if (user.getRole().equals("admin")) {
			user.setRole("user");
			map = purchaseService.getPurchaseList(search, user);
			user.setRole("admin");
		} else {
			map = purchaseService.getPurchaseList(search, user);
		}
		
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
		
		String url = "purchase/listPurchase";
		
		model.addAttribute("list", list);
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("getList", "fncGetPurchaseList");
		model.addAttribute("messageMap", messageMap);
		
		System.out.println("[PurchaseController.listPurchase()] end");
		
		return url;
	}
	
	
	@RequestMapping(value = "/listAdminPurchase/{page}")
	public String listAdminPurchase(
			@ModelAttribute("search") SearchVO search, 
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
	public String getPurchase(
			@PathVariable("tranNo") int tranNo,
			Model model) {
		System.out.println("[PurchaseController.getPurchase()] start");
		
		Map<String,Object> resultMap = purchaseService.getPurchase(tranNo);
		
		PurchaseVO purchase = (PurchaseVO) resultMap.get("purchase");
		model.addAttribute("purchase", purchase);
		model.addAttribute("TransactionLists", resultMap.get("TransactionLists"));
		
		for(PaymentOption po : PaymentOption.values()) {
			if(purchase.getPaymentOption().trim().equals(po.getNumber())) {
				model.addAttribute("paymentOption", po.getOption());
			}
		}	
		
		System.out.println("[PurchaseController.getPurchase()] end");
		
		return "purchase/getPurchase";
	}
	
	@RequestMapping(value = "/addPurchaseView/{prodNo}")
	public String addPurchaseView(
			@PathVariable("prodNo") int prodNo,
			HttpSession session, 
			Model model) {
		System.out.println("[PurchaseController.addPurchaseView()] start");
		
		UserVO user =  (UserVO)session.getAttribute("user");
		ProductVO result = productService.getProduct(prodNo);
		
		model.addAttribute("product", result);
		model.addAttribute("userId", user.getUserId());
		
		System.out.println("[PurchaseController.addPurchaseView()] end");
		
		return "purchase/addPurchaseView";
		
	}
	
	@RequestMapping(value = "/addPurchase")
	public String addPurchase(
			@ModelAttribute("user") UserVO user,
			@ModelAttribute("product") ProductVO product,
			@ModelAttribute("purchase") PurchaseVO purchase,
			HttpSession session,
			Model model) {
		System.out.println("[PurchaseController.addPurchase()] start");
		
		purchase.setTranCode(TranStatusCode.PURCHASED.getNumber());
		
		UserVO userResult = userService.getUser(user.getUserId());
		purchase.setBuyer(userResult);
		
		ProductVO productResult = productService.getProduct(product.getProdNo());
		purchase.setPurchaseProd(productResult);
		
		// purchaseService.addPurchase(purchase);
		
		// 갱신된 마일리지 반영
		session.setAttribute("user",  userService.getUser(user.getUserId()));
		
		model.addAttribute("purchase", purchase);
		
		System.out.println("[PurchaseController.addPurchase()] end");
		
		return "redirect:/purchase/listPurchase/1";
	}
	
	@RequestMapping(value = "/updateTranCode/{page}", method = RequestMethod.GET)
	public String updateTranCode(
			@RequestParam("tranNo") String tranNo,
			@RequestParam("updateTranCode") String tranCode,
			@PathVariable("page") int page,
			Model model) {
		System.out.println("[PurchaseController.updateTranCode()] start");
		
		// PurchaseVO
		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranCode(tranCode);
		purchaseVO.setTranNo(Integer.parseInt(tranNo));
		
		purchaseService.updateTranCode(purchaseVO);
		
		System.out.println("[PurchaseController.updateTranCode()] end");
		
		return "redirect:/purchase/listAdminPurchase/" + page;
	}
	
	@RequestMapping(value = "/updatePurchaseView/{tranNo}")
	public String updatePurchaseView(
			@PathVariable("tranNo") int tranNo,
			Model model) {
		System.out.println("[PurchaseController.updatePurchaseView()] start");
		
		Map<String,Object> resultMap = purchaseService.getPurchase(tranNo);
		PurchaseVO purchaseResult = (PurchaseVO) resultMap.get("purchase");
		
		model.addAttribute("purchase", purchaseResult);
		
		System.out.println("[PurchaseController.updatePurchaseView()] end");
		
		return "purchase/updatePurchaseView";
	}
	
	@RequestMapping(value = "/updatePurchase")
	public String updatePurchase(@ModelAttribute("purchase") PurchaseVO purchase) {
		System.out.println("[PurchaseController.updatePurchase()] start");
		
		purchaseService.updatePurchase(purchase);
		
		System.out.println("[PurchaseController.updatePurchase()] end");
		
		return "redirect:/purchase/getPurchase/" + purchase.getTranNo();
	}
	
	@PostMapping(value = "/deletePurchase") 
	public String deletePurchase(
			@ModelAttribute("purchase") PurchaseVO purchase,
			@ModelAttribute("product") ProductVO product, 
			@ModelAttribute("user") UserVO user,
			HttpSession session) {
		System.out.println("[PurchaseController.deletePurchase()] start");
		
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchaseService.deletePurchase(purchase);
		
		String url = null;
		UserVO loginUser = (UserVO) session.getAttribute("user");			
		
		if (loginUser.getRole().equals("admin")) {
			url = "redirect:/purchase/listAdminPurchase/1";
		} else {
			url = "redirect:/purchase/listPurchase/1";
		}
		
		// 갱신된 마일리지 반영
		session.setAttribute("user",  userService.getUser(loginUser.getUserId()));
		
		System.out.println("[PurchaseController.deletePurchase()] end");
		
		return url;
	}
}
