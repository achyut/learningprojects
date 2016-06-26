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
public class AddressValidator extends ValidationChain{
    
    public AddressValidator(IValidation obj) {
        super(obj);
    }

    @Override
    public void validate(ICostumer obj) throws Exception {
        super.validate(obj); 
        if(obj.getAddress().isEmpty()){
            throw new Exception("Address cannot be empty!");
        }
    }
}
