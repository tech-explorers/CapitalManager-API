package com.capital.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.SqlQuery;
import org.springframework.stereotype.Repository;

import com.capital.entities.LoginEntity;
import com.capital.entities.UserEntity;
import com.capital.exception.AppException;
import com.capital.service.UserService;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	static Logger logger = Logger.getLogger(UserDao.class.getName());
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<LoginEntity> getAllUsers() {
		Criteria criteria = getSession().createCriteria(LoginEntity.class);
		List<LoginEntity> entities = criteria.list();
		return entities;
	}

	public boolean registerUser(UserEntity userEntity) {
		boolean flag = false;
		Session session = getSession();
		try {
			long count = checkIfExists(UserEntity.class, "emailId", userEntity.getEmailId(), session);
			if (count == 0) {
				session.save(userEntity);
				flag = true;
			} else
				throw new AppException("Email id already registered!");

		} catch (Exception e) {
			System.out.println("Exception Occurred while registering user");
			System.err.println(e.getMessage());
		}
		return flag;
	}

	private long checkIfExists(Class type, String paramName, String paramValue, Session session) {
		Criteria criteria = getSession().createCriteria(type);
		Criterion criterion = Restrictions.eq(paramName, paramValue);
		criteria.add(criterion);
		criteria.setProjection(Projections.rowCount());
		long count = (Long) criteria.uniqueResult();
		return count;
	}
	
	@SuppressWarnings("deprecation")
	public String seqNextVal(String seqName)
	{
		String sql= "select " +seqName+".nextval from dual";
		SQLQuery<BigDecimal> q = getSession().createSQLQuery(sql);
		BigDecimal seqNum = q.list().get(0);
		return String.valueOf(seqNum);
	}

	public boolean findUser(String emailId) {
		Session session = getSession();
		long count = 0;
		try {
			count = checkIfExists(LoginEntity.class, "emailId", emailId, session);			
		}
		catch(Exception e) {
			logger.error("Exception occured while finding user");
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	public boolean authenticateUser(String password) {
		Session session = getSession();
		long count = 0;
		try {
			count = checkIfExists(LoginEntity.class, "password", password, session);			
		}
		catch(Exception e) {
			logger.error("Exception occured while authenticating user");
		}
		if (count > 0) {
			return true;
		}
		return false;
	}

	public UserEntity fetchUserDetails(String emailId) {
		Criteria criteria = getSession().createCriteria(UserEntity.class);
		Criterion criterion = Restrictions.eq("emailId", emailId);
		criteria.add(criterion);
		List<UserEntity> entities = criteria.list();
		return entities.get(0);
	}

}
