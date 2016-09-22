package com.hs.pm.dao;


import java.util.List;

import com.hs.pm.bean.ProjectReceipt;

public class ProjectReceiptDAO extends BaseDAO<ProjectReceipt> {
	
	/*
	 * �����տƱ
	 * 
	 */
	public boolean createProjectReceipt(ProjectReceipt pr){		
		if(new ProjectStartDAO().checkProjectExist(pr.getProjectsn())){
			return super.create(pr);
		}else{
			return false;
		}
		
	}
	
	
	/*
	 * 
	 * ������Ŀ��Ų�ѯ�տƱ
	 */
	public List<ProjectReceipt> findByProjectSN(String projectsn){
		String hql = "from ProjectReceipt pr where pr.projectsn like '" + projectsn + "'";
		return super.list(hql);
	}
	
	
	
	/*
	 * 
	 * ������Ŀ��ź͹�Ӧ�̲�ѯ��ͬ���
	 * 
	 */
	public float findReceiptAmountByProjectSN(String projectsn){
		float value = 0f;
		String hql = "from ProjectReceipt pr where pr.projectsn like '" + projectsn + "'";
		List<ProjectReceipt> lists = super.list(hql);
		if(lists != null){
			for(int i=0;i<lists.size();i++){
				value += lists.get(i).getReceiptamount();				
			}
		}
		return value;
	}
	
	/*
	 * 
	 * ������Ŀ��ź͹�Ӧ�̲�ѯ��ͬ���
	 * 
	 */
	public float findBillReceiptAmountByProjectSN(String projectsn){
		float value = 0f;
		String hql = "from ProjectReceipt pr where pr.projectsn like '" + projectsn + "'";
		List<ProjectReceipt> lists = super.list(hql);
		if(lists != null){
			for(int i=0;i<lists.size();i++){
				value += lists.get(i).getReceiptbillamount();				
			}
		}
		return value;
	}
}
