package com.hs.pm.dao;

import com.hs.pm.bean.ProjectFinance;

/*
 * 
 * 财务中心录入DAO
 * 
 */

public class ProjectFinanceDAO extends BaseDAO<ProjectFinance> {
	
	/*
	 * 财务中心记录录入
	 * return true 创建成功
	 */
	public boolean createProjectFinance(ProjectFinance p){
		return super.create(p);
	}
	
	
	/*
	 * 根据项目编号搜索进度信息
	 * 
	 */
	public ProjectFinance findByProjectSN(String projectsn){
		String hql = "from ProjectFinance p where p.projectsn like '" + projectsn + "'";
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
