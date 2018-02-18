package com.capital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.capital.dao.LoginDao;
import com.capital.entities.LoginEntity;

@Component
@Transactional
public class LoginService {

	@Autowired
	private LoginDao dao;

	public List<LoginEntity> getAllUsers() {
		return dao.getAllUsers();
	}

}
