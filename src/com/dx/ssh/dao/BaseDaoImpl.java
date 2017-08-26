package com.dx.ssh.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public abstract class BaseDaoImpl<T> implements BaseDao<T> {
	protected SessionFactory sessionFactory = null;
	private Class<T> entityClass;

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
	 * 用构造方法处理Class<T>
	 */
	public BaseDaoImpl() {
		Type type = (Type) getClass().getGenericSuperclass();
		entityClass = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
	}

	@Override
	public Integer getTotal() {
		Integer total = 0;
		try {
			total = this.getSessionFactory().getCurrentSession().createCriteria(entityClass).list().size();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public Integer getTotal(String hql) {
		Integer total = 0;
		try {
			Object object = this.getSessionFactory().getCurrentSession().createQuery(hql).uniqueResult();
			total = Integer.parseInt(object.toString());
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return total;
	}

	@Override
	public List<T> getTops(String hql, int top) {
		try {
			Query query = getSessionFactory().getCurrentSession().createQuery(hql);
			query.setMaxResults(top); // 查询出来的记录数
			return (List<T>) query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 保存记录
	 */
	@Override
	public boolean save(T entity) {
		// Transaction transaction =
		// this.sessionFactory.getCurrentSession().beginTransaction();
		try {
			this.getSessionFactory().getCurrentSession().save(entity);
			// transaction.commit();
			return true;
		} catch (Exception ex) {
			// transaction.rollback();
			return false;
		}
	}

	/**
	 * 删除记录
	 */
	@Override
	public boolean delete(T entity) {
		// Transaction transaction =
		// this.sessionFactory.getCurrentSession().beginTransaction();
		try {
			this.getSessionFactory().getCurrentSession().delete(entity);
			// transaction.commit();
			return true;
		} catch (Exception ex) {
			// transaction.rollback();
			return false;
		}
	}

	/**
	 * 更新
	 */
	@Override
	public boolean update(T entity) {
		// Transaction transaction =
		// this.sessionFactory.getCurrentSession().beginTransaction();
		try {
			this.getSessionFactory().getCurrentSession().saveOrUpdate(entity);
			// transaction.commit();
			return true;
		} catch (Exception ex) {
			// transaction.rollback();
			return false;
		}
	}

	/**
	 * 根据传进来的HQL语句更新，返回受影响行数
	 */
	@Override
	public int updateByQuery(String hql) {
		// Transaction transaction =
		// this.sessionFactory.getCurrentSession().beginTransaction();
		int result = this.getSessionFactory().getCurrentSession().createQuery(hql).executeUpdate();
		// transaction.commit();

		return result;
	}

	/**
	 * 根据传进来的ID进行查询
	 */
	@Override
	public T findById(Integer id) {
		T result = this.getSessionFactory().getCurrentSession().get(entityClass, id);
		return result;
	}

	/**
	 * 根据传进来的hql查询
	 */
	@Override
	public List<T> findByQuery(String hql) {
		List<T> result = (List<T>) this.getSessionFactory().getCurrentSession().createQuery(hql).list();
		return result;
	}

	/**
	 * 查找所有表里的所有内容
	 */
	@Override
	public List<T> findAll() {
		List<T> list = null;
		try {
			list = this.getSessionFactory().getCurrentSession().createCriteria(entityClass).list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * jQuery Easyui Datagrid需要的方法，根据传进来的hql，当前页数，页面长度返回数据列
	 */
	@Override
	public List<T> queryWithPage(String hql, int pageNum, int pageSize) {
		List<T> result = this.getSessionFactory().getCurrentSession().createQuery(hql)
				.setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();

		System.out.println("(pageNum - 1) * pageSize:" + (pageNum - 1) * pageSize);
		System.out.println("pageSize:" + pageSize);

		return result;
	}

}
