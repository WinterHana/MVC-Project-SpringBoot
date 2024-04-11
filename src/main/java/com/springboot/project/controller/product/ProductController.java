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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.util.HistoryUtil;
import com.model2.mvc.common.util.TranStatusCodeUtil;
import com.springboot.project.controller.common.CommonController;
import com.springboot.project.service.domain.FileVO;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.PurchaseVO;
import com.springboot.project.service.product.ProductService;
import com.springboot.project.service.purchase.PurchaseService;

@Controller
@RequestMapping("/product/*")
public class ProductController extends CommonController  {
	
	@Autowired
	@Qualifier("productServiceImpl")
	ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	PurchaseService purchaseService;
	
	// Navigation
	@GetMapping("listProduct")
	public String listUserProduct() {
		System.out.println("[ProductController.listUserProduct()] start");
				
		System.out.println("[ProductController.listUserProduct()] end");
		
		return "product/listProduct";
	}
	
	@RequestMapping("getProduct/{prodNo}")
	public String getProduct(
			@PathVariable("prodNo") int prodNo, 
			Model model) {
		System.out.println("[ProductController.getProduct()] start");
		
		ProductVO product = productService.getProduct(prodNo);
		model.addAttribute("product", product);
		
		System.out.println("[ProductController.getProduct()] end");
		
		return "product/getProduct";
	}
	
	// Navigation
	@PostMapping("addProductView")
	public String addProductView() {
		System.out.println("[ProductController.addProductView()] start");
		
		System.out.println("[ProductController.addProductView()] end");
		
		return "product/addProduct";
	}
	
	@PostMapping("/addProduct")
	public String addProduct(
			@ModelAttribute("product") ProductVO product,
			@RequestParam("multipartFile") List<MultipartFile> multiFileLists) {
		System.out.println("[ProductController.addProduct()] start");
		
		// 제품 추가
		productService.addProduct(product, multiFileLists);
		
		System.out.println("[ProductController.addProduct()] end");
		
		return "redirect:/product/listProduct";
	}
	
	@PostMapping(value = "/updateProductView")
	public String updateProductView(
			@ModelAttribute("product") ProductVO product,
			Model model) {
		System.out.println("[ProductController.updaeProductView()] start");
		
		model.addAttribute("product", productService.getProduct(product.getProdNo()));
		
		System.out.println("[ProductController.updaeProductView()] end");
		
		return "product/updateProduct";
	}
	
	@PostMapping(value = "/updateProduct")
	public String updateProduct(
			@ModelAttribute("product") ProductVO product,
			@RequestParam("multipartFile") List<MultipartFile> multiFileLists) {
		System.out.println("[ProductController.updateProduct()] start");
		
		// 제품 수정
		productService.updateProduct(product, multiFileLists);
		
		System.out.println("[ProductController.updateProduct()] end");
		
		return "redirect:/product/getProduct/" + product.getProdNo();
	}
	
	@PostMapping(value = "/deleteProduct")
	public String deleteProduct(@ModelAttribute("product") ProductVO product) {
		System.out.println("[ProductController.deleteProduct()] start");
		
		productService.deleteProduct(product);
		
		System.out.println("[ProductController.deleteProduct()] end");
		
		return "redirect:/product/listProduct";
	}
}
