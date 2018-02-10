package com.capital.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@RequestMapping(name="/login",method = RequestMethod.GET)
	public boolean loginPage(@RequestParam("userName") String username, @RequestParam("passWord") String password) {
		return false;		
	}
}
