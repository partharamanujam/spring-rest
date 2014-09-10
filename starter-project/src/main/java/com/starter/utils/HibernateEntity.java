package com.starter.utils;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class HibernateEntity<T> {
	private Class<T> type;

	public HibernateEntity(Class<T> type) {
		this.type = type;
	}

	public T create(T t) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			return t;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}

	public T read(String id) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			T t = (T) session.get(this.type, id);
			session.getTransaction().commit();
			return t;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}

	public T read(Long id) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			T t = (T) session.get(this.type, id);
			session.getTransaction().commit();
			return t;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}

	public T read(BigInteger id) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			@SuppressWarnings("unchecked")
			T t = (T) session.get(this.type, id);
			session.getTransaction().commit();
			return t;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}

	public T update(T t) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
			return t;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}

	public T delete(T t) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(t);
			session.getTransaction().commit();
			return t;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> hqlQuery(String qryStr) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(qryStr);
			List<T> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> sqlQuery(String qryStr) {
		Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createSQLQuery(qryStr).addEntity(this.type);
			List<T> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			if(session != null) {
				HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			}
			throw (e);
		}
	}
}
