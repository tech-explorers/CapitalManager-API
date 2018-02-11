package com.capital.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capital.config.AppConfig;

@RestController
public class AppController {
	@RequestMapping(path="/home",method = RequestMethod.GET)
	public String home()  {
		//return "Hii Welcome to the test method";
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.refresh();
		DriverManagerDataSource ds = ctx.getBean(DriverManagerDataSource.class);
		return ds.getUrl();
	}
	
	@RequestMapping(path="/login",method = RequestMethod.GET)
	public boolean loginPage(@RequestParam("userName") String username, @RequestParam("passWord") String password) {
		return false;		
	}
}
