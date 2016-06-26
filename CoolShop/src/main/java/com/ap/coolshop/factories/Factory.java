/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.factories;

import com.ap.coolshop.dal.CustomerDAL;
import com.ap.coolshop.dal.JDBCUow;
import com.ap.coolshop.dal.hibernate.HibernateCustomerDAL;
import com.ap.coolshop.dal.hibernate.HibernateUow;
import com.ap.coolshop.middlelayer.Customer;
import com.ap.coolshop.middlelayer.ICostumer;
import com.ap.coolshop.validations.AddressValidator;
import com.ap.coolshop.validations.BillValidation;
import com.ap.coolshop.validations.CustomerBasicValidation;
import com.ap.coolshop.validations.IValidation;
import com.ap.coolshop.validations.PhoneNumberValidator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ananda
 * @param <AnyType>
 */
public class Factory<AnyType> {
    
    private static Map<String,Object> objects = new HashMap<>();
    private static final String connectionString = "jdbc:mysql://localhost/coolshop";
    private static final String  username = "root";
    private static final String password = "root";
    private static final String driverName= "com.mysql.jdbc.Driver";
    
    public static <AnyType> AnyType getObject(String objectName) throws Exception{
        if(objects.isEmpty()){
            objects.put("JDBC",new CustomerDAL(connectionString,username,password,driverName));
            objects.put("Hibernate",new HibernateCustomerDAL());
            objects.put("JDBCUow",new JDBCUow(connectionString, username, password,driverName));
            objects.put("HibernateUow",new HibernateUow());
        }
        
        IValidation<ICostumer> validator = new PhoneNumberValidator(new CustomerBasicValidation());
        
        if(objectName.equals("Lead")){
            objects.put("Lead",new Customer(validator));
        }
        
        validator = new BillValidation(
                    new AddressValidator(
                    new PhoneNumberValidator(
                    new CustomerBasicValidation())));
        
        if(objectName.equals("Customer")){
            objects.put("Customer",new Customer(validator));
        }
        
        validator = new CustomerBasicValidation();
        
        if(objectName.equals("Self Service")){
            objects.put("Self Service",new Customer(validator));
        }
        
        validator = new AddressValidator(new CustomerBasicValidation());
        
        if(objectName.equals("Home Delivery")){
            objects.put("Home Delivery",new Customer(validator));
        }
        
        return (AnyType)objects.get(objectName);
    }

}
