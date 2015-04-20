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
import com.icker.pm.dao.DiscussDao;
import com.icker.pm.dao.MilestoneDao;
import com.icker.pm.dao.ProjectDao;
import com.icker.pm.dao.ResourceDao;
import com.icker.pm.dao.TaskDao;
import com.icker.pm.dao.UserDao;
import com.icker.pm.pojo.Milestone;
import com.icker.pm.pojo.Project;
import com.icker.pm.pojo.ProjectMember;
import com.icker.pm.pojo.ProjectMemberId;
import com.icker.pm.pojo.Task;
import com.icker.pm.pojo.User;
import com.icker.pm.server.email.Mail;
import com.icker.pm.server.email.MailSender;
import com.icker.pm.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectDao projectDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private TaskDao taskDao;
	@Autowired
	private MilestoneDao milestoneDao;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private DiscussDao discussDao;

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
		Project p = projectDao.findProjectById(project);
		this.sendMail(p);
		return projectDao.deleteProject(p);
	}

	private void sendMail(Project p) throws Exception {
		List<ProjectMember> pms = p.getProjectMembers();
		for (ProjectMember pm : pms) {
			MailSender sender = new MailSender(new Mail(
					"项目注销通知",
					pm.getId().getUser().getName()
							+ "，您好！<br><p>&nbsp;&nbsp;&nbsp;&nbsp; 您参与的项目：&nbsp;&nbsp;<b>"
							+ p.getName() + "</b>&nbsp;&nbsp; 已被注销！",
					pm.getId().getUser().getEmail()));
			sender.start();
		}
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
	public boolean addProject(User creator, Project project,
			List<User> members, String sendEmail) throws Exception {
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

			// 邮件通知项目成员
			if (StringUtils.isNotBlank(sendEmail)
					&& Constant.SEND_MAIL_YES.equals(sendEmail))
				sendMail(pm, creator);
		}
		ProjectMember projectMember = new ProjectMember(new ProjectMemberId(
				project, creator), Constant.ROLE_MEMBER, Constant.IS_NOT_ACCESS);
		projectMembers.add(projectMember);
		project.setProjectMembers(projectMembers);

		return projectDao.saveProject(project);
	}

	/**
	 * 新增项目：邮件通知，项目邀请成员
	 * 
	 * @param pm
	 * @param creator
	 * @return
	 */
	private boolean sendMail(ProjectMember pm, User creator) {
		MailSender sender = new MailSender(new Mail("项目邀请通知", pm.getId().getUser()
				.getName()
				+ "，您好！<br>"
				+ "<p>&nbsp;&nbsp;&nbsp;&nbsp; 您被<b>&nbsp;&nbsp;"
				+ creator.getName()
				+ "&nbsp;&nbsp;</b>邀请参与项目&nbsp;&nbsp;<b>"
				+ pm.getId().getProject().getName() + "</b>&nbsp;&nbsp; 的研发。",
				pm.getId().getUser().getEmail()));
		sender.start();
		return true;
	}

	/**
	 * 修改项目是否通知
	 * 
	 * @param pm
	 * @param creator
	 * @return
	 */
	private boolean sendEditMail(ProjectMember pm, String oldProName) {
		MailSender sender = new MailSender(new Mail("项目修改通知", pm.getId().getUser()
				.getName()
				+ "，您好！<br>"
				+ "<p>&nbsp;&nbsp;&nbsp;&nbsp; 您参与的项目：&nbsp;&nbsp;<b>"
				+ oldProName + "</b>&nbsp;&nbsp;已经做了修改。</p>", pm.getId()
				.getUser().getEmail()));
		sender.start();
		return true;
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

	@Override
	public boolean updateProject(Project project, String sendEmail)
			throws Exception {
		Project oldPro = projectDao.findProjectById(project);
		List<ProjectMember> pms = oldPro.getProjectMembers();
		if (StringUtils.isNotBlank(sendEmail)
				&& Constant.SEND_MAIL_YES.equals(sendEmail))
			for (ProjectMember projectMember : pms) {
				this.sendEditMail(projectMember, oldPro.getName());
			}
		oldPro.setName(project.getName());
		oldPro.setDescription(project.getDescription());
		return projectDao.updateProject(oldPro);
	}

	@Override
	public List<Integer> findActionCount(Project project) throws Exception {
		List<Integer> counts = new ArrayList<Integer>();
		Project p = projectDao.findProjectById(project);
		int task = p.getTasks().size();
		int mile = p.getMilestones().size();
		int resource = p.getResources().size();
		int discuss = p.getDiscusses().size();
		int user = p.getProjectMembers().size();
		counts.add(user);
		counts.add(task);
		counts.add(mile);
		counts.add(resource);
		counts.add(discuss);
		
		return counts;
	}

	@Override
	public List<List<Object>> findTotalPieCharts(Project project)
			throws Exception {
		List<List<Object>> result = new ArrayList<List<Object>>();
		Project p = projectDao.findProjectById(project);
		int task = p.getTasks().size();
		int mile = p.getMilestones().size();
		int resource = p.getResources().size();
		int discuss = p.getDiscusses().size();
		int user = p.getProjectMembers().size();
		List<Object> tasks = new ArrayList<Object>();
		tasks.add("任务");
		tasks.add(task);
		List<Object> miles = new ArrayList<Object>();
		miles.add("里程碑");
		miles.add(mile);
		List<Object> resources = new ArrayList<Object>();
		resources.add("资源");
		resources.add(resource);
		List<Object> discusses = new ArrayList<Object>();
		discusses.add("写字板");
		discusses.add(discuss);
		List<Object> users = new ArrayList<Object>();
		users.add("项目成员");
		users.add(user);
		result.add(users);
		result.add(tasks);
		result.add(miles);
		result.add(resources);
		result.add(discusses);
		return result;
	}

	@Override
	public List<Integer> taskHistogram(Project project) throws Exception {
		List<Integer> counts = new ArrayList<Integer>();
		int complete = taskDao.findByStatus(project.getId(), Constant.TASK_STATUS_COMPLETED).size();
		int unfinished = taskDao.findByStatus(project.getId(), Constant.TASK_STATUS_UNFINISHED).size();
		int extend = 0;
		List<Task> all = taskDao.findAll(project);
		Date now = new Date();
		for (Task task : all) {
			if(DateFormatUtil.StringToDate("yyyy/MM/dd hh:mm:ss", task.getEndDate()).getTime() < now.getTime()) {
				if(task.getStatus().equals(Constant.TASK_STATUS_UNFINISHED)) {
					extend++;
					unfinished--;
				}
			}
		}
		counts.add(complete);
		counts.add(unfinished);
		counts.add(extend);
		return counts;
	}

	@Override
	public List<Integer> mileHistogram(Project project) throws Exception {
		List<Integer> counts = new ArrayList<Integer>();
		int complete = milestoneDao.findByStatus(project, Constant.MILE_COMPLETED).size();
		int unfinished = milestoneDao.findByStatus(project, Constant.MILE_UNFINISHED).size();
		int extend = 0;
		List<Milestone> all = milestoneDao.findAll(project);
		Date now = new Date();
		for (Milestone milestone : all) {
			if(DateFormatUtil.StringToDate("yyyy/MM/dd hh:mm:ss", milestone.getEndDate()).getTime() < now.getTime()) {
				if(milestone.getStatus().equals(Constant.MILE_UNFINISHED)) {
					extend++;
					unfinished--;
				}
			}
		}
		counts.add(complete);
		counts.add(unfinished);
		counts.add(extend);
		return counts;
	}

}
