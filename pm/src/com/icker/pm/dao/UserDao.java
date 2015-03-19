package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.User;

public interface UserDao {
	
	/**
	 * 新增或修改User
	 * @param user
	 * @return
	 */
	public String saveUser(User user) throws Exception;
	
	/**
	 * 根据userId查找User
	 * @param userId
	 * @return
	 */
	public User findUserById(String userId) throws Exception;
	
	/**
	 * 查询所有User
	 * @return
	 */
	public List<User> findAll() throws Exception;
	
	/**
	 * 通过email账号查询
	 * @return
	 * @throws Exception
	 */
	public User findByEmail(String email) throws Exception;
}