package com.icker.pm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.common.util.PageUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;
import com.icker.pm.vo.UserProjectVO;

@Controller
@RequestMapping("/projectController")
@SessionAttributes(value = { "user" })
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	/**
	 * 显示所有项目
	 * @param modelMap
	 * @param modelAndView
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/projectList")
	public ModelAndView projectList(ModelMap modelMap, ModelAndView modelAndView) {
		User user = (User) modelMap.get("user");
		modelAndView = getProjects(modelAndView, user);
		modelAndView = getIsDoingPros(modelAndView, user);
		modelAndView = getHaveDonePros(modelAndView, user);
		return modelAndView;
	}

	/**
	 * 分页查询所有项目的私有方法
	 * @param modelAndView
	 * @param user
	 * @return
	 */
	private ModelAndView getProjects(ModelAndView modelAndView, User user) {
		// 查询用户所有的项目
		try {
			List<UserProjectVO> upVOs = new ArrayList<UserProjectVO>();
			List<Project> projects = projectService.findByUser(user);
			// 分页
			PageUtil pageUtil = new PageUtil(projects.size());
			pageUtil.setCurrentPageBeginNo((pageUtil.currentPageNo - 1)
					* pageUtil.getEachPageMaxSize()); // 设置起始行
			List<Project> pagingPros = projectService.pagingFindByUser(user,
					pageUtil);
			int sequence = 1;
			for (Project project : pagingPros) {
				UserProjectVO upVO = new UserProjectVO(user.getId(),
						project.getId(), project.getName(),
						project.getDescription(), project.getCreateTime());
				upVO.setSequence(sequence++);
				upVOs.add(upVO);
			}

			modelAndView.addObject("size", projects.size());
			/*modelAndView.addObject("pagingPros", pagingPros);*/
			modelAndView.addObject("upVOs", upVOs);
			modelAndView.addObject("page", pageUtil);
			modelAndView.setViewName("pro/proList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	private ModelAndView getHaveDonePros(ModelAndView modelAndView, User user) {
		try {
			List<UserProjectVO> haveDonePros = new ArrayList<UserProjectVO>();
			List<Project> haveDone = projectService.findByUser(user, Constant.HAVING_DONE);
			// 分页
			PageUtil pageUtil = new PageUtil(haveDone.size());
			pageUtil.setCurrentPageBeginNo((pageUtil.currentPageNo - 1)* pageUtil.getEachPageMaxSize()); // 设置起始行
			List<Project> pagingPros = projectService.pagingFindByUser(user,pageUtil,Constant.HAVING_DONE);
			int sequence = 1;
			for (Project project : pagingPros) {
				UserProjectVO upVO = new UserProjectVO(user.getId(),project.getId(), project.getName(),project.getDescription(), project.getCreateTime());
				upVO.setSequence(sequence++);
				haveDonePros.add(upVO);
			}
			
			modelAndView.addObject("countHaveDone", haveDone.size());
			modelAndView.addObject("haveDonePros", haveDonePros);
			modelAndView.addObject("haveDonePage", pageUtil);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	private ModelAndView getIsDoingPros(ModelAndView modelAndView, User user) {
		try {
			List<UserProjectVO> isDoingPros = new ArrayList<UserProjectVO>();
			List<Project> isDoing = projectService.findByUser(user, Constant.IS_DOING);
			// 分页
			PageUtil pageUtil = new PageUtil(isDoing.size());
			pageUtil.setCurrentPageBeginNo((pageUtil.currentPageNo - 1)* pageUtil.getEachPageMaxSize()); // 设置起始行
			List<Project> pagingPros = projectService.pagingFindByUser(user,pageUtil,Constant.IS_DOING);
			int sequence = 1;
			for (Project project : pagingPros) {
				UserProjectVO upVO = new UserProjectVO(user.getId(),project.getId(), project.getName(),project.getDescription(), project.getCreateTime());
				upVO.setSequence(sequence++);
				isDoingPros.add(upVO);
			}
						
			modelAndView.addObject("isDoingPros", isDoingPros);
			modelAndView.addObject("isDoingPage", pageUtil);
			modelAndView.addObject("countIsDoing", isDoing.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 添加项目
	 * @param proUserEmails
	 * @param proUserNicks
	 * @param proUserPasswords
	 * @param proName
	 * @param proDesc
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addProject")
	public Map<String, Object> addProject(String[] proUserEmails,
			String[] proUserNicks, String[] proUserPasswords, String proName,
			String proDesc, ModelAndView modelAndView, ModelMap modelMap) {
		String date = DateFormatUtil.DateToString(new Date());
		Map<String, Object> result = new HashMap<String, Object>();
		User creator = (User) modelMap.get("user");
		Project project = new Project(proName, proDesc, date, Constant.IS_DOING);
		List<User> members = new ArrayList<User>();
		for (int i = 0; i < proUserEmails.length; i++)
			members.add(new User(proUserEmails[i], proUserPasswords[i],
					proUserNicks[i], date, Constant.STATUS_IS_USABLE, null,
					null));
		try {
			result.put("success",
					projectService.addProject(creator, project, members));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 删除项目
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteProject")
	public Map<String, Object> deleteProject(Project project,
			ModelAndView modelAndView, ModelMap modelMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("success", projectService.removeProject(project));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 编辑项目之前返回有用信息
	 * @param project
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/projectMembers")
	public Map<String, Object> projectMembers(Project project, ModelMap modelMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User creator = (User) modelMap.get("user");
			List<Map<String, Object>> maps = projectService
					.findUsersByProject(project, creator);
			result.put("members", maps);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 编辑项目
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editProject")
	public Map<String, Object> editProject(Project project,
			ModelAndView modelAndView, ModelMap modelMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Project oldPro = projectService.findById(project);
			if(oldPro.getName().equals(project.getName()) && oldPro.getDescription().equals(project.getDescription())) {
				map.put("success", false);
				map.put("errMsg", "未做任何修改！");
			} else {
				oldPro.setName(project.getName());
				oldPro.setDescription(project.getDescription());
				map.put("success", projectService.updateProject(oldPro));
				map.put("msg", "修改成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
