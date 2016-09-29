package com.ibm.prolaeoc.DAO;

import javax.persistence.Persistence;

public class CreateTable {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("Persistencia");
		}

}
