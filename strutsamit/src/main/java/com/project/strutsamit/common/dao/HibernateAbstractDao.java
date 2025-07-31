package com.project.strutsamit.common.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class HibernateAbstractDao {
	private Session session;
	private Transaction tx;
	private final boolean autoCloseFlag = true;

	public HibernateAbstractDao() {
	}

	protected void autoClose() {
		if (autoCloseFlag && null != session && session.isOpen()) {
			session.close();
			session = null;
		}
	}

	protected void save(Object obj) {
		try {
			startOperation();
			session.save(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			autoClose();
		}
	}

	protected void saveOrUpdate(Object obj) {
		try {
			startOperation();
			session.saveOrUpdate(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			autoClose();
		}
	}

	protected void delete(Object obj) {
		try {
			startOperation();
			session.delete(obj);
			tx.commit();
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			autoClose();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object find(Class clazz, Serializable id) {
		Object obj = null;
		try {
			startOperation();
			obj = session.load(clazz, id);
			tx.commit();
		} catch (ObjectNotFoundException objectNotFound) {
			// Return null if object not found
			obj = null;
		} catch (HibernateException e) {
			handleException(e);
		} finally {
			autoClose();
		}
		return obj;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List findAll(Class clazz) {
		List<Object> objects = null;
		try {
			startOperation();
			objects = session.createQuery("from " + clazz.getName()).getResultList();
			tx.commit();
		} catch (HibernateException hException) {
			handleException(hException);
		} finally {
			autoClose();
		}
		return objects;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List findAllBy(String hql) {
		List<Object> objects = null;
		try {
			startOperation();
			objects = session.createQuery(hql).getResultList();
			tx.commit();
		} catch (HibernateException hException) {
			handleException(hException);
		} finally {
			autoClose();
		}
		return objects;
	}

	protected void handleException(HibernateException hException) {
		tx.rollback();
		throw hException;
	}

	protected void startOperation() throws HibernateException {
		session = HibernateSessionProvider.getSession();
		tx = session.beginTransaction();
	}

	protected Session getSession() {
		return session;
	}

	protected Transaction getTx() {
		return tx;
	}

}