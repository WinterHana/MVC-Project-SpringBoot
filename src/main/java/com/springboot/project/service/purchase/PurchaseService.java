package com.springboot.project.service.purchase;

import java.util.Map;

import com.springboot.project.service.domain.AddPurchaseDataVO;
import com.springboot.project.service.domain.PurchaseVO;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.TransactionListVO;
import com.springboot.project.service.domain.UserVO;

public interface PurchaseService {
	
	public Map<String, Object> getPurchase(int tranNo);
	
	public Map<String, Object> getPurchaseList(SearchVO searchVO, UserVO userVO);
	
	public Map<Integer, String> getSalaList();
	
	public int addPurchase(AddPurchaseDataVO addPurchaseData);
	
	public int updatePurchase(PurchaseVO purchaseVO);
	
	public int updateTranCode(PurchaseVO purchaseVO);
	
	public int deletePurchase(PurchaseVO purchaseVO);
	
	public int addTransactionList(TransactionListVO transactionList);
}
