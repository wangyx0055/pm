package com.icker.pm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.TaskDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.EmailTimerTaskVO;

@Repository
public class TaskDaoImpl extends BaseDao<Task> implements TaskDao {

	public List<EmailTimerTaskVO> findEmailVO() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAll(Project project) throws Exception {
//		super.findByParam(Task.class, project.getId(), hql);
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findByName(String name) throws Exception {
		String hql = "from Task t where t.name like :name";
		List<Task> tasks = super.getEntityManager().createQuery(hql).setParameter("name","%"+name+"%").getResultList();
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
}
