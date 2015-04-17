package com.icker.pm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.TaskDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.EmailTimerTaskVO;

@Repository
public class TaskDaoImpl extends BaseDao<Task> implements TaskDao {

	@Override
	public List<EmailTimerTaskVO> findEmailVO() throws Exception {
		List<EmailTimerTaskVO> vos = new ArrayList<EmailTimerTaskVO>();
		List<Task> tasks = super.findAll("from Task t");
		for (Task task : tasks) {
			EmailTimerTaskVO vo = new EmailTimerTaskVO();
			vo.setEmail(task.getCreator().getEmail());
			vo.setEndTime(task.getEndDate());
			vo.setNick(task.getCreator().getName());
			vo.setTaskName(task.getName());
			vos.add(vo);
		}
		return vos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAll(Project project) throws Exception {
		String hql = "from Task t where t.project.id = ?0 order by t.createTime desc";
		return super.getEntityManager().createQuery(hql).setParameter(0, project.getId()).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByName(String name) throws Exception {
		String hql = "from Task t where t.name like :name order by t.createTime desc";
		List<Task> tasks = super.getEntityManager().createQuery(hql)
				.setParameter("name", "%" + name + "%").getResultList();
		return tasks;
	}

	@Override
	public void saveTask(Task task) throws Exception {
		super.save(task);
	}

	@Override
	public Task findTask(String id) throws Exception {
		return (Task) super.findById(Task.class, id);
	}

	@Override
	public void update(Task task) throws Exception {
		super.update(task);
	}

	@Override
	public void delete(Task task) throws Exception {
		super.delete(task);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByStatus(String proId, String status)
			throws Exception {
		String hql = "from Task t where t.project.id = ?0 and t.status = ?1 order by t.createTime desc";
		return super.getEntityManager().createQuery(hql).setParameter(0, proId)
				.setParameter(1, status).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAll(String performer, Project project, String status)
			throws Exception {
		String hql = "from Task t where t.performer.id = ?0 and t.project.id = ?1 and t.status = ?2 order by t.createTime desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, performer).setParameter(1, project.getId())
				.setParameter(2, status).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByName(String name, Project project, String status)
			throws Exception {
		String hql = "from Task t where t.name like ?0 and t.project.id = ?1 and t.status = ?2 order by t.createTime desc";
		return super.getEntityManager().createQuery(hql)
				.setParameter(0, "%" + name + "%")
				.setParameter(1, project.getId()).setParameter(2, status)
				.getResultList();
	}
}
