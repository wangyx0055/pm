package com.icker.pm.server.email;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	public static boolean sendHtmlMail(Mail mail) {
		//身份认证，创建一个密码验证器 
		Authenticator authenticator = new MyAuthenticator(mail.getUsername(), mail.getPassword());
		//根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session session = Session.getDefaultInstance(mail.getProperties(), authenticator);
		//根据session创建一个邮件消息 
		Message message = new MimeMessage(session);
		
		try {
			//设置发送人地址
			Address fromAddress = new InternetAddress(mail.getFromAddress());
			message.setFrom(fromAddress);
			
			//设置接收者地址
			Address toAddress = new InternetAddress(mail.getToAddress());
			message.addRecipient(RecipientType.TO, toAddress);
			
			//设置主题
			message.setSubject(mail.getSubject());
			//设置内容
			Multipart multipart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mail.getContent(), "text/html; charset=utf-8");    
			multipart.addBodyPart(html); 
            message.setContent(multipart);
            
            //发送
            Transport.send(message); 
            System.out.println("发送成功");
            return true;
		} catch (AddressException e) {
			System.err.println("无此地址，或地址错误");
			e.printStackTrace();
		} catch (MessagingException e) {
			System.err.println("设置失败");
			e.printStackTrace();
		}
		System.out.println("发送失败");
		return false;
	}
	
	public static boolean sendTextMail(Mail mail) {
		return false;
		
	}
}
