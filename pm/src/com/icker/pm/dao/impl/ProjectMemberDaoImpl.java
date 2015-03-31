package com.icker.pm.dao.impl;

import org.springframework.stereotype.Repository;

import com.icker.pm.dao.ProjectMemberDao;
import com.icker.pm.pojo.ProjectMember;

@Repository
public class ProjectMemberDaoImpl extends BaseDao<ProjectMember> implements ProjectMemberDao{

	@Override
	public ProjectMember findProjectMember(ProjectMember projectMember)
			throws Exception {
		return (ProjectMember) super.findById(ProjectMember.class, projectMember.getId());
	}
	
}
