package com.hs.pm.dao;


import java.util.List;

import com.hs.pm.bean.ProjectReceipt;

public class ProjectReceiptDAO extends BaseDAO<ProjectReceipt> {
	
	/*
	 * 创建收款开票
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
	 * 根据项目编号查询收款开票
	 */
	public List<ProjectReceipt> findByProjectSN(String projectsn){
		String hql = "from ProjectReceipt pr where pr.projectsn like '" + projectsn + "'";
		return super.list(hql);
	}
	
	
	
	/*
	 * 
	 * 根据项目编号和供应商查询合同金额
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
	 * 根据项目编号和供应商查询合同金额
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
