package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.TaskVO;

public interface TaskService {
	public List<Task> findAll(Project project) throws Exception;
	public List<TaskVO> findAllVOs(Project project) throws Exception;
}
