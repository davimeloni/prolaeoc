package com.ibm.prolaeoc.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.websocket.Session;

import org.hibernate.Query;

import com.ibm.prolaeoc.model.Badge;

public class DAO<T> {

	private final Class<T> classe;

	public DAO(Class<T> classe) {
		this.classe = classe;
	}

	public void add(T t) {

		// enable the entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// open transaction
		em.getTransaction().begin();

		// persist the object
		em.persist(t);

		// commit the transaction
		em.getTransaction().commit();

		// close the entity manager
		em.close();
	}

	public void remove(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.remove(em.merge(t));

		em.getTransaction().commit();
		em.close();
	}

	public void update(T t) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.merge(t);

		em.getTransaction().commit();
		em.close();
	}

	public List<T> listAll() {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
	
		
		List<T> list = em.createQuery(query).getResultList();

		em.close();
		return list;
	}

	public T searchForId(Integer id) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instance = em.find(classe, id);
		em.close();
		return instance;
	}

	public int countAll() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select count(n) from Badge n")
				.getSingleResult();
		em.close();

		return (int) result;
	}
	
	

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		EntityManager em = new JPAUtil().getEntityManager();
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		List<T> list = em.createQuery(query).setFirstResult(firstResult)
				.setMaxResults(maxResults).getResultList();

		em.close();
		return list;
	}
	
	public List<String> listCreated() {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<String> query = em.createQuery("select location from Badge where status=:pstatus", String.class);
		query.setParameter("pstatus", "Created");
		
		return query.getResultList();
	}
	
	public List<Badge> listHandbag(String location) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Badge> query = em.createQuery("from Badge where status=:pstatus and location=:plocation", Badge.class);
		query.setParameter("pstatus", "Created");
		query.setParameter("plocation", location);
		
		return query.getResultList();
	}

}