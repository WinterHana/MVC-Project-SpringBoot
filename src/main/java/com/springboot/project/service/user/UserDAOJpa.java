package com.springboot.project.service.user;

import java.util.List;

import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.User;

public interface UserDAOJpa {
	public void addUser(User user);
	
	public User getUser(String userId);
	
	public void updateUser(User user);
	
	public void deleteUser(String userId);
	
	public List<User> getUserList(SearchVO search);
	
	public int getUserCount(SearchVO search);
	
	public List<User> getUserIdAndUserNames();
}
