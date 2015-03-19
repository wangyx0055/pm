package com.icker.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.User;
import com.icker.pm.service.UserService;

@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class })
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public String addUser(User user) throws Exception {
		String id = null;
		try {
			id = userDao.saveUser(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return id;
	}

	@Override
	public String updateUser(User user) throws Exception {
		String id = null;
		try {
			id = userDao.saveUser(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return id;
	}

	@Override
	public User findUserById(String userId) throws Exception {
		User user = null;
		try {
			user = userDao.findUserById(userId);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return user;
	}

	@Override
	public List<User> findAll() throws Exception {
		try {
			return userDao.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean hasUser(User user) throws Exception {
		boolean flag = false;
		User u = userDao.findByEmail(user.getEmail());
		flag = (u!=null)?true:false;
		if(!flag)
			throw new Exception("用户账号输入错误！");
		else if(flag && !u.getPassword().equals(user.getPassword()))
			throw new Exception("密码不正确");
		return flag;
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		try {
			return userDao.findByEmail(email);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
