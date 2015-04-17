package com.icker.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.DiscussDao;
import com.icker.pm.pojo.Discuss;
import com.icker.pm.pojo.Project;

@Repository
public class DiscussDaoImpl extends BaseDao<Discuss> implements DiscussDao {

	@Override
	public boolean save(Discuss discuss) throws Exception {
		return super.save(discuss);
	}

	@Override
	public boolean update(Discuss discuss) throws Exception {
		return super.update(discuss);
	}

	@Override
	public boolean remove(Discuss discuss) throws Exception {
		return super.delete(discuss);
	}

	@Override
	public Discuss findById(Discuss discuss) throws Exception {
		return (Discuss) super.findById(Discuss.class, discuss.getId());
	}

	@Override
	public Discuss findById(String id) throws Exception {
		return (Discuss) super.findById(Discuss.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discuss> findByProject(Project project) throws Exception {
		String hql = "from Discuss dis where dis.project.id = ?0 order by dis.createTime desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, project.getId()).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Discuss> findByTitle(String proId, String title)
			throws Exception {
		String hql = "from Discuss dis where dis.project.id = ?0 and dis.title like ?1 order by dis.createTime desc";
		return super.getEntityManager().createQuery(hql).setParameter(0, proId).setParameter(1, "%"+title+"%").getResultList();
	}

}
