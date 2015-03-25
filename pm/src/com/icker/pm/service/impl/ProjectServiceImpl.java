package com.icker.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.PageUtil;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.TaskAssignDao;
import com.icker.pm.dao.TaskDao;
import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.Task;
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
	@Autowired
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Autowired
	private TaskDao taskDao;
	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	@Autowired
	private TaskAssignDao taskAssignDao;
	public void setTaskAssignDao(TaskAssignDao taskAssignDao) {
		this.taskAssignDao = taskAssignDao;
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
		try {
			ProjectMember pm = new ProjectMember();
			pm.setProjectId(project.getId());
			// 删除项目
			projectDao.deleteProject(project);
			// 删除相关项目成员
			projectDao.deleteProjectMember(pm);
			// 删除子任务
			List<Task> tasks = taskDao.findTasksByProjectId(project.getId());
			for (Task task : tasks)
				taskAssignDao.deleteTaskAssignByTask(task);
			// 删除任务
			taskDao.deleteTaskByProject(project);
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
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
	@Override
	public String addProject(User creator, Project project, List<User> members)
			throws Exception {
		try {
			for (User member : members)
				if(null == userDao.findByEmail(member.getEmail()))
					userDao.saveUser(member);
			String proId = projectDao.saveProject(project);
			ProjectMember proMem = new ProjectMember(project.getId(), creator.getId(), Constant.ROLE_MEMBER, Constant.IS_NOT_ACCESS);
			projectDao.saveProjectMember(proMem);
			for (User user : members) {
				ProjectMember pm = new ProjectMember(project.getId(), user.getId(), Constant.ROLE_MEMBER, Constant.IS_NOT_ACCESS);
				projectDao.saveProjectMember(pm);
			}
			return proId;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<Project> pagingFindByUser(User user,PageUtil pageUtil) throws Exception {
		try {
			return projectDao.pagingFindByUser(user,pageUtil);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
