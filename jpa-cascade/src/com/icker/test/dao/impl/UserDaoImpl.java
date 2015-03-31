package com.icker.test.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.test.dao.UserDao;
import com.icker.test.pojo.Project;
import com.icker.test.pojo.User;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao{
	
	public void saveUser(User user) throws Exception {
		super.save(user);
	}

	public List<User> findAllUsers() throws Exception {
		return super.findAll("from User");
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByEmail(String email) throws Exception {
		// 根据用户查找项目
		List<User> users = super.getEntityManager().createQuery("from User user where user.email = ?0").setParameter(0, email).getResultList();
		// ManyToOne，从many端查找one的一端，必须使用join
		String hql = "select pm.id.project from ProjectMember pm join pm.id.user u where u.email = ?0";
		List<Project> projects = super.getEntityManager().createQuery(hql).setParameter(0, email).getResultList();
		for (Project project : projects) {
			System.out.println("project:\t"+project.getName());
		}
		return users.get(0);
	}

	@Override
	public List<User> findAll() throws Exception {
		return super.findAll("from User");
	}
}
