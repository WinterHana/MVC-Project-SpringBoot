package com.springboot.project.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.SearchVO;
import com.springboot.project.controller.common.CommonController;
import com.springboot.project.service.domain.UserVO;
import com.springboot.project.service.user.UserService;

@RestController
@RequestMapping("/rest/user/*")
public class UserRestController extends CommonController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@PostMapping(value = "checkDuplication")
	public Map<String, Object> checkDuplication(@RequestBody UserVO user) {
		System.out.println("[UserController.checkDuplicationUser()] start");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean result = userService.checkDuplication(user.getUserId());
		
		map.put("result", result);
		map.put("userId", user.getUserId());
		
		System.out.println("[UserController.checkDuplicationUser()] end");
		
		return map;
	}			
		
	@PostMapping(value = "getUser/{userId}")
	public UserVO getUser(@PathVariable("userId") String userId) {
		
		System.out.println("[UserController.getUser()] start");
		
		UserVO user = userService.getUser(userId);

		System.out.println("[UserController.getUser()] end");
		
		return user;
	}
	
	// GetUserList  Asynchronous
	@PostMapping(value = "listUser/{page}")
	public Map<String, Object> listUser(
			@RequestBody SearchVO search,
			@PathVariable("page") int page) {
		System.out.println("[UserController.listUser()] start");
		
		// 1. Page setting Default page = 1;
		search.setPage(page);
		if(search.getPage() == 0) {
			search.setPage(1);
		}
		
		search.setPageUnit(PAGE_UNIT); 
		search.setPageSize(5);
		Map<String, Object> map = userService.getUserList(search);
		 
		Page resultPage	= new Page (
				search.getPage(), 
				((Integer)map.get("totalCount")).intValue(), 
				PAGE_UNIT,
				5
		);
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("list", map.get("list"));
		result.put("resultPage", resultPage);
		result.put("search", search);
		// result.put("getList", "fncGetUserList");
		
		
		System.out.println("[UserController.listUser()] end");
		
		return result;
	}
	
	// AutoComplete Asynchronous
	@PostMapping(value = "getUserIds")
	public List<String> getUserIds() {
		System.out.println("[UserController.getUserIds()] start");
		
		List<String> result = userService.getUserIdAndUserNames("userId");
		
		System.out.println("[UserController.getUserIds()] end");
		
		return result;
	}
	
	// AutoComplete Asynchronous
	@PostMapping(value = "getUserNames")
	public List<String> getUserNames() {
		System.out.println("[UserController.getUserNames()] start");
		
		List<String> result = userService.getUserIdAndUserNames("userName");
		
		System.out.println("[UserController.getUserNames()] end");
		
		return result;
	}
}
