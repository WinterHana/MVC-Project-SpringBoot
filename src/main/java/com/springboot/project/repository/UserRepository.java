package com.springboot.project.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.service.domain.User;

public interface UserRepository extends JpaRepository<User, String>{
	
    @Query("SELECT u FROM User u WHERE " +
            "(:searchCondition = 'userId' AND u.userId LIKE %:searchKeyword%) OR " +
            "(:searchCondition = 'userName' AND u.userName LIKE %:searchKeyword%) " +
            "ORDER BY u.userId")
     List<User> findBySearchConditionAndKeyword(@Param("searchCondition") String searchCondition, @Param("searchKeyword") String searchKeyword);

    
}
