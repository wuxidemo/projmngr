package com.hs.pm.dao;


import java.util.List;

import com.hs.pm.bean.ProjectPay;

public class ProjectPayDAO extends BaseDAO<ProjectPay> {
	
	/*
	 * ����������Ʊ
	 * 
	 */
	public boolean createProjectPay(ProjectPay pp){		
		if(new ProjectStartDAO().checkProjectExist(pp.getProjectsn())){
			return super.create(pp);
		}else{
			return false;
		}
		
	}
	
	
	/*
	 * 
	 * ������Ŀ��Ų�ѯ������Ʊ
	 */
	public List<ProjectPay> findByProjectSN(String projectsn){
		String hql = "from ProjectPay pp where pp.projectsn like '" + projectsn + "'";
		return super.list(hql);
	}
	
	
	/*
	 * 
	 * ������Ŀ��ź͹�Ӧ�̲�ѯ��ͬ���
	 * 
	 */
	public float findAmountByProjectSN(String projectsn,String vendor,String contracttype){
		float value = 0f;
		String hql = "from ProjectPay pp where pp.projectsn like '" + projectsn + "' and pp.payvendor like '" + vendor + "' and pp.contracttype like '" + contracttype + "'" ;
		List<ProjectPay> lists = super.list(hql);
		if(lists != null){
			for(int i=0;i<lists.size();i++){
				//if(lists.get(i).getPayamount() != null && !lists.get(i).getPayamount().equals("")){
				    value += lists.get(i).getPayamount();
				//}
			}
		}
		return value;
	}

	/*
	 * 
	 * ������Ŀ��ź͹�Ӧ�̲�ѯ��ͬ���
	 * 
	 */
	public float findBillAmountByProjectSN(String projectsn,String vendor,String contracttype){
		float value = 0f;
		String hql = "from ProjectPay pp where pp.projectsn like '" + projectsn + "' and pp.payvendor like '" + vendor + "' and pp.contracttype like '" + contracttype + "'";
		List<ProjectPay> lists = super.list(hql);
		if(lists != null){
			for(int i=0;i<lists.size();i++){
				//if(lists.get(i).getPaybillamount() != null && !lists.get(i).getPaybillamount().equals("")){
				    value += lists.get(i).getPaybillamount();
				//}
			}
		}
		return value;
	}
	
	/*
	 * 
	 * 
	 */
	public List<ProjectPay> lists(String hql){
		return super.list(hql);
	}
}
