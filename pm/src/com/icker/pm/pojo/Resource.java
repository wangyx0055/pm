package com.icker.pm.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 资源文件
 * 
 * @author Icker
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "resource", catalog = "pm")
public class Resource implements Serializable{

	/** 资源文件ID */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid")
	@GeneratedValue(generator = "generator")
	@Column(name = "id", length = 32)
	private String id;
	/** 资源文件名 */
	@Column(name = "name", length = 50)
	private String name;
	/** 文件路径 */
	@Column(name = "path", length = 64)
	private String path;
	/** 业务层：文件类型 */
	@Column(name = "type", nullable = false, length = 1)
	private String type;
	/** 文件上传时间 */
	@Column(name = "uptime", length = 32)
	private String upTime;
	/** 格式层：文件类型，格式：jpg、doc */
	@Column(name = "format", length = 32)
	private String format;
	/** 文件大小: 单位是 KB */
	@Column(name = "size")
	private Double size;
	/** 上传者 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "uploader", insertable = true, updatable = true, referencedColumnName = "id")
	private User uploader;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "project", insertable = true, updatable = true, referencedColumnName = "id")
	private Project project;

	public Resource(String id, String name, String path, String type,
			User uploader, Project project) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
		this.type = type;
		this.uploader = uploader;
		this.project = project;
	}

	public Resource() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getUploader() {
		return uploader;
	}

	public void setUploader(User uploader) {
		this.uploader = uploader;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	public String getUpTime() {
		return upTime;
	}
	
	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getFormat() {
		return format;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public Double getSize() {
		return size;
	}
	
	public void setSize(Double size) {
		this.size = size;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result
				+ ((uploader == null) ? 0 : uploader.hashCode());
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
		Resource other = (Resource) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (uploader == null) {
			if (other.uploader != null)
				return false;
		} else if (!uploader.equals(other.uploader))
			return false;
		return true;
	}
}