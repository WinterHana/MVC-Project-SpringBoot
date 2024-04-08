package com.springboot.project.service.user;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.springboot.project.service.domain.UserVO;


public interface UserService {
	
	public int addUser(UserVO userVO);
	
	public UserVO loginUser(UserVO userVO);
	
	public UserVO getUser(String userId);
	
	public Map<String, Object> getUserList(SearchVO searchVO);
	
	public int updateUser(UserVO userVO);
	
	public int deleteUser(String userId);
	
	public boolean checkDuplication(String userId);
	
	public List<String> getUserIdAndUserNames(String key);
}