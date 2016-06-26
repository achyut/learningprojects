/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.middlelayer;

import com.ap.coolshop.validations.IValidation;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ananda
 */
@Entity
@Table(name="customers")
public class Customer extends CustomerBase implements Serializable{

    public Customer(IValidation validator) {
        super(validator);
    }

    public Customer() {
        super(null);
    }
}
