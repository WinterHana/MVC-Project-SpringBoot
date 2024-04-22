package com.springboot.project.service.product;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.project.service.domain.CartVO;
import com.springboot.project.service.domain.FileVO;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.SearchVO;

public interface ProductService {
	public ProductVO getProduct(int productId);
	
	public Map<String, Object> getProductList(SearchVO searchVO);
	
	public int addProduct(ProductVO productVO, List<MultipartFile> multipartFiles);
	
	public int updateProduct(ProductVO productVO, List<MultipartFile> multipartFiles);
	
	public int deleteProduct(ProductVO product);
	
	public int addProductImage(FileVO file);
	
	public List<FileVO> getProductImage(int prodNo);
	
	public List<String> getProductInfo(String key);
	
	public int addCart(CartVO cart);
	
	public int deleteCart(CartVO cart);
	
	public boolean checkIsCart(CartVO cart);
	
	public Map<String, Object> getCartList(String userId);
}
