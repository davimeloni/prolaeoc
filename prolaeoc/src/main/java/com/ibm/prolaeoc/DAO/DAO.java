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

	public T searchForId(long l) {
		EntityManager em = new JPAUtil().getEntityManager();
		T instance = em.find(classe, l);
		em.close();
		return instance;
	}

	public Badge searchForSerial(String s) {
		s = s.toUpperCase();
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Badge> query = em.createQuery("from Badge where uid=:puid and status=:pstatus", Badge.class);
		query.setParameter("puid", s);
		query.setParameter("pstatus", "InReception");

		return query.getSingleResult();
	}

	public Long countAll() {
		EntityManager em = new JPAUtil().getEntityManager();
		Long result = (Long) em.createQuery("select count(n) from Badge n").getSingleResult();
		em.close();

		return result;
	}

	public static List<Object[]> listBadgesByOperator() {

		EntityManager em = new JPAUtil().getEntityManager();

		String jpql = "SELECT op.name, COUNT(b.id) as total FROM Badge b INNER JOIN Operator op ON (b.operator = op.id) GROUP BY op.name ORDER BY op.name ASC";

		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		// query.setParameter("operatorID",operatorID);

		return query.getResultList();
	}

	public static List<Object[]> listBadgesByStatus() {

		EntityManager em = new JPAUtil().getEntityManager();

		String jpql = "SELECT status, COUNT(status) from Badge "
				+ "GROUP BY status ORDER BY status ASC";

		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
		// query.setParameter("operatorID",operatorID);

		return query.getResultList();
	}

	public List<String> listCreated() {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<String> query = em.createQuery("select location from Badge where status=:pstatus", String.class);
		query.setParameter("pstatus", "Created");
		//em.close();
		return query.getResultList();
	}

	public List<Badge> listHandbag(String location) {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Badge> query = em.createQuery("from Badge where status=:pstatus and location=:plocation",
				Badge.class);
		query.setParameter("pstatus", "Created");
		query.setParameter("plocation", location);
		//em.close();
		return query.getResultList();
	}

	public List<Badge> listReception(String handbag) {
		handbag = handbag.toUpperCase();
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Badge> query = em.createQuery("from Badge where handbag=:phandbag and status=:pstatus ",
				Badge.class);
		query.setParameter("phandbag", handbag);
		query.setParameter("pstatus", "SentToReception");
		
		return query.getResultList();
	}

	public Long lastBadgeForPIN() {
		EntityManager em = new JPAUtil().getEntityManager();
		long result = (Long) em.createQuery("select pin from Badge ORDER by id DESC").setMaxResults(1)
				.getSingleResult();
		em.close();
		return result;
	}

	public String lastHandbagNumber() {
		EntityManager em = new JPAUtil().getEntityManager();
		String result = (String) em.createQuery("select handbag_number from Handbag ORDER by id DESC").setMaxResults(1)
				.getSingleResult();
		em.close();
		return result;
	}

	public List<Badge> listBadgesToActivate() {
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Badge> query = em.createQuery("from Badge where status=:pstatus", Badge.class);
		query.setParameter("pstatus", "InReception");
		em.close();
		return query.getResultList();

	}

	public List<Badge> listReportByUID(String s) {
		s = s.toUpperCase();
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Badge> query = em.createQuery("from Badge where uid=:puid", Badge.class);
		query.setParameter("puid", s);

		return query.getResultList();
	}

}