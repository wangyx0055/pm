package com.icker.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icker.pm.pojo.User;
import com.icker.pm.service.UserService;

@Controller
public class MainController {
	@Autowired
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/")
	public String login(User user) {
		return "user/login";
	}
	
	@RequestMapping("/error")
	public String error(User user) {
		return "error";
	}
}
