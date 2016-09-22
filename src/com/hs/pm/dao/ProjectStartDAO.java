package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.Project;
import com.hs.pm.bean.ProjectStart;

public class ProjectStartDAO extends BaseDAO<ProjectStart>{
	
	
	/*
	 * 项目立项
	 * return true 创建成功
	 */
	public boolean createProjectStart(ProjectStart p){
		//写入tb_proj
		{
			Project pp = new Project();
			pp.setProjectsn(p.getProjectsn());
			pp.setProjectname(p.getProjectname());
			new ProjectDAO().createProject(pp);
		}
		
		return super.create(p);
	}
	
	
	/*
	 * 根据项目编号搜索项目信息
	 * 
	 */
	public ProjectStart findByProjectSN(String projectsn){
		String hql = "from ProjectStart p where p.projectsn like '" + projectsn + "'";
		return super.find(hql);
	}
	
	/*
	 * 根据项目编号检查项目是否已创建
	 * 
	 */
	
	public boolean checkProjectExist(String projectsn){
		String hql = "from ProjectStart p where p.projectsn like '" + projectsn + "'";
		if(super.find(hql) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public List<ProjectStart> list(String hql){
		return super.list(hql);
	}

}
