package com.icker.pm.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.dao.TaskAssignDao;
import com.icker.pm.pojo.Task;

@Repository
public class TaskAssignDaoImpl implements TaskAssignDao{
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
	public void deleteTaskAssignByTask(Task task) throws Exception {
		sessionFactory.getCurrentSession().createQuery("delete from TasksAssign ta where ta.taskId = ?").setString(0, task.getId()).executeUpdate();
	}
}
