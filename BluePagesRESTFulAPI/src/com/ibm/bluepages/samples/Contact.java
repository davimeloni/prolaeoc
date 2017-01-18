package com.ibm.bluepages.samples;

/*
 * Contact.java v3.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.Contact
 * (C) Copyright IBM Corporation 1998-2009. All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication, or 
 * disclosure restricted by GSA ADP Contract with IBM Corporation.
 *
 * Enterprise Directory Team
 * IBM Global Services
 *
 */
import java.text.DecimalFormat;
import java.util.Hashtable;

import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.BPResults;

/**
 * <code>Contact</code> displays the tieline and e-mail address of a person or 
 * persons specified by name.
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.Contact NAME [-d] [-u 
 * APILOCATORURL]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.Contact "Jones, B"</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.Contact "Bali, B" -d -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * </code> 
 *
 * @version 3.0 - Created on January 22, 1999.<br /><i>Last updated on January
 * 8, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class Contact {
    public static void main(String args[]) {
        /*
         * Declarations.
         */
        BPResults results;          // Results of BluePages method
        Hashtable row;              // One row of the results
        String targetName;          // Name (or initial portion) to find
        
        /*
         * Check the arguments.
         */
        if (args.length < 1) {
            System.err.println("Usage: java com.ibm.bluepages.samples.Contact " +                "NAME [-u APILOCATORURL] [-d]");
            return;
        }
        
        targetName = args[0];
        
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
        
        // If specified, set API locator URL before calling BluePages functions.
        if (args.length > 1) {
            System.setProperty("bluepages.api.locator", args[1]);
        }
        
        /*
         * Call the method to fetch the data.
         */
        // results = BluePages.getPersonsByName(targetName);
        results = BluePages.getPersonsByNameFuzzy(targetName);
        
        /*
         * Make sure the method didn't fail unexpectedly.
         */
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return;
        }
        
        /*
         * Display the tie line and e-mail address for each matching
         * person.
         */
        if (results.rows() == 0)
            System.err.println("No matches found.");
        else {
            System.out.println(results.rows() + " record(s) found:\n");
            
            DecimalFormat df = new DecimalFormat("000");
           
            for (int i = 0; i < results.rows(); i++) {
                row = results.getRow(i);
                System.out.println(df.format(i + 1) + ". " + 
                    (String)row.get("NAME"));
                System.out.println("     " + (String) row.get("TIE") + "   "
                        + (String) row.get("EMAILADDRESS"));
            }
        }
    }
}
