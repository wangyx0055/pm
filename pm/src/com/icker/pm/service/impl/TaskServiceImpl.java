package com.icker.pm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.TaskDao;
import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.User;
import com.icker.pm.service.TaskService;
import com.icker.pm.vo.TaskVO;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UserDao userDao;
	
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
			vo.setStartDate(task.getStartDate());
			vo.setDescription(task.getDescription());
			vo.setSequence(sequence++);
			vos.add(vo);
		}
		return vos;
	}

	/**
	 * 根据项目id和执行者id查找任务信息
	 */
	@Override
	public List<TaskVO> findTaskVOs(String performer, Project project) throws Exception {
		List<TaskVO> vos = new ArrayList<TaskVO>();
		Project p = projectDao.findProjectById(project);
		List<Task> tasks = p.getTasks();
		int sequence = 1;
		for (Task task : tasks) {
			if(task.getPerformer().getId().equals(performer)) {
				TaskVO vo = new TaskVO();
				vo.setId(task.getId());
				vo.setName(task.getName());
				vo.setPerformer(task.getPerformer().getName());
				vo.setPerformerId(task.getPerformer().getId());
				vo.setProgress(task.getProgress());
				vo.setPriority(task.getPriority());
				vo.setEndDate(task.getEndDate());
				vo.setStartDate(task.getStartDate());
				vo.setDescription(task.getDescription());
				vo.setSequence(sequence++);
				vos.add(vo);
			}
		}
		return vos;
	}

	/**
	 * 根据名称对任务进行模糊查询
	 */
	@Override
	public List<TaskVO> findTaskVOsByName(String name, Project project)
			throws Exception {
		List<TaskVO> vos = new ArrayList<TaskVO>();
		List<Task> tasks = taskDao.findByName(name);
		Iterator<Task> iterator = tasks.iterator();
		while (iterator.hasNext()) {
			Task task = (Task) iterator.next();
			if(!task.getProject().getId().equals(project.getId())) 
				iterator.remove();
		}
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
			vo.setStartDate(task.getStartDate());
			vo.setDescription(task.getDescription());
			vo.setSequence(sequence++);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public void saveTask(Task task) throws Exception {
		Task t = new Task();
		t.setName(task.getName());
		t.setDescription(task.getDescription());
		t.setCreateTime(task.getCreateTime());
		User creator = userDao.findUserById(task.getCreator().getId());
		t.setCreator(creator);
		t.setEndDate(task.getEndDate());
		User performer = userDao.findUserById(task.getPerformer().getId());
		t.setPerformer(performer);
		t.setPriority(task.getPriority());
		t.setProgress((double) 0);
		Project project = projectDao.findProjectById(task.getProject());
		t.setProject(project);
		t.setStartDate(task.getStartDate());
		taskDao.saveTask(t);
	}

	@Override
	public void updateTask(TaskVO vo) throws Exception {
		Task task = taskDao.findTask(vo.getId());
		task.setName(vo.getName());
		task.setDescription(vo.getDescription());
		task.setStartDate(vo.getStartDate());
		task.setEndDate(vo.getEndDate());
		task.setPriority(vo.getPriority());
		task.setProgress(vo.getProgress());
		User performer = userDao.findUserById(vo.getPerformerId());
		task.setPerformer(performer);
		taskDao.update(task);
	}

	@Override
	public void remove(String id) throws Exception {
		Task task = taskDao.findTask(id);
		taskDao.delete(task);
	}

}
