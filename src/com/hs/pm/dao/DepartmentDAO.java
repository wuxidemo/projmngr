package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.Department;;

public class DepartmentDAO extends BaseDAO<Department> {

	/*
	 * ������ҵ��
	 * 
	 */
	public boolean createDepartment(Department d){
		return super.create(d);
	}
	
	
	/*
	 * 
	 * ������ҵ��
	 * 
	 */
	public Department findDepartment(String name){
		String hql = "from Department d where d.department like '" + name + "'";
		return super.find(hql);
	}
	
	/*
	 * 
	 * ������ҵ��
	 * 
	 */
	public void findDepartment(Department d){
		super.update(d);
	}
	
	/*
	 * @param username
	 * �û��ʺ�
	 * return User
	 * ���� User����
	 */
	public void deleteDepartment(String department){
		
		String hql = "from Department d where d.department like '" + department + "'";
		Department d = super.find(hql);
		super.delete(d);	
	}	

	public List<Department> list(){
		String hql = "from Department d";
		return super.list(hql);
	}
}
