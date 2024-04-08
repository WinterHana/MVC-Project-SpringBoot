package com.springboot.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.service.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
