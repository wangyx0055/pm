package com.icker.test.pojo;

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
@Entity
@Table(name = "user", catalog = "test")
public class User {
	/** 用户id，使用的是Hibernate对JPA的主键生成策略的扩展，uuid自动在数据库表中生成32位的字符串 */
	@Id
	@GeneratedValue(generator = "idGenerator")     
	@GenericGenerator(name = "idGenerator", strategy = "uuid") 
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
	
	/** 
	 * 配置级联
	 */
	/** 项目成员 */
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, mappedBy = "id.user")
	private List<ProjectMember> projectMembers;
	
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

	public List<ProjectMember> getProjectMembers() {
		return projectMembers;
	}

	public void setProjectMembers(List<ProjectMember> projectMembers) {
		this.projectMembers = projectMembers;
	}
	
	public User() {
	}

	public User(String id, String email, String password, String name) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}
}