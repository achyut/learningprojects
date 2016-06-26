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
public class PhoneNumberValidator extends ValidationChain{
    
    public PhoneNumberValidator(IValidation obj) {
        super(obj);
    }

    @Override
    public void validate(ICostumer obj) throws Exception {
        super.validate(obj);
        if(obj.getPhoneNumber().isEmpty()){
            throw new Exception("Phone number cannot be empty!");
        }
        
    }
    
    
    
}
