package com.springboot.project.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.project.controller.common.CommonController;
import com.springboot.project.controller.product.ProductController;
import com.springboot.project.service.domain.UserVO;
import com.springboot.project.service.user.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/user/*")
public class UserController extends CommonController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@PostMapping("login")
	public String login(
			HttpSession session, 
			@ModelAttribute("user") UserVO user,
			Model model) {
		System.out.println("[UserController.loginUser()] start");
		
		session.setAttribute("user",  userService.loginUser(user));

		System.out.println("[UserController.loginUser()] end");
		
		return "index";
	}
	
	// Error 방지 GetMapping
	@GetMapping("login")
	public void loginGet() {
		// Blank
	}
	
	// Navigation
	@GetMapping("loginView")
	public String loginView() {
		System.out.println("[UserController.loginUser()] start");
		
		System.out.println("[UserController.loginUser()] end");
		
		return "user/login";
	}
	
	// Navigation
	@GetMapping("signup")
	public String signup() {
		System.out.println("[UserController.signup()] start");
		
		System.out.println("[UserController.signup()] end");
		
		return "user/signup";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout()] start");
		
		session.invalidate();
		
		System.out.println("[UserController.logout()] end");
		
		return "user/login";
	}

	@PostMapping("checkDuplication")
	public void checkDuplication(
			@ModelAttribute("user") UserVO user,
			Model model) {
		System.out.println("[UserController.checkDuplicationUser()] start");
		
		boolean result = userService.checkDuplication(user.getUserId());
		
		model.addAttribute("result", result);
		model.addAttribute("userId", user.getUserId());
		
		System.out.println("[UserController.checkDuplicationUser()] end");
	}		
	
	// GetUser, Navigation
	@PostMapping( "getUser/{userId}")
	public String getUser(
			@PathVariable("userId") String userId,
			Model model) {
		
		System.out.println("[UserController.getUser()] start");
		
		model.addAttribute("user", userService.getUser(userId));

		System.out.println("[UserController.getUser()] end");
		
		return "user/getUser";
	}	
	
	// Update Navigation
	@GetMapping(value = "getUser/{userId}")
	public String getUserMethodGet(
			@PathVariable("userId") String userId, 
			Model model) {
		
		System.out.println("[UserController.getUser()] start");
		
		model.addAttribute("user", userService.getUser(userId));
		
		System.out.println("[UserController.getUser()] end");
		
		return "user/getUser";
	}	
	
	@PostMapping("addUser")
	public String addUser(@ModelAttribute("user") UserVO user) {
		System.out.println("[UserController.addUser()] start");
		
		userService.addUser(user);
		
		System.out.println("[UserController.addUser()] end");
		
		return "redirect:/user/loginView";
	}
	
	// Navigation
	@GetMapping("listUser")
	public String listUser() {
		System.out.println("[UserController.listUser()] start");
		
		System.out.println("[UserController.listUser()] end");
		
		return "user/listUser";
	}
	
	// GetUser, Navigation
	@PostMapping("updateUserView/{userId}")
	public String updateUserView(
			@PathVariable("userId") String userId,
			Model model) {
		System.out.println("[UserController.updateUserView()] start");
		
		UserVO resultUser = userService.getUser(userId);
		
		model.addAttribute("user", resultUser);
		
		System.out.println("[UserController.updateUserView()] end");
		
		return "user/updateUser";
	}
	
	@PostMapping("updateUser")
	public String updateUser(
			HttpSession session, 
			@ModelAttribute("user") UserVO user) {
		System.out.println("[UserController.updateUser()] start");
		
		userService.updateUser(user);
		
		String sessionId=((UserVO)session.getAttribute("user")).getUserId();
		
		if(sessionId.equals(user.getUserId())){
			session.setAttribute("user", user);
		}
		
		System.out.println("[UserController.updateUser()] end");
		
		return "redirect:/user/getUser/" + user.getUserId();
	}
	
	@PostMapping(value = "deleteUser")
	public String deleteUser(
			HttpSession session,
			@ModelAttribute("user") UserVO user) {
		System.out.println("[UserController.deleteUser()] start");

		String url = null;
		String userRole=((UserVO)session.getAttribute("user")).getRole();
		
		if(userRole.equals("admin")) {
			url = "redirect:/user/listUser";
		} else {
			url = "redirect:/user/loginView";
		}
		
		userService.deleteUser(user.getUserId());
		
		System.out.println("[UserController.deleteUser()] end");
		
		return url;
	}
}
