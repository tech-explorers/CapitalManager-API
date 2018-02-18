package com.capital.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capital.entities.LoginEntity;

@Repository
public class LoginDao {
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<LoginEntity> getAllUsers() {
		Criteria criteria = getSession().createCriteria(LoginEntity.class);
		List<LoginEntity> entities = criteria.list();
		return entities;
	}

}
