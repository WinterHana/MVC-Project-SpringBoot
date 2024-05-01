package com.springboot.project.service.product;

import java.util.List;
import java.util.Map;

import com.springboot.project.service.domain.CartVO;
import com.springboot.project.service.domain.FileVO;
import com.springboot.project.service.domain.ProductTagVO;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.TagVO;

public interface ProductDAO {
	// product
	public int addProduct(ProductVO productVO);
	
	public ProductVO getProduct(int prodNo);
	
	public int updateProduct(ProductVO productVO);
	
	public int deleteProduct(int prodNo);
	
	public List<ProductVO> getProductList(SearchVO searchVO);
	
	public int getProductCount(SearchVO searchVO);
	
	public int updateProductCount(Map<String, Integer> map);
	
	public int addProductImage(FileVO file);
	
	public int updateAddProductImage(FileVO file);
	
	public List<FileVO> getProductImage(int prodNo);
	
	public int deleteProductImage(int prodNo);
	
	public List<ProductVO> getProductInfo();
	
	// Cart
	public int addCart(CartVO cart);
	
	public int deleteCart(CartVO cart);
	
	public int checkIsCart(CartVO cart);
	
	public List<CartVO> getCartList(String userId);
	
	// tag
	public TagVO getTag(String tagName);
	
	public int addTag(String tagName);
	
	public int addProductTag(ProductTagVO productTag);
	
	public int addProductTagWithSeq(int tagNo);
	
	public int deleteTag(String tagName);
	
	public int deleteProductTag(String tagName);
	
	public List<TagVO> getTagFromProduct(int prodNo);
	
	public List<ProductVO> getWeatherRecommendProduct(int tagNo);
}
