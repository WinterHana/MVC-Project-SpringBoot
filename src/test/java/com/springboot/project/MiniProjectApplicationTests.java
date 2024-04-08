package com.springboot.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.project.repository.UserRepository;

@SpringBootTest
class MiniProjectApplicationTests {
	
	@Autowired
	UserRepository userRepository;
	
	@Test
	public void jpaTest() {
		
	}
}
