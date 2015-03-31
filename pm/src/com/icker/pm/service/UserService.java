package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.User;

public interface UserService {
	public boolean addUser(User user) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public User findUserById(String userId) throws Exception;
	public boolean hasUser(User user) throws Exception;
	public User findUserByEmail(String email) throws Exception;
	public List<User> findAll() throws Exception;
}
