package com.springboot.project.service.domain.purchase;

import java.sql.Date;
import java.util.List;

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
public class AddPurchaseDataVO {
	private PurchaseVO purchase;
	private List<TransactionListVO> transactionLists;
}
