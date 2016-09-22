package com.hs.pm.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	
	public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}
