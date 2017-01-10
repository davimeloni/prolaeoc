package com.ibm.prolaeoc.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.ibm.prolaeoc.model.Operator;

public class OperatorDAO {
	

public Operator exists(Operator operator) {
	
	System.out.println("Entrou aqui .....");
	
	EntityManager em = new JPAUtil().getEntityManager();
	TypedQuery<Operator> query = em.createQuery(
			  " select u from Operator u "
			+ " where u.email = :pEmail and u.password = :pSenha", 
			Operator.class);
	
	query.setParameter("pEmail", operator.getEmail());
	query.setParameter("pSenha", operator.getPassword());
	try {
				
		Operator result =  query.getSingleResult();
		return result;			
	} catch (NoResultException ex) {			
		return null;
	} finally {
		em.close();
	}		
	
}

//	public Operator getOperatorByEmail(String email) {
//		Operator operator = new Operator();
//		EntityManager em = new JPAUtil().getEntityManager();
//		TypedQuery<Operator> query = em.createQuery(
//				  " select u from Operator u "
//				+ " where u.email = :pEmail", 
//				Operator.class);
//		query.setParameter("pEmail", email);
//		
//		return operator;
//	}
}


