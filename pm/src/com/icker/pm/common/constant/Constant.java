package com.icker.pm.common.constant;

public class Constant {
	/**
	 * User的status
	 */
	public static String STATUS_IS_NOT_USABLE = "0";
	public static String STATUS_IS_USABLE = "1";

	/**
	 * ProjectMember的role
	 */
	/** 项目经理 */
	public static String ROLE_CREATOR = "0";
	/** 项目成员 */
	public static String ROLE_MEMBER = "1";
	/** 客户 */
	public static String ROLE_CUSTOMER = "2";

	/**
	 * ProjectMember的status
	 */
	/** 审核未通过0 */
	public static String IS_NOT_ACCESS = "0";
	/** 审核通过1 */
	public static String IS_ACCESS = "1";
	/** 被拒绝2 */
	public static String IS_REDUCE = "2";

	/**
	 * Project的status
	 */
	/** 0：在实施 */
	public static String IS_DOING = "0";
	/** 1：已经完成 */
	public static String HAVING_DONE = "1";

	/**
	 * Milestone的status
	 */
	/** 延期 */
	public static String MILE_EXTENSION = "2";
	/** 未完成 */
	public static String MILE_UNFINISHED = "0";
	/** 已完成 */
	public static String MILE_COMPLETED = "1";
	
	/**
	 * 任务的状态
	 */
	/** 未完成 */
	public static String TASK_STATUS_UNFINISHED = "0";
	/** 完成 */
	public static String TASK_STATUS_COMPLETED = "1";
	/** 延期 */
	public static String TASK_STATUS_EXTENSION = "2";
	
	
	/**
	 * 用于日历的事件类型
	 * 0：任务事件
	 * 1：里程碑事件
	 */
	public static String TASK_EVENT = "0";
	/**
	 * 用于日历的事件类型
	 * 0：任务事件
	 * 1：里程碑事件
	 */
	public static String MILE_EVENT = "1";

}
