package com.springboot.project.service.product;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.project.service.domain.CartVO;
import com.springboot.project.service.domain.FileVO;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.TagDataVO;
import com.springboot.project.service.domain.TagVO;

public interface ProductService {
	// Product
	public ProductVO getProduct(int productId);
	
	public Map<String, Object> getProductList(SearchVO searchVO);
	
	public int addProduct(ProductVO productVO, List<MultipartFile> multipartFiles, String tagList);
	
	public int updateProduct(ProductVO productVO, List<MultipartFile> multipartFiles);
	
	public int deleteProduct(ProductVO product);
	
	public int addProductImage(FileVO file);
	
	public List<FileVO> getProductImage(int prodNo);
	
	public List<String> getProductInfo(String key);
	
	// Cart
	public int addCart(CartVO cart);
	
	public int deleteCart(CartVO cart);
	
	public boolean checkIsCart(CartVO cart);
	
	public Map<String, Object> getCartList(String userId);
	
	// Tag
	public int addTag(TagDataVO tagData);
	
	public int deleteProductTag(TagDataVO tagData);
	
	public List<TagVO> getTagFromProduct(int prodNo);
	
	// Recommend
	public Map<String, Object> getWeatherRecommendProduct(int size);
	
}
