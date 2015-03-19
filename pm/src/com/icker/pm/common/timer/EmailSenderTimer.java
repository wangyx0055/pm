package com.icker.pm.common.timer;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.icker.pm.common.email.Mail;
import com.icker.pm.common.email.MailSender;
import com.icker.pm.dao.TaskDao;
import com.icker.pm.dao.impl.TaskDaoImpl;
import com.icker.pm.vo.EmailTimerTaskVO;

public class EmailSenderTimer extends TimerTask{

	@Override
	public void run() {
		//当任务过期了，就自动发送邮件
		//前提是先搜出所有的task表、taskAssign表、user表
		//根据任务，找到任务分配表、再通过任务分配表找到所有未过期的子任务所对应的用户，
		//任务：任务名称、截止时间、完成时间、任务执行者邮箱、昵称
		//比对时间是否即将过期，并发送通知邮件
		//获得当前时间
		Date currentDate = new Date();
		long currentTime = currentDate.getTime();
		TaskDao tasksDao = new TaskDaoImpl();
		List<EmailTimerTaskVO> vos = null;
		try {
			vos = tasksDao.findEmailVO();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (EmailTimerTaskVO emailTimerTaskVO : vos) {
			long endTime = emailTimerTaskVO.getEndTime().getTime();
			long time = endTime - currentTime;
			if (time < 3600*24*1000) {
				MailSender.sendHtmlMail(new Mail("任务到期通知", emailTimerTaskVO.getNick()+
						"，你好！<br>"+"<p>&nbsp;&nbsp;&nbsp;&nbsp;任务 <b>"+emailTimerTaskVO.getTaskName()+
						"</b> 还有不到一天就要到期，请加紧步伐！！</p>", emailTimerTaskVO.getEmail()));
			} else if(time < 0) {
				MailSender.sendHtmlMail(new Mail("任务到期通知", emailTimerTaskVO.getNick()+
						"，你好！<br>"+"<p>&nbsp;&nbsp;&nbsp;&nbsp;任务 <b>"+emailTimerTaskVO.getTaskName()+
						"</b> 已经过期，如有问题请及时联系项目相关人员！！</p>", emailTimerTaskVO.getEmail()));
			}
		}
	}
}
