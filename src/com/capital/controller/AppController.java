package com.capital.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capital.entities.LoginEntity;
import com.capital.entities.UserEntity;
import com.capital.model.ResponseModel;
import com.capital.model.UserResponse;
import com.capital.service.UserService;
import com.capital.util.GeneralUtil;

@RestController
public class AppController {

	@Autowired
	private UserService userService;

	static Logger logger = Logger.getLogger(AppController.class.getName());

	@RequestMapping(path = "/home", method = RequestMethod.GET)
	public String home() {
		List<LoginEntity> users = userService.getAllUsers();
		System.out.println(users);
		return "Hii Welcome to the test method";

	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public ResponseModel<UserResponse> loginPage(@RequestBody LoginEntity loginEntity) {
		return userService.loginUser(loginEntity);
	}

	@RequestMapping(path = "/register", method = RequestMethod.POST, consumes = "application/json")
	public ResponseModel<UserResponse> registration(@RequestBody UserEntity userEntity) {
		//Below hashing is temporary. Hashing will be performed at frontend layer.
		userEntity.getLoginEntity().setPassword(GeneralUtil.getHashedValue(userEntity.getLoginEntity().getPassword()));
		return userService.registerUser(userEntity);

	}
}
