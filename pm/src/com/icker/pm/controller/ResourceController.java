package com.icker.pm.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.common.util.FileUploadUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.service.ProjectService;

@Controller
@RequestMapping("/resourceController")
@SessionAttributes(value = { "user" })
public class ResourceController {

	@Autowired
	private ProjectService projectService;

	/**
	 * 文件资源首页
	 * 
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/resources")
	public ModelAndView resources(Project project, ModelAndView modelAndView,
			ModelMap modelMap) {
		try {
			Project p = projectService.findById(project);
			modelAndView.setViewName("file/file");
			modelAndView.addObject("project", p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	/**
	 * 批量上传文件
	 * @param resources
	 * @param modelAndView
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/batchUpload")
	public Map<String, Object> batchUpload(@RequestParam MultipartFile[] resources, ModelAndView modelAndView,
			ModelMap modelMap, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			FileUploadUtil.multifileUpload(resources, request);
			result.put("success", true);
			result.put("info", "上传成功！！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
