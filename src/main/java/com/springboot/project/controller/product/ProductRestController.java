package com.springboot.project.controller.product;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.HistoryUtil;
import com.model2.mvc.common.util.TranStatusCodeUtil;
import com.springboot.project.controller.common.CommonController;
import com.springboot.project.service.domain.FileVO;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.PurchaseVO;
import com.springboot.project.service.product.ProductService;
import com.springboot.project.service.purchase.PurchaseService;

@RestController
@RequestMapping("/rest/product/*")
public class ProductRestController extends CommonController  {
	
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;
		
	// 제품 리스트를 가져옴
	@PostMapping(value = "/listProduct/{page}")
	public Map<String, Object> listUserProduct(
			@RequestBody SearchVO search,
			@PathVariable("page") int page) {
		System.out.println("[ProductController.listUserProduct()] start");
		
		// 1. Page setting Default page = 1;
		search.setPage(page);
		if(search.getPage() == 0) {
			search.setPage(1);
		}
		
		search.setPageUnit(PAGE_UNIT); 
		search.setPageSize(8);
		
		// 2. Get ProductList
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage	= new Page(
				search.getPage(), 
				((Integer)map.get("totalCount")).intValue(), 
				PAGE_UNIT,
				8
		);
		
		// 3. Set ModelAndView
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", map.get("list"));
		result.put("resultPage", resultPage);
		result.put("search", search);
		result.put("getList", "fncGetProductList");
		
		System.out.println("[ProductController.listUserProduct()] end");
		
		return result;
	}
	
	// Test
	@RequestMapping(value = "/getProduct/{prodNo}")
	public Map<String, Object> getProduct(
			@PathVariable("prodNo") int prodNo) {
		System.out.println("[ProductController.getProduct()] start");
		
		ProductVO product = productService.getProduct(prodNo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("path", "forward:/product/getProduct.jsp");
		map.put("product", product);
		
		System.out.println("[ProductController.getProduct()] end");
		
		return map;
	}
	
	// AutoComplete Asynchronous
	@PostMapping(value = "getProdNames")
	public List<String> getProdNames() {
		System.out.println("[UserController.getProdNames()] start");
		
		List<String> result = productService.getProductInfo("prodName");
		
		System.out.println("[UserController.getProdNames()] end");
		
		return result;
	}
	
	// AutoComplete Asynchronous
	@PostMapping(value = "getProdNos")
	public List<String> getProdNos() {
		System.out.println("[UserController.getProdNos()] start");
		
		List<String> result = productService.getProductInfo("prodNo");
		
		System.out.println("[UserController.getProdNos()] end");
		
		return result;
	}
}
