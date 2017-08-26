package com.dx.ssh.dao;

import org.hibernate.SessionFactory;

import com.dx.ssh.entity.CommonEntity;

public class CommonDaoImpl  extends BaseDaoImpl<CommonEntity> implements CommonDao {
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
