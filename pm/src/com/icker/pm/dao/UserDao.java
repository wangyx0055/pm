package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.User;

public interface UserDao {
	public boolean saveUser(User user) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public User findUserById(String userId) throws Exception;
	public List<User> findAll() throws Exception;
	public User findByEmail(String email) throws Exception;
}