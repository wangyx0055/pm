package com.icker.pm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icker.pm.common.enumerate.ResourceType;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.common.util.FileUploadUtil;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Resource;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;
import com.icker.pm.service.ResourceService;
import com.icker.pm.vo.ResourceVO;

@Controller
@RequestMapping("/resourceController")
@SessionAttributes(value = { "user" })
public class ResourceController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 文件资源首页
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
	public Map<String, Object> batchUpload(@RequestParam MultipartFile[] resources, String id,
			String type, HttpServletRequest request, ModelMap modelMap) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User uploader = (User) modelMap.get("user");
			Project p = new Project();
			p.setId(id);
			Project project = projectService.findById(p);
			if(StringUtils.isBlank(type)) 
				type = ResourceType.getType("其他");
			List<Map<String, String>> maps = FileUploadUtil.multifileUpload(resources, request, null, true);
			for (Map<String, String> map : maps) {
				Resource resource = new Resource();
				resource.setName(map.get("fileName"));
				resource.setPath(map.get("path"));
				resource.setFormat(map.get("format"));
				resource.setSize(Double.valueOf(map.get("size")));
				resource.setUpTime(DateFormatUtil.DateToString(new Date()));
				resource.setType(type);
				resource.setUploader(uploader);
				resource.setProject(project);
				resourceService.save(resource);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/changeType")
	public ModelAndView changeType(String type, ModelAndView modelAndView, ModelMap modelMap) {
		modelAndView.addObject("type", type);
		modelAndView.setViewName("file/upload");
		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping("/resourceTypes")
	public List<Map<String, Object>> resourceTypes(ModelAndView modelAndView, ModelMap modelMap) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < ResourceType.values().length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", String.valueOf(i));
			map.put("typeName", ResourceType.getTypeName(String.valueOf(i)));
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 查询项目所有资源文件
	 * @param project
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/allResources")
	public ModelAndView allResources(Project project, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			List<Resource> list = resourceService.findAll(project);
			List<ResourceVO> vos = this.getResourceVOs(list);
			
			modelAndView.addObject("resources", vos);
			modelAndView.setViewName("file/files");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	/**
	 * 文件资源下载
	 * @param path
	 * @param request
	 * @param response
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/download")
	public ModelAndView download(String path, HttpServletRequest request,  
		      HttpServletResponse response, ModelAndView modelAndView){
		String contentType = "application/octet-stream";  
		try {
			FileUploadUtil.download(request, response, path, contentType);
		} catch (Exception e) {
			e.printStackTrace();
		}  
		return null;
	}  
	
	/**
	 * 删除文件资源
	 * @param vo
	 * @param modelAndView
	 * @param modelMap
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteResource")
	public ModelAndView deleteResource(ResourceVO vo, ModelAndView modelAndView, ModelMap modelMap, HttpServletRequest request) {
		try {
//			modelMap.
			String path = File.separator+"WEB-INF"+vo.getPath();
			String filePath = request.getSession().getServletContext().getRealPath(path);
			vo.setPath(filePath);
			Project project = projectService.findProject(vo.getProjectId());
			resourceService.delete(vo);
			List<Resource> resources = resourceService.findAll(project);
			List<ResourceVO> vos = this.getResourceVOs(resources);
			modelAndView.addObject("resources", vos);
			modelAndView.setViewName("file/files");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}

	private List<ResourceVO> getResourceVOs(List<Resource> list) {
		List<ResourceVO> vos = new ArrayList<ResourceVO>();
		for (int i = 0; i < list.size(); i++) {
			ResourceVO vo = new ResourceVO();
			vo.setId(list.get(i).getId());
			vo.setFormat(list.get(i).getFormat());
			vo.setName(list.get(i).getName());
			vo.setPath(list.get(i).getPath());
			vo.setType(list.get(i).getType());
			vo.setTypeName(ResourceType.getTypeName(list.get(i).getType()));
			vo.setProjectId(list.get(i).getProject().getId());
			vo.setUpTime(list.get(i).getUpTime());
			vo.setUploader(list.get(i).getUploader().getName());
			vo.setUploaderId(list.get(i).getUploader().getId());
			vo.setSize(list.get(i).getSize());
			vo.setSequence(i+1);
			vos.add(vo);
		}
		return vos;
	}
	
	@ResponseBody
	@RequestMapping("/findResources")
	public ModelAndView findResources(Resource resource, String projectId, ModelAndView modelAndView, ModelMap modelMap) {
		try {
			Project project = projectService.findProject(projectId);
			resource.setProject(project);
			List<Resource> list = resourceService.findByType(resource);
			List<ResourceVO> resources = this.getResourceVOs(list);
			modelAndView.addObject("resources", resources);
			modelAndView.setViewName("file/files");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

























