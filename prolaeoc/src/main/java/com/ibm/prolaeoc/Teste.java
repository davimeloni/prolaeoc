package com.ibm.prolaeoc;

import javax.persistence.EntityManager;

import com.ibm.prolaeoc.DAO.JPAUtil;
import com.ibm.prolaeoc.model.Badge;

public class Teste {

	public static void main(String[] args) {

		Badge badge = null;
		
		badge.setName("Davi");

		// consegue a entity manager
		EntityManager em = new JPAUtil().getEntityManager();

		// abre transacao
		em.getTransaction().begin();

		// persiste o objeto
		em.persist(badge);

		// commita a transacao
		em.getTransaction().commit();

		// fecha a entity manager
		em.close();
	}

}
