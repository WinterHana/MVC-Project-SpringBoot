package com.springboot.project.service.domain;

import com.springboot.project.service.domain.product.ProductVO;
import com.springboot.project.service.domain.purchase.PurchaseVO;
import com.springboot.project.service.domain.user.UserVO;

public class RestApiCommonVO {
	private ProductVO product;
	private UserVO user;
	private PurchaseVO purchase;
	
	public RestApiCommonVO() {
		// Blank;
	}

	public ProductVO getProduct() {
		return product;
	}

	public UserVO getUser() {
		return user;
	}

	public PurchaseVO getPurchase() {
		return purchase;
	}

	public void setProduct(ProductVO product) {
		this.product = product;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public void setPurchase(PurchaseVO purchase) {
		this.purchase = purchase;
	}
}
