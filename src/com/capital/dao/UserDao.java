package com.capital.dao;

import java.math.BigDecimal;
import java.util.List;

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

@Repository
public class UserDao {
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

	public boolean registerUser(UserEntity userEntity) {
		boolean flag = false;
		try {
			Criteria criteria = getSession().createCriteria(UserEntity.class);
			Criterion criterion = Restrictions.eq("emailId", userEntity.getEmailId());
			criteria.add(criterion);
			criteria.setProjection(Projections.rowCount());
			long count = (Long) criteria.uniqueResult();
			if (count == 0) {
				getSession().save(userEntity);
				flag = true;
			} else
				throw new AppException("Email id already registered!");

		} catch (Exception e) {
			System.out.println("Exception Occurred while registering user");
			System.err.println(e.getMessage());
		}
		return flag;
	}
	
	@SuppressWarnings("deprecation")
	public String seqNextVal(String seqName)
	{
		String sql= "select " +seqName+".nextval from dual";
		SQLQuery<BigDecimal> q = getSession().createSQLQuery(sql);
		BigDecimal seqNum = q.list().get(0);
		return String.valueOf(seqNum);
	}

}
