package com.icker.pm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.User;
import com.icker.pm.service.MilestoneService;
import com.icker.pm.service.ProjectService;
import com.icker.pm.service.UserService;
import com.icker.pm.vo.MilestoneVO;

@Controller
@RequestMapping("/milestoneController")
@SessionAttributes(value={"user"})
public class MilestoneController {
	
	@Autowired
	private ProjectService projectService;
	@Autowired
	private MilestoneService milestoneService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/milestone")
	public ModelAndView milestone(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			modelAndView.addObject("project", p);
			modelAndView.setViewName("milestone/milestone");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/saveMilestone")
	public ModelAndView saveMilestone(MilestoneVO vo, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			User creator = (User) modelMap.get("user");
			Project project = projectService.findProject(vo.getProjectId());
			User performer = userService.findUserById(vo.getPerformer());
			Milestone milestone = new Milestone();
			milestone.setCreator(creator);
			milestone.setEndDate(vo.getEndDate());
			milestone.setName(vo.getName());
			milestone.setPerformer(performer);
			milestone.setProject(project);
			milestone.setStatus(Constant.MILE_UNFINISHED);
			milestone.setProgress(0D);
			milestoneService.save(milestone);
			
			List<Milestone> list = milestoneService.findByStatus(project, Constant.MILE_UNFINISHED);
			List<MilestoneVO> milestones = getMilestoneVOs(list);
			modelAndView.addObject("milestones", milestones);
			modelAndView.setViewName("milestone/milestones");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 查询已经延期的里程碑
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/extension")
	public ModelAndView extension(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<Milestone> list = milestoneService.findByStatus(project, Constant.MILE_EXTENSION);
			List<MilestoneVO> milestones = getMilestoneVOs(list);
			modelAndView.addObject("milestones", milestones);
			modelAndView.setViewName("milestone/milestones");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 查询已经完成的里程碑
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/completed")
	public ModelAndView completed(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<Milestone> list = milestoneService.findByStatus(project, Constant.MILE_COMPLETED);
			List<MilestoneVO> milestones = getMilestoneVOs(list);
			modelAndView.addObject("milestones", milestones);
			modelAndView.setViewName("milestone/milestones");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 查询还未完成的里程碑
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unfinished")
	public ModelAndView unfinished(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<Milestone> list = milestoneService.findByStatus(project, Constant.MILE_UNFINISHED);
			List<MilestoneVO> milestones = getMilestoneVOs(list);
			modelAndView.addObject("milestones", milestones);
			modelAndView.setViewName("milestone/milestones");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	private List<MilestoneVO> getMilestoneVOs(List<Milestone> list) {
		List<MilestoneVO> milestones = new ArrayList<MilestoneVO>();
		int sequence = 1;
		for (Milestone milestone : list) {
			MilestoneVO vo = new MilestoneVO();
			vo.setCreator(milestone.getCreator().getId());
			vo.setEndDate(milestone.getEndDate());
			vo.setFinishDate(milestone.getFinishDate());
			vo.setId(milestone.getId());
			vo.setName(milestone.getName());
			vo.setPerformer(milestone.getPerformer().getName());
			vo.setPerformerId(milestone.getPerformer().getId());
			vo.setProgress(milestone.getProgress());
			vo.setProjectId(milestone.getProject().getId());
			vo.setProjectName(milestone.getProject().getName());
			vo.setStatus(milestone.getStatus());
			vo.setSequence(sequence++);
			milestones.add(vo);
		}
		return milestones;
	}
	
	/**
	 * 删除里程碑
	 * @param milestone
	 * @param projectId
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/remove")
	public ModelAndView remove(Milestone milestone, String projectId, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project project = projectService.findProject(projectId);
			milestoneService.remove(milestone);
			List<Milestone> list = milestoneService.findByStatus(project, milestone.getStatus());
			List<MilestoneVO> milestones = getMilestoneVOs(list);
			modelAndView.addObject("milestones", milestones);
			modelAndView.addObject("status", milestones.get(0).getStatus());
			modelAndView.setViewName("milestone/milestones");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 更新里程碑
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editMilestone")
	public ModelAndView editMilestone(MilestoneVO vo, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project project = projectService.findProject(vo.getProjectId());
			User performer = userService.findUserById(vo.getPerformerId());
			Milestone milestone = new Milestone();
			milestone.setId(vo.getId());
			milestone.setEndDate(vo.getEndDate());
			milestone.setName(vo.getName());
			milestone.setProgress(vo.getProgress());
			milestone.setProject(project);
			milestone.setPerformer(performer);
			milestone.setStatus(vo.getStatus());
			milestoneService.update(milestone);
			
			List<Milestone> list = milestoneService.findByStatus(project, milestone.getStatus());
			List<MilestoneVO> milestones = getMilestoneVOs(list);
			modelAndView.addObject("milestones", milestones);
			modelAndView.setViewName("milestone/milestones");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
