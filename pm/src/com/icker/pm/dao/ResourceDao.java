package com.icker.pm.dao;

import java.util.List;

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
	 * 根据项目id查找相关资源
	 * @param proId
	 * @return
	 */
//	public List<Resource> findResourceByProject(String projectId) throws Exception;
	
	/**
	 * 根据id查找文件
	 * @param resource
	 * @return
	 */
	public Resource findResourceById(Resource resource) throws Exception;
}
