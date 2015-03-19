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
@Table(name = "message", catalog = "pm")
public class Message implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7582328522380638816L;
	private String id;
	private String content;
	private String sender;
	private String receiver;
	private String projectId;
	private String status;
	private String createTime;

	// Constructors

	/** default constructor */
	public Message() {
	}

	/** minimal constructor */
	public Message(String content, String sender, String receiver,
			String status, String createTime) {
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.status = status;
		this.createTime = createTime;
	}

	/** full constructor */
	public Message(String content, String sender, String receiver,
			String projectId, String status, String createTime) {
		this.content = content;
		this.sender = sender;
		this.receiver = receiver;
		this.projectId = projectId;
		this.status = status;
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

	@Column(name = "content", nullable = false)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "sender", nullable = false, length = 32)
	public String getSender() {
		return this.sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	@Column(name = "receiver", nullable = false, length = 32)
	public String getReceiver() {
		return this.receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Column(name = "projectId", length = 32)
	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Column(name = "status", nullable = false, length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "createTime", nullable = false, length = 32)
	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}