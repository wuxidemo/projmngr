package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.ProjectContract;

public class ProjectContractDAO extends BaseDAO<ProjectContract> {
	
	/*
	 * 创建合同
	 * 
	 */
	public boolean createProjectContract(ProjectContract pc){		
		if(new ProjectStartDAO().checkProjectExist(pc.getProjectsn())){
			return super.create(pc);
		}else{
			return false;
		}
		
	}
	
	
	/*
	 * 
	 * 根据项目编号查询合同
	 */
	public ProjectContract findByProjectSN(String projectsn){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "'";
		return super.find(hql);
	}

	/*
	 * 
	 * 根据项目编号和合同类型 查询合同
	 */
	public ProjectContract findByProjectSNAndType(String projectsn,String type){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype like '" + type + "'";//like
		return super.find(hql);
	}

	/*
	 * 
	 * 根据项目编号和合同类型查询合同
	 */
	public List<ProjectContract> findByProjectSNNorType(String projectsn,String type){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype not like '" + type + "'";//not like
		return super.list(hql);
	}
	
	/*
	 * 
	 * 根据项目编号和供应商查询合同金额
	 * 
	 */
	public float findAmountByProjectSN(String projectsn,String vendor,String contracttype){
		float value = 0f;
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contractvendor like '" + vendor + "' and pc.contracttype like '" + contracttype + "'";
		List<ProjectContract> lists = super.list(hql);
		if(lists != null){
			for(int i=0;i<lists.size();i++){
				value += Float.valueOf(lists.get(i).getContractamount());
			}
		}
		return value;
	}
	
	public List<ProjectContract> lists(String hql){
		return super.list(hql);
	}
	
	/*
	 * 
	 * 根据项目编号和合同类型=建设合同查询合同
	 */
	public List<ProjectContract> findByProjectSNAndType1(String projectsn){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype like '建设合同'";
		return super.list(hql);
	}
	
	/*
	 * 
	 * 根据项目编号和合同类型=建设合同查询合同
	 */
	public List<ProjectContract> findByProjectSNAndType2(String projectsn){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype not like '建设合同'";
		return super.list(hql);
	}
}
