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
public class ValidationChain implements IValidation<ICostumer>{

    private IValidation<ICostumer> next = null;
    
    public ValidationChain(IValidation obj) {
        this.next = obj;
    }

    @Override
    public void validate(ICostumer obj) throws Exception {
        next.validate(obj);
    }
    
}
