package com.icker.pm.vo;

import com.icker.pm.pojo.User;


public class UserVO extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5655569863836237452L;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
