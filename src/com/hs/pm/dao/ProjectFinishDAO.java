package com.hs.pm.dao;

import com.hs.pm.bean.ProjectFinish;


public class ProjectFinishDAO extends BaseDAO<ProjectFinish>{
	
	
	/*
	 * ��Ŀ�깤
	 * return true �����ɹ�
	 */
	public boolean createProjectFinish(ProjectFinish p){
		return super.create(p);
	}
	
	
	/*
	 * 
	 * ������Ŀ�깤��¼
	 */
	public void updateProjectFinish(ProjectFinish p){
	    super.update(p);
	}
	
	
	/*
	 * ������Ŀ���������Ŀ��Ϣ
	 * 
	 */
	public ProjectFinish findByProjectSN(String projectsn){
		String hql = "from ProjectFinish pf where pf.projectsn like '" + projectsn + "'";
		return super.find(hql);
	}
	
	/*
	 * ������Ŀ��ż����Ŀ�Ƿ��Ѵ���
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
