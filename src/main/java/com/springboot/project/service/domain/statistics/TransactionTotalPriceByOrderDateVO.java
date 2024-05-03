package com.springboot.project.service.domain.statistics;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionTotalPriceByOrderDateVO {
	private Date orderDate;
	private int totalPrice;
}
