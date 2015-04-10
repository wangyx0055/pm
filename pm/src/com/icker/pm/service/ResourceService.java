package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Resource;
import com.icker.pm.vo.ResourceVO;

public interface ResourceService {
	public boolean save(Resource resource) throws Exception;

	public List<Resource> findAll(Project project) throws Exception;

	public boolean delete(ResourceVO vo) throws Exception;

	public List<Resource> findByType(Resource resource) throws Exception;
}
