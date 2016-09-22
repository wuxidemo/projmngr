package com.hs.pm.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hs.pm.util.HibernateUtil;

public class BaseDAO<T> {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	

	public boolean create(T object){
		boolean result = true;
		Session session = sessionFactory.openSession();
		try{
		    session.beginTransaction();
		    session.persist(object);
		    session.getTransaction().commit();
		    result = true;
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
			result = false;
		}finally{
			session.close();
		}
		return result;
	}
	
	public void update(T object){
		Session session = sessionFactory.openSession();
		try{
		session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}	

	
	public void delete(T object){
		Session session = sessionFactory.openSession();
		try{
		session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}
	}	
	
	
	public T find(Class <? extends T> clazz, Serializable id){
		Session session = sessionFactory.openSession();
		try{
		    session.beginTransaction();
		    return (T)session.get(clazz, id);
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
		}		
		return null;
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> list(String hql){
		Session session = sessionFactory.openSession();
		try{
		    session.beginTransaction();
		    return session.createQuery(hql).list();
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.getTransaction().commit();
			session.close();
		}		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public T find(String hql){
		Session session = sessionFactory.openSession();
		try{
		    session.beginTransaction();
		    List<T> lists = session.createQuery(hql).list();
		    if(lists.size() >0){
		        return lists.get(0);
		    }else{
		    	return null;
		    }
		}catch(Exception e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}		
		return null;
	}
	

}
