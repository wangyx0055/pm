package com.icker.pm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icker.pm.common.constant.Constant;
import com.icker.pm.common.util.DateFormatUtil;
import com.icker.pm.common.util.PageUtil;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.ProjectMemberId;
import com.icker.pm.pojo.User;
import com.icker.pm.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UserDao userDao;

	@Override
	public boolean addProject(Project project) throws Exception {
		return projectDao.saveProject(project);
	}

	@Override
	public boolean updateProject(Project project) throws Exception {
		return projectDao.updateProject(project);
	}

	@Override
	public boolean removeProject(Project project) throws Exception {
		return projectDao.deleteProject(projectDao.findProjectById(project));
	}

	@Override
	public Project findById(Project project) throws Exception {
		return projectDao.findProjectById(project);
	}

	@Override
	public List<Project> findByUser(User u) throws Exception {
		User user = userDao.findUserById(u.getId());
		List<ProjectMember> pms = user.getProjectMembers();
		List<Project> projects = new ArrayList<Project>();
		for (ProjectMember pm : pms)
			projects.add(pm.getId().getProject());
		return projects;
	}

	@Override
	public List<Project> findAll() throws Exception {
		return projectDao.findAll();
	}

	@Override
	public ProjectMember findPMByUserPro(User user, Project project)
			throws Exception {
		List<ProjectMember> pms = user.getProjectMembers();
		if (null != pms && !pms.isEmpty()) {
			for (ProjectMember pm : pms) {
				if (project.getId().equals(pm.getId().getProject().getId()))
					return pm;
			}
		}
		return null;
	}

	@Override
	public boolean addProject(User creator, Project project, List<User> members)
			throws Exception {
		List<ProjectMember> projectMembers = new ArrayList<ProjectMember>();
		for (User member : members) {
			User user = userDao.findByEmail(member.getEmail());
			ProjectMemberId id = null;
			if (null != user)
				id = new ProjectMemberId(project, user);
			else {
				userDao.saveUser(member);
				id = new ProjectMemberId(project, member);
			}
			ProjectMember pm = new ProjectMember(id, Constant.ROLE_MEMBER,
					Constant.IS_NOT_ACCESS);
			projectMembers.add(pm);
		}
		ProjectMember projectMember = new ProjectMember(new ProjectMemberId(
				project, creator), Constant.ROLE_MEMBER, Constant.IS_NOT_ACCESS);
		projectMembers.add(projectMember);
		project.setProjectMembers(projectMembers);
		return projectDao.saveProject(project);
	}

	@Override
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil)
			throws Exception {
		return projectDao.pagingFindByUser(user, pageUtil);
	}

	@Override
	public List<Project> pagingFindByUser(User user, PageUtil pageUtil,
			String status) throws Exception {
		return projectDao.pagingFindByUser(user, pageUtil, status);
	}

	@Override
	public List<Map<String, Object>> findUsersByProject(Project project,
			User creator) throws Exception {
		Project p = projectDao.findProjectById(project);
		List<ProjectMember> pms = p.getProjectMembers();
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (ProjectMember pm : pms) {
			if (creator.getEmail().equals(pm.getId().getUser().getEmail()))
				continue;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", pm.getId().getUser().getId());
			map.put("name", pm.getId().getUser().getName());
			map.put("password", pm.getId().getUser().getPassword());
			map.put("email", pm.getId().getUser().getEmail());
			maps.add(map);
		}
		return maps;
	}

	@Override
	public boolean updateProject(Project project, List<User> users)
			throws Exception {
		Project oldPro = projectDao.findProjectById(project);
		List<ProjectMember> pms = oldPro.getProjectMembers();
		oldPro.setName(project.getName());
		oldPro.setDescription(project.getDescription());
		// 判断成员是否修改
		// 成员个数不变
		if (users.size() == pms.size()) {
			for (int i = 0; i < pms.size(); i++) {
				for (int j = 0; j < users.size(); j++) {
					if (pms.get(i).getId().getUser().getId()
							.equals(users.get(j).getId())) {
						// 成员不变
						if (pms.get(i).getId().getUser().getEmail()
								.equals(users.get(j).getEmail()))
							continue;
						else if (this.hasUser(users.get(j))) // 成员变化
							pms.get(i)
									.getId()
									.setUser(
											userDao.findByEmail(users.get(j)
													.getEmail()));
						else {
							userDao.saveUser(new User(users.get(j).getEmail(),
									users.get(j).getPassword(), users.get(j)
											.getName(), DateFormatUtil
											.DateToString(new Date()),
									Constant.STATUS_IS_USABLE, null, null));
							pms.get(i)
									.getId()
									.setUser(
											userDao.findByEmail(users.get(j)
													.getEmail()));
						}
					}
				}
			}
			return projectDao.saveProject(oldPro);
		} else if (users.size() > pms.size()) { // 编辑后的成员多于原有成员
			for (int i = 0; i < users.size(); i++) {
				for (int j = 0; j < pms.size(); j++) {
					if (StringUtils.isBlank(users.get(i).getId())) {
						boolean f = true;
						for (int k = 0; k < pms.size(); k++) {
							if (users
									.get(i)
									.getEmail()
									.equals(pms.get(k).getId().getUser()
											.getEmail())) {
								f = false;
								break;
							}
						}
						if (f) {
							if (this.hasUser(users.get(i)))
								pms.add(new ProjectMember(new ProjectMemberId(
										project, userDao.findByEmail(users.get(
												i).getEmail())),
										Constant.ROLE_MEMBER,
										Constant.IS_NOT_ACCESS));
							else {
								userDao.saveUser(new User(
										users.get(i).getEmail(),
										users.get(i).getPassword(),
										users.get(i).getName(),
										DateFormatUtil.DateToString(new Date()),
										Constant.STATUS_IS_NOT_USABLE, null,
										null));
								User user = userDao.findByEmail(users.get(i)
										.getEmail());
								ProjectMemberId pmId = new ProjectMemberId(
										project, user);
								ProjectMember pm = new ProjectMember(pmId,
										Constant.ROLE_MEMBER,
										Constant.IS_NOT_ACCESS);
								pms.add(pm);
							}
						}
					} else if (pms.get(j).getId().getUser().getId()
							.equals(users.get(i).getId())) {
						// 成员不变
						if (pms.get(j).getId().getUser().getEmail()
								.equals(users.get(i).getEmail()))
							continue;
						else if (this.hasUser(users.get(i)))
							pms.add(new ProjectMember(new ProjectMemberId(
									project, userDao.findByEmail(users.get(i)
											.getEmail())),
									Constant.ROLE_MEMBER,
									Constant.IS_NOT_ACCESS));
						else {
							userDao.saveUser(new User(users.get(i).getEmail(),
									users.get(i).getPassword(), users.get(i)
											.getName(), DateFormatUtil
											.DateToString(new Date()),
									Constant.STATUS_IS_NOT_USABLE, null, null));
							User user = userDao.findByEmail(users.get(i)
									.getEmail());
							ProjectMemberId pmId = new ProjectMemberId(project,
									user);
							ProjectMember pm = new ProjectMember(pmId,
									Constant.ROLE_MEMBER,
									Constant.IS_NOT_ACCESS);
							pms.add(pm);
						}
					}
				}
			}
		} else if (users.size() < pms.size()) { // 编辑后的成员少于原有成员
			// 未完待续。。。太麻烦了
		}
		// 仅仅修改了自身
		return projectDao.updateProject(oldPro);
	}

	private boolean hasUser(User user) throws Exception {
		boolean flag = false;
		User u = userDao.findByEmail(user.getEmail());
		flag = (u != null) ? true : false;
		if (!flag)
			throw new Exception("用户账号输入错误！");
		else if (flag && !u.getPassword().equals(user.getPassword()))
			throw new Exception("密码不正确");
		return flag;
	}

	@Override
	public int findCount(User user, String status) throws Exception {
		return projectDao.findCountOfPro(user, status);
	}

	@Override
	public List<Project> findByUser(User user, String status) throws Exception {
		return projectDao.findByUser(user, status);
	}

	@Override
	public List<Map<String, Object>> findUsers(Project project)
			throws Exception {
		List<User> users = userDao.findUsersByProject(project);
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (User user : users) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", user.getId());
			map.put("name", user.getName());
			map.put("password", user.getPassword());
			map.put("email", user.getEmail());
			maps.add(map);
		}
		return maps;
	}

	@Override
	public Project findProject(String id) throws Exception {
		return projectDao.findById(id);
	}

}
