package com.icker.pm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.dao.ProjectDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.TasksAssign;
import com.icker.pm.pojo.User;

@Repository
public class ProjectDaoImpl implements ProjectDao{
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
	public boolean saveProject(Project project, User creator,
			List<User> registedUsers, List<User> unRegistedUsers) throws Exception{
		return false;
	}

	@Override
	public boolean deleteProject(Project project, ProjectMember projectMember,
			Task task, List<TasksAssign> tasksAssigns) throws Exception{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Project findProjectById(Project project) throws Exception{
		return (Project) sessionFactory.getCurrentSession().get(Project.class, project.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findAll() throws Exception {
		return sessionFactory.getCurrentSession().createQuery("from Project").list();
	}
}
