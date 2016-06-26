/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal;

import com.ap.coolshop.factories.Factory;
import com.ap.coolshop.middlelayer.ICostumer;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author ananda
 */
public class CustomerDAL extends TemplateDAL<ICostumer>{

    public CustomerDAL(String connectionString, String username, String password,String driverName) {
        super(connectionString, username, password,driverName);
    }

    @Override
    public void add(ICostumer obj) throws Exception {
        obj.validate();
        System.out.println(obj);
        for(ICostumer rec:records){
            if(obj.getId()==rec.getId()){
                return;
            }
        }
        super.add(obj);
    }
    
    

    @Override
    public void executeCommand(ICostumer obj) throws Exception{
        
        if(obj.getId()==null){
            Double billamount = null;
            if(obj.getBillAmount()!=null){
                billamount = obj.getBillAmount();
            }

            String query = "INSERT INTO customers ( customertype, customername, address, phonenumber, billamount, billdate ) VALUES ( '"+obj.getCustomerType()+"', '"+obj.getCustomerName()+"', '"+obj.getAddress()+"', '"+obj.getPhoneNumber()+"', "+billamount+", '"+obj.getBillDate()+"')";
            Statement stmt = conn.createStatement();
            //System.out.println(query);
            stmt.execute(query);
            stmt.close();
        }
        
    }

    @Override
    public List<ICostumer> executeCommand() throws Exception {
        try{
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM customers";
            ResultSet resultset = stmt.executeQuery(sql);
            records.clear();
            while(resultset.next()){
                String customerType = resultset.getString("customertype");
                ICostumer cust = Factory.getObject(customerType);
                cust.setId(resultset.getInt("id"));
                cust.setAddress(resultset.getString("address"));
                cust.setCustomerName(resultset.getString("customername"));
                cust.setCustomerType(customerType);
                cust.setPhoneNumber(resultset.getString("phonenumber"));
                cust.setBillAmount(resultset.getDouble("billamount"));
                cust.setBillDate(resultset.getString("billdate"));
                records.add(cust);
            }
            resultset.close();
            stmt.close();
            return records;
        }
        catch(Exception e){
            throw e;
        }
        
    }
  
}
