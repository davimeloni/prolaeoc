package com.ibm.bluepages.samples;

/*
 * DumpPerson.java v3.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.DumpPerson
 * (C) Copyright IBM Corporation 1998-2009. All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication, or 
 * disclosure restricted by GSA ADP Contract with IBM Corporation.
 *
 * Enterprise Directory Team
 * IBM Global Services
 *
 */
import java.util.Enumeration;
import java.util.Hashtable;

import com.ibm.bluepages.BPResults;
import com.ibm.bluepages.BluePages;

/**
 * <code>DumpPerson</code> displays all available information about a person 
 * specified by CNUM.
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.DumpPerson CNUM [-d]
 * [-u APILOCATORURL]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.DumpPerson 091445897</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.DumpPerson 091445897 -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * <p>Example: <code>java com.ibm.bluepages.samples.DumpPerson 091445897 -d -u  
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * </code>
 * 
 * @version 3.0 - Created on January 22, 1999.<br /><i>Last updated on January
 * 6, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class DumpPerson {    
    public static void main(String args[]) {
        /*
         * Declarations.
         */
        BPResults results;          // Results of BluePages method
        Hashtable personRow;        // One row of the person's results
        Hashtable miscRow;          // One row of some other results
        Enumeration keys;           // Keys in hashtable
        Enumeration values;         // Values in hashtable
        String targetCnum;          // CNUM to find
        
        /*
         * Check the arguments.
         */
        if (args.length < 1) {
            System.err.println("Usage: java com.ibm.bluepages.samples.DumpPerson " +                "CNUM [-u APILOCATORURL] [-d]");
            return;
        }
        
        targetCnum = args[0];
        
        // Check other arguments, if specified.
        for (int i = 1; i < args.length; i++) {
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
         * Call the method to retrieve the data from the persons table.
         */
        results = BluePages.getPersonByCnum(targetCnum);
        
        /*
         * Make sure the method didn't fail unexpectedly.
         */
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return;
        }
        
        /*
         * Make sure the person is found.
         */
        if (results.rows() == 0) {
            System.err.println("Error: Unable to find person with CNUM " +
                    targetCnum + ".");
            return;
        }
        
        /*
         * Display the person-related information.
         */
        System.out.println("Person Information");
        System.out.println("------------------");
        personRow = results.getRow(0);
        for (keys = personRow.keys(), values = personRow.elements();
                keys.hasMoreElements(); )
            System.out.println((String) keys.nextElement() + ": " +
                    (String) values.nextElement());
        
        /*
         * Call the method to retrieve the data from the departments
         * table.
         */
        miscRow = BluePages.getDeptData((String) personRow.get("DIV"),
                (String) personRow.get("DEPT"));

        /*
         * Display information about the department.
         */
        System.out.println();
        System.out.println("Department Information");
        System.out.println("----------------------");
        
        if (miscRow.size() == 0) {
            System.err.println("Unable to find department information.");
        }
        else {
            for (keys = miscRow.keys(), values = miscRow.elements();
                    keys.hasMoreElements(); )
                System.out.println((String) keys.nextElement() + ": " +
                    (String) values.nextElement());
        }
        
        
        /*
         * Call the method to retrieve the data from the employee types
         * table.
         */
        miscRow = BluePages.getEmployeeCode(
                (String) personRow.get("EMPTYPE"));
        
        /*
         * Display information about the employee type.
         */
        System.out.println();
        System.out.println("Employee Type Information");
        System.out.println("-------------------------");

        if (miscRow.size() == 0) {
            System.err.println("Unable to find employee type information.");
        }
        else {
            for (keys = miscRow.keys(), values = miscRow.elements();
                    keys.hasMoreElements(); )
                System.out.println((String) keys.nextElement() + ": " +
                    (String) values.nextElement());
        }
                
        /*
         * Call the method to retrieve the data from the HR organization codes
         * table.
         */
        miscRow = BluePages.getOrganizationCode(
                (String) personRow.get("ORGCODE"));
        
        /*
         * Display information about the organization code.
         */
        System.out.println();
        System.out.println("Organization Code Information");
        System.out.println("-----------------------------");
        
        if (miscRow.size() == 0) {
            System.err.println("Unable to find organization code information.");
        }
        else {
            for (keys = miscRow.keys(), values = miscRow.elements();
                    keys.hasMoreElements(); )
                System.out.println((String) keys.nextElement() + ": " +
                    (String) values.nextElement());
        }
        
        /*
         * Call the method to retrieve the data from the work locations
         * table.
         */
        miscRow = BluePages.getWorkLocation(
                (String) personRow.get("WORKLOC"));
        
        /*
         * Display information about the work location.
         */
        System.out.println();
        System.out.println("Work Location Information");
        System.out.println("-------------------------");
 
        if (miscRow.size() == 0) {
            System.err.println("Unable to find work location information.");
        }
        else {
            for (keys = miscRow.keys(), values = miscRow.elements();
                    keys.hasMoreElements(); )
                System.out.println((String) keys.nextElement() + ": " +
                    (String) values.nextElement());
        }
        
        /*
         * Call the method to retrieve the data from the countries table.
         */
        miscRow = BluePages.getCountryCode(
                (String) personRow.get("EMPCC"));
        
        /*
         * Display information about the country.
         */
        System.out.println();
        System.out.println("Country Information");
        System.out.println("-------------------");

        if (miscRow.size() == 0) {
            System.err.println("Unable to find country information.");
        }
        else {
            for (keys = miscRow.keys(), values = miscRow.elements();
                    keys.hasMoreElements(); ) 
                System.out.println((String) keys.nextElement() + ": " +
                    (String) values.nextElement());
        }
                
        System.out.println("\nElapsed time= " + 
            (System.currentTimeMillis() - now) + " ms.");
    }    
}
