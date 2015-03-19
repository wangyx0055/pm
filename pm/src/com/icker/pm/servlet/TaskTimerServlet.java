package com.icker.pm.servlet;

import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.icker.pm.common.timer.EmailSenderTimer;

public class TaskTimerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8538454969500139884L;
	
	public TaskTimerServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		//设置定时器，每1天执行一次定时器
		System.out.println("设置定时器，每1天执行一次定时器");
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new EmailSenderTimer(), new Date(), 24*3600*1000);
	}
}
