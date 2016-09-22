package com.hs.pm.dao;


import com.hs.pm.bean.ProjectProgress;

/*
 * 
 * 项目进度录入DAO
 * 
 */

public class ProjectProgressDAO extends BaseDAO<ProjectProgress> {
	
	/*
	 * 进度录入
	 * return true 创建成功
	 */
	public boolean createProjectProgress(ProjectProgress p){
		return super.create(p);
	}
	
	
	/*
	 * 根据项目编号搜索进度信息
	 * 
	 */
	public ProjectProgress findByProjectSN(String projectsn){
		String hql = "from ProjectProgress p where p.projectsn like '" + projectsn + "'";
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

}
