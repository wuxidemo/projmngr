package com.hs.pm.dao;



import java.util.List;

import com.hs.pm.bean.Project;

public class ProjectDAO extends BaseDAO<Project> {

	/*
	 * 创建项目
	 * 
	 */
	public boolean createProject(Project p){
		return super.create(p);
	}
	
	
	/*
	 * @param useraccount
	 * 用户帐号
	 * return User
	 * 返回 User对象
	 */
	public Project searchProjectBySn(String projectsn){
		
		String hql = "from Project p where p.projectsn like '" + projectsn + "'";
		return super.find(hql);		
	}


	public void updateProjectBySn(String projectsn,String dep){
		String hql = "from Project p where p.projectsn like '" + projectsn + "'";
		Project p = super.find(hql);
		p.setDepartment(dep);
		super.update(p);
	}
	
	/**
	 * 列出所有未分配事业部的项目
	 * 
	 * @return
	 */
	public List<Project> listNotDeployProjects(){
		String hql = "from Project p where p.department is null or p.department = ''";
		return super.list(hql);
	}
	
	
}
