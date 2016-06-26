/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.factories;

import com.ap.coolshop.middlelayer.Customer;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author ananda
 */
public class HibernateSessionFactory {
    private static SessionFactory factory = null;

    public static SessionFactory getHibernateFactory() throws Exception{
        if(factory==null){
            try{
            factory = new Configuration().
                  configure().
                //addPackage("com.ap.coolshop.middlelayer").
                addAnnotatedClass(Customer.class).
                  buildSessionFactory();
            }catch (Throwable ex) { 
             System.err.println("Failed to create sessionFactory object." + ex);
             throw new Exception(ex); 
            }
        }
        return factory;
    }
}
