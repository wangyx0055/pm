package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.EmailTimerTaskVO;

public interface TaskDao {
	public List<EmailTimerTaskVO> findEmailVO() throws Exception;
	public List<Task> findAll(Project project) throws Exception;
}
