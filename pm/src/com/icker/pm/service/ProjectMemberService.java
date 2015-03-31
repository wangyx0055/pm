package com.icker.pm.service;

import java.util.List;

import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.ProjectMemberId;

public interface ProjectMemberService {
	public List<ProjectMember> findAll() throws Exception;
	public ProjectMember findById(ProjectMemberId id) throws Exception;
	public boolean addProjectMember(ProjectMember projectMember) throws Exception;
	public boolean updateProjectMember(ProjectMember projectMember) throws Exception;
	public boolean removeProjectMember(ProjectMember projectMember) throws Exception;
}
