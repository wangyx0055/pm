package com.icker.pm.service;

import java.util.List;
import java.util.Map;

import com.icker.pm.common.util.PageUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.User;

public interface ProjectService {
	public boolean addProject(Project project) throws Exception;
	public boolean updateProject(Project project) throws Exception;
	public boolean removeProject(Project project) throws Exception;
	public boolean addProject(User creator, Project project, List<User> members, String sendEmail) throws Exception;
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil) throws Exception;
	public Project findById(Project project) throws Exception;
	public List<Project> findByUser(User user) throws Exception;
	public List<Project> findAll() throws Exception;
	public ProjectMember findPMByUserPro(User user,Project project) throws Exception;
	public List<Map<String, Object>> findUsersByProject(Project project, User creator) throws Exception;
	public boolean updateProject(Project project, List<User> users) throws Exception;
	public int findCount(User user, String status) throws Exception;
	public List<Project> findByUser(User user, String status) throws Exception;
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil, String status) throws Exception;
	public List<Map<String, Object>> findUsers(Project project) throws Exception;
	public Project findProject(String id) throws Exception;
	public boolean updateProject(Project project, String sendEmail) throws Exception;
	public List<Integer> findActionCount(Project project) throws Exception;
	public List<List<Object>> findTotalPieCharts(Project project) throws Exception;
	public List<Integer> taskHistogram(Project project) throws Exception;
	public List<Integer> mileHistogram(Project project) throws Exception;
}
