package com.icker.pm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.dao.MilestoneDao;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.User;
import com.icker.pm.server.email.Mail;
import com.icker.pm.server.email.MailSender;
import com.icker.pm.service.MilestoneService;
import com.icker.pm.vo.MilestoneVO;

@Service
@Transactional
public class MilestoneServiceImpl implements MilestoneService {

	@Autowired
	private MilestoneDao milestoneDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UserDao userDao;

	@Override
	public boolean save(Milestone milestone, String sendEmail) throws Exception {
		if(Constant.SEND_MAIL_YES.equals(sendEmail)) {
			String title = "里程碑分配通知";
			String content = milestone.getPerformer().getName()
					+ "，你好！<br>"
					+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;您被： <b>"
					+ milestone.getCreator().getName()
					+ "</b> 分配负责了一个新的里程碑事件："+milestone.getName()
					+"，请核实。</p>";
			String to = milestone.getPerformer().getEmail();
			MailSender sender = new MailSender(new Mail(title,content,to));
			sender.start();
		}
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

	@Override
	public void update(MilestoneVO vo) throws Exception {
		Milestone milestone = milestoneDao.findById(vo.getId());
		if(Constant.SEND_MAIL_YES.equals(vo.getSendEmail())) {
			String title = "里程碑修改通知";
			String content = milestone.getPerformer().getName()
							+ "，你好！<br>"
							+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;您当前负责的里程碑： <b>"
							+ milestone.getName()
							+ "</b> 已经有所修改，或许负责人不再是您，请核实。</p>";
			String to = milestone.getPerformer().getEmail();
			MailSender mailSender = new MailSender(new Mail(title,content,to));
			mailSender.start();
			if(!vo.getPerformerId().equals(milestone.getPerformer().getId())) {
				String newContent = milestone.getPerformer().getName()
						+ "，你好！<br>"
						+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;您被： <b>"
						+ milestone.getCreator().getName()
						+ "</b> 分配了一项新的里程碑："+vo.getName()
						+",请核实。</p>";
				User user = userDao.findUserById(vo.getPerformerId());
				MailSender sender = new MailSender(new Mail(title,newContent,user.getEmail()));
				sender.start();
			}
		}
		
		milestone.setEndDate(vo.getEndDate());
		milestone.setName(vo.getName());
		milestone.setProgress(vo.getProgress());
		milestone.setProject(projectDao.findById(vo.getProjectId()));
		milestone.setPerformer(userDao.findUserById(vo.getPerformerId()));
		milestone.setStatus(vo.getStatus());
		milestone.setDescription(vo.getDescription());
		milestoneDao.update(milestone);
	}

}
