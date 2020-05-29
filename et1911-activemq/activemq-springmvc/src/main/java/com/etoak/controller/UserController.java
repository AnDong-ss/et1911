package com.etoak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etoak.bean.User;
import com.etoak.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/toReg")
	public String toReg() {
		return "reg";
	}
	
	@RequestMapping("/reg")
	public String reg(User user) {
		userService.addUser(user);
		return "reg";
	}
}
