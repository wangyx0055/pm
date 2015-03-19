package com.icker.pm.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.icker.pm.common.util.PageUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;

@Controller
@RequestMapping("/projectController")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@RequestMapping("/getProjects")
	public Map<String, Object> getProjects(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		int currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		try {
			List<Project> projects = projectService.findAll();
			PageUtil pageUtil = new PageUtil(projects.size());
			pageUtil.setCurrentPageNo(currentPageNo);
			pageUtil.setCurrentPageBeginNo((currentPageNo-1)*pageUtil.getEachPageMaxSize()+1);	//设置起始行
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
