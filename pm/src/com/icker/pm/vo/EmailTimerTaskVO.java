package com.icker.pm.vo;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 任务：任务名称、截止时间、任务执行者邮箱、昵称
 * @author Icker
 *
 */
public class EmailTimerTaskVO {
	private String taskName;
	private Timestamp endTime;
	private String email;
	private String nick;
	
	public EmailTimerTaskVO() {
		super();
	}
	public EmailTimerTaskVO(String taskName, Date endTime,
			String email, String nick) {
		super();
		this.taskName = taskName;
		this.endTime = new Timestamp(endTime.getTime());
		this.email = email;
		this.nick = nick;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
}
