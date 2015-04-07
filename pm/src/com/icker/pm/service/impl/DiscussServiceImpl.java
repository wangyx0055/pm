package com.icker.pm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.enumerate.DiscussType;
import com.icker.pm.dao.DiscussDao;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.pojo.Discuss;
import com.icker.pm.pojo.Project;
import com.icker.pm.service.DiscussService;
import com.icker.pm.vo.DiscussVO;

@Service
@Transactional
public class DiscussServiceImpl implements DiscussService {
	
	@Autowired
	private DiscussDao discussDao;
	@Autowired
	private ProjectDao projectDao;
	
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

}
