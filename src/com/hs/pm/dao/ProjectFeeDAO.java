package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.ProjectFee;

/*
 * 
 * ��ҵ������¼��DAO
 * 
 */

public class ProjectFeeDAO extends BaseDAO<ProjectFee> {
	
	/*
	 * ����¼��
	 * return true �����ɹ�
	 */
	public boolean createProjectFee(ProjectFee p){
		return super.create(p);
	}
	
	
	/*
	 * ������Ŀ�������������Ϣ
	 * 
	 */
	public List<ProjectFee> findByProjectSN(String projectsn){
		String hql = "from ProjectFee p where p.projectsn like '" + projectsn + "'";
		return super.list(hql);
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
