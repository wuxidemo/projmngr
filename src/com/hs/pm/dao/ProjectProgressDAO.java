package com.hs.pm.dao;


import com.hs.pm.bean.ProjectProgress;

/*
 * 
 * ��Ŀ����¼��DAO
 * 
 */

public class ProjectProgressDAO extends BaseDAO<ProjectProgress> {
	
	/*
	 * ����¼��
	 * return true �����ɹ�
	 */
	public boolean createProjectProgress(ProjectProgress p){
		return super.create(p);
	}
	
	
	/*
	 * ������Ŀ�������������Ϣ
	 * 
	 */
	public ProjectProgress findByProjectSN(String projectsn){
		String hql = "from ProjectProgress p where p.projectsn like '" + projectsn + "'";
		return super.find(hql);
	}
	
	/*
	 * ������Ŀ��ż����Ŀ�Ƿ��Ѵ���
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
