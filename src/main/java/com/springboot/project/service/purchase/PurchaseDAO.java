package com.springboot.project.service.purchase;

import java.util.List;
import java.util.Map;

import com.springboot.project.service.domain.purchase.PurchaseVO;
import com.springboot.project.service.domain.purchase.TransactionListVO;
import com.springboot.project.service.domain.statistics.TransactionTotalPriceByOrderDateVO;
import com.springboot.project.service.domain.user.UserVO;

public interface PurchaseDAO {
	public PurchaseVO getPurchase(int tranNo);
	
	public List<PurchaseVO> getPurchaseList(Map<String,Object> map);
	
	public int getPurchaseCount(Map<String,Object> map);
	
	public List<Map<String, Object>> getSaleList();
	
	public int addPurchase(PurchaseVO purchaseVO);
	
	public int deletePurchase(int tranNo);
	
	public int deletePurchaseProdNo(int prodNo);
	
	public int deletePurchaseBuyerId(String buyerId);
	
	public int updatePurchase(PurchaseVO purchaseVO);
	
	public int updateTranCode(Map<String,Object> map);
	
	public int addTransactionList(TransactionListVO transactionList);
	
	public List<TransactionListVO> getTransactionList(int tranNo);

	public List<TransactionTotalPriceByOrderDateVO> getTransactionTotalPriceByOrderDate();
}
