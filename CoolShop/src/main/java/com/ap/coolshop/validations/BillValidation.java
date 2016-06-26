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
public class BillValidation extends ValidationChain{
    
    public BillValidation(IValidation obj) {
        super(obj);
    }

    @Override
    public void validate(ICostumer obj) throws Exception {
        super.validate(obj); 
        
        if(obj.getBillAmount()==null || obj.getBillAmount()==0){
            throw new Exception("Bill amount cannot be empty!");
        }
        
        if(obj.getBillDate()==null){
            throw new Exception("Bill date cannot be empty!");
        }   
    }
    
    
}
