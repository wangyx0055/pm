package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.TaskVO;

public interface TaskService {
	public List<Task> findAll(Project project) throws Exception;
	public List<TaskVO> findAllVOs(Project project) throws Exception;
	public List<TaskVO> findTaskVOs(String performer, Project project) throws Exception;
	public List<TaskVO> findTaskVOsByName(String name, Project project) throws Exception;
	public void saveTask(Task task) throws Exception;
	public void updateTask(TaskVO vo) throws Exception;
	public void remove(String id) throws Exception;
}
