package com.icker.pm.vo;

/**
 * 任务：任务名称、截止时间、任务执行者邮箱、昵称
 * @author Icker
 *
 */
public class EmailTimerTaskVO {
	private String taskName;
	private String endTime;
	private String email;
	private String nick;
	
	public EmailTimerTaskVO() {
		super();
	}
	public EmailTimerTaskVO(String taskName, String endTime,
			String email, String nick) {
		super();
		this.taskName = taskName;
		this.endTime = endTime;
		this.email = email;
		this.nick = nick;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
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
