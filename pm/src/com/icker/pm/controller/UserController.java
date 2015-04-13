package com.icker.pm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icker.pm.pojo.User;
import com.icker.pm.service.UserService;

@Controller
@RequestMapping("/userController")
@SessionAttributes(value={"user"})
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 登陆
	 * @param user
	 * @param modelMap
	 * @return
	 */
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
	
	/**
	 * 用户注册
	 * @param user
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/signUp")
	public ModelAndView signUp(User user, ModelAndView modelAndView, ModelMap modelMap, RedirectAttributes attributes) {
		try {
			User u = userService.findUserByEmail(user.getEmail());
			if(null != u)
				throw new Exception("已经存在该用户，请直接登陆。");
			userService.addUser(user);
			attributes.addAttribute("user", user);
			modelAndView.setViewName("redirect:/userController/login");;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping("/home")
	public String home(ModelAndView modelAndView, ModelMap modelMap) {
		return "main";
	}
}
