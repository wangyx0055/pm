package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;

public interface MilestoneService {
	public boolean save(Milestone milestone) throws Exception;
	public boolean update(Milestone milestone) throws Exception;
	public boolean remove(Milestone milestone) throws Exception;
	public boolean remove(String id) throws Exception;
	public List<Milestone> findByStatus(Project project, String status) throws Exception;
	
}
