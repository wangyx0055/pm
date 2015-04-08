package com.icker.pm.common.email;

import java.util.Properties;

import com.icker.pm.common.util.PropertiesUtil;

public class Mail {

	private Properties properties = PropertiesUtil.loadProperties();
	
	//发送邮件的SMTP服务器主机
	private String mailServerHost = "smtp.sina.com";
	//发送邮件的SMTP服务器端口
	private String mailServerPort = "25";
	//发送者的地址
	private String fromAddress = "cgyubg@sina.com";
	//登录邮箱的账号密码
	private String username = "cgyubg@sina.com";
	private String password = "summer1993";
	
	//主题
	private String subject;
	//内容
	private String content;
	//接收方的地址
	private String toAddress;
	
	public Mail() {
		super();
	}
	public Mail(String subject, String content, String toAddress) {
		mailServerHost = properties.getProperty("mail.smtp.host");
		mailServerPort = properties.getProperty("mail.smtp.port");
		properties.put("mail.smtp.host", properties.getProperty("mail.smtp.host"));
		properties.put("mail.smtp.port", properties.getProperty("mail.smtp.port"));
		properties.put("mail.smtp.auth", properties.getProperty("mail.smtp.auth"));
		
		fromAddress = properties.getProperty("mail.username");
		username = properties.getProperty("mail.username");
		password = properties.getProperty("mail.password");
		
		this.subject = subject;
		this.content = content;
		this.toAddress = toAddress;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMailServerHost() {
		return mailServerHost;
	}
	public void setMailServerHost(String mailServerHost) {
		this.mailServerHost = mailServerHost;
	}
	public String getMailServerPort() {
		return mailServerPort;
	}
	public void setMailServerPort(String mailServerPort) {
		this.mailServerPort = mailServerPort;
	}
	public String getFromAddress() {
		return fromAddress;
	}
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToAddress() {
		return toAddress;
	}
	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}
