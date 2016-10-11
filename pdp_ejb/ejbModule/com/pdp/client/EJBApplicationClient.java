package com.pdp.client;

import javax.naming.NamingException;

import javax.naming.Context;
import com.pdp.business.LibraryPersistentBeanRemote;
import com.pdp.businesslogic.LibraryPersistentBean;
import com.pdp.clientutility.ClientUtility;
import com.pdp.model.Book;

public class EJBApplicationClient {
    
    public static void main(String[] args) {
    	LibraryPersistentBeanRemote libraryBean = doLookup();
        libraryBean.addBook(new Book("Book one"));
        libraryBean.addBook(new Book("Second book"));
        System.out.println(libraryBean.getBooks()); // 4. Call business logic
    }
 
    private static LibraryPersistentBeanRemote doLookup() {
        Context context = null;
        LibraryPersistentBeanRemote bean = null;
        try {
            // 1. Obtaining Context
            context = ClientUtility.getInitialContext();
            // 2. Generate JNDI Lookup name
            String lookupName = getLookupName();
            // 3. Lookup and cast
            bean = (LibraryPersistentBeanRemote) context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
    }
 
    private static String getLookupName() {

        String appName = "";
 
        String moduleName = "pdp_ejb";

        String distinctName = "";
 
        // The EJB bean implementation class name
        String beanName = LibraryPersistentBean.class.getSimpleName();
 
        // Fully qualified remote interface name
        final String interfaceName = LibraryPersistentBeanRemote.class.getName();
 
        // Create a look up string name
        String name = "ejb:" + appName + "/" + moduleName + "/" + 
            distinctName    + "/" + beanName + "!" + interfaceName;
 
        return name;
    }
    
}
