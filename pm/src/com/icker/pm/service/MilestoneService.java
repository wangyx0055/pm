package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;
import com.icker.pm.vo.MilestoneVO;

public interface MilestoneService {
	public boolean save(Milestone milestone, String sendEmail) throws Exception;
	public boolean update(Milestone milestone) throws Exception;
	public boolean remove(Milestone milestone) throws Exception;
	public boolean remove(String id) throws Exception;
	public List<Milestone> findByStatus(Project project, String status) throws Exception;
	public List<Milestone> findByName(Project p, String name, String status) throws Exception;
	public List<Milestone> findByUser(Project p, String performer, String status) throws Exception;
	public void complete(Milestone milestone) throws Exception;
	public void release(Milestone milestone) throws Exception;
	public List<Milestone> findAll(Project project) throws Exception;
	public Milestone findById(String id) throws Exception;
	public void update(MilestoneVO vo) throws Exception;
	
}
