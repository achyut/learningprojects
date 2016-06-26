/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.validations;

/**
 *
 * @author ananda
 */
public interface IValidation<AnyType> {
    
    public void validate(AnyType obj) throws Exception;
}
