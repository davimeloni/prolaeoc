package com.ibm.bluepages.samples;

/*
 * ClassLevels.java v3.0 - Sample Program
 *
 * Licensed Materials - Property of IBM
 * BluePages Java Toolkit - com.ibm.bluepages.samples.ClassLevels
 * (C) Copyright IBM Corporation 1998-2009. All Rights Reserved.
 * US Government Users Restricted Rights - Use, duplication, or 
 * disclosure restricted by GSA ADP Contract with IBM Corporation.
 *
 * Enterprise Directory Team
 * IBM Global Services
 *
 */
import com.ibm.bluepages.BluePages;
import com.ibm.bluepages.BPResults;
import com.ibm.bluepages.slaphapi.SLAPHAPIResults;

/**
 * <code>ClassLevels</code> displays the software levels of the classes <code>
 * BluePages</code> and <code>BPResults</code>.
 * 
 * <p>Syntax: <code>java com.ibm.bluepages.samples.ClassLevels</code>
 *
 * @version 3.0 - Created on January 22, 1999.<br /><i>Last updated on May 8, 
 * 2003.</i>
 * @author Enterprise Directory Team<br />IBM Global Services<br />
 * edirect@us.ibm.com
 *
 */
public class ClassLevels {
    public static void main(String args[]) {
        System.out.println("BluePages class level is: " + (String)
                BluePages.getClassLevel().get("full"));
        System.out.println("BPResults class level is: " + (String)
                BPResults.getClassLevel().get("full"));
        System.out.println("SLAPHAPIResults class level is: " + (String)
                SLAPHAPIResults.getClassLevel().get("full"));
        
    }
}
