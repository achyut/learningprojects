/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal.hibernate;

import com.ap.coolshop.middlelayer.ICostumer;

/**
 *
 * @author ananda
 */
public class HibernateCustomerDAL extends HibernateDAL<ICostumer>{

    public HibernateCustomerDAL() throws Exception {
    }

    @Override
    public void add(ICostumer obj) throws Exception {
        obj.validate();
        super.add(obj); //To change body of generated methods, choose Tools | Templates.
    }
    
}
