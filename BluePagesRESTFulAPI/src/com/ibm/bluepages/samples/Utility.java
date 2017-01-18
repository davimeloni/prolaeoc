package com.ibm.bluepages.samples;

/*
 * Utility.java v1.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.Utility
 * (C) Copyright IBM Corporation 1998-2009. All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication, or 
 * disclosure restricted by GSA ADP Contract with IBM Corporation.
 *
 * Enterprise Directory Team
 * IBM Global Services
 *
 */
import java.util.Vector;

import com.ibm.bluepages.BPResults;
import com.ibm.bluepages.BluePages;

/**
 * <code>Utility</code> calls these query functions:  
 * <code>BluePages.getCountryCodes()</code>,
 * <code>BluePages.getDeptDataAll()</code>,  
 * <code>BluePages.getEmployeeCodes()</code>,
 * <code>BluePages.getOrganizationCodes()</code>, and
 * <code>BluePages.getWorkLocations()</code>.
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.Utility TYPE [-d]
 * [-u APILOCATORURL]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.Utility COUNTRIES</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.Utility DEPTS -d</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.Utility EMPCODES-u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * <p>Example: <code>java com.ibm.bluepages.samples.Utility ORGCODES -d -u  
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * </code>
 * <p>Example: <code>java com.ibm.bluepages.samples.Utility WORKLOCS</code>
 * 
 * @version 1.0 - Created on August 30, 2009.<br /><i>Last updated on September
 * 19, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class Utility {    
    public static void main(String args[]) {
        /*
         * Declarations.
         */
        BPResults results = null;   // Results of BluePages method
        String utilType;            // Utility type
        String columnName = null;   // Column name
        
        /*
         * Check the arguments.
         */
        if (args.length < 1) {
            System.err.println("Usage: java com.ibm.bluepages.samples.Utility " +                "COUNTRIES|DEPTS|EMPCODES|ORGCODES|WORKLOCS [-u APILOCATORURL] [-d]");
            System.exit(1);
        }
        
        utilType = args[0];
        
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
        
        if (utilType.equalsIgnoreCase("COUNTRIES")) {
            results = BluePages.getCountryCodes();
            columnName = "COUNTRY";
        } else if (utilType.equalsIgnoreCase("DEPTS")) {
            results = BluePages.getDeptDataAll();
            columnName = "TITLE";
        } else if (utilType.equalsIgnoreCase("EMPCODES")) {
            results = BluePages.getEmployeeCodes();
            columnName = "DESC";
        } else if (utilType.equalsIgnoreCase("ORGCODES")) {
            results = BluePages.getOrganizationCodes();
            columnName = "ORGDISPLAY";
        } else if (utilType.equalsIgnoreCase("WORKLOCS")) {
            results = BluePages.getWorkLocations();
            columnName = "CITY";
        } else {
            System.err.println("Invalid utility type: " + utilType);
            System.exit(1);
        }
        
        /*
         * Make sure the method didn't fail unexpectedly.
         */
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return;
        }
        
        /*
         * Make sure there are rows in the table.
         */
        if (results.rows() == 0) {
            System.err.println("Error: Unable to return " + utilType);
            return;
        }
        
        
        /*
         * Get one of the columns in a vector..
         */
        Vector column = results.getColumn(columnName);
        
        
        /*
         * Display 10 rows from that column..
         */

        int i = 10;
        if (column.size() < i) {
            i = column.size();
        }
        
        System.out.println("Displaying " + i + " rows from the " + columnName + 
            " column:");
        System.out.println("----------------------------------------------");
        
        for (int r = 0; r < i; r++) {
            System.out.println((String)column.elementAt(r));
        }
                
        System.out.println("\nElapsed time= " + 
            (System.currentTimeMillis() - now) + " ms.");
    }    
}
