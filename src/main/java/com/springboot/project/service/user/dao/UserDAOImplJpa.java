package com.springboot.project.service.user.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.project.repository.UserRepository;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.User;
import com.springboot.project.service.user.UserDAOJpa;

public class UserDAOImplJpa implements UserDAOJpa {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User getUser(String userId) {
		Optional<User> result = userRepository.findById(userId);
		return result.get();
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUser(String userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public List<User> getUserList(SearchVO search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUserCount(SearchVO search) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> getUserIdAndUserNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
