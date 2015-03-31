package com.icker.pm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.common.util.PageUtil;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.User;

@Repository
public class ProjectDaoImpl extends BaseDao<Project> implements ProjectDao {

	@Override
	public Project findProjectById(Project project) throws Exception {
		return (Project) super.findById(Project.class, project.getId());
	}

	@Override
	public List<Project> findAll() throws Exception {
		return super.findAll("from Project");
	}

	@Override
	public boolean saveProject(Project project) throws Exception {
		return super.save(project);
	}

	@Override
	public boolean saveProjectMember(ProjectMember pm) throws Exception {
		return super.save(pm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil)
			throws Exception {
		String hql = "from Project p, ProjectMember pm where pm.id.user.id = ?0 and pm.id.project.id = p.id order by p.createTime desc";
		List<Project> projects = new ArrayList<Project>();
		List<Object> objs = super.getEntityManager().createQuery(hql)
				.setParameter(0, user.getId())
				.setFirstResult(pageUtil.getCurrentPageBeginNo())
				.setMaxResults(pageUtil.getEachPageMaxSize()).getResultList();
		Iterator<Object> iterator = objs.iterator();
		while (iterator.hasNext()) {
			Object[] objects = (Object[]) iterator.next();
			Project project = (Project) objects[0];
			projects.add(project);
		}
		return projects;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil, String status)
			throws Exception {
		String hql = "from Project p, ProjectMember pm where pm.id.user.id = ?0 and p.status = ?1 and pm.id.project.id = p.id order by p.createTime desc";
		List<Project> projects = new ArrayList<Project>();
		List<Object> objs = super.getEntityManager().createQuery(hql)
				.setParameter(0, user.getId()).setParameter(1, status)
				.setFirstResult(pageUtil.getCurrentPageBeginNo())
				.setMaxResults(pageUtil.getEachPageMaxSize()).getResultList();
		Iterator<Object> iterator = objs.iterator();
		while (iterator.hasNext()) {
			Object[] objects = (Object[]) iterator.next();
			Project project = (Project) objects[0];
			projects.add(project);
		}
		return projects;
	}
	
	@Override
	public boolean deleteProject(Project project) throws Exception {
		return super.delete(project);
	}

	@Override
	public void deleteProjectMember(ProjectMember pm) throws Exception {

		// sessionFactory.getCurrentSession().createQuery("delete from ProjectMember pm where pm.projectId = ?").setString(0,
		// pm.getProjectId()).executeUpdate();
	}

	@Override
	public boolean updateProject(Project project) throws Exception {
		return super.update(project);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findCountOfPro(User user, String status) throws Exception {
		int count = 0;
		String hql = "from Project p where p.status = ?0";
		List<Project> projects = super.getEntityManager().createQuery(hql).setParameter(0, status).getResultList();
		for (Project project : projects) {
			List<ProjectMember> pms = project.getProjectMembers();
			for (ProjectMember pm : pms)
				if(pm.getId().getUser().getId().equals(user.getId()))
					count++;
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findByUser(User user, String status) throws Exception {
		String hql = "from Project p where p.status = ?0";
		List<Project> list = new ArrayList<Project>(); 
		List<Project> projects = super.getEntityManager().createQuery(hql).setParameter(0, status).getResultList();
		for (Project project : projects) {
			List<ProjectMember> pms = project.getProjectMembers();
			for (ProjectMember pm : pms)
				if(pm.getId().getUser().getId().equals(user.getId()))
					list.add(project);
		}
		return list;
	}

}
