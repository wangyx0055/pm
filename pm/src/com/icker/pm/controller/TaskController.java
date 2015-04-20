package com.icker.pm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;
import com.icker.pm.service.TaskService;
import com.icker.pm.vo.TaskVO;

@Controller
@RequestMapping("/taskController")
@SessionAttributes(value = { "user" })
public class TaskController extends ExceptionController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;

	/**
	 * 跳转到Task任务管理页面
	 * 
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/tasks")
	public ModelAndView tasks(Project project, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			List<Task> unfinished = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_UNFINISHED);
			List<Task> completed = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_COMPLETED);
			List<Task> extension = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_EXTENSION);
			List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
			List<TaskVO> completedVOs = this.getVOs(completed);
			List<TaskVO> extensionVOs = this.getVOs(extension);
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("tasks", unfinishedVOs);
			modelAndView.addObject("completed", completedVOs);
			modelAndView.addObject("extension", extensionVOs);
			modelAndView.setViewName("task/task");
			modelAndView.addObject("project", p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 根据用户查找任务
	 * 
	 * @param performer
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userTasks")
	public ModelAndView userTasks(String performer, Project project,
			ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project p = null;
			if (StringUtils.isBlank(performer)) {
				p = projectService.findById(project);
				List<Task> unfinished = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_UNFINISHED);
				List<Task> completed = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_COMPLETED);
				List<Task> extension = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_EXTENSION);
				
				List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
				List<TaskVO> completedVOs = this.getVOs(completed);
				List<TaskVO> extensionVOs = this.getVOs(extension);
				
				modelAndView.addObject("tasks", unfinishedVOs);
				modelAndView.addObject("completed", completedVOs);
				modelAndView.addObject("extension", extensionVOs);
				modelAndView.setViewName("task/userTasks");
			} else {
				p = projectService.findById(project);
				List<Task> unfinished = taskService.findAll(performer, project, Constant.TASK_STATUS_UNFINISHED);
				List<Task> completed = taskService.findAll(performer, project, Constant.TASK_STATUS_COMPLETED);
				List<Task> extension = taskService.findAll(performer, project, Constant.TASK_STATUS_EXTENSION);
				List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
				List<TaskVO> completedVOs = this.getVOs(completed);
				List<TaskVO> extensionVOs = this.getVOs(extension);
				modelAndView.addObject("tasks", unfinishedVOs);
				modelAndView.addObject("completed", completedVOs);
				modelAndView.addObject("extension", extensionVOs);
				modelAndView.setViewName("task/userTasks");
			}
			modelAndView.addObject("project", p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findTasksByName")
	public ModelAndView findTasksByName(String name, Project project,
			ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			List<Task> unfinished = taskService.findByName(name, project, Constant.TASK_STATUS_UNFINISHED);
			List<Task> completed = taskService.findByName(name, project, Constant.TASK_STATUS_COMPLETED);
			List<Task> extension = taskService.findByName(name, project, Constant.TASK_STATUS_EXTENSION);
			List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
			List<TaskVO> completedVOs = this.getVOs(completed);
			List<TaskVO> extensionVOs = this.getVOs(extension);
			modelAndView.addObject("tasks", unfinishedVOs);
			modelAndView.addObject("completed", completedVOs);
			modelAndView.addObject("extension", extensionVOs);
			modelAndView.addObject("project", p);
			modelAndView.setViewName("task/userTasks");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 新增任务
	 * 
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addTask")
	public ModelAndView addTask(TaskVO vo, ModelAndView modelAndView,
			ModelMap modelMap) {
		User creator = (User) modelMap.get("user");
		Task task = new Task();
		task.setName(vo.getName());
		task.setDescription(vo.getDescription());
		task.setCreateTime(DateFormatUtil.DateToString(new Date()));
		task.setCreator(creator);
		task.setEndDate(vo.getEndDate());
		User performer = new User();
		performer.setId(vo.getPerformerId());
		task.setPerformer(performer);
		task.setPriority(vo.getPriority());
		task.setProgress(0.00);
		Project project = new Project();
		project.setId(vo.getProject());
		task.setProject(project);
		task.setStartDate(vo.getStartDate());
		task.setStatus(Constant.TASK_STATUS_UNFINISHED);
		try {
			taskService.saveTask(task,vo.getSendEmail());
			Project p = projectService.findById(project);
			List<Task> unfinished = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_UNFINISHED);
			List<Task> completed = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_COMPLETED);
			List<Task> extension = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_EXTENSION);
			List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
			List<TaskVO> completedVOs = this.getVOs(completed);
			List<TaskVO> extensionVOs = this.getVOs(extension);
			modelAndView.addObject("tasks", unfinishedVOs);
			modelAndView.addObject("completed", completedVOs);
			modelAndView.addObject("extension", extensionVOs);
			modelAndView.addObject("project", p);
			modelAndView.setViewName("task/userTasks");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 更新任务
	 * 
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTask")
	public ModelAndView updateTask(TaskVO vo, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			taskService.updateTask(vo);
			Project project = new Project();
			project.setId(vo.getProject());
			Project p = projectService.findById(project);
			List<Task> unfinished = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_UNFINISHED);
			List<Task> completed = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_COMPLETED);
			List<Task> extension = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_EXTENSION);
			List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
			List<TaskVO> completedVOs = this.getVOs(completed);
			List<TaskVO> extensionVOs = this.getVOs(extension);
			modelAndView.addObject("tasks", unfinishedVOs);
			modelAndView.addObject("completed", completedVOs);
			modelAndView.addObject("extension", extensionVOs);
			modelAndView.addObject("project", p);
			modelAndView.setViewName("task/userTasks");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("/deleteTask")
	public ModelAndView deleteTask(TaskVO vo, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			taskService.remove(vo.getId());
			Project project = new Project();
			project.setId(vo.getProject());
			Project p = projectService.findById(project);
			
			List<Task> unfinished = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_UNFINISHED);
			List<Task> completed = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_COMPLETED);
			List<Task> extension = taskService.findByStatus(p.getId(), Constant.TASK_STATUS_EXTENSION);
			List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
			List<TaskVO> completedVOs = this.getVOs(completed);
			List<TaskVO> extensionVOs = this.getVOs(extension);
			modelAndView.addObject("tasks", unfinishedVOs);
			modelAndView.addObject("completed", completedVOs);
			modelAndView.addObject("extension", extensionVOs);
			modelAndView.addObject("project", p);
			modelAndView.setViewName("task/userTasks");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 完成任务
	 * @param task
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/completeTask")
	public ModelAndView completeTask(String proId, Task task, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			taskService.completeTask(task);
			List<Task> unfinished = taskService.findByStatus(proId, Constant.TASK_STATUS_UNFINISHED);
			List<Task> completed = taskService.findByStatus(proId, Constant.TASK_STATUS_COMPLETED);
			List<Task> extension = taskService.findByStatus(proId, Constant.TASK_STATUS_EXTENSION);
			List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
			List<TaskVO> completedVOs = this.getVOs(completed);
			List<TaskVO> extensionVOs = this.getVOs(extension);
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("tasks", unfinishedVOs);
			modelAndView.addObject("completed", completedVOs);
			modelAndView.addObject("extension", extensionVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("/releaseTask")
	public ModelAndView releaseTask(String proId, Task task, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			taskService.releaseTask(task);
			List<Task> unfinished = taskService.findByStatus(proId, Constant.TASK_STATUS_UNFINISHED);
			List<Task> completed = taskService.findByStatus(proId, Constant.TASK_STATUS_COMPLETED);
			List<Task> extension = taskService.findByStatus(proId, Constant.TASK_STATUS_EXTENSION);
			List<TaskVO> unfinishedVOs = this.getVOs(unfinished);
			List<TaskVO> completedVOs = this.getVOs(completed);
			List<TaskVO> extensionVOs = this.getVOs(extension);
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("tasks", unfinishedVOs);
			modelAndView.addObject("completed", completedVOs);
			modelAndView.addObject("extension", extensionVOs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 日历：任务事件
	 * @param task
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/event")
	public ModelAndView event(Task task, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Task t = taskService.findById(task.getId());
			modelAndView.addObject("task", t);
			modelAndView.setViewName("task/event");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	private List<TaskVO> getVOs(List<Task> tasks) {
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
			vo.setStatus(task.getStatus());
			if(Constant.TASK_STATUS_COMPLETED.equals(task.getStatus()))
				vo.setProgress(100D);
			else
				vo.setProgress(task.getProgress());
			vo.setStartDate(task.getStartDate());
			vo.setFinishDate(task.getFinishDate());
			vo.setDescription(task.getDescription());
			vo.setSequence(sequence++);
			vos.add(vo);
		}
		return vos;
	}
}
