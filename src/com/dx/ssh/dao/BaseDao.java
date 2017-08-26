package com.dx.ssh.dao;

import java.util.List;

import org.hibernate.SessionFactory;

public interface BaseDao<T> {
	public SessionFactory getSessionFactory();

	public void setSessionFactory(SessionFactory sessionFactory);

	public Integer getTotal();

	public Integer getTotal(String hql);
	
	public List<T> getTops(String hql,int top);

	public boolean save(T entity);

	public boolean delete(T entity);

	public boolean update(T entity);

	public int updateByQuery(String hql);

	public T findById(Integer id);

	public List<T> findByQuery(String hql);

	public List<T> findAll();

	public List<T> queryWithPage(String hql, int pageNum, int pageSize);
}
