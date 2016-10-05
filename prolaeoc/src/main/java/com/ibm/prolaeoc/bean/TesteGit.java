package com.ibm.prolaeoc.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.DAO.JPAUtil;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Location;

public class TesteGit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Badge badge = new Badge();
		//badge.setName("Davi");
		Date date = new Date();
		
		int i = new DAO<Badge>(Badge.class).countAll();
		System.out.println(i);
		//System.out.println(new DAO<Badge>(Badge.class).listAll());
		
		//List<Badge> badges = new DAO<Badge>(Badge.class).listAll();
		
		//EntityManager em = new JPAUtil().getEntityManager();
		
		//Query query = em.createQuery("select count(location) As Contador from Badge where status=:pstatus");
		//query.setParameter("pstatus", "Created");
		
		//System.out.println(query.getResultList().toString());
		
		List<Badge> badges;
		badges = new DAO<Badge>(Badge.class).listHandbag("HORTOLANDIA");
		
		for (Badge b: badges) {
			System.out.println(b.getName());
		}
		
		System.out.println(date);
		
	}

}
