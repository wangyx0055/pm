package com.icker.pm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.TaskDao;
import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.User;
import com.icker.pm.server.email.Mail;
import com.icker.pm.server.email.MailSender;
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
		return taskDao.findAll(project);
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
	public List<TaskVO> findTaskVOs(String performer, Project project)
			throws Exception {
		List<TaskVO> vos = new ArrayList<TaskVO>();
		Project p = projectDao.findProjectById(project);
		List<Task> tasks = p.getTasks();
		int sequence = 1;
		for (Task task : tasks) {
			if (task.getPerformer().getId().equals(performer)) {
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
	public List<Task> findByName(String name, Project project, String status)
			throws Exception {
		return taskDao.findByName(name, project, status);
	}

	@Override
	public void saveTask(Task task, String sendEmail) throws Exception {
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
		t.setStatus(task.getStatus());
		
		if(Constant.SEND_MAIL_YES.equals(sendEmail)) {
			String title = "任务分配通知";
			String content = performer.getName()
					+ "，你好！<br>"
					+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;您被： <b>"
					+ creator.getName()
					+ "</b> 分配了一项新的任务："+task.getName()
					+"，请核实。</p>";
			String to = performer.getEmail();
			this.sendEMail(title, content, to);
		}
		
		taskDao.saveTask(t);
	}

	@Override
	public void updateTask(TaskVO vo) throws Exception {
		User performer = userDao.findUserById(vo.getPerformerId());
		Task task = taskDao.findTask(vo.getId());
		
		if(Constant.SEND_MAIL_YES.equals(vo.getSendEmail())) {
			String title = "任务修改通知";
			String content = task.getPerformer().getName()
							+ "，你好！<br>"
							+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;您当前负责的任务： <b>"
							+ task.getName()
							+ "</b> 已经有所修改，或许负责人不再是您，请核实。</p>";
			String to = task.getPerformer().getEmail();
			this.sendEMail(title, content, to);
			if(!vo.getPerformerId().equals(task.getPerformer().getId())) {
				String newContent = performer.getName()
						+ "，你好！<br>"
						+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;您被： <b>"
						+ task.getCreator().getName()
						+ "</b> 分配了一项新的任务："+vo.getName()
						+",请核实。</p>";
				User user = userDao.findUserById(vo.getPerformerId());
				this.sendEMail("任务分配通知", newContent, user.getEmail());
			}
		}
		
		task.setName(vo.getName());
		task.setDescription(vo.getDescription());
		task.setStartDate(vo.getStartDate());
		task.setEndDate(vo.getEndDate());
		task.setPriority(vo.getPriority());
		task.setProgress(vo.getProgress());
		task.setPerformer(performer);
		taskDao.update(task);
	}

	private void sendEMail(String title, String content, String to) {
		MailSender sender = new MailSender(new Mail(title,content,to));
		sender.start();
	}

	@Override
	public void remove(String id) throws Exception {
		Task task = taskDao.findTask(id);
		taskDao.delete(task);
	}

	@Override
	public void completeTask(Task task) throws Exception {
		Task t = taskDao.findTask(task.getId());
		t.setStatus(Constant.TASK_STATUS_COMPLETED);
		t.setFinishDate(DateFormatUtil.DateToString(new Date()));
		taskDao.update(t);
	}

	@Override
	public List<Task> findByStatus(String proId, String status)
			throws Exception {
		return taskDao.findByStatus(proId, status);
	}

	@Override
	public List<Task> findAll(String performer, Project project, String status)
			throws Exception {
		return taskDao.findAll(performer, project, status);
	}

	@Override
	public void releaseTask(Task task) throws Exception {
		Task t = taskDao.findTask(task.getId());
		t.setStatus(Constant.TASK_STATUS_UNFINISHED);
		t.setFinishDate(null);
		taskDao.update(t);
	}

	@Override
	public Task findById(String id) throws Exception {
		return taskDao.findTask(id);
	}
}
