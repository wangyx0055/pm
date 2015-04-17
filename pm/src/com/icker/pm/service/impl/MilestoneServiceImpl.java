package com.icker.pm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.DateFormatUtil;
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
		m.setDescription(milestone.getDescription());
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
	public List<Milestone> findByStatus(Project project, String status)
			throws Exception {
		return milestoneDao.findByStatus(project, status);
	}

	@Override
	public List<Milestone> findByName(Project p, String name, String status)
			throws Exception {
		return milestoneDao.findByName(p, name, status);
	}

	@Override
	public List<Milestone> findByUser(Project p, String performer, String status)
			throws Exception {
		return milestoneDao.findByUser(p, performer, status);
	}

	@Override
	public void complete(Milestone milestone) throws Exception {
		Milestone m = milestoneDao.findById(milestone.getId());
		m.setStatus(Constant.MILE_COMPLETED);
		m.setFinishDate(DateFormatUtil.DateToString(new Date()));
		milestoneDao.update(m);
	}

	@Override
	public void release(Milestone milestone) throws Exception {
		Milestone m = milestoneDao.findById(milestone.getId());
		m.setStatus(Constant.MILE_UNFINISHED);
		m.setFinishDate(DateFormatUtil.DateToString(new Date()));
		milestoneDao.update(m);
	}

	@Override
	public List<Milestone> findAll(Project project) throws Exception {
		return milestoneDao.findAll(project);
	}

	@Override
	public Milestone findById(String id) throws Exception {
		return milestoneDao.findById(id);
	}

}
