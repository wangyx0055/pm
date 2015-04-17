package com.icker.pm.dao;

import java.util.List;

import com.icker.pm.pojo.Discuss;
import com.icker.pm.pojo.Project;

public interface DiscussDao {
	public boolean save(Discuss discuss) throws Exception;
	public boolean update(Discuss discuss) throws Exception;
	public boolean remove(Discuss discuss) throws Exception;
	public Discuss findById(Discuss discuss) throws Exception;
	public Discuss findById(String id) throws Exception;
	public List<Discuss> findByProject(Project project) throws Exception;
	public List<Discuss> findByTitle(String proId, String title) throws Exception;
}
