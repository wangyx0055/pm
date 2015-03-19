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
@Table(name = "tasks", catalog = "pm")
public class Task implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7440013082303901998L;
	private String id;
	private String projectId;
	private String name;
	private String describes;
	private String superId;
	private String startTime;
	private String endTime;
	private String finishTime;
	private String createTime;

	// Constructors

	/** default constructor */
	public Task() {
	}

	/** minimal constructor */
	public Task(String projectId, String name, String superId,
			String startTime, String endTime, String createTime) {
		this.projectId = projectId;
		this.name = name;
		this.superId = superId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createTime = createTime;
	}

	/** full constructor */
	public Task(String projectId, String name, String describes,
			String superId, String startTime, String endTime,
			String finishTime, String createTime) {
		this.projectId = projectId;
		this.name = name;
		this.describes = describes;
		this.superId = superId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.finishTime = finishTime;
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

	@Column(name = "projectId", nullable = false, length = 32)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "name", nullable = false, length = 30)
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

	@Column(name = "superId", nullable = false, length = 32)
	public String getSuperId() {
		return this.superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	@Column(name = "startTime", nullable = false, length = 32)
	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(name = "endTime", nullable = false, length = 32)
	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(name = "finishTime", length = 32)
	public String getFinishTime() {
		return this.finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "createTime", nullable = false, length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}