package com.ibm.prolaeoc.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ibm.prolaeoc.DAO.JPAUtil;
import com.ibm.prolaeoc.model.Badge;

public class BadgeRepository {
	
	private EntityManager manager;
	
	/*public void add (Badge badge) {
		
		manager = JPAUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		trx.begin();
		this.manager.persist(badge);
		trx.commit();
	}*/
	
	
	//manager
	
	//query de add
	//query de excluir
	
	//query para dar update nos tickets, merge
	
	//query coletar todos os badges com status pending
	//metodo para separar todos por localidade e mandar pro Bean
	
	//query para coletar os badges de acordo com a localidade selecionada pelo operador
	
	//query para coletar badges de acordo com o numero de malote que a recepcionista colocar
	
	

}
