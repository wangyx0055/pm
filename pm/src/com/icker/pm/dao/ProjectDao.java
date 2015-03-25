package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.TasksAssign;
import com.icker.pm.pojo.User;


public interface ProjectDao {
	
	/**
	 * 新建或修改项目
	 * @param projects	项目
	 * @param creator	创建者
	 * @param registedUsers		已注册用户列表
	 * @param unRegistedUsers	未注册用户列表
	 * @return
	 */
	public boolean saveProject(Project project, User creator, List<User> registedUsers, List<User> unRegistedUsers) throws Exception;
	
	/**
	 * 删除项目
	 * @param project	项目
	 * @param projectMember	项目成员
	 * @param task	任务
	 * @param tasksAssigns	任务分配
	 * @return
	 */
	public boolean deleteProject(Project project, ProjectMember projectMember, Task task, List<TasksAssign> tasksAssigns) throws Exception;
	public Project findProjectById(Project project) throws Exception;
	public List<Project> findByUser(User user) throws Exception;
	public List<Project> findAll() throws Exception;
	public ProjectMember findProjectMember(ProjectMember projectMember) throws Exception;
}
