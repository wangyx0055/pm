package com.icker.pm.server;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.dao.TaskDao;
import com.icker.pm.server.email.Mail;
import com.icker.pm.server.email.MailSender;
import com.icker.pm.vo.EmailTimerTaskVO;

/**
 * 每24小时访问一次数据库，查看是否有任务或项目即将过期，邮件通知项目成员。
 * 
 * @author Icker
 * 
 */
@Component
@Transactional
public class TaskTimer {

	@Autowired
	private TaskDao taskDao;

	/**
	 * Scheduled中的数据使用cron表达式用于定时，详情请看onenote
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void job() {

		/*
		 * 当任务过期了，就自动发送邮件 前提是先搜出所有的task表、taskAssign表、user表
		 * 根据任务，找到任务分配表、再通过任务分配表找到所有未过期的子任务所对应的用户， 任务：任务名称、截止时间、完成时间、任务执行者邮箱、昵称
		 * 比对时间是否即将过期，并发送通知邮件 获得当前时间
		 */
		Date currentDate = new Date();
		long currentTime = currentDate.getTime();
		List<EmailTimerTaskVO> vos = null;
		try {
			vos = taskDao.findEmailVO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (EmailTimerTaskVO emailTimerTaskVO : vos) {
			String endDate = emailTimerTaskVO.getEndTime();
			Date date = DateFormatUtil.StringToDate("yyyy/MM/dd hh:mm:ss",
					endDate);
			long endTime = date.getTime();
			long time = endTime - currentTime;
			if (time < 3600 * 24 * 1000) {
				String title = "任务到期通知";
				String content = emailTimerTaskVO.getNick() + "，你好！<br>"
						+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;任务 <b>"
						+ emailTimerTaskVO.getTaskName()
						+ "</b> 还有不到一天就要到期，请加紧步伐！！</p>";
				String to = emailTimerTaskVO.getEmail();
				MailSender sender = new MailSender(new Mail(title, content, to));
				sender.start();
			} else if (time < 0) {
				String title = "任务到期通知";
				String content = emailTimerTaskVO.getNick() + "，你好！<br>"
						+ "<p>&nbsp;&nbsp;&nbsp;&nbsp;任务 <b>"
						+ emailTimerTaskVO.getTaskName()
						+ "</b> 已经过期，如有问题请及时联系项目相关人员！！</p>";
				String to = emailTimerTaskVO.getEmail();
				MailSender sender = new MailSender(new Mail(title, content, to));
				sender.start();
			}
		}
	}
}
