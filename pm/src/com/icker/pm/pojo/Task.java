package com.icker.pm.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author Icker
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "task", catalog = "pm")
public class Task implements Serializable{

	/** 任务Id */
	@Id
	@GeneratedValue(generator = "generator")
	@GenericGenerator(name = "generator", strategy = "uuid")
	@Column(name = "id", length = 32)
	private String id;
	/** 任务名称 */
	@Column(name = "name", length = 30)
	private String name;
	/** 任务描述 */
	@Column(name = "description")
	private String description;
	/** 任务开始时间 */
	@Column(name = "start_date")
	private String startDate;
	/** 任务截止时间 */
	@Column(name = "end_date")
	private String endDate;
	/** 任务实际完成时间 */
	@Column(name = "finish_date")
	private String finishDate;
	/** 任务创建时间 */
	@Column(name = "create_date")
	private String createTime;
	/** 任务实现优先级；1：优先级高；2：优先级中；3：优先级低 */
	@Column(name = "priority")
	private String priority;
	/** 任务实现进度 0-100浮点数 */
	@Column(name = "progress", scale=2)
	private Double progress;
	/** 任务所属项目 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "projectid", referencedColumnName = "id")
	private Project project;
	/** 任务创建者，分配者 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "creator", referencedColumnName = "id")
	private User creator;
	/** 任务所属，执行者，负责人 */
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "performer", referencedColumnName = "id")
	private User performer;
	/** 父任务 */
	@Column(length = 32)
	private String parentId;

	/** 父任务关联表 */
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "task_parents", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "parent_id"))
	private Set<Task> parentTasks;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
	@JoinTable(name = "task_children", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "child_id"))
	private Set<Task> childrenTasks;

	public Task() {
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

	public Double getProgress() {
		return progress;
	}

	public void setProgress(Double progress) {
		this.progress = progress;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getPerformer() {
		return performer;
	}

	public void setPerformer(User performer) {
		this.performer = performer;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Set<Task> getParentTasks() {
		return parentTasks;
	}

	public void setParentTasks(Set<Task> parentTasks) {
		this.parentTasks = parentTasks;
	}

	public Set<Task> getChildrenTasks() {
		return childrenTasks;
	}

	public void setChildrenTasks(Set<Task> childrenTasks) {
		this.childrenTasks = childrenTasks;
	}

	public Task(String id, String name, String description, String startDate,
			String endDate, String finishDate, String createTime, String priority,
			Double progress, Project project, User creator, User performer, String parentId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.finishDate = finishDate;
		this.createTime = createTime;
		this.priority = priority;
		this.project = project;
		this.creator = creator;
		this.performer = performer;
		this.parentId = parentId;
		this.progress = progress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childrenTasks == null) ? 0 : childrenTasks.hashCode());
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
				+ ((parentTasks == null) ? 0 : parentTasks.hashCode());
		result = prime * result
				+ ((performer == null) ? 0 : performer.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result
				+ ((progress == null) ? 0 : progress.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
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
		Task other = (Task) obj;
		if (childrenTasks == null) {
			if (other.childrenTasks != null)
				return false;
		} else if (!childrenTasks.equals(other.childrenTasks))
			return false;
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
		if (parentTasks == null) {
			if (other.parentTasks != null)
				return false;
		} else if (!parentTasks.equals(other.parentTasks))
			return false;
		if (performer == null) {
			if (other.performer != null)
				return false;
		} else if (!performer.equals(other.performer))
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
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}