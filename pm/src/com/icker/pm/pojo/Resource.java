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
@Table(name = "resource", catalog = "pm")
public class Resource implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5649386958996192070L;
	private String id;
	private String name;
	private String producer;
	private String proid;
	private String address;
	private String type;
	private String taskId;

	// Constructors

	/** default constructor */
	public Resource() {
	}

	/** minimal constructor */
	public Resource(String name, String producer, String proid, String address,
			String type) {
		this.name = name;
		this.producer = producer;
		this.proid = proid;
		this.address = address;
		this.type = type;
	}

	/** full constructor */
	public Resource(String name, String producer, String proid, String address,
			String type, String taskId) {
		this.name = name;
		this.producer = producer;
		this.proid = proid;
		this.address = address;
		this.type = type;
		this.taskId = taskId;
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

	@Column(name = "producer", nullable = false, length = 32)
	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	@Column(name = "proid", nullable = false, length = 32)
	public String getProid() {
		return this.proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	@Column(name = "address", nullable = false, length = 64)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "type", nullable = false, length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "taskId", length = 32)
	public String getTaskId() {
		return this.taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}