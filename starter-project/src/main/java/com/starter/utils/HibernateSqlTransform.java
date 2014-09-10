package com.starter.utils;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

public class HibernateSqlTransform<T> {
	private Class<T> type;

	public HibernateSqlTransform(Class<T> type) {
		this.type = type;
	}

	public T readOne(String sqlQry) {
		try {
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery(sqlQry);
			query.setResultTransformer(Transformers.aliasToBean(this.type));
			@SuppressWarnings("unchecked")
			List<T> list = query.list();
			session.getTransaction().commit();
			if (list.isEmpty()) {
				return null;
			} else {
				return list.get(0);
			}			
		} catch (Exception e) {
			HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			throw(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> read(String sqlQry, int firstResult, int maxResults) {
		try {
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery(sqlQry);
			if(firstResult >= 0) {
				query.setFirstResult(firstResult);
			}
			if(maxResults > 0) {
				query.setMaxResults(maxResults);
			}
			query.setResultTransformer(Transformers.aliasToBean(this.type));
			List<T> list = query.list();
			session.getTransaction().commit();
			return list;			
		} catch (Exception e) {
			HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			throw(e);
		}
	}

	public List<T> read(String sqlQry) {
		return read(sqlQry, -1, -1);
	}

	public BigInteger count(String sqlQry) {
		try {
			Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT COUNT(*) AS count FROM (" + sqlQry + ") temp");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			@SuppressWarnings("unchecked")
			Map<String, Object> result = (Map<String, Object>) query.list().get(0);
			BigInteger count = (BigInteger) result.get("count");
			session.getTransaction().commit();
			return count;			
		} catch (Exception e) {
			HibernateSessionFactory.getSessionFactory().getCurrentSession().getTransaction().rollback();
			throw(e);
		}
	}
}
