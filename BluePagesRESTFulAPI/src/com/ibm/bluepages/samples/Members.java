package com.ibm.bluepages.samples;

/*
 * Members.java v3.0.4 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.Members
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
import java.util.HashMap;
import java.util.Map;

import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.slaphapi.LDAPEntry;
import com.ibm.bluepages.slaphapi.LDAPAttribute;
import com.ibm.bluepages.slaphapi.SLAPHAPIResults;

/**
 * <code>Members</code> retrieves the DNs of the members of the BlueGroups
 * that match the specified group name. 
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.Members GROUPNAME
 * [-u APILOCATORURL] [-d]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.Members MQ* -d 
 * <p>Example: <code>java com.ibm.bluepages.samples.Members mygroup -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * </code>
 * 
 * @version 3.0.4 - Created on January 18, 2006.<br /><i>Last updated on 
 * September 14, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class Members {
    public static void main(String args[]) throws UnsupportedEncodingException {
        //
        // Check the arguments.
        //
        if (args.length < 1) {
            System.err.println("Usage: java com.ibm.bluepages.samples.Members " +                "GROUPNAME [-u APILOCATORURL] [-d]");
            return;
        }
        
        String groupName = args[0];
        
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
        
        // Prepare the SLAPHAPI search parameters..
        Map parms = new HashMap();
        parms.put(SLAPHAPIResults.SLAPHAPI_SEARCH_PARM_BASE, "base");        
        
        // URL-encode the specified SLAPHAPI/LDAP filter and call the SLAPHAPI 
        // method to retrieve the BP data.        
        SLAPHAPIResults results = 
            BluePages.callSLAPHAPI("ou=memberlist,ou=ibmgroups,o=ibm.com/(cn=" +
                URLEncoder.encode(groupName, BluePages.DEFAULT_SLAPHAPI_ENCODING) + 
                ")", new String[] { "uniquemember", "cn" }, parms);
        
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
            
            // Get the CN attribute which contains the name of the group..
            attribute = entry.getAttribute("cn");
            
            // Display the name of the group..
            System.out.println("Group: " + attribute.getValue());
            
            // Remove "cn" from the entry so it isn't displayed twice.. 
            entry.removeAttribute("cn");
            
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
