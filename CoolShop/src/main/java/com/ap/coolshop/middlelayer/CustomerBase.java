/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.middlelayer;

import java.util.Date;
import com.ap.coolshop.validations.IValidation;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 *
 * @author ananda
 */
@MappedSuperclass
public abstract class CustomerBase implements ICostumer{
    
    @Transient
    private IValidation validator = null;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Integer id;
    
    @Column(name = "customername")
    protected String customerName;
    
    @Column(name = "phonenumber")
    protected String phoneNumber;
    
    @Column(name = "billamount")
    protected Double billAmount;
    
    @Column(name = "billdate")
    protected String billDate;
    
    @Column(name = "address")
    protected String address;
    
    @Column(name = "customertype")
    protected String customerType;
    
    public CustomerBase(IValidation validator) {
        this.validator = validator;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String getCustomerType() {
        return customerType;
    }

    @Override
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    
    
    @Override
    public String getCustomerName() {
        return customerName;
    }

    @Override
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public Double getBillAmount() {
        return billAmount;
    }

    @Override
    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public String getBillDate() {
        return billDate;
    }

    @Override
    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public void validate() throws Exception{
           validator.validate(this);
    }

    @Override
    public String toString() {
        return "CustomerBase{" + "validator=" + validator + ", customerName=" + customerName + ", phoneNumber=" + phoneNumber + ", billAmount=" + billAmount + ", billDate=" + billDate + ", address=" + address + ", customerType=" + customerType + '}';
    }
    
   
}
