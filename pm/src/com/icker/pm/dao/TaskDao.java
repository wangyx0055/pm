package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.EmailTimerTaskVO;

public interface TaskDao {
	public List<EmailTimerTaskVO> findEmailVO() throws Exception;
	public List<Task> findAll(Project project) throws Exception;
	public List<Task> findByName(String name) throws Exception;
	public void saveTask(Task task) throws Exception;
	public Task findTask(String id) throws Exception;
	public void update(Task task) throws Exception;
	public void delete(Task task) throws Exception;
	public List<Task> findByStatus(String proId, String status) throws Exception;
	public List<Task> findAll(String performer, Project project, String status) throws Exception;
	public List<Task> findByName(String name, Project project, String status) throws Exception;
}
