package com.hs.pm.dao;

import java.util.List;

import com.hs.pm.bean.Department;;

public class DepartmentDAO extends BaseDAO<Department> {

	/*
	 * 创建事业部
	 * 
	 */
	public boolean createDepartment(Department d){
		return super.create(d);
	}
	
	
	/*
	 * 
	 * 搜索事业部
	 * 
	 */
	public Department findDepartment(String name){
		String hql = "from Department d where d.department like '" + name + "'";
		return super.find(hql);
	}
	
	/*
	 * 
	 * 更新事业部
	 * 
	 */
	public void findDepartment(Department d){
		super.update(d);
	}
	
	/*
	 * @param username
	 * 用户帐号
	 * return User
	 * 返回 User对象
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
