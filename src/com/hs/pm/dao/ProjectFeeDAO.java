package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.ProjectFee;

/*
 * 
 * 事业部费用录入DAO
 * 
 */

public class ProjectFeeDAO extends BaseDAO<ProjectFee> {
	
	/*
	 * 费用录入
	 * return true 创建成功
	 */
	public boolean createProjectFee(ProjectFee p){
		return super.create(p);
	}
	
	
	/*
	 * 根据项目编号搜索费用信息
	 * 
	 */
	public List<ProjectFee> findByProjectSN(String projectsn){
		String hql = "from ProjectFee p where p.projectsn like '" + projectsn + "'";
		return super.list(hql);
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
