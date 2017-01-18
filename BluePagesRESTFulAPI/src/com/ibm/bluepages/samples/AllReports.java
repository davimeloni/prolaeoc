package com.ibm.bluepages.samples;

/*
 * AllReports.java v3.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.slaphapi.AllReports
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
 * <code>AllReports</code> displays a reporting hierarchy consisting of the 
 * person specified by CNUM and all reports at all lower levels.
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.AllReports CNUM
 * [-u APILOCATORURL] [-d]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.AllReports 089596897</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.AllReports 089596897 -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * <p>Example: <code>java com.ibm.bluepages.samples.AllReports 089596897 -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator -d 
 * </code> 
 *
 * @version 3.0 - Created on January 22, 1999.<br /><i>Last updated on January 
 * 6, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class AllReports {
    public static void main(String args[]) {

        /*
         * Declarations.
         */
        String targetCnum;          // CNUM to find

        /*
         * Check the arguments.
         */
        if (args.length < 1) {
            System.err.println("Usage: java com.ibm.bluepages.samples.AllReports " +                "CNUM [-u APILOCATORURL] [-d]");
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
         * Call the method that displays the current person and recurses
         * on the direct reports.
         */
        displayPersonAndRecurseReports(targetCnum, 1);  
        
        System.out.println("\nElapsed time= " + 
            (System.currentTimeMillis() - now) + " ms.");
    }

    /*
     * Displays the current person and recurses on the direct reports.
     *
     * The method is inefficient because it calls getPersonByCnum to
     * fetch a name that was already available from the last call to
     * getDirectReportsOfLite, but it makes the code more
     * straightforward.
     */
    private static void displayPersonAndRecurseReports(String cnum,
        int depth) {        
        /*
         * Declarations.
         */
        BPResults results;          // Results of BluePages operation
        int i;                      // Loop counter
        Vector column;              // Column
        String indent;              // Blanks for indenting

        /*
         * Build the string needed to indent according to the depth.
         */
        indent = "";
        for (i = 1; i < depth; i++)
            indent = indent.concat("   ");

        /*
         * Fetch the data for the specified person.
         */
        results = BluePages.getPersonByCnum(cnum);

        /*
         * Make sure the method didn't fail unexpectedly.
         */
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return;
        }

        /*
         * Make sure the person was found.
         */
        column = results.getColumn("NAME");
        if (column.size() == 0) {
            System.err.println("Error: Unable to find person with CNUM " +
                cnum + ".");
            return;
        }

        Vector column2 = results.getColumn("CNUM");

        /*
         * Display the name of the current person.
         */
        System.out.println(indent + column2.elementAt(0) + " - " + 
            (String)column.elementAt(0));

        /*
         * Fetch the direct reports.  The light method is fine because we
         * need only the cnum.
         */
        results = BluePages.getDirectReportsOfLite(cnum);

        /*
         * Make sure the method didn't fail unexpectedly.
         */
        if (!results.succeeded()) {
            System.err.println("Error: BluePages method failed unexpectedly.");
            return;
        }

        /*
         * Call this method recursively to handle each direct report.
         */
        column = results.getColumn("CNUM");
        for (i = 0; i < column.size(); i++) {
            displayPersonAndRecurseReports((String) column.elementAt(i),
                depth + 1);
        }        
    }    
}
