package com.icker.pm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.enumerate.DiscussType;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.dao.DiscussDao;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Discuss;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.User;
import com.icker.pm.service.DiscussService;
import com.icker.pm.vo.DiscussVO;

@Service
@Transactional
public class DiscussServiceImpl implements DiscussService {
	
	@Autowired
	private DiscussDao discussDao;
	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<Discuss> findAll(Project project) throws Exception {
		Project p = projectDao.findProjectById(project);
		return p.getDiscusses();
	}

	@Override
	public boolean save(Discuss discuss) throws Exception {
		return discussDao.save(discuss);
	}

	@Override
	public boolean update(Discuss discuss) throws Exception {
		return discussDao.update(discuss);
	}

	@Override
	public boolean remove(Discuss discuss) throws Exception {
		return discussDao.remove(discuss);
	}

	@Override
	public Discuss findById(Discuss discuss) throws Exception {
		return discussDao.findById(discuss);
	}

	@Override
	public List<DiscussVO> findAllVOs(Project project) throws Exception {
		Project p = projectDao.findProjectById(project);
		List<DiscussVO> vos = new ArrayList<DiscussVO>();
		List<Discuss> discusses = p.getDiscusses();
		int sequence = 1;
		for (Discuss discuss : discusses) {
			DiscussVO vo = new DiscussVO();
			vo.setId(discuss.getId());
			vo.setTitle(discuss.getTitle());
			vo.setAuthor(discuss.getAuthor().getName());
			vo.setAuthorId(discuss.getAuthor().getId());
			vo.setSequence(sequence++);
			vo.setContent(discuss.getContent());
			vo.setCreateTime(discuss.getCreateTime());
			vo.setType(discuss.getType());
			vo.setTypeName(DiscussType.getTypeName(discuss.getType()));
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public boolean save(DiscussVO vo) throws Exception {
		Discuss discuss = new Discuss();
		User user = userDao.findUserById(vo.getAuthorId());
		discuss.setAuthor(user);
		Project p = new Project();
		p.setId(vo.getProjectId());
		Project project = projectDao.findProjectById(p);
		discuss.setProject(project);
		discuss.setTitle(vo.getTitle());
		discuss.setContent(vo.getContent());
		discuss.setType(vo.getType());
		discuss.setCreateTime(DateFormatUtil.DateToString(new Date()));
		return discussDao.save(discuss);
	}

	@Override
	public List<DiscussVO> findByType(Project project, String type)
			throws Exception {
		Project p = projectDao.findProjectById(project);
		List<DiscussVO> vos = new ArrayList<DiscussVO>();
		List<Discuss> discusses = p.getDiscusses();
		Iterator<Discuss> iterator = discusses.iterator();
		while (iterator.hasNext()) {
			Discuss discuss = (Discuss) iterator.next();
			if(!type.equals(discuss.getType())) 
				iterator.remove();
		}
		int sequence = 1;
		for (Discuss discuss : discusses) {
			DiscussVO vo = new DiscussVO();
			vo.setId(discuss.getId());
			vo.setTitle(discuss.getTitle());
			vo.setAuthor(discuss.getAuthor().getName());
			vo.setAuthorId(discuss.getAuthor().getId());
			vo.setSequence(sequence++);
			vo.setContent(discuss.getContent());
			vo.setCreateTime(discuss.getCreateTime());
			vo.setType(discuss.getType());
			vo.setTypeName(DiscussType.getTypeName(discuss.getType()));
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public void update(DiscussVO vo) throws Exception {
		User user = userDao.findUserById(vo.getAuthorId());
		Project p = new Project();
		p.setId(vo.getProjectId());
		Project project = projectDao.findProjectById(p);
		Discuss discuss = new Discuss(vo.getId(), vo.getContent(), vo.getTitle(), vo.getType(), vo.getCreateTime(), user, project);
		discussDao.update(discuss);
	}

	@Override
	public Discuss findById(String id) throws Exception {
		return discussDao.findById(id);
	}

}
