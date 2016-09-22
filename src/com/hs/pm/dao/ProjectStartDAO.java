package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.Project;
import com.hs.pm.bean.ProjectStart;

public class ProjectStartDAO extends BaseDAO<ProjectStart>{
	
	
	/*
	 * ��Ŀ����
	 * return true �����ɹ�
	 */
	public boolean createProjectStart(ProjectStart p){
		//д��tb_proj
		{
			Project pp = new Project();
			pp.setProjectsn(p.getProjectsn());
			pp.setProjectname(p.getProjectname());
			new ProjectDAO().createProject(pp);
		}
		
		return super.create(p);
	}
	
	
	/*
	 * ������Ŀ���������Ŀ��Ϣ
	 * 
	 */
	public ProjectStart findByProjectSN(String projectsn){
		String hql = "from ProjectStart p where p.projectsn like '" + projectsn + "'";
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
	
	public List<ProjectStart> list(String hql){
		return super.list(hql);
	}

}
