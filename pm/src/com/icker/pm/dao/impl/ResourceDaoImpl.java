package com.icker.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.ResourceDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Resource;

@Repository
public class ResourceDaoImpl extends BaseDao<Resource> implements ResourceDao {

	@Override
	public boolean saveResource(Resource resource) throws Exception {
		return super.save(resource);
	}

	@Override
	public void deleteResourceById(Resource resource) throws Exception {
		super.delete(resource);
	}

	@Override
	public Resource findResourceById(Resource resource) throws Exception {
		return (Resource) super.findById(Resource.class, resource.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findAll(Project project) throws Exception {
		String hql = "from Resource r where r.project.id = ?0 order by r.upTime desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, project.getId()).getResultList();
	}

	@Override
	public Resource findById(String id) throws Exception {
		return (Resource) super.findById(Resource.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findByType(Resource resource) throws Exception {
		String hql = "from Resource r where r.type = ?0 and r.project.id = ?1 order by r.upTime desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, resource.getType())
				.setParameter(1, resource.getProject().getId()).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> findByName(String projectId, String name)
			throws Exception {
		String hql = "from Resource r where r.project.id = ?0 and r.name like ?1 order by r.upTime desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, projectId).setParameter(1, "%" + name + "%")
				.getResultList();
	}
}
