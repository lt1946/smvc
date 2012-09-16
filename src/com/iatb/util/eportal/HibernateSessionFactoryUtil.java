package com.iatb.util.eportal;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {
			/*
			 * �?�� 捕获Throwable对象, 否则捕获不到 Error及其子类和NoClassDefFoundError类型的错�?
			 */
			throw new ExceptionInInitializerError(ex);
		}
	}

	private HibernateSessionFactoryUtil() {

	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}