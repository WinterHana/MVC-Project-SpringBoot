package com.springboot.project.service.purchase;

import java.util.List;
import java.util.Map;

import com.springboot.project.service.domain.product.SearchVO;
import com.springboot.project.service.domain.purchase.AddPurchaseDataVO;
import com.springboot.project.service.domain.purchase.PurchaseVO;
import com.springboot.project.service.domain.purchase.TransactionListVO;
import com.springboot.project.service.domain.statistics.TransactionTotalPriceByOrderDateVO;
import com.springboot.project.service.domain.user.UserVO;

public interface PurchaseService {
	
	public Map<String, Object> getPurchase(int tranNo);
	
	public Map<String, Object> getPurchaseList(SearchVO searchVO, UserVO userVO);
	
	public Map<Integer, String> getSalaList();
	
	public int addPurchase(AddPurchaseDataVO addPurchaseData);
	
	public int updatePurchase(PurchaseVO purchaseVO);
	
	public int updateTranCode(PurchaseVO purchaseVO);
	
	public int deletePurchase(PurchaseVO purchaseVO);
	
	public int addTransactionList(TransactionListVO transactionList);
	
	public List<TransactionTotalPriceByOrderDateVO> getTransactionTotalPriceByOrderDate();
}
