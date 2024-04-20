package com.springboot.project.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

import com.springboot.project.controller.common.CommonController;
import com.springboot.project.controller.product.ProductController;
import com.springboot.project.service.domain.Page;
import com.springboot.project.service.domain.SearchVO;
import com.springboot.project.service.domain.UserVO;
import com.springboot.project.service.user.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/rest/user/*")
public class UserRestController extends CommonController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@PostMapping("login")
	public void login(
			HttpSession session, 
			@RequestBody UserVO user,
			Model model) {
		System.out.println("[UserRestController.loginUser()] start");
		
		session.setAttribute("user",  userService.loginUser(user));

		System.out.println("[UserRestController.loginUser()] end");
	}
	
	@GetMapping("logout")
	public void logout(HttpSession session) {
		System.out.println("[UserController.logout()] start");
		
		session.invalidate();
		
		System.out.println("[UserController.logout()] end");
	}
	
	@PostMapping(value = "checkDuplication")
	public Map<String, Object> checkDuplication(@RequestBody UserVO user) {
		System.out.println("[UserRestController.checkDuplicationUser()] start");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		boolean result = userService.checkDuplication(user.getUserId());
		
		map.put("result", result);
		map.put("userId", user.getUserId());
		
		System.out.println("[UserRestController.checkDuplicationUser()] end");
		
		return map;
	}			
		
	@PostMapping(value = "getUser/{userId}")
	public UserVO getUser(@PathVariable("userId") String userId) {
		
		System.out.println("[UserRestController.getUser()] start");
		
		UserVO user = userService.getUser(userId);

		System.out.println("[UserRestController.getUser()] end");
		
		return user;
	}
	
	// GetUserList  Asynchronous
	@PostMapping(value = "listUser/{page}")
	public Map<String, Object> listUser(
			@RequestBody SearchVO search,
			@PathVariable("page") int page) {
		System.out.println("[UserRestController.listUser()] start");
		
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
		
		System.out.println("[UserRestController.listUser()] end");
		
		return result;
	}
	
	@PostMapping("addUser")
	public void addUser(@RequestBody UserVO user) {
		System.out.println("[UserController.addUser()] start");
		
		userService.addUser(user);
		
		System.out.println("[UserController.addUser()] end");
	}
	
	@PostMapping("updateUser")
	public void updateUser(
			HttpSession session, 
			@RequestBody UserVO user) {
		System.out.println("[UserController.updateUser()] start");
		
		userService.updateUser(user);
		
		UserVO sessionUser = (UserVO)session.getAttribute("user");
		
		if(sessionUser != null) {
			String sessionId=sessionUser.getUserId();
			
			if(sessionId.equals(user.getUserId())){
				session.setAttribute("user", user);
			}
		}
		
		System.out.println("[UserController.updateUser()] end");
	}
	
	@PostMapping(value = "deleteUser")
	public void deleteUser(
			@RequestBody UserVO user) {
		System.out.println("[UserController.deleteUser()] start");
		
		userService.deleteUser(user.getUserId());
		
		System.out.println("[UserController.deleteUser()] end");
	}
	
	// AutoComplete Asynchronous
	@PostMapping(value = "getUserIds")
	public List<String> getUserIds() {
		System.out.println("[UserRestController.getUserIds()] start");
		
		List<String> result = userService.getUserIdAndUserNames("userId");
		
		System.out.println("[UserRestController.getUserIds()] end");
		
		return result;
	}
	
	// AutoComplete Asynchronous
	@PostMapping(value = "getUserNames")
	public List<String> getUserNames() {
		System.out.println("[UserRestController.getUserNames()] start");
		
		List<String> result = userService.getUserIdAndUserNames("userName");
		
		System.out.println("[UserRestController.getUserNames()] end");
		
		return result;
	}
}
