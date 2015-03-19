package com.icker.pm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.dao.ResourceDao;
import com.icker.pm.pojo.Resource;

@Repository
public class ResourceDaoImpl implements ResourceDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public String saveResource(Resource resource) throws Exception{
		return (String) sessionFactory.getCurrentSession().save(resource);
	}

	@Override
	public void deleteResourceById(Resource resource) throws Exception{
		sessionFactory.getCurrentSession().delete(resource);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findResourceByProject(String projectId) throws Exception{
		return sessionFactory.getCurrentSession()
				.createQuery("from Resource r where r.proid = :proid")
				.setString("proid", projectId).list();
	}

	@Override
	public Resource findResourceById(Resource resource) throws Exception{
		return (Resource) sessionFactory.getCurrentSession().get(Resource.class, resource.getId());
	}

	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
