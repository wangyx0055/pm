package com.icker.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.User;

@Repository
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public boolean saveUser(User user) throws Exception {
		return super.save(user);
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		return super.update(user);
	}

	@Override
	public User findUserById(String userId) throws Exception {
		return (User) super.findById(User.class, userId);
	}

	@Override
	public List<User> findAll() throws Exception {
		return super.findAll("from User");
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByEmail(String email) throws Exception {
		List<User> users = super.getEntityManager()
				.createQuery("from User user where user.email = ?0")
				.setParameter(0, email).getResultList();
		if (!users.isEmpty())
			return users.get(0);
		else
			return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUsersByProject(Project project) throws Exception {
		String hql = "select pm.id.user from ProjectMember pm join pm.id.project p where p.id = ?0";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, project.getId()).getResultList();
	}

}
