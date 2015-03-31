package com.icker.pm.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.dao.ResourceDao;
import com.icker.pm.pojo.Resource;

@Repository
public class ResourceDaoImpl extends BaseDao<Resource> implements ResourceDao {
	
	@Override
	public boolean saveResource(Resource resource) throws Exception{
		return super.save(resource);
	}

	@Override
	public void deleteResourceById(Resource resource) throws Exception{
		super.delete(resource);
	}
	
	@Override
	public Resource findResourceById(Resource resource) throws Exception{
		return (Resource) super.findById(Resource.class, resource.getId());
	}
}
