package com.icker.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icker.test.pojo.User;
import com.icker.test.service.UserService;

@Controller
public class TestController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String test(){
		User user = new User();
		user.setEmail("cgyubg@sina.com");
		user.setPassword("111111");
		try {
			if (userService.hasUser(user)) {
				user = userService.findUserByEmail(user.getEmail());
				return "/index";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/";
	}
}
