package com.icker.test.service;

import java.util.List;

import com.icker.test.pojo.User;

public interface UserService {
	public List<User> findAll() throws Exception;

	boolean hasUser(User user) throws Exception;

	public User findUserByEmail(String email) throws Exception;
}
