package com.dx.ssh.dao;

import org.hibernate.SessionFactory;

import com.dx.ssh.entity.PageViewEntity;

public class PageViewDaoImpl extends BaseDaoImpl<PageViewEntity> implements PageViewDao {
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
