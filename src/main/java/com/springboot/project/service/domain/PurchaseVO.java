package com.springboot.project.service.domain;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PurchaseVO {
	
	private UserVO buyer;
	private ProductVO purchaseProd;
	private String dlvyAddr;
	private Date dlvyDate;
	private String dlvyRequest;
	private Date orderDate;
	private String paymentOption;
	private String receiverName;
	private String receiverPhone;
	private String tranCode;
	private int tranNo;
	private int totalPrice;
	private String tranName;
}