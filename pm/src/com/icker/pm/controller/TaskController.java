package com.icker.pm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.pojo.Project;
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
}
