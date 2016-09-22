package com.hs.pm.dao;


import java.util.List;

import com.hs.pm.bean.ProjectPay;

public class ProjectPayDAO extends BaseDAO<ProjectPay> {
	
	/*
	 * 创建付款收票
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
	 * 根据项目编号查询付款收票
	 */
	public List<ProjectPay> findByProjectSN(String projectsn){
		String hql = "from ProjectPay pp where pp.projectsn like '" + projectsn + "'";
		return super.list(hql);
	}
	
	
	/*
	 * 
	 * 根据项目编号和供应商查询合同金额
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
	 * 根据项目编号和供应商查询合同金额
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
