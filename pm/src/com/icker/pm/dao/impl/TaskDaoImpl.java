package com.icker.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.dao.TaskDao;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.EmailTimerTaskVO;

@Repository
public class TaskDaoImpl implements TaskDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findTasksByProjectId(String projectId) throws Exception{
		Query query = sessionFactory
				.getCurrentSession()
				.createQuery("from Task task where task.projectId = :projectId")
				.setString("projectId", projectId);
		return query.list();
	}

	@Override
	public List<EmailTimerTaskVO> findEmailVO() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
