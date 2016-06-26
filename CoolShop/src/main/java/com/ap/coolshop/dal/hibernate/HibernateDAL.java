/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal.hibernate;

import com.ap.coolshop.dal.IDal;
import com.ap.coolshop.dal.Uow;
import com.ap.coolshop.factories.HibernateSessionFactory;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ananda
 */
public class HibernateDAL<AnyType> implements IDal<AnyType>{
    private static SessionFactory factory;
    List<AnyType> records;
    private Uow uow = null;
    private Class<AnyType> persistentClass;  
    
    private Session session = null;
    private Transaction tx = null;
    
    @Override
    public void setUnitOfWork(Uow unit) throws Exception {
         if(unit!=null){
            uow = unit;
            HibernateUow uow1 = (HibernateUow)unit;
            session = uow1.getSession();
            tx = uow1.getTransaction();
        }
    }
    
    public HibernateDAL() throws Exception {
        factory = HibernateSessionFactory.getHibernateFactory();
        records = new ArrayList<>();
        this.persistentClass = (Class<AnyType>) ((ParameterizedType) getClass()  
                                .getGenericSuperclass()).getActualTypeArguments()[0];  
    }
    
    
    private void openSession(){
        if(session==null){
            session = factory.openSession();
            tx = session.beginTransaction();
        }
    }
    
    private void closeSession(){
        if(uow==null){
            tx.commit();
            session.close();
            session = null;
            tx = null;
        }
    }
    @Override
    public void add(AnyType obj) throws Exception {
        records.add(obj);
    }

    @Override
    public AnyType getData(int row) {
        return records.get(row);
    }

    
    @Override
    public Iterable<AnyType> getData() {
        return records;
    }

    @Override
    public void update(AnyType obj) throws Exception {
        
    }

    public Class<AnyType> getPersistentClass(){
        return this.persistentClass;
    }
    
    @Override
    public Iterable<AnyType> search() throws Exception {
      openSession();
      try{
         Criteria crit = session.createCriteria(getPersistentClass());
         records = crit.list();
         closeSession();
         return records;
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         throw new Exception(e);
      }
    }

    @Override
    public void save() throws Exception {
        openSession();
        try{
         for(AnyType obj: records){
             session.saveOrUpdate(obj);
        }
         closeSession();
         records.clear();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         throw new Exception(e);
      }
    }

    
}
