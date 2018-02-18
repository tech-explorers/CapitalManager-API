package com.capital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capital.entities.LoginEntity;
import com.capital.service.LoginService;

@RestController
public class AppController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String home() {
		List<LoginEntity> users = loginService.getAllUsers();
		System.out.println(users);
		return "Hii Welcome to the test method";

	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public boolean loginPage(@RequestParam("userName") String username, @RequestParam("passWord") String password) {
		return false;
	}
}
