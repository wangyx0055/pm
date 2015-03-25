package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.EmailTimerTaskVO;

public interface TaskDao {
	/**
	 * 根据项目id查找所有相关任务
	 * @param proId
	 * @return
	 */
	public List<Task> findTasksByProjectId(String projectId) throws Exception;
	
	public List<EmailTimerTaskVO> findEmailVO() throws Exception;

	public void deleteTaskByProject(Project project) throws Exception;
}
