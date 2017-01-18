package com.ibm.bluepages.samples;

/*
 * BPTester.java v3.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.BPTester
 * (C) Copyright IBM Corporation 1998-2009. All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication, or 
 * disclosure restricted by GSA ADP Contract with IBM Corporation.
 *
 * Enterprise Directory Team
 * IBM Global Services
 *
 */
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;

import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.BPResults;

/**
 * <code>BPTester</code> calls a number of methods in <code>BluePages</code> and
 * <code>BPResults</code> in order to verify the successful installation and 
 * operation of the toolkit.
 * 
 * <p>In the future, this program may indicate a failure that is due to changes 
 * in the database since this program was written instead of an actual problem 
 * with the toolkit installation or operation.
 *  
 * <p>Syntax: <code>java com.ibm.bluepages.samples.BPTester [-u APILOCATORURL]
 * [-d]</code>
 *
 * @version 3.0 - Created on January 22, 1999.<br /><i>Last updated on January
 * 6, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class BPTester {
    public static void main(String args[]) {
        String divider;             // Divider displayed between tests
        BPResults bpr;              // Results of BluePages method
        Hashtable row;              // One row, with column names as keys
        Vector column;              // One column
        Enumeration e;              // Enumeration of hashtable values
        
        divider = "------------------------------------------------------------";
        
        // Check other arguments, if specified.
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-u")) {
                // If specified, set API locator URL before calling BluePages functions.
                if (++i < args.length) {
                    System.setProperty("bluepages.api.locator", args[i]);
                 }
            } else if (args[i].equalsIgnoreCase("-d")) {
                // Turn on the debug..
                System.setProperty("bluepages.debug", "true");
            }
        }
        
        long now = System.currentTimeMillis();
        
        /*
         * Test getClassLevel for BluePages.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getClassLevel() for BluePages");
        System.out.println("Expected results:");
        System.out.println("   Full class level");
        System.out.println();
        System.out.println((String) BluePages.getClassLevel().get("full"));
        
        /*
         * Test getClassLevel for BPResults.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getClassLevel() for BPResults");
        System.out.println("Expected results:");
        System.out.println("   Full class level");
        System.out.println();
        System.out.println((String) BPResults.getClassLevel().get("full"));
        
        /*
         * Test getPersonsByName and getColumn.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByName(\"Szczy\")");
        System.out.println("   getColumn(\"NAME\")");
        System.out.println("Expected results:");
        System.out.println("   List of names preceded by CNUMs");
        System.out.println();
        bpr = BluePages.getPersonsByName("Szczy");
        if (bpr.succeeded()) {
            /*
            column = bpr.getColumn("NAME");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
            */
            for (int i = 0; i < bpr.rows(); i++) {
                row = bpr.getRow(i);
                System.out.println((String)row.get("CNUM") + " - " +
                    (String)row.get("NAME"));
            }

        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        
        /*
         * Test getPersonsByNameLite and getColumn.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByNameLite(\"Green,\")");
        System.out.println("   getColumn(\"name\"), where column name is lowercase");
        System.out.println("Expected results:");
        System.out.println("   List of names preceded by CNUMs");
        System.out.println();
        bpr = BluePages.getPersonsByNameLite("Green,");
        if (bpr.succeeded()) {
            /*
            column = bpr.getColumn("name");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
            */
            for (int i = 0; i < bpr.rows(); i++) {
                row = bpr.getRow(i);
                System.out.println((String)row.get("CNUM") + " - " +
                    (String)row.get("NAME"));
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        
        /*
         * Test getPersonsByNameFuzzy and getColumn. (BBB)
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByNameFuzzy(\"Bali\")");
        System.out.println("   getColumn(\"NAME\")");
        System.out.println("Expected results:");
        System.out.println("   List of names preceded by CNUMs");
        System.out.println();
        bpr = BluePages.getPersonsByNameFuzzy("Bali");
        if (bpr.succeeded()) {
            /*
            column = bpr.getColumn("NAME");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
            */
            for (int i = 0; i < bpr.rows(); i++) {
                row = bpr.getRow(i);
                System.out.println((String)row.get("CNUM") + " - " +
                    (String)row.get("NAME"));
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        
        /*
         * Test getPersonsByNameFuzzyLite and getColumn. (BBB)
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByNameFuzzyLite(\"Bali,\")");
        System.out.println("   getColumn(\"name\"), where column name is lowercase");
        System.out.println("Expected results:");
        System.out.println("   List of names preceded by CNUMs");
        System.out.println();
        bpr = BluePages.getPersonsByNameFuzzyLite("Bali,");
        if (bpr.succeeded()) {
            /*
            column = bpr.getColumn("name");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
            */
            for (int i = 0; i < bpr.rows(); i++) {
                row = bpr.getRow(i);
                System.out.println((String)row.get("CNUM") + " - " +
                    (String)row.get("NAME"));
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        
        /*
         * Test getPersonsByNameFuzzyLite and getColumn. (BBB)
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByNameFuzzyLite(\"Bali,Bora\")");
        System.out.println("   getColumn(\"name\"), where column name is lowercase");
        System.out.println("Expected results:");
        System.out.println("   List of names preceded by CNUMs");
        System.out.println();
        bpr = BluePages.getPersonsByNameFuzzyLite("Bali,Bora");
        if (bpr.succeeded()) {
            /*
            column = bpr.getColumn("name");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
            */
            for (int i = 0; i < bpr.rows(); i++) {
                row = bpr.getRow(i);
                System.out.println((String)row.get("CNUM") + " - " +
                    (String)row.get("NAME"));
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        
        /*
         * Test getPersonByCnum and getRow.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonByCnum(\"6A8614897\")");
        System.out.println("   getRow(0)");
        System.out.println("Expected results:");
        System.out.println("   One name");
        System.out.println();
        bpr = BluePages.getPersonByCnum("6A8614897");
        if (bpr.succeeded()) {
            if (bpr.rows() == 1) {
                row = bpr.getRow(0);
                System.out.println((String) row.get("NAME"));
            } else {
                System.out.println("Error: " + bpr.rows() + " people found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getPersonByCnum with nonexistent CNUM.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonByCnum(\"Z2C4B6M8\"), where CNUM is nonexistent");
        System.out.println("Expected results:");
        System.out.println("   No persons found");
        System.out.println();
        bpr = BluePages.getPersonByCnum("Z2C4B6M8");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("No persons found");
            } else {
                System.out.println("Error: " + bpr.rows() + " persons found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getPersonsBySerial.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsBySerial(\"429180\")");
        System.out.println("Expected results:");
        System.out.println("   List of names");
        System.out.println();
        bpr = BluePages.getPersonsBySerial("429180");
        if (bpr.succeeded()) {
            column = bpr.getColumn("name");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getPersonsBySerial with nonexistent serial number.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsBySerial(\"Z2C4B6\"), where serial number is nonexistent");
        System.out.println("Expected results:");
        System.out.println("   No persons found");
        System.out.println();
        bpr = BluePages.getPersonsBySerial("Z2C4B6");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("No persons found");
            } else {
                System.out.println("Error: " + bpr.rows() + " persons found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getPersonsByNotesID.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByNotesID(\"CN=Bora Bali/\")");
        System.out.println("Expected results:");
        System.out.println("   List of Notes IDs");
        System.out.println();
        bpr = BluePages.getPersonsByNotesID("CN=Bora Bali/");
        if (bpr.succeeded()) {
            column = bpr.getColumn("notesID");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getPersonsByNotesIDLite.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByNotesIDLite(\"CN=Bora Bali/OU=Southbury/O=IBM@IBMUS\")");
        System.out.println("Expected results:");
        System.out.println("   List of Notes IDs");
        System.out.println();
        bpr = BluePages.getPersonsByNotesIDLite("CN=Bora Bali/OU=Southbury/O=IBM@IBMUS");
        if (bpr.succeeded()) {
            column = bpr.getColumn("notesid");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getPersonsByInternet.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getPersonsByInternet(\"balibora@us.ibm.com\")");
        System.out.println("Expected results:");
        System.out.println("   List of names");
        System.out.println();
        bpr = BluePages.getPersonsByInternet("balibora@us.ibm.com");
        if (bpr.succeeded()) {
            column = bpr.getColumn("name");
            for (e = column.elements(); e.hasMoreElements(); ) {
                System.out.println((String) e.nextElement());
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getMgrChainOf.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getMgrChainOf(\"429180897\")");
        System.out.println("Expected results:");
        System.out.println("   List of names of managers");
        System.out.println();
        bpr = BluePages.getMgrChainOf("429180897");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("Error: No managers found");
            } else {
                column = bpr.getColumn("name");
                for (e = column.elements(); e.hasMoreElements(); ) {
                    System.out.println((String) e.nextElement());
                }
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getMgrChainOf with no management chain.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getMgrChainOf(\"484655897\"), where person has no managers");
        System.out.println("Expected results:");
        System.out.println("   No managers found");
        System.out.println();
        bpr = BluePages.getMgrChainOf("484655897");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("No managers found");
            } else {
                System.out.println("Error: " + bpr.rows() + " managers found");
                column = bpr.getColumn("name");
                for (e = column.elements(); e.hasMoreElements(); ) {
                    System.out.println((String) e.nextElement());
                }
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getMgrChainOf with nonexistent CNUM.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getMgrChainOf(\"Z2C4B6M8\"), where CNUM is nonexistent");
        System.out.println("Expected results:");
        System.out.println("   No managers found");
        System.out.println();
        bpr = BluePages.getMgrChainOf("Z2C4B6M8");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("No managers found");
            } else {
                System.out.println("Error: " + bpr.rows() + " managers found");
                column = bpr.getColumn("name");
                for (e = column.elements(); e.hasMoreElements(); ) {
                    System.out.println((String) e.nextElement());
                }
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getDirectReportsOf.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getDirectReportsOf(\"666052897\")");
        System.out.println("Expected results:");
        System.out.println("   Nonzero number of persons found");
        System.out.println();
        bpr = BluePages.getDirectReportsOf("666052897");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("Error: No persons found");
            } else {
                System.out.println(bpr.rows() + " person(s) found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getDirectReportsOfLite.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getDirectReportsOfLite(\"666052897\")");
        System.out.println("Expected results:");
        System.out.println("   Nonzero number of persons found");
        System.out.println();
        bpr = BluePages.getDirectReportsOfLite("666052897");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("Error: No persons found");
            } else {
                System.out.println(bpr.rows() + " person(s) found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getDirectReportsOf with no reports.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getDirectReportsOf(\"429180897\"), where person has no reports");
        System.out.println("Expected results:");
        System.out.println("   No persons found");
        System.out.println();
        bpr = BluePages.getDirectReportsOfLite("429180897");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("No persons found");
            } else {
                System.out.println("Error: " + bpr.rows() + " person(s) found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getDepartmentMembers.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getDepartmentMembers(\"6C\", \"6E3A\")");
        System.out.println("Expected results:");
        System.out.println("   Nonzero number of persons found");
        System.out.println();
        bpr = BluePages.getDepartmentMembers("6C", "6E3A");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("Error: No persons found");
            } else {
                System.out.println(bpr.rows() + " person(s) found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getDepartmentMembersLite.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getDepartmentMembersLite(\"6C\", \"6E3A\")");
        System.out.println("Expected results:");
        System.out.println("   Nonzero number of persons found");
        System.out.println();
        bpr = BluePages.getDepartmentMembersLite("6C", "6E3A");
        if (bpr.succeeded()) {
            if (bpr.rows() == 0) {
                System.out.println("Error: No persons found");
            } else {
                System.out.println(bpr.rows() + " person(s) found");
            }
        } else {
            System.out.println("Error: " + bpr.getStatusMsg());
        }
        
        /*
         * Test getDeptData.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getDeptData(\"6C\", \"6E3A\")");
        System.out.println("Expected results:");
        System.out.println("   Title of department");
        System.out.println();
        row = BluePages.getDeptData("6C", "6E3A");
        if (row.isEmpty()) {
            System.out.println("Error: Department not found or method failed");
        } else {
            System.out.println((String) row.get("TITLE"));
        }
        
        /*
         * Test getCountryCode.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getCountryCode(\"897\")");
        System.out.println("Expected results:");
        System.out.println("   Country code and country name");
        System.out.println();
        row = BluePages.getCountryCode("897");
        if (row.isEmpty()) {
            System.out.println("Error: Country not found or method failed");
        } else {
            System.out.println((String) row.get("CC"));
            System.out.println((String) row.get("COUNTRY"));
        }
        
        /*
         * Test getWorkLoc.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getWorkLocation(\"RPL\")");
        System.out.println("Expected results:");
        System.out.println("   City name");
        System.out.println();
        row = BluePages.getWorkLocation("RPL");
        if (row.isEmpty()) {
            System.out.println("Error: Work location not found or method failed");
        } else {
            System.out.println((String) row.get("CITY"));
        }
        
        /*
         * Test getWorkLoc with nonexistent location.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getWorkLocation(\"---\"), where work location is nonexistent");
        System.out.println("Expected results:");
        System.out.println("   No locations found");
        System.out.println();
        row = BluePages.getWorkLocation("---");
        if (row.isEmpty()) {
            System.out.println("No locations found");
        } else {
            System.out.println("Error: Location found");
        }
        
        /*
         * Test getEmployeeCode.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getEmployeeCode(\"P\")");
        System.out.println("Expected results:");
        System.out.println("   Employee type");
        System.out.println();
        row = BluePages.getEmployeeCode("P");
        if (row.isEmpty()) {
            System.out.println("Error: Employee type not found or method failed");
        } else {
            System.out.println((String) row.get("DESC"));
        }
        
        /*
         * Test getOrganizationCode.
         */
        System.out.println();
        System.out.println(divider);
        System.out.println("Test of:");
        System.out.println("   getOrganizationCode(\"JU\")");
        System.out.println("Expected results:");
        System.out.println("   Organization name");
        System.out.println();
        row = BluePages.getOrganizationCode("JU");
        if (row.isEmpty()) {
            System.out.println("Error: Organization code not found or method failed");
        } else {
            System.out.println((String) row.get("ORGDISPLAY"));
        }
        
        System.out.println("\nElapsed time= " + 
            (System.currentTimeMillis() - now) + " ms.");
    }
}
