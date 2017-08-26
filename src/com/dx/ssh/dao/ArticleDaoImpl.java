package com.dx.ssh.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.dx.ssh.entity.ArticleEntity;

public class ArticleDaoImpl extends BaseDaoImpl<ArticleEntity> implements ArticleDao {
	@Override
	public SessionFactory getSessionFactory() {
		System.out.println(sessionFactory);
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * 查next
	 */
	@Override
	public List<ArticleEntity> findNextArticle(int top, Timestamp createDate) {
		List<ArticleEntity> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession()
					.createQuery("From ArticleEntity where createDate>? order by createDate");
			query.setTimestamp(0, createDate);
			query.setMaxResults(top);
			return query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 查last
	 */
	@Override
	public List<ArticleEntity> findLastArticle(int top, Timestamp createDate) {
		List<ArticleEntity> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession()
					.createQuery("From ArticleEntity where createDate<? order by createDate asc");
			query.setTimestamp(0, createDate);
			query.setMaxResults(top);
			return query.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 最新三篇文章
	 */
	@Override
	public List<ArticleEntity> findAllLastArticle(int top) {
		List<ArticleEntity> list = null;
		try {
			Query query = getSessionFactory().getCurrentSession()
					.createQuery("From ArticleEntity order by createDate desc");
			query.setMaxResults(3); // 查询出来的记录数
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}
}
