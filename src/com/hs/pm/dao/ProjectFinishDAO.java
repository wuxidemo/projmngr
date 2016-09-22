package com.hs.pm.dao;

import com.hs.pm.bean.ProjectFinish;


public class ProjectFinishDAO extends BaseDAO<ProjectFinish>{
	
	
	/*
	 * 项目完工
	 * return true 创建成功
	 */
	public boolean createProjectFinish(ProjectFinish p){
		return super.create(p);
	}
	
	
	/*
	 * 
	 * 更新项目完工记录
	 */
	public void updateProjectFinish(ProjectFinish p){
	    super.update(p);
	}
	
	
	/*
	 * 根据项目编号搜索项目信息
	 * 
	 */
	public ProjectFinish findByProjectSN(String projectsn){
		String hql = "from ProjectFinish pf where pf.projectsn like '" + projectsn + "'";
		return super.find(hql);
	}
	
	/*
	 * 根据项目编号检查项目是否已创建
	 * 
	 */
	
	public boolean checkProjectExist(String projectsn){
		String hql = "from ProjectFinish pf where pf.projectsn like '" + projectsn + "'";
		if(super.find(hql) != null){
			return true;
		}else{
			return false;
		}
	}

}
