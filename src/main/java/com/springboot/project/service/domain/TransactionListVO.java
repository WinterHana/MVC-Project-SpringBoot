package com.springboot.project.service.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TransactionListVO {
	int tranNo;
	int prodNo;
	int price;
	int count;
	String prodName;
}
