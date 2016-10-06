package com.ibm.prolaeoc.DAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.ibm.prolaeoc.model.Operator;

public class OperatorDAO {
	
public boolean exits(Operator operator) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		TypedQuery<Operator> query = em.createQuery(
				  " select u from Operator u "
				+ " where u.email = :pEmail and u.password = :pSenha", 
				Operator.class);
		
		query.setParameter("pEmail", operator.getEmail());
		query.setParameter("pSenha", operator.getPassword());
		try {
			Operator result =  query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}finally {
			em.close();
		}		
		return true;
	}
}


