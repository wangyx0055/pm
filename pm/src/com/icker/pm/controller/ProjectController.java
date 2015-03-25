package com.icker.pm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.common.util.PageUtil;
import com.icker.pm.common.util.UUIDUtil;
import com.icker.pm.pojo.Project;
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

	@ResponseBody
	@RequestMapping("/projectList")
	public ModelAndView projectList(HttpServletRequest request,
			ModelAndView modelAndView) {
		User user = (User) request.getSession().getAttribute("user");
		modelAndView = getProjects(modelAndView, user);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("/addProject")
	public Map<String, Object> addProject(String[] proUserEmails,
			String[] proUserNicks, String[] proUserPasswords, String proName,
			String proDesc, ModelAndView modelAndView,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		User creator = (User) request.getSession().getAttribute("user");
		String createTime = DateFormatUtil.DateToString(new Date());
		Project project = new Project(proName, proDesc, null, createTime);
		project.setId(UUIDUtil.getUUID());
		List<User> members = new ArrayList<User>();
		for (int i = 0; i < proUserEmails.length; i++) {
			User member = new User(proUserEmails[i], proUserPasswords[i], proUserNicks[i], createTime, Constant.STATUS_IS_USABLE,proUserNicks[i]);
			member.setId(UUIDUtil.getUUID());
			members.add(member);
		}
		try {
			projectService.addProject(creator, project, members);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/deleteProject")
	public Map<String, Object> deleteProject(Project project, ModelAndView modelAndView, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			projectService.removeProject(project);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	private ModelAndView getProjects(ModelAndView modelAndView, User user) {
		// 查询用户所有的项目
		try {
			List<UserProjectVO> upVOs = new ArrayList<UserProjectVO>();
			List<Project> projects = null;
			projects = projectService.findByUser(user);
			// 分页
			PageUtil pageUtil = new PageUtil(projects.size());
			pageUtil.setCurrentPageBeginNo((pageUtil.currentPageNo - 1)
					* pageUtil.getEachPageMaxSize()); // 设置起始行
			List<Project> pagingPros = projectService.pagingFindByUser(user, pageUtil);
			int sequence = 1;
			for (Project project : pagingPros) {
				UserProjectVO upVO = new UserProjectVO(user.getId(),
						project.getId(), project.getName(),
						project.getDescribes(), project.getCreateTime());
				upVO.setSequence(sequence++);
				upVOs.add(upVO);
			}
			
			modelAndView.addObject("size", projects.size());
			modelAndView.addObject("pagingPros", pagingPros);
			modelAndView.addObject("upVOs", upVOs);
			modelAndView.addObject("page", pageUtil);
			modelAndView.setViewName("pro/proList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
