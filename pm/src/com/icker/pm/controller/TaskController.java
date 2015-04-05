package com.icker.pm.controller;

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

import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;
import com.icker.pm.service.TaskService;
import com.icker.pm.vo.TaskVO;

@Controller
@RequestMapping("/taskController")
@SessionAttributes(value={"user"})
public class TaskController {
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;
	
	/**
	 * 跳转到Task任务管理页面
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/tasks")
	public ModelAndView tasks(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			List<TaskVO> tasks = taskService.findAllVOs(p);
			modelAndView.setViewName("task/task");
			modelAndView.addObject("project",p);
			modelAndView.addObject("tasks",tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 根据用户查找任务
	 * @param performer
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/userTasks")
	public ModelAndView userTasks(String performer, Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project p = null;
			List<TaskVO> tasks = null;
			if(StringUtils.isBlank(performer)){
				p = projectService.findById(project);
				tasks = taskService.findAllVOs(p);
			} else {
				p = projectService.findById(project);
				tasks = taskService.findTaskVOs(performer, project);
			}
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("project",p);
			modelAndView.addObject("tasks",tasks);
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
	public ModelAndView findTasksByName(String name, Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			List<TaskVO> tasks = taskService.findTaskVOsByName(name, project);
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("project",p);
			modelAndView.addObject("tasks",tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 新增任务
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addTask")
	public ModelAndView addTask(TaskVO vo, ModelAndView modelAndView, ModelMap modelMap) {
		User creator = (User)modelMap.get("user");
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
		try {
			taskService.saveTask(task);
			Project p = projectService.findById(project);
			List<TaskVO> tasks = taskService.findAllVOs(p);
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("project",p);
			modelAndView.addObject("tasks",tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 更新任务
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTask")
	public ModelAndView updateTask(TaskVO vo, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			taskService.updateTask(vo);
			Project project = new Project();
			project.setId(vo.getProject());
			Project p = projectService.findById(project);
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("project",p);
			List<TaskVO> tasks = taskService.findAllVOs(p);
			modelAndView.addObject("tasks",tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/deleteTask")
	public ModelAndView deleteTask(TaskVO vo, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			taskService.remove(vo.getId());
			Project project = new Project();
			project.setId(vo.getProject());
			Project p = projectService.findById(project);
			modelAndView.setViewName("task/userTasks");
			modelAndView.addObject("project",p);
			List<TaskVO> tasks = taskService.findAllVOs(p);
			modelAndView.addObject("tasks",tasks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
