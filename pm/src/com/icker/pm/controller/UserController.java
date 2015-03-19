package com.icker.pm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icker.pm.pojo.User;
import com.icker.pm.service.UserService;

@Controller
@RequestMapping("/userController")
public class UserController {
	@Autowired
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) {
		try {
			if (userService.hasUser(user)) {
				user = userService.findUserByEmail(user.getEmail());
				request.getSession().setAttribute("user", user);
				return "main";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/";
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		if(null == request.getSession().getAttribute("user"))
			return "/";
		return "main";
	}
}
