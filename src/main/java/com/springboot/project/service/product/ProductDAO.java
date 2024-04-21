package com.springboot.project.service.product;

import java.util.List;
import java.util.Map;

import com.springboot.project.service.domain.CartVO;
import com.springboot.project.service.domain.FileVO;
import com.springboot.project.service.domain.ProductVO;
import com.springboot.project.service.domain.SearchVO;

public interface ProductDAO {
	public int addProduct(ProductVO productVO);
	
	public ProductVO getProduct(int prodNo);
	
	public int updateProduct(ProductVO productVO);
	
	public int deleteProduct(int prodNo);
	
	public List<ProductVO> getProductList(SearchVO searchVO);
	
	public int getProductCount(SearchVO searchVO);
	
	// product의 개수 조절
	public int updateProductCount(Map<String, Integer> map);
	
	// productImage 추가
	public int addProductImage(FileVO file);
	
	// productImage 수정
	public int updateAddProductImage(FileVO file);
	
	// productImage 정보 가져오기
	public List<FileVO> getProductImage(int prodNo);
	
	// productImage 삭제
	public int deleteProductImage(int prodNo);
	
	public List<ProductVO> getProductInfo();
	
	public int addCart(CartVO cart);
	
	public int deleteCart(CartVO cart);
	
	public int checkIsCart(CartVO cart);
}
