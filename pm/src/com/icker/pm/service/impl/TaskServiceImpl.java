package com.icker.pm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.TaskDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.service.TaskService;
import com.icker.pm.vo.TaskVO;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private ProjectDao projectDao;
	
	@Override
	public List<Task> findAll(Project project) throws Exception {
		return null;
	}

	@Override
	public List<TaskVO> findAllVOs(Project project) throws Exception {
		Project p = projectDao.findProjectById(project);
		List<Task> tasks = p.getTasks();
		List<TaskVO> vos = new ArrayList<TaskVO>();
		int sequence = 1;
		for (Task task : tasks) {
			TaskVO vo = new TaskVO();
			vo.setId(task.getId());
			vo.setName(task.getName());
			vo.setPerformer(task.getPerformer().getName());
			vo.setPerformerId(task.getPerformer().getId());
			vo.setProgress(task.getProgress());
			vo.setPriority(task.getPriority());
			vo.setEndDate(task.getEndDate());
			vo.setSequence(sequence++);
			vos.add(vo);
		}
		return vos;
	}

}
