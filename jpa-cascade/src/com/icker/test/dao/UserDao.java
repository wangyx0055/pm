package com.icker.test.dao;

import java.util.List;

import com.icker.test.pojo.User;

public interface UserDao {
	public void saveUser(User user) throws Exception;
	public List<User> findAllUsers() throws Exception;
	public User findByEmail(String email) throws Exception;
	public List<User> findAll() throws Exception;
}
