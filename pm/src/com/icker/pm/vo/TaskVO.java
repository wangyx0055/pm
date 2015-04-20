package com.icker.pm.vo;

public class TaskVO {
	/** 任务Id */
	private String id;
	/** 状态 0：未完成；1：完成；2：延期 */
	private String status;
	/** 序号 */
	private Integer sequence;
	/** 任务名称 */
	private String name;
	/** 任务描述 */
	private String description;
	/** 任务开始时间 */
	private String startDate;
	/** 任务截止时间 */
	private String endDate;
	/** 任务实际完成时间 */
	private String finishDate;
	/** 任务创建时间 */
	private String createTime;
	/** 任务实现优先级；1：优先级高；2：优先级中；3：优先级低 */
	private String priority;
	/** 任务实现进度 0-100浮点数 */
	private Double progress;
	/** 任务所属项目，主键ID */
	private String project;
	/** 任务创建者，分配者，主键ID */
	private String creator;
	/** 任务所属，执行者，负责人，名称 */
	private String performer;
	/** 任务所属，执行者，负责人，主键ID */
	private String performerId;
	/** 父任务 */
	private String parentId;
	/** 是否邮件通知 1：通知；0：不通知*/
	private String sendEmail;
	
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public Double getProgress() {
		return progress;
	}
	public void setProgress(Double progress) {
		this.progress = progress;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public String getPerformerId() {
		return performerId;
	}
	public void setPerformerId(String performerId) {
		this.performerId = performerId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public TaskVO() {
		super();
	}
	public TaskVO(String id, String name, String description, String startDate,
			String endDate, String finishDate, String createTime,
			String priority, Double progress, String project, String creator,
			String performer, String performerId, String parentId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.finishDate = finishDate;
		this.createTime = createTime;
		this.priority = priority;
		this.progress = progress;
		this.project = project;
		this.creator = creator;
		this.performer = performer;
		this.performerId = performerId;
		this.parentId = parentId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((finishDate == null) ? 0 : finishDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result
				+ ((performer == null) ? 0 : performer.hashCode());
		result = prime * result
				+ ((performerId == null) ? 0 : performerId.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result
				+ ((progress == null) ? 0 : progress.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((sendEmail == null) ? 0 : sendEmail.hashCode());
		result = prime * result
				+ ((sequence == null) ? 0 : sequence.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		TaskVO other = (TaskVO) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (finishDate == null) {
			if (other.finishDate != null)
				return false;
		} else if (!finishDate.equals(other.finishDate))
			return false;
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
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (performer == null) {
			if (other.performer != null)
				return false;
		} else if (!performer.equals(other.performer))
			return false;
		if (performerId == null) {
			if (other.performerId != null)
				return false;
		} else if (!performerId.equals(other.performerId))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (progress == null) {
			if (other.progress != null)
				return false;
		} else if (!progress.equals(other.progress))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (sendEmail == null) {
			if (other.sendEmail != null)
				return false;
		} else if (!sendEmail.equals(other.sendEmail))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
}
