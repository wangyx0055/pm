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
@Table(name = "tasksassign", catalog = "pm")
public class TasksAssign implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6046214688202321173L;
	private String id;
	private String taskId;
	private String assigner;
	private String executor;
	private String propress;
	private String createTime;

	// Constructors

	/** default constructor */
	public TasksAssign() {
	}

	/** full constructor */
	public TasksAssign(String taskId, String assigner, String executor,
			String propress, String createTime) {
		this.taskId = taskId;
		this.assigner = assigner;
		this.executor = executor;
		this.propress = propress;
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

	@Column(name = "taskId", nullable = false, length = 32)
	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Column(name = "assigner", nullable = false, length = 32)
	public String getAssigner() {
		return this.assigner;
	}

	public void setAssigner(String assigner) {
		this.assigner = assigner;
	}

	@Column(name = "executor", nullable = false, length = 32)
	public String getExecutor() {
		return this.executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	@Column(name = "propress", nullable = false, length = 32)
	public String getPropress() {
		return this.propress;
	}

	public void setPropress(String propress) {
		this.propress = propress;
	}

	@Column(name = "createTime", nullable = false, length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}