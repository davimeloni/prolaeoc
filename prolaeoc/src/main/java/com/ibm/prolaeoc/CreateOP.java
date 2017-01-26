package com.ibm.prolaeoc;

import java.util.Date;

import com.ibm.prolaeoc.DAO.DAO;
import com.ibm.prolaeoc.model.Badge;
import com.ibm.prolaeoc.model.Handbag;
import com.ibm.prolaeoc.model.Operator;

public class CreateOP {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Badge badge = new Badge();

		Date date = new Date();
		Handbag hb = new Handbag();
		Operator op = new Operator();

		badge.setPin(1);
//		hb.setHandbag_number("LAEOC00001");
//		
		op.setEmail("operator4");
		op.setEnabled(true);
		op.setName("Operator 4 name");
		op.setPassword("123");
		op.setType("Operator");
		
		new DAO<Operator>(Operator.class).add(op);
	}

}
