package com.icker.pm.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.icker.pm.dao.TaskDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.Task;
import com.icker.pm.vo.EmailTimerTaskVO;

@Repository
public class TaskDaoImpl extends BaseDao<Task> implements TaskDao {

	@Override
	public List<EmailTimerTaskVO> findEmailVO() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> findAll(Project project) throws Exception {
//		super.findByParam(Task.class, project.getId(), hql);
		return null;
	}
}
