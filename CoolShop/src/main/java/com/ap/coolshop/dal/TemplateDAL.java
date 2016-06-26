/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ananda
 * @param <AnyType>
 */
public abstract class TemplateDAL<AnyType> extends CommonDAL<AnyType>{

    Connection conn = null;
    Uow uow = null;
    
    public TemplateDAL(String connectionString,String username,String password,String driverName) {
        super(connectionString,username,password,driverName);
    }
    
    @Override
    public void setUnitOfWork(Uow unit) throws Exception {
        if(unit!=null){
            uow = unit;
            JDBCUow uow1 = (JDBCUow)unit;
            conn = uow1.getConnection();
        }
    }
    
    private void openConnection() throws Exception{
        if(conn==null){
            try {
                Class.forName(driverName);
                conn = DriverManager.getConnection(connectionString,username,password);
            } catch (Exception ex) {
                throw new Exception(ex);
            }
        }        
    }
    
    
    public abstract void executeCommand(AnyType obj) throws Exception;
    public abstract List<AnyType> executeCommand() throws Exception;
    
    private void closeConnection() throws Exception{
        if(uow==null){
            try {
                conn.close();
                conn = null;
            } catch (SQLException ex) {
                throw new Exception(ex);
            }
        }
        
    }
    
    public void execute(AnyType obj) throws Exception{
        openConnection();
        executeCommand(obj);
        closeConnection();
    }

    public List<AnyType> execute() throws Exception{
        List<AnyType> results = null;
        openConnection();
        results = executeCommand();
        closeConnection();
        return results;
    }
    
    @Override
    public void save() throws Exception{
        for(AnyType obj: records){
            execute(obj);
        }
    }

    @Override
    public List<AnyType> search() throws Exception {
        return execute();
    }
    
}
