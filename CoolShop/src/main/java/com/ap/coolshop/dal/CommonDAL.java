/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal;

import com.ap.coolshop.middlelayer.ICostumer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ananda
 * @param <AnyType>
 */
public abstract class CommonDAL<AnyType> implements IDal<AnyType>{
    List<AnyType> records;
    protected String connectionString = "";
    protected String username = "";
    protected String password = "";
    protected String driverName = "";
    
    public CommonDAL(String connectionString,String username,String password,String driverName) {
        this.records = new ArrayList<>();
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
        this.driverName = driverName;
    }
    
    @Override
    public void add(AnyType obj) throws Exception{
        records.add(obj);
    }

    @Override
    public Iterable<AnyType> getData() {
        return records;
    }

    @Override
    public AnyType getData(int row) {
        return records.get(row);
    }
    
    @Override
    public void update(AnyType obj) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AnyType> search() throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save() throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
