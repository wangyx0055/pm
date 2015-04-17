package com.icker.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.MilestoneDao;
import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;

@Repository
public class MilestoneDaoImpl extends BaseDao<Milestone> implements
		MilestoneDao {

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
		String hql = "from Milestone m where m.project.id = ?0 and m.status = ?1 order by m.endDate desc";
		List<Milestone> milestones = super.getEntityManager().createQuery(hql)
				.setParameter(0, project.getId()).setParameter(1, status)
				.getResultList();
		return milestones;
	}

	@Override
	public Milestone findById(String id) throws Exception {
		return (Milestone) super.findById(Milestone.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Milestone> findByName(Project p, String name, String status)
			throws Exception {
		String hql = "from Milestone m where m.project.id = ?0 and m.name like ?1 and m.status = ?2 order by m.endDate desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, p.getId()).setParameter(1, "%" + name + "%")
				.setParameter(2, status).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Milestone> findByUser(Project p, String performer, String status)
			throws Exception {
		String hql = "from Milestone m where m.project.id = ?0 and m.performer.id = ?1 and m.status = ?2 order by m.endDate desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, p.getId()).setParameter(1, performer)
				.setParameter(2, status).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Milestone> findAll(Project project) throws Exception {
		String hql = "from Milestone m where m.project.id = ?0";
		return super.getEntityManager().createQuery(hql).setParameter(0, project.getId()).getResultList();
	}

}
