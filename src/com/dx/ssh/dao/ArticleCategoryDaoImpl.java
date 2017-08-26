package com.dx.ssh.dao;

import org.hibernate.SessionFactory;

import com.dx.ssh.entity.ArticleCategoryEntity;

public class ArticleCategoryDaoImpl extends BaseDaoImpl<ArticleCategoryEntity> implements ArticleCategoryDao {
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
