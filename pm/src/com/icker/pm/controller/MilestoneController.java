package com.icker.pm.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.User;
import com.icker.pm.service.MilestoneService;
import com.icker.pm.service.ProjectService;
import com.icker.pm.service.TaskService;
import com.icker.pm.service.UserService;
import com.icker.pm.vo.MilestoneVO;

@Controller
@RequestMapping("/milestoneController")
@SessionAttributes(value = { "user" })
public class MilestoneController extends ExceptionController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private MilestoneService milestoneService;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@RequestMapping("/milestone")
	public ModelAndView milestone(Project project, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			modelAndView.addObject("project", p);
			modelAndView.setViewName("milestone/milestone");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 新增里程碑
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveMilestone")
	public ModelAndView saveMilestone(MilestoneVO vo,
			ModelAndView modelAndView, ModelMap modelMap) {
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
			milestone.setDescription(vo.getDescription());
			milestone.setStatus(Constant.MILE_UNFINISHED);
			milestone.setProgress(0D);
			milestoneService.save(milestone);

			List<Milestone> unfinished = milestoneService.findByStatus(project, Constant.MILE_UNFINISHED);
			List<Milestone> finished = milestoneService.findByStatus(project, Constant.MILE_COMPLETED);
			List<Milestone> extension = milestoneService.findByStatus(project, Constant.MILE_EXTENSION);
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 查询已经延期的里程碑
	 * 
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/extension")
	public ModelAndView extension(Project project, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			List<Milestone> list = milestoneService.findByStatus(project,
					Constant.MILE_EXTENSION);
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
	 * 
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/completed")
	public ModelAndView completed(Project project, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			List<Milestone> list = milestoneService.findByStatus(project,
					Constant.MILE_COMPLETED);
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
	 * 
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/unfinished")
	public ModelAndView unfinished(Project project, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			List<Milestone> list = milestoneService.findByStatus(project,
					Constant.MILE_UNFINISHED);
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
			vo.setDescription(milestone.getDescription());
			milestones.add(vo);
		}
		return milestones;
	}

	/**
	 * 删除里程碑
	 * 
	 * @param milestone
	 * @param projectId
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/remove")
	public ModelAndView remove(Milestone milestone, String projectId,
			ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project project = projectService.findProject(projectId);
			milestoneService.remove(milestone);
			
			List<Milestone> unfinished = milestoneService.findByStatus(project, Constant.MILE_UNFINISHED);
			List<Milestone> finished = milestoneService.findByStatus(project, Constant.MILE_COMPLETED);
			List<Milestone> extension = milestoneService.findByStatus(project, Constant.MILE_EXTENSION);
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 更新里程碑
	 * 
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editMilestone")
	public ModelAndView editMilestone(MilestoneVO vo,
			ModelAndView modelAndView, ModelMap modelMap) {
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
			milestone.setDescription(vo.getDescription());
			milestoneService.update(milestone);

			List<Milestone> unfinished = milestoneService.findByStatus(project, Constant.MILE_UNFINISHED);
			List<Milestone> finished = milestoneService.findByStatus(project, Constant.MILE_COMPLETED);
			List<Milestone> extension = milestoneService.findByStatus(project, Constant.MILE_EXTENSION);
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 查询所有里程碑
	 * @param p
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findAll")
	public ModelAndView findAll(Project p, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<Milestone> unfinished = milestoneService.findByStatus(p, Constant.MILE_UNFINISHED);
			List<Milestone> finished = milestoneService.findByStatus(p, Constant.MILE_COMPLETED);
			List<Milestone> extension = milestoneService.findByStatus(p, Constant.MILE_EXTENSION);
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 根据里程碑名称查找
	 * @param p
	 * @param name
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByName")
	public ModelAndView findByName(Project p, String name, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<Milestone> unfinished = milestoneService.findByName(p, name, Constant.MILE_UNFINISHED);
			List<Milestone> finished = milestoneService.findByName(p, name, Constant.MILE_COMPLETED);
			List<Milestone> extension = milestoneService.findByName(p, name, Constant.MILE_EXTENSION);
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 根据用户查询里程碑
	 * @param p
	 * @param performer
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findByUser")
	public ModelAndView findByUser(Project p, String performer, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<Milestone> unfinished = null;
			List<Milestone> finished = null;
			List<Milestone> extension = null;
			if(StringUtils.isBlank(performer)) {
				unfinished = milestoneService.findByStatus(p, Constant.MILE_UNFINISHED);
				finished = milestoneService.findByStatus(p, Constant.MILE_COMPLETED);
				extension = milestoneService.findByStatus(p, Constant.MILE_EXTENSION);
			} else {
				unfinished = milestoneService.findByUser(p, performer, Constant.MILE_UNFINISHED);
				finished = milestoneService.findByUser(p, performer, Constant.MILE_COMPLETED);
				extension = milestoneService.findByUser(p, performer, Constant.MILE_EXTENSION);
			}
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 完成里程碑
	 * @param proId
	 * @param milestone
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/completeMilestone")
	public ModelAndView completeMilestone(String proId, Milestone milestone, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			milestoneService.complete(milestone);
			Project p = new Project();
			p.setId(proId);
			List<Milestone> unfinished = null;
			List<Milestone> finished = null;
			List<Milestone> extension = null;
			unfinished = milestoneService.findByStatus(p, Constant.MILE_UNFINISHED);
			finished = milestoneService.findByStatus(p, Constant.MILE_COMPLETED);
			extension = milestoneService.findByStatus(p, Constant.MILE_EXTENSION);
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
		
	}
	
	/**
	 * 没有完成里程碑
	 * @param proId
	 * @param milestone
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/releaseMilestone")
	public ModelAndView releaseMilestone(String proId, Milestone milestone, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			milestoneService.release(milestone);
			Project p = new Project();
			p.setId(proId);
			List<Milestone> unfinished = null;
			List<Milestone> finished = null;
			List<Milestone> extension = null;
			unfinished = milestoneService.findByStatus(p, Constant.MILE_UNFINISHED);
			finished = milestoneService.findByStatus(p, Constant.MILE_COMPLETED);
			extension = milestoneService.findByStatus(p, Constant.MILE_EXTENSION);
			List<MilestoneVO> unfinishedVos = this.getMilestoneVOs(unfinished);
			List<MilestoneVO> finishedVos = this.getMilestoneVOs(finished);
			List<MilestoneVO> extesionVos = this.getMilestoneVOs(extension);
			modelAndView.addObject("unfinished", unfinishedVos);
			modelAndView.addObject("finished", finishedVos);
			modelAndView.addObject("extension", extesionVos);
			modelAndView.setViewName("milestone/unfinished");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
		
	}
	
	/**
	 * 日历事件
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/calendarEvents")
	public Map<String, Object> calendarEvents(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", 1);
		List<Map<String, Object>> results = new ArrayList<Map<String,Object>>();
		try {
			List<Milestone> milestones = milestoneService.findAll(project);
			for (Milestone milestone : milestones) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("id", milestone.getId());
				result.put("title", milestone.getName());
				result.put("url", "milestoneController/event?id="+milestone.getId());
				result.put("class", "event-special");
				Date date = DateFormatUtil.StringToDate("yyyy/MM/dd hh:mm:ss", milestone.getEndDate());
				result.put("start", date.getTime()+10000);
				result.put("end", date.getTime());
				results.add(result);
			}
			List<Task> tasks = taskService.findAll(project);
			for (Task task : tasks) {
				Map<String, Object> result = new HashMap<String, Object>();
				result.put("id", task.getId());
				result.put("title", task.getName());
				result.put("url", "taskController/event?id="+task.getId());
				if(task.getStatus().equals(Constant.TASK_STATUS_EXTENSION))
					result.put("class", "event-important");
				if(task.getStatus().equals(Constant.TASK_STATUS_COMPLETED))
					result.put("class", "event-success");
				if(task.getStatus().equals(Constant.TASK_STATUS_UNFINISHED))
					result.put("class", "event-info");
				Date startDate = DateFormatUtil.StringToDate("yyyy/MM/dd hh:mm:ss", task.getStartDate());
				Date endDate = DateFormatUtil.StringToDate("yyyy/MM/dd hh:mm:ss", task.getEndDate());
				result.put("start", startDate.getTime());
				result.put("end", endDate.getTime());
				results.add(result);
			}
			map.put("result", results);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 日历：里程碑事件
	 * @param milestone
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/event")
	public ModelAndView event(Milestone milestone, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Milestone m = milestoneService.findById(milestone.getId());
			modelAndView.addObject("milestone", m);
			modelAndView.setViewName("milestone/event");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
}
