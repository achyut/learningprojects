/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.dal;

/**
 *
 * @author ananda
 * @param <AnyType>
 */
public interface IDal<AnyType> {
    
    public void setUnitOfWork(Uow unit) throws Exception;
    public void add(AnyType obj) throws Exception;
    public void update(AnyType obj) throws Exception;
    public Iterable<AnyType> search() throws Exception;
    public void save() throws Exception;
    public Iterable<AnyType> getData();
    public AnyType getData(int row);
}
