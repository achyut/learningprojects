/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.validations;

import com.ap.coolshop.middlelayer.ICostumer;

/**
 *
 * @author ananda
 */
public class CustomerBasicValidation implements IValidation<ICostumer>{

    @Override
    public void validate(ICostumer obj) throws Exception {
        if(obj.getCustomerName()==null ){
            throw new Exception("Customer name cannot be null");
        }
        if(obj.getCustomerName().trim().isEmpty()){
            throw new Exception("Customer name cannot be empty!");
        }
    }
    
}
