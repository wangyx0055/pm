package com.icker.pm.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Icker
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user", catalog = "pm")
public class User implements Serializable{
	/** 用户id */
	@Id
	@GeneratedValue(generator = "generator")     
	@GenericGenerator(name = "generator", strategy = "uuid") 
	@Column(name = "id", length = 32)
	private String id;
	/** 用户邮箱，登陆账号 */
	@Column(name = "email", length = 32)
	private String email;
	/** 用户密码 */
	@Column(name = "password", length = 8)
	private String password;
	/** 用户名，昵称 */
	@Column(name = "name", length = 20)
	private String name;
	/** 用户注册时间 */
	@Column(name = "regdate", length = 20)
	private String regDate;
	/** 用户是否可用 1表示可用，0表示不可用 */
	@Column(name = "status")
	private String status;
	/** 用户激活码 */
	@Column(name = "active")
	private String active;
	/** 头像地址 */
	@Column(name = "logo")
	private String logo;
	
	/** 
	 * 配置级联
	 */
	/** 项目成员 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "id.user")
	private List<ProjectMember> projectMembers;
	/** 项目写字板 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "author")
	private List<Discuss> discusses;
	/** 发送的项目消息 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "sender")
	private List<Message> sendMsges;
	/** 接收的项目消息 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "receiver")
	private List<Message> receiveMsges;
	/** 上传的文件 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "uploader")
	private List<Resource> resources;
	/** 创建的任务 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "creator")
	private List<Task> createdTasks;
	/** 执行的任务 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "performer")
	private List<Task> performedTasks;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<ProjectMember> getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(List<ProjectMember> projectMembers) {
		this.projectMembers = projectMembers;
	}
	
	public List<Task> getCreatedTasks() {
		return createdTasks;
	}
	
	public void setCreatedTasks(List<Task> createdTasks) {
		this.createdTasks = createdTasks;
	}
	
	public List<Discuss> getDiscusses() {
		return discusses;
	}
	
	public void setDiscusses(List<Discuss> discusses) {
		this.discusses = discusses;
	}
	
	public List<Task> getPerformedTasks() {
		return performedTasks;
	}
	
	public void setPerformedTasks(List<Task> performedTasks) {
		this.performedTasks = performedTasks;
	}
	
	public List<Message> getReceiveMsges() {
		return receiveMsges;
	}
	
	public void setReceiveMsges(List<Message> receiveMsges) {
		this.receiveMsges = receiveMsges;
	}
	
	public List<Resource> getResources() {
		return resources;
	}
	
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}
	
	public List<Message> getSendMsges() {
		return sendMsges;
	}
	
	public void setSendMsges(List<Message> sendMsges) {
		this.sendMsges = sendMsges;
	}

	public void addProjectMember(ProjectMember projectMember) {
		if(projectMembers.isEmpty()) {
			this.projectMembers = new ArrayList<ProjectMember>();
			this.projectMembers.add(projectMember);
		} else
			this.projectMembers.add(projectMember);
	}
	
	public User() {
	}

	public User(String email, String password, String name,
			String regDate, String status, String active, String logo) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
		this.status = status;
		this.active = active;
		this.logo = logo;
	}

	public User(String id, String email, String password, String name,
			String regDate, String status, String active, String logo) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
		this.status = status;
		this.active = active;
		this.logo = logo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result
				+ ((createdTasks == null) ? 0 : createdTasks.hashCode());
		result = prime * result
				+ ((discusses == null) ? 0 : discusses.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logo == null) ? 0 : logo.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result
				+ ((performedTasks == null) ? 0 : performedTasks.hashCode());
		result = prime * result
				+ ((projectMembers == null) ? 0 : projectMembers.hashCode());
		result = prime * result
				+ ((receiveMsges == null) ? 0 : receiveMsges.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		result = prime * result
				+ ((resources == null) ? 0 : resources.hashCode());
		result = prime * result
				+ ((sendMsges == null) ? 0 : sendMsges.hashCode());
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
		User other = (User) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (createdTasks == null) {
			if (other.createdTasks != null)
				return false;
		} else if (!createdTasks.equals(other.createdTasks))
			return false;
		if (discusses == null) {
			if (other.discusses != null)
				return false;
		} else if (!discusses.equals(other.discusses))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logo == null) {
			if (other.logo != null)
				return false;
		} else if (!logo.equals(other.logo))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (performedTasks == null) {
			if (other.performedTasks != null)
				return false;
		} else if (!performedTasks.equals(other.performedTasks))
			return false;
		if (projectMembers == null) {
			if (other.projectMembers != null)
				return false;
		} else if (!projectMembers.equals(other.projectMembers))
			return false;
		if (receiveMsges == null) {
			if (other.receiveMsges != null)
				return false;
		} else if (!receiveMsges.equals(other.receiveMsges))
			return false;
		if (regDate == null) {
			if (other.regDate != null)
				return false;
		} else if (!regDate.equals(other.regDate))
			return false;
		if (resources == null) {
			if (other.resources != null)
				return false;
		} else if (!resources.equals(other.resources))
			return false;
		if (sendMsges == null) {
			if (other.sendMsges != null)
				return false;
		} else if (!sendMsges.equals(other.sendMsges))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}