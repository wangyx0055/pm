package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Resource;

public interface ResourceDao {
	/**
	 * 添加或修改资源
	 * @param resource
	 * @return
	 */
	public boolean saveResource(Resource resource) throws Exception;
	
	/**
	 * 根据id删除文件
	 * @param resource
	 * @return
	 */
	public void deleteResourceById(Resource resource) throws Exception;
	
	/**
	 * 根据id查找文件
	 * @param resource
	 * @return
	 */
	public Resource findResourceById(Resource resource) throws Exception;

	public List<Resource> findAll(Project project) throws Exception;

	public Resource findById(String id) throws Exception;

	public List<Resource> findByType(Resource resource) throws Exception;
}
