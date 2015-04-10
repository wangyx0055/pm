package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.common.util.PageUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.User;


public interface ProjectDao {
	
	public boolean saveProject(Project project) throws Exception;
	public boolean saveProjectMember(ProjectMember pm) throws Exception;
	/**
	 * 删除项目
	 * @param project	项目
	 * @param projectMember	项目成员
	 * @param task	任务
	 * @param tasksAssigns	任务分配
	 * @return
	 */
	public boolean deleteProject(Project project) throws Exception;
	public Project findProjectById(Project project) throws Exception;
	public List<Project> findAll() throws Exception;
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil) throws Exception;
	public void deleteProjectMember(ProjectMember pm) throws Exception;
	public boolean updateProject(Project project) throws Exception;
	public int findCountOfPro(User user, String status) throws Exception;
	public List<Project> findByUser(User user, String status) throws Exception;
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil, String status) throws Exception;
	public Project findById(String id) throws Exception;
	
}
