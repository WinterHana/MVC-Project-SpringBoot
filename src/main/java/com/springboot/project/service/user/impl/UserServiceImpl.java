package com.springboot.project.service.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.common.util.CommonUtil;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.UserVO;
import com.springboot.project.service.purchase.PurchaseDAO;
import com.springboot.project.service.user.UserDAO;
import com.springboot.project.service.user.UserService;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	@Autowired
	@Qualifier("userDAOImpl")
	private UserDAO userDAO;

	@Autowired
	@Qualifier("purchaseDAOImpl")
	private PurchaseDAO purchaseDAO;
	
	public UserServiceImpl() {
		System.out.println("[" + getClass().getName() + " Default constructor] Call");
	}
	
	public UserServiceImpl(UserDAO userDAO) {
		System.out.println("[" + getClass().getName() + " UserDAO set constructor] Call");
		this.userDAO = userDAO;
	}
	
	public void setUserDAO(UserDAO userDAO) {
		System.out.println("[" + getClass().getName() + ".setUserDAO] Call");
		this.userDAO = userDAO;
	}
	
	@Transactional
	@Override
	public int addUser(UserVO userVO) {
		int result = 0;
		
		result = userDAO.addUser(userVO);
		
//		try {
//			result = userDAO.addUser(userVO);
//			
//		} catch(Exception e) {
//			System.out.println("[" + getClass().getName() + ".addUser] Exception");
//			e.printStackTrace();
//		}
		
		return result;
	}

	@Override
	public UserVO loginUser(UserVO userVO) {
		UserVO result = null;
		
		try {
			result =userDAO.getUser(userVO.getUserId());
			
			if(! result.getPassword().equals(userVO.getPassword())) {
				throw new Exception("Password Error Exception");
			}
			
		} catch (Exception e) {
			System.out.println("[" + getClass().getName() + ".loginUser] Exception");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public UserVO getUser(String userId) {
		UserVO result = null;
		
		try {
			result =userDAO.getUser(userId);
			
		} catch (Exception e) {
			System.out.println("[" + getClass().getName() + ".getUser] Exception");
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Map<String,Object> getUserList(SearchVO searchVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<UserVO> list = null;
		int totalCount = 0;
		
		// 번호나 정수에 대해서 한글 입력에 대한 유효성 처리
		if(searchVO != null) {
			String searchCondition = searchVO.getSearchCondition();
			String serachKeyword = searchVO.getSearchKeyword();
			
			if(searchCondition != null && serachKeyword != null) {
				serachKeyword = CommonUtil.null2str(serachKeyword);
				System.out.println("searchKeyword : " + serachKeyword);
				
				searchVO.setSearchKeyword(serachKeyword);
			}
		}
		
		try {
			list =  userDAO.getUserList(searchVO);
			totalCount = userDAO.getUserCount(searchVO);
			
		} catch (Exception e) {
			System.out.println("[" + getClass().getName() + ".getUserList] Exception");
			e.printStackTrace();
		}

		map.put("totalCount", totalCount);
		map.put("list", list);
		
		return map;
	}
	
	@Transactional
	@Override
	public int updateUser(UserVO userVO) {
		int result = 0;
		
		result = userDAO.updateUser(userVO);
		
//		try {
//			result = userDAO.updateUser(userVO);
//			
//		} catch (Exception e) {
//			System.out.println("[" + getClass().getName() + ".updateUser] Exception");
//			e.printStackTrace();
//		}
		
		return result;
	}

	@Override
	public boolean checkDuplication(String userId) {
		boolean result = true;
		
		try {
			UserVO userVO=userDAO.getUser(userId);
			
			if(userVO != null) {
				result=false;
			}
			
		} catch (Exception e) {
			System.out.println("[" + getClass().getName() + ".checkDuplication] Exception");
			e.printStackTrace();
		}
		
		return result;
	}

	@Transactional
	@Override
	public int deleteUser(String userId) {
		int result = 0;
		
		result += purchaseDAO.deletePurchaseBuyerId(userId);
		result += userDAO.deleteUser(userId);
		
//		try {
//			result += purchaseDAO.deletePurchaseBuyerId(userId);
//			result += userDAO.deleteUser(userId);
//		} catch (Exception e) {
//			System.out.println("[" + getClass().getName() + ".deleteUser] Exception");
//			e.printStackTrace();
//		}
		
		return result;
	}

	@Override
	public List<String> getUserIdAndUserNames(String key) {
		List<UserVO> queryResult = null;
		List<String> result = new ArrayList<String>();
		try {
			queryResult = userDAO.getUserIdAndUserNames();
			if(queryResult != null && queryResult.size() >= 0) {
				queryResult.stream().forEach(s -> {
					if(key.equals("userId")) {
						result.add(s.getUserId());
					} else if (key.equals("userName")) {
						result.add(s.getUserName());
					}
				});
			}
			
		} catch (Exception e) {
			System.out.println("[" + getClass().getName() + ".getUserIdAndUserNames] Exception");
			e.printStackTrace();
		}
		
		return result.stream().distinct().collect(Collectors.toList());
	}
}