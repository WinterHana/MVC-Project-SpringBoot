package com.springboot.project.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CartVO {
	
	public CartVO(String userId, int prodNo) {
		super();
		this.userId = userId;
		this.prodNo = prodNo;
	}
	
	String userId;
	int prodNo;
	int price;
	int count;
}
