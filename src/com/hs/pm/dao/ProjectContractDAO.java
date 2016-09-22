package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.ProjectContract;

public class ProjectContractDAO extends BaseDAO<ProjectContract> {
	
	/*
	 * ������ͬ
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
	 * ������Ŀ��Ų�ѯ��ͬ
	 */
	public ProjectContract findByProjectSN(String projectsn){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "'";
		return super.find(hql);
	}

	/*
	 * 
	 * ������Ŀ��źͺ�ͬ���� ��ѯ��ͬ
	 */
	public ProjectContract findByProjectSNAndType(String projectsn,String type){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype like '" + type + "'";//like
		return super.find(hql);
	}

	/*
	 * 
	 * ������Ŀ��źͺ�ͬ���Ͳ�ѯ��ͬ
	 */
	public List<ProjectContract> findByProjectSNNorType(String projectsn,String type){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype not like '" + type + "'";//not like
		return super.list(hql);
	}
	
	/*
	 * 
	 * ������Ŀ��ź͹�Ӧ�̲�ѯ��ͬ���
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
	 * ������Ŀ��źͺ�ͬ����=�����ͬ��ѯ��ͬ
	 */
	public List<ProjectContract> findByProjectSNAndType1(String projectsn){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype like '�����ͬ'";
		return super.list(hql);
	}
	
	/*
	 * 
	 * ������Ŀ��źͺ�ͬ����=�����ͬ��ѯ��ͬ
	 */
	public List<ProjectContract> findByProjectSNAndType2(String projectsn){
		String hql = "from ProjectContract pc where pc.projectsn like '" + projectsn + "' and pc.contracttype not like '�����ͬ'";
		return super.list(hql);
	}
}
