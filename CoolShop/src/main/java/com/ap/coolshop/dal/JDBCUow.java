/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ananda
 */
public class JDBCUow implements Uow{

    Connection conn = null;
    private String connectionString;
    private String username;
    private String password;
    
    public Connection getConnection() throws Exception{
        try {
            conn = DriverManager.getConnection(this.connectionString,this.username,this.password);
            if(conn!=null){
                conn.setAutoCommit(false);
            }
            return conn;
        }
        catch(Exception e){
            throw e;
        }
    }
    
    public JDBCUow(String connectionString,String username,String password,String driverName) throws Exception {
        Class.forName(driverName);
        this.connectionString = connectionString;
        this.username = username;
        this.password = password;
    }
    
    @Override
    public void commit() throws Exception{
        try {
            conn.commit();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public void rollback() throws Exception{
        try {
            conn.rollback();
        } catch (SQLException ex) {
            throw new Exception(ex);
        }
    }
    
}
