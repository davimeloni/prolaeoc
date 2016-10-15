package com.ibm.prolaeoc.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.DAO.JPAUtil;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Handbag;
import com.ibm.prolaeoc.model.Location;
import com.ibm.prolaeoc.model.Operator;

import antlr.debug.ParserListener;

public class TesteGit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Badge badge = new Badge();
		// badge.setName("Davi");
		Date date = new Date();
		Handbag hb = new Handbag();
		Operator op = new Operator();

		/*
		 * int i = new DAO<Badge>(Badge.class).countAll();
		 * System.out.println(i); //System.out.println(new
		 * DAO<Badge>(Badge.class).listAll());
		 * 
		 * //List<Badge> badges = new DAO<Badge>(Badge.class).listAll();
		 * 
		 * //EntityManager em = new JPAUtil().getEntityManager();
		 * 
		 * //Query query = em.
		 * createQuery("select count(location) As Contador from Badge where status=:pstatus"
		 * ); //query.setParameter("pstatus", "Created");
		 * 
		 * //System.out.println(query.getResultList().toString());
		 * 
		 * List<Badge> badges; badges = new
		 * DAO<Badge>(Badge.class).listHandbag("HORTOLANDIA");
		 * 
		 * for (Badge b: badges) { System.out.println(b.getName()); }
		 * 
		 * System.out.println(date);
		 

		List<String> locations = new DAO<String>(String.class).listCreated();
		List<String> toPrint = new ArrayList<>();
		Set<String> uniqueSet = new HashSet<String>(locations);
		for (String temp : uniqueSet) {
			String e = temp + ": " + Collections.frequency(locations, temp);
			toPrint.add(e);
		}
		List<Badge> bb = new DAO<Badge>(Badge.class).listReception("lalala");
		for(Badge b: bb) {
			System.out.println(b.getHandbag()+ " " + b.getName());
		}*/
		
		//badge = new DAO<Badge>(Badge.class).searchForId(1002);
		
		//long l = Long.parseLong(new DAO<String>(String.class).lastHandbagNumber().substring(5)) + 1;
		
		//hb.setHandbag_number("LAEOC" + String.format("%05d", l));
		
		//System.out.println(hb.getHandbag_number());
		
		//new DAO<Badge>(Badge.class).remove(badge);
		//System.out.println(new DAO<Badge>(Badge.class).listBadgesToActivate());
		
		//badge = new DAO<Badge>(Badge.class).searchForSerial(12346);
		//System.out.println(badge.getName());
		
		op.setEmail("recepcionist@br.ibm.com");
		op.setEnabled(true);
		op.setName("Recepcionist");
		op.setPassword("54321");
		op.setType("RECEPCIONIST");
		
		//hb.setHandbag_number("LAEOC00000");
		
		new DAO<Operator>(Operator.class).add(op);
	}

}
