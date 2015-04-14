package com.icker.pm.vo;

import com.icker.pm.pojo.User;


@SuppressWarnings("serial")
public class UserVO extends User{
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
