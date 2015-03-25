package com.icker.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.dao.ProjectDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectDao projectDao;
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	@Override
	public String addProject(Project project) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateProject(Project project) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean removeProject(Project project) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Project findById(Project project) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Project> findByUser(User user) throws Exception {
		try {
			return projectDao.findByUser(user);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public List<Project> findAll() throws Exception {
		try {
			return projectDao.findAll();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	@Override
	public ProjectMember findPMByUserPro(User user, Project project)
			throws Exception {
		try {
			ProjectMember pm = new ProjectMember();
			pm.setUserId(user.getId());
			pm.setProjectId(project.getId());
			return projectDao.findProjectMember(pm);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
