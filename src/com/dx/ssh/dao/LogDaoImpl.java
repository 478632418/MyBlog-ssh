package com.dx.ssh.dao;

import org.hibernate.SessionFactory;

import com.dx.ssh.entity.LogEntity;

public class LogDaoImpl extends BaseDaoImpl<LogEntity> implements LogDao {
	@Override
	public SessionFactory getSessionFactory() {
		System.out.println(sessionFactory);
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
