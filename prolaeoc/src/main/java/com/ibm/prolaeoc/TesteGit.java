package com.ibm.prolaeoc;

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

		badge.setPin(1);
//		hb.setHandbag_number("LAEOC00001");
//		
		op.setEmail("admin3");
		op.setEnabled(true);
		op.setName("Admin3 Name");
		op.setPassword("456");
		op.setType("admin3");
		
		new DAO<Operator>(Operator.class).add(op);
	}

}
