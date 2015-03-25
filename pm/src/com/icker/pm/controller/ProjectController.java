package com.icker.pm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.common.util.PageUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;
import com.icker.pm.vo.UserProjectVO;

@Controller
@RequestMapping("/projectController")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	@RequestMapping("/projectList")
	@ResponseBody
	public ModelAndView getProjects(HttpServletRequest request, ModelAndView modelAndView) {
		/*User user = (User) request.getSession().getAttribute("user");
		int currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		try {
			List<Project> projects = projectService.findAll();
			PageUtil pageUtil = new PageUtil(projects.size());
			pageUtil.setCurrentPageNo(currentPageNo);
			pageUtil.setCurrentPageBeginNo((currentPageNo-1)*pageUtil.getEachPageMaxSize()+1);	//设置起始行
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		User user = (User) request.getSession().getAttribute("user");
		List<UserProjectVO> upVOs = new ArrayList<UserProjectVO>();
		List<Project> projects = null;
		// 查询用户所有的项目
		try {
			projects = projectService.findByUser(user);
			for (Project project : projects) {
				UserProjectVO upVO = new UserProjectVO(user.getId(), project.getId(), project.getName(), project.getDescribes(), project.getCreateTime());
				upVOs.add(upVO);
			}
//			ProjectMember pm = projectService.findPMByUserPro(user, project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*request.setAttribute("projects", projects);
		request.setAttribute("upVOs", upVOs);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("html", "/jsp/pro/proList");
		result.put("projects", projects);
		result.put("upVOs", upVOs);*/
		modelAndView.addObject("projects", projects);
		modelAndView.addObject("upVOs", upVOs);
		modelAndView.setViewName("pro/proList");
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
