package com.icker.pm.vo;

public class ResourceVO {
	/** 序号 */
	private int sequence;
	private String id;
	/** 资源文件名 */
	private String name;
	/** 文件路径 */
	private String path;
	/** 业务层：文件类型 */
	private String type;
	/** 业务层：文件类型名称 */
	private String typeName;
	/** 文件上传时间 */
	private String upTime;
	/** 格式层：文件类型，格式：jpg、doc */
	private String format;
	/** 文件大小: 单位是 KB */
	private Double size;
	/** 上传者姓名 */
	private String uploader;
	private String uploaderId;
	
	private String project;
	private String projectId;
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
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
	public String getUploader() {
		return uploader;
	}
	public void setUploader(String uploader) {
		this.uploader = uploader;
	}
	public String getUploaderId() {
		return uploaderId;
	}
	public void setUploaderId(String uploaderId) {
		this.uploaderId = uploaderId;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
