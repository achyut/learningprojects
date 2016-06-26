/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal.hibernate;

import com.ap.coolshop.dal.Uow;
import com.ap.coolshop.factories.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ananda
 */
public class HibernateUow implements Uow{
    Session session = null;
    Transaction tx = null;
    private static SessionFactory factory;
    
    public Session getSession(){
        session = factory.openSession();
        return session;
    }
    public Transaction getTransaction(){
        tx = session.beginTransaction();
        return tx;
    }
            
    public HibernateUow() throws Exception {
       factory = HibernateSessionFactory.getHibernateFactory();
    }
    
    
    @Override
    public void commit() throws Exception {
        if (tx!=null){
            tx.commit();
            session.close();
        }
    }

    @Override
    public void rollback() throws Exception {
        if (tx!=null){ tx.rollback(); session.close(); }
    }
    
}
