package com.icker.pm.pojo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Icker
 */
@Entity
@Table(name = "projectmember", catalog = "pm")
public class ProjectMember {
	/** 联合主键 */
	@EmbeddedId
	private ProjectMemberId id;
	/** 权限，0创始人，1普通项目成员，2客户，3超级管理员，4其他 */
	@Column(name = "role", length = 1)
	private String role;
	/** 用于邮箱验证，0邮箱未审核通过，1正常，2被拒绝 */
	@Column(name = "status", length = 1)
	private String status;

	public ProjectMember() {
	}

	public ProjectMember(ProjectMemberId id, String role, String status) {
		super();
		this.id = id;
		this.role = role;
		this.status = status;
	}

	public ProjectMemberId getId() {
		return id;
	}

	public void setId(ProjectMemberId id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectMember other = (ProjectMember) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}