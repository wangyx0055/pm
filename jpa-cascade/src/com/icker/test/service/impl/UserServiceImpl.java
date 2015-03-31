package com.icker.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.test.dao.UserDao;
import com.icker.test.pojo.User;
import com.icker.test.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
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
		return userDao.findByEmail(email);
	}
	
	@Override
	public List<User> findAll() throws Exception {
		return userDao.findAll();
	}

}
