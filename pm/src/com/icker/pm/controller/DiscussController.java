package com.icker.pm.controller;

import java.util.ArrayList;
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

import com.icker.pm.common.enumerate.DiscussType;
import com.icker.pm.pojo.Discuss;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.User;
import com.icker.pm.service.DiscussService;
import com.icker.pm.service.ProjectService;
import com.icker.pm.vo.DiscussVO;

@Controller
@RequestMapping("/discussController")
@SessionAttributes(value={"user"})
public class DiscussController {
	
	@Autowired
	private DiscussService discussService;
	@Autowired
	private ProjectService projectService;
	
	/**
	 * 查找所有写字板
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/findDiscuss")
	public ModelAndView findDiscuss(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			List<DiscussVO> discusses = discussService.findAllVOs(project);
			modelAndView.addObject("project", p);
			modelAndView.addObject("discusses", discusses);
			modelAndView.setViewName("discuss/discusses");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 查找所有类型的写字板类型
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/discussTypes")
	public List<Map<String, Object>> discussTypes(ModelAndView modelAndView, ModelMap modelMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < 5; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", String.valueOf(i));
			map.put("typeName", DiscussType.getTypeName(String.valueOf(i)));
			list.add(map);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("discusses", result);
		return list;
	}
	
	/**
	 * 新增写字板
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addDiscuss")
	public ModelAndView addDiscuss(DiscussVO vo, ModelAndView modelAndView, ModelMap modelMap) {
		User user = (User) modelMap.get("user");
		vo.setAuthorId(user.getId());
		try {
			discussService.save(vo);
			Project project = new Project();
			project.setId(vo.getProjectId());
			List<DiscussVO> discusses = discussService.findAllVOs(project);
			modelAndView.addObject("discusses", discusses);
			modelAndView.setViewName("discuss/discussList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 根据写字板类型查找
	 * @param type
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/discusses")
	public ModelAndView discusses(String type, Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<DiscussVO> discusses = null;
			if(StringUtils.isBlank(type))
				discusses = discussService.findAllVOs(project);
			else
				discusses = discussService.findByType(project, type);
			modelAndView.addObject("discusses", discusses);
			modelAndView.setViewName("discuss/discussList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 更新写字板
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editDiscuss")
	public ModelAndView editDiscuss(DiscussVO vo, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Discuss discuss = discussService.findById(vo.getId());
			vo.setAuthorId(discuss.getAuthor().getId());
			vo.setProjectId(discuss.getProject().getId());
			vo.setCreateTime(discuss.getCreateTime());
			discussService.update(vo);
			List<DiscussVO> discusses = discussService.findAllVOs(discuss.getProject());
			modelAndView.addObject("discusses", discusses);
			modelAndView.setViewName("discuss/discussList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 删除写字板
	 * @param discuss
	 * @param projectId
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteDiscuss")
	public ModelAndView deleteDiscuss(Discuss discuss, String projectId, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Discuss dis = discussService.findById(discuss);
			Project project = new Project();
			project.setId(projectId);
			Project p = projectService.findById(project);
			discussService.remove(dis);
			List<DiscussVO> discusses = discussService.findAllVOs(p);
			modelAndView.addObject("discusses", discusses);
			modelAndView.setViewName("discuss/discussList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
}
