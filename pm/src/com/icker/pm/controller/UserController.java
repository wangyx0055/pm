package com.icker.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.icker.pm.pojo.User;
import com.icker.pm.service.UserService;

@Controller
@RequestMapping("/userController")
@SessionAttributes(value={"user"})
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String login(User user, ModelMap modelMap) {
		try {
			if (userService.hasUser(user)) {
				user = userService.findUserByEmail(user.getEmail());
				modelMap.addAttribute("user", user);
				return "main";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/";
	}
	
	@RequestMapping("/home")
	public String home(ModelMap modelMap) {
		return "main";
	}
}
