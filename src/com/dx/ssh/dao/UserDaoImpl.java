package com.dx.ssh.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.dx.ssh.entity.UserEntity;

public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao {
	@Override
	public SessionFactory getSessionFactory() {
		System.out.println(sessionFactory);
		return sessionFactory;
	}

	@Override
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public UserEntity login(String acount, String pwd) {
		try {
			String hql = "from UserEntity where acount=? and pwd=?";
			Session session = getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			query.setString(0, acount);
			query.setString(1, pwd);
			return (UserEntity) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
