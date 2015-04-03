package com.icker.pm.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 采用JPA的基类
 * @author Icker
 *
 * @param <T>
 */
public class BaseDao<T> {
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll(String hql) throws Exception {
		Query query = entityManager.createQuery(hql);
		return query.getResultList();
	}

	public Object findById(Class<T> c, Object id) {
		return entityManager.find(c, id);
	}

	public Object findByParam(Class<T> c, Object param, String hql) {
		
		return entityManager.find(c, param);
	}
	
	public boolean save(Object object) {
		entityManager.persist(object);
		return true;
	}

	public boolean update(Object object) {
		entityManager.merge(object);
		return true;
	}

	public boolean delete(Object object) {
		entityManager.remove(object);
		return true;
	}
	
}
