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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mvc.common.util.HistoryUtil;
import com.mvc.common.util.TranStatusCodeUtil;
import com.springboot.project.controller.common.CommonController;
import com.springboot.project.service.domain.Page;
import com.springboot.project.service.domain.product.CartVO;
import com.springboot.project.service.domain.product.FileVO;
import com.springboot.project.service.domain.product.ProductVO;
import com.springboot.project.service.domain.product.SearchVO;
import com.springboot.project.service.domain.product.TagDataVO;
import com.springboot.project.service.domain.product.TagVO;
import com.springboot.project.service.domain.purchase.PurchaseVO;
import com.springboot.project.service.domain.statistics.ProductCountByTagVO;
import com.springboot.project.service.domain.statistics.ProductCountByTransactionVO;
import com.springboot.project.service.product.ProductService;
import com.springboot.project.service.purchase.PurchaseService;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
	
	@PostMapping("/addProduct")
	public void addProduct(
			@ModelAttribute("product") ProductVO product,
			@RequestParam("multipartFile") List<MultipartFile> multiFileLists,
			@RequestParam("tagList") String tagList) {
		System.out.println("[ProductController.addProduct()] start");
		
		// 제품 추가
		productService.addProduct(product, multiFileLists, tagList);
		
		System.out.println("[ProductController.addProduct()] end");
	}
	
	@PostMapping(value = "/updateProduct")
	public void updateProduct(
			@ModelAttribute("product") ProductVO product,
			@RequestParam("multipartFile") List<MultipartFile> multiFileLists) {
		System.out.println("[ProductController.updateProduct()] start");
		
		// 제품 수정
		productService.updateProduct(product, multiFileLists);
		
		System.out.println("[ProductController.updateProduct()] end");
	}
	
	@PostMapping(value = "/deleteProduct")
	public void deleteProduct(@RequestBody ProductVO product) {
		System.out.println("[ProductController.deleteProduct()] start");
		
		productService.deleteProduct(product);
		
		System.out.println("[ProductController.deleteProduct()] end");
	}
	
	@PostMapping(value = "/addCart")
	public boolean addCart(@RequestBody CartVO cart) {
		System.out.println("[ProductController.addCart()] start");
		
		productService.addCart(cart);
		
		System.out.println("[ProductController.addCart()] end");
		
		return true;
	}
	
	@PostMapping(value = "/deleteCart")
	public boolean deleteCart(@RequestBody CartVO cart) {
		System.out.println("[ProductController.deleteCart()] start");
		
		productService.deleteCart(cart);
		
		System.out.println("[ProductController.deleteCart()] end");
		
		return true;
	}
	
	@PostMapping(value = "/addTag")
	public int addTag(@RequestBody TagDataVO tagData) {
		System.out.println("[ProductController.addTag()] start");
		
		int result = productService.addTag(tagData);
		
		System.out.println("[ProductController.addTag()] end");
		
		return result;
	}
	
	@PostMapping(value = "/deleteProductTag")
	public int deleteProductTag(@RequestBody TagDataVO tagData) {
		System.out.println("[ProductController.deleteProductTag()] start");
		
		int result = productService.deleteProductTag(tagData);
		
		System.out.println("[ProductController.deleteProductTag()] end");
		
		return result;
	}
	
	@PostMapping(value = "/getTagFromProduct/{prodNo}")
	public List<TagVO> getTagFromProduct(@PathVariable("prodNo") int prodNo) {
		System.out.println("[ProductController.getTagFromProduct()] start");
		
		List<TagVO> resultList = productService.getTagFromProduct(prodNo);
		
		System.out.println("[ProductController.getTagFromProduct()] end");
		
		return resultList;
	}
	
	@PostMapping(value = "/getWeatherRecommendProduct/{size}")
	public Map<String, Object> getWeatherRecommendProduct(@PathVariable("size") int size) {
		System.out.println("[ProductController.getWeatherRecommendProduct()] start");
		
		Map<String, Object> resultList = productService.getWeatherRecommendProduct(size);
		
		System.out.println("[ProductController.getWeatherRecommendProduct()] end");
		
		return resultList;
	}
	
	@PostMapping(value = "/getCartRecommendProduct/{size}")
	public List<ProductVO> getCartRecommendProduct(
			@RequestBody ProductVO product,
			@PathVariable("size") int size) {
		System.out.println("[ProductController.getWeatherRecommendProduct()] start");
		
		List<ProductVO> resultList = productService.getCartRecommendProduct(product.getProdNo(), size);
		
		System.out.println("[ProductController.getWeatherRecommendProduct()] end");
		
		return resultList;
	}
	
	@PostMapping(value = "/getProductCountByTagName")
	public List<ProductCountByTagVO> getProductCountByTagName() {
		System.out.println("[ProductController.getWeatherRecommendProduct()] start");
		
		List<ProductCountByTagVO> resultList = productService.getProductCountByTagName();
		
		System.out.println("[ProductController.getWeatherRecommendProduct()] end");
		
		return resultList;
		
	}
	
	@PostMapping(value = "/getProductCountByTransaction")
	public List<ProductCountByTransactionVO> getProductCountByTransaction() {
		System.out.println("[ProductController.getProductCountByTransaction()] start");
		
		List<ProductCountByTransactionVO> resultList = productService.getProductCountByTransaction();
		
		System.out.println("[ProductController.getProductCountByTransaction()] end");
		
		return resultList;
		
	}
}
