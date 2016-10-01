package com.ibm.prolaeoc.bean;

import javax.persistence.EntityManager;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.DAO.JPAUtil;
import com.ibm.prolaeoc.model.Badge;

public class TesteGit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Badge badge = new Badge();
		
		badge.setName("Davi");
		
		new DAO<Badge>(Badge.class).add(badge);
		

	}

}
