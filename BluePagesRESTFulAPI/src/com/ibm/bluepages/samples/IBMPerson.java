package com.ibm.bluepages.samples;

/*
 * IBMPerson.java v3.0.4 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.IBMPerson
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
import java.util.HashMap;
import java.util.Map;

import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.slaphapi.LDAPAttribute;
import com.ibm.bluepages.slaphapi.LDAPEntry;
import com.ibm.bluepages.slaphapi.SLAPHAPIResults;

/**
 * <code>IBMPerson</code> searches the Enterprise Directory for a given UID 
 * (i.e., CNUM) using SLAPHAPI (Standalone LDAP HTTP API).
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.IBMPerson UID [-u 
 * APILOCATORURL] [-d]</code>
 * 
 * <p>Example: <code>java com.ibm.bluepages.samples.IBMPerson 091445897 -d</code>
 * <p>Example: <code>java com.ibm.bluepages.samples.IBMPerson 091445897 -u 
 * http://bluepages.ibm.com/BpHttpApisv3/apilocator
 * </code>
 *  
 * @version 3.0.4 - Created on January 22, 1999.<br /><i>Last updated on 
 * September 14, 2009.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class IBMPerson {
    public static void main(String args[]) throws UnsupportedEncodingException {        
        //
        // Check the arguments.
        //
        if (args.length < 1) {
            System.err.println("Usage: java com.ibm.bluepages.samples.IBMPerson " +                "UID [-u APILOCATORURL] [-d]");
            return;
        }
        
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
        
        // URL-encode the specified UID and construct the SLAPHAPI filter..
        String filter = "ibmperson/" + URLEncoder.encode("(uid=" + args[0] + 
            ")", BluePages.DEFAULT_SLAPHAPI_ENCODING);
        
        // Retrieve these attributes only.
        String[] attributes = { "notesemail", "tieline", "mail", "uid", 
            "telephonenumber", "sn", "cn", "additional" };
                
        // Prepare the SLAPHAPI search parameters..
        Map parms = new HashMap();
        parms.put(SLAPHAPIResults.SLAPHAPI_SEARCH_PARM_PRINTABLE, "false");
        parms.put(SLAPHAPIResults.SLAPHAPI_SEARCH_PARM_ENCODING, "ISO-8859-1");  
        
        //
        // Call the SLAPHAPI method to retrieve the BP data (all attributes).
        //
        SLAPHAPIResults results = BluePages.callSLAPHAPI(filter, attributes, parms);
        
        //
        // Make sure the method didn't fail unexpectedly.
        //
        if (!results.succeeded()) {
            System.err.println("Error: " + results.getStatusMsg());
            return;
        }
        
        //
        // Check to see whether SLAPHAPI returned any results.
        //
        if (results.getSize() == 0) {
            System.err.println("Error: SLAPHAPI search returned no results.");
            return;
        }
        
        //
        // Display SLAPHAPI results.
        //
        LDAPEntry ibmPerson = results.getEntry();
        if (ibmPerson != null) {
            String value = null;
            
            // Get the UID attribute..
            LDAPAttribute attr = ibmPerson.getAttribute("uid");
            // If it exists, display its first value..
            if (attr != null) { 
                value = attr.getValue();
                System.out.println("uid:             " + value);
            }
            
            // Get the NOTESEMAIL attribute..
            attr = ibmPerson.getAttribute("notesemail");
            // If it exists, display its first value..
            if (attr != null) {
                value = attr.getValue();
                System.out.println("notesemail:      " + value);
            }
            
            // Get the MAIL attribute..
            attr = ibmPerson.getAttribute("mail");
            // If it exists, display its first value..
            if (attr != null) {
                value = attr.getValue();
                System.out.println("mail:            " + value);
            }
            
            // Get the TIELINE attribute..
            attr = ibmPerson.getAttribute("tieline");
            // If it exists, display its first value..
            if (attr != null) {
                value = attr.getValue();
                System.out.println("tieline:         " + value);
            }
            
            // Get the TELEPHONENUMBER attribute..
            attr = ibmPerson.getAttribute("telephonenumber");
            // If it exists, display its first value..
            if (attr != null) {
                value = attr.getValue();
                System.out.println("telephonenumber: " + value);
            }
            
            // Get the ADDITIONAL attribute..
            attr = ibmPerson.getAttribute("additional");
            // If it exists, display its first value..
            if (attr != null) {
                value = attr.getValue();
                System.out.println("additional:      " + value);
            }
        }
    }
}
