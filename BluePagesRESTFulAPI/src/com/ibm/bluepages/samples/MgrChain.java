package com.ibm.bluepages.samples;

/*
 * MgrChain.java v3.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.MgrChain
 * (C) Copyright IBM Corporation 1998-2009. All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication, or 
 * disclosure restricted by GSA ADP Contract with IBM Corporation.
 *
 * Enterprise Directory Team
 * IBM Global Services
 *
 */
import java.util.Vector;

import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.BPResults;

/**
 * <code>MgrChain</code> displays the manager chain of a person specified by 
 * CNUM.
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.MgrChain CNUM 
 * [-u APILOCATORURL] [-d]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.MgrChain 642180897 -d</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.MgrChain 642180897 -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * </code>
 *
 * @version 3.0 - Created on January 22, 1999.<br /><i>Last updated on January
 * 10, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class MgrChain {
    public static void main(String args[]) {
        /*
         * Declarations.
         */
        BPResults results;          // Results of BluePages method
        Vector deptColumn;          // Column of departments
        Vector nameColumn;          // Column of names
        Vector cnumColumn;          // Column of CNUMs
        String targetCnum;          // CNUM to find
        
        /*
         * Check the arguments.
         */
        if (args.length < 1) {
            System.err.println("Usage: java com.ibm.bluepages.samples.MgrChain " +                "CNUM [-u APILOCATOR] [-d]");
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
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return;
        }
        if (results.rows() == 0) {
            System.err.println("Error: Unable to find person with CNUM " +
                    targetCnum + ".");
            return;
        }
        
        /*
         * Call the method to fetch the data.
         */
        results = BluePages.getMgrChainOf(targetCnum);
        
        /*
         * Make sure the method didn't fail unexpectedly.
         */
        if (!results.succeeded()) {
            System.err.println("Error: BluePages method failed unexpectedly.");
            return;
        }
        
        /*
         * Display the manager chain.
         */
        if (results.rows() == 0)
            System.out.println("Person has no manager chain.");
        else {
            deptColumn = results.getColumn("DEPT");
            nameColumn = results.getColumn("NAME");
            cnumColumn = results.getColumn("CNUM");
            for (int i = 0; i < deptColumn.size(); i++) {
                System.out.println((String) deptColumn.elementAt(i) + ": " +
                        (String) nameColumn.elementAt(i) + " (" +
                        (String) cnumColumn.elementAt(i) + ")");
            }
        }

        System.out.println("\nElapsed time= " + 
            (System.currentTimeMillis() - now) + " ms.");
    }    
}
