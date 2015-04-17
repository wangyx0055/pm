package com.icker.pm.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.dao.ResourceDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Resource;
import com.icker.pm.service.ResourceService;
import com.icker.pm.vo.ResourceVO;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceDao resourceDao;

	@Override
	public boolean save(Resource resource) throws Exception {
		return resourceDao.saveResource(resource);
	}

	@Override
	public List<Resource> findAll(Project project) throws Exception {
		return resourceDao.findAll(project);
	}

	@Override
	public boolean delete(ResourceVO vo) throws Exception {
		Resource resource = resourceDao.findById(vo.getId());
		resourceDao.deleteResourceById(resource);
		File file = new File(vo.getPath());
		if (file.isFile() && file.exists())
			file.delete();
		else
			return false;
		return true;
	}

	@Override
	public List<Resource> findByType(Resource resource) throws Exception {
		return resourceDao.findByType(resource);
	}

	@Override
	public List<Resource> findByName(String projectId, String name)
			throws Exception {
		return resourceDao.findByName(projectId, name);
	}

}
