package com.icker.pm.vo;

public class DiscussVO {
	/** 序号 */
	private Integer sequence;
	/** 写字板ID */
	private String id;
	/** 写字板内容 */
	private String content;
	/** 写字板标题 */
	private String title;
	/** 写字板类型 （需求+设计+技术文章） */
	private String type;
	/** 写字板类型名称 （需求+设计+技术文章） */
	private String typeName;
	/** 创建时间 */
	private String createTime;
	/** 作者 */
	private String author;
	/** 作者ID */
	private String authorId;
	/** 所属项目 */
	private String project;
	/** 所属项目ID */
	private String projectId;
	
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
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
	public DiscussVO(String id, String content, String title, String type,
			String typeName, String createTime, String author, String authorId,
			String project, String projectId) {
		super();
		this.id = id;
		this.content = content;
		this.title = title;
		this.type = type;
		this.typeName = typeName;
		this.createTime = createTime;
		this.author = author;
		this.authorId = authorId;
		this.project = project;
		this.projectId = projectId;
	}
	public DiscussVO() {
		super();
	}
}
