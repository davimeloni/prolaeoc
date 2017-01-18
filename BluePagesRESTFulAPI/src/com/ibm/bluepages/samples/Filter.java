package com.ibm.bluepages.samples;

/*
 * Filter.java v3.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.Filter
 * (C) Copyright IBM Corporation 1998-2009. All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication, or 
 * disclosure restricted by GSA ADP Contract with IBM Corporation.
 *
 * Enterprise Directory Team
 * IBM Global Services
 *
 */
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Enumeration;

import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.slaphapi.LDAPEntry;
import com.ibm.bluepages.slaphapi.LDAPAttribute;
import com.ibm.bluepages.slaphapi.SLAPHAPIResults;

/**
 * <code>Filter</code> searches the Enterprise Directory for the specified filter
 * using SLAPHAPI (Standalone LDAP HTTP API).
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.Filter OBJECTCLASS
 * LDAPFILTER [-u APILOCATORURL] [-d]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.Filter ibmperson 
 * uid=091445897 -d</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.Filter ibmperson
 * uid=091445897 -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * </code>
 * 
 * @version 3.0 - Created on January 22, 1999.<br /><i>Last updated on January
 * 10, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class Filter {
    public static void main(String args[]) throws UnsupportedEncodingException {
        //
        // Check the arguments.
        //
        if (args.length < 2) {
            System.err.println("Usage: java com.ibm.bluepages.samples.Filter " +                "OBJECTCLASS LDAPFILTER [-u APILOCATORURL] [-d]");
            return;
        }
        
        String objectClass = args[0];
        String filter = args[1];
        
        // Check other arguments, if specified.
        for (int i = 2; i < args.length; i++) {
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
        
        // URL-encode the specified SLAPHAPI/LDAP filter and call the SLAPHAPI 
        // method to retrieve the BP data.
        SLAPHAPIResults results = 
            BluePages.callSLAPHAPI(objectClass + "/" + 
                URLEncoder.encode(filter, BluePages.DEFAULT_SLAPHAPI_ENCODING));
        
        // Make sure the method didn't fail unexpectedly.
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return;
        }
        
        // Check to see whether SLAPHAPI returned any results.
        if (results.getSize() == 0) {
            System.err.println("Error: SLAPHAPI search returned no results.");
            return;
        }
        
        String attrID;            // Attribute Name
        LDAPEntry entry;          // Hashtable to store LDAPAttribute instances
        LDAPAttribute attribute;  // Vector to store the values
        
        // Display SLAPHAPI results.
        
        // Get an enumerator for the DNs found in the search results.
        // Iterate through DNs and get attributes for each DN.
        for (Enumeration e = results.getEntries(); e.hasMoreElements(); ) {
            entry = (LDAPEntry) e.nextElement();
            
            // Display the DN.
            System.out.println("DN: " + entry.getDN());
            
            // Get an enumerator for the attributes returned for the current DN.
            // Iterate through attributes and get values for each attribute.
            for (Enumeration a = entry.getAttributes(); a.hasMoreElements(); ) {
                // Get the attributes.
                attribute = (LDAPAttribute)a.nextElement();
                
                // Get attribute name.
                attrID = attribute.getID();
                
                // Get the values for the current attribute in an enumerator.
                // Iterate through values and display each.
                for (Enumeration v = attribute.getValues(); v.hasMoreElements(); ) {
                    System.out.println("    " + attrID + ": " +
                            (String)v.nextElement());
                }
            }
            System.out.println(" ");
        }
    }
}
