package com.icker.pm.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.common.util.PageUtil;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.TasksAssign;
import com.icker.pm.pojo.User;

@Repository
public class ProjectDaoImpl implements ProjectDao {
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
	public boolean deleteProject(Project project, ProjectMember projectMember,
			Task task, List<TasksAssign> tasksAssigns) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Project findProjectById(Project project) throws Exception {
		return (Project) sessionFactory.getCurrentSession().get(Project.class,
				project.getId());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findAll() throws Exception {
		return sessionFactory.getCurrentSession().createQuery("from Project")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> findByUser(User user) throws Exception {
		List<ProjectMember> pms = sessionFactory.getCurrentSession()
				.createQuery("from ProjectMember pm where pm.userId = ?")
				.setString(0, user.getId()).list();
		List<Project> projects = new ArrayList<Project>();
		for (ProjectMember projectMember : pms) {
			Project project = new Project();
			project = (Project) sessionFactory.getCurrentSession().get(
					Project.class, projectMember.getProjectId());
			projects.add(project);
		}
		return projects;
	}

	@Override
	public ProjectMember findProjectMember(ProjectMember projectMember)
			throws Exception {
		return (ProjectMember) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from ProjectMember pm where pm.projectId = ? and pm.userId = ?")
				.setString(0, projectMember.getProjectId())
				.setString(1, projectMember.getUserId()).uniqueResult();
	}

	@Override
	public String saveProject(Project project) throws Exception {
		return (String) sessionFactory.getCurrentSession().save(project);
	}

	@Override
	public String saveProjectMember(ProjectMember pm) throws Exception {
		return (String) sessionFactory.getCurrentSession().save(pm);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil)
			throws Exception {
		String hql = "from Project p, ProjectMember pm where pm.userId = ? and pm.projectId = p.id order by p.createTime desc";
		List<Project> projects = new ArrayList<Project>();
		List<Object> objs = sessionFactory.getCurrentSession().createQuery(hql)
				.setString(0, user.getId())
				.setFirstResult(pageUtil.getCurrentPageBeginNo())
				.setMaxResults(pageUtil.getEachPageMaxSize()).list();
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
		sessionFactory.getCurrentSession().delete(project);
		return true;
	}

	@Override
	public void deleteProjectMember(ProjectMember pm) throws Exception {
		sessionFactory.getCurrentSession().createQuery("delete from ProjectMember pm where pm.projectId = ?").setString(0, pm.getProjectId()).executeUpdate();
	}

}
