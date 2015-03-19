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
@Table(name = "projects", catalog = "pm")
public class Project implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8292478503280039898L;
	private String id;
	private String name;
	private String describes;
	private String logoAddr;
	private String createTime;

	// Constructors

	/** default constructor */
	public Project() {
	}

	/** minimal constructor */
	public Project(String name, String createTime) {
		this.name = name;
		this.createTime = createTime;
	}

	/** full constructor */
	public Project(String name, String describes, String logoAddr,
			String createTime) {
		this.name = name;
		this.describes = describes;
		this.logoAddr = logoAddr;
		this.createTime = createTime;
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

	@Column(name = "name", nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "describes")
	public String getDescribes() {
		return this.describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}

	@Column(name = "logoAddr", length = 64)
	public String getLogoAddr() {
		return this.logoAddr;
	}

	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}

	@Column(name = "createTime", nullable = false, length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}