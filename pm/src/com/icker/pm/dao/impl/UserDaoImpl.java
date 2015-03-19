package com.icker.pm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String saveUser(User user) throws Exception {
		return (String) sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User findUserById(String userId) throws Exception {
		return (User) sessionFactory.getCurrentSession()
				.get(User.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() throws Exception {
		return sessionFactory.getCurrentSession().createQuery("from User user")
				.list();
	}

	@Override
	public User findByEmail(String email) throws Exception {
		return (User) sessionFactory.getCurrentSession()
				.createQuery("from User user where user.email = :email")
				.setString("email", email).uniqueResult();
	}

}
