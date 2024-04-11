package com.springboot.project.service.user;

import java.util.List;

import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.UserVO;

public interface UserDAO {
	public int addUser(UserVO userVO);
	
	public UserVO getUser(String userId);
	
	public int updateUser(UserVO userVO);
	
	public int deleteUser(String userId);
	
	public List<UserVO> getUserList(SearchVO searchVO);
	
	public int getUserCount(SearchVO searchVO);
	
	public List<UserVO> getUserIdAndUserNames();
}
