/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal;

/**
 *
 * @author ananda
 */
public interface Uow {
    public void commit() throws Exception;
    public void rollback() throws Exception;
}
