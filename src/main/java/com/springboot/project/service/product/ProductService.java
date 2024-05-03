package com.springboot.project.service.product;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.springboot.project.service.domain.product.CartVO;
import com.springboot.project.service.domain.product.FileVO;
import com.springboot.project.service.domain.product.ProductVO;
import com.springboot.project.service.domain.product.SearchVO;
import com.springboot.project.service.domain.product.TagDataVO;
import com.springboot.project.service.domain.product.TagVO;
import com.springboot.project.service.domain.statistics.ProductCountByTagVO;
import com.springboot.project.service.domain.statistics.ProductCountByTransactionVO;

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
	
	public List<ProductVO> getCartRecommendProduct(int prodNo, int size);
	
	// Product statistics
	public List<ProductCountByTagVO> getProductCountByTagName();
	
	public List<ProductCountByTransactionVO> getProductCountByTransaction();
	
}
