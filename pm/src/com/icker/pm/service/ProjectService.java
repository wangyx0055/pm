package com.icker.pm.service;

import java.util.List;

import com.icker.pm.common.util.PageUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.User;

public interface ProjectService {
	public String addProject(Project project) throws Exception;
	public String updateProject(Project project) throws Exception;
	public boolean removeProject(Project project) throws Exception;
	public Project findById(Project project) throws Exception;
	public List<Project> findByUser(User user) throws Exception;
	public List<Project> findAll() throws Exception;
	public ProjectMember findPMByUserPro(User user,Project project) throws Exception;
	public String addProject(User creator, Project project, List<User> members) throws Exception;
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil) throws Exception;
}
