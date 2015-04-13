package com.icker.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.MilestoneDao;
import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;

@Repository
public class MilestoneDaoImpl extends BaseDao<Milestone> implements MilestoneDao {

	@Override
	public boolean save(Milestone milestone) throws Exception {
		return super.save(milestone);
	}

	@Override
	public boolean update(Milestone milestone) throws Exception {
		return super.update(milestone);
	}

	@Override
	public boolean remove(Milestone milestone) throws Exception {
		return super.delete(super.findById(Milestone.class, milestone.getId()));
	}

	@Override
	public boolean remove(String id) throws Exception {
		return super.delete(super.findById(Milestone.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Milestone> findByStatus(Project project, String status)
			throws Exception {
		String hql = "from Milestone m where m.project.id = ?0 and m.status = ?1";
		List<Milestone> milestones = super.getEntityManager().createQuery(hql).setParameter(0, project.getId()).setParameter(1, status).getResultList();
		return milestones;
	}

	@Override
	public Milestone findById(String id) throws Exception {
		return (Milestone) super.findById(Milestone.class, id);
	}

}
