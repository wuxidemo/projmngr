package com.hs.pm.dao;

import com.hs.pm.bean.ProjectFinance;

/*
 * 
 * ��������¼��DAO
 * 
 */

public class ProjectFinanceDAO extends BaseDAO<ProjectFinance> {
	
	/*
	 * �������ļ�¼¼��
	 * return true �����ɹ�
	 */
	public boolean createProjectFinance(ProjectFinance p){
		return super.create(p);
	}
	
	
	/*
	 * ������Ŀ�������������Ϣ
	 * 
	 */
	public ProjectFinance findByProjectSN(String projectsn){
		String hql = "from ProjectFinance p where p.projectsn like '" + projectsn + "'";
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
