package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.TaskVO;

public interface TaskService {
	public List<Task> findAll(Project project) throws Exception;
	public List<TaskVO> findAllVOs(Project project) throws Exception;
	public List<TaskVO> findTaskVOs(String performer, Project project) throws Exception;
	public void saveTask(Task task, String sendEmail) throws Exception;
	public void updateTask(TaskVO vo) throws Exception;
	public void remove(String id) throws Exception;
	public void completeTask(Task task) throws Exception;
	public List<Task> findByStatus(String proId, String status) throws Exception;
	public List<Task> findAll(String performer, Project project, String status) throws Exception;
	public List<Task> findByName(String name, Project project, String status) throws Exception;
	public void releaseTask(Task task) throws Exception;
	public Task findById(String id) throws Exception;
}
