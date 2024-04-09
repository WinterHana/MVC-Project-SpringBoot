package com.springboot.project;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.springboot.project.repository.UserRepository;
import com.springboot.project.service.domain.User;

import jakarta.transaction.Transactional;

@SpringBootTest
class UserRepositoryJpaTest {
	
	@Autowired
	UserRepository userRepository;
	
	// @Test
	public void classTest() {
		System.out.println(userRepository.getClass().getName());
	}
	
	// @Test
	public void getUserTest() {
		String userId = "admin";
		
		Optional<User> result = userRepository.findById(userId);
		
		System.out.println("======================");
		User user = result.get();
		System.out.println(user);
		System.out.println("======================");
	}

	
	@Test
	public void getUserListTest() {
		Pageable pageable = PageRequest.of(1, 10);
		
		Page<User> result = userRepository.findAll(pageable);
		
		System.out.println(result);
		
		System.out.println("Total Pages : " + result.getTotalPages());
		
		System.out.println("Total Count : " + result.getTotalElements());
		
		System.out.println("Page Number : " + result.getNumber());
		
		System.out.println("Page Size : " + result.getSize());
		
		System.out.println("has next page? : " + result.hasNext());
		
		System.out.println("first page? : " + result.isFirst());
		
		System.out.println("-------------------------------------");
		
		for(User user : result.getContent()) {
			System.out.println(user);
		}
	}
}
