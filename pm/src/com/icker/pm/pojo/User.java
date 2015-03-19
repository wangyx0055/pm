package com.icker.pm.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Icker
 */
@Entity
@Table(name = "userinfo", catalog = "pm")
public class User implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217648617036725556L;
	private String id;
	private String email;
	private String password;
	private String name;
	private String regDate;
	private String status;
	private String active;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String email, String password, String name, String regDate,
			String status) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
		this.status = status;
	}

	/** full constructor */
	public User(String email, String password, String name, String regDate,
			String status, String active) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.regDate = regDate;
		this.status = status;
		this.active = active;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", nullable = false, length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "regDate", nullable = false, length = 32)
	public String getRegDate() {
		return this.regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "active")
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

}