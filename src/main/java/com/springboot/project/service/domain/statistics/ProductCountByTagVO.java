package com.springboot.project.service.domain.statistics;

import java.util.List;

import com.springboot.project.service.domain.purchase.PurchaseVO;
import com.springboot.project.service.domain.purchase.TransactionListVO;

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
public class ProductCountByTagVO {
	private String tagName;
	private int count;
}
