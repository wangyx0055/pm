package com.icker.pm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.dao.MilestoneDao;
import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;
import com.icker.pm.service.MilestoneService;

@Service
@Transactional
public class MilestoneServiceImpl implements MilestoneService {

	@Autowired
	private MilestoneDao milestoneDao;
	
	@Override
	public boolean save(Milestone milestone) throws Exception {
		return milestoneDao.save(milestone);
	}

	@Override
	public boolean update(Milestone milestone) throws Exception {
		Milestone m = milestoneDao.findById(milestone.getId());
		m.setPerformer(milestone.getPerformer());
		m.setEndDate(milestone.getEndDate());
		m.setProject(milestone.getProject());
		m.setProgress(milestone.getProgress());
		m.setName(milestone.getName());
		return milestoneDao.update(m);
	}

	@Override
	public boolean remove(Milestone milestone) throws Exception {
		return milestoneDao.remove(milestone);
	}

	@Override
	public boolean remove(String id) throws Exception {
		return milestoneDao.remove(id);
	}

	@Override
	public List<Milestone> findByStatus(Project project, String status) throws Exception {
		return milestoneDao.findByStatus(project, status);
	}

}
