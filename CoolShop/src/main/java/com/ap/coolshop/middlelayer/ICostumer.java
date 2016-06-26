/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ap.coolshop.middlelayer;

/**
 *
 * @author ananda
 */

public interface ICostumer extends Cloneable{
    public Integer getId();
    public void setId(Integer id);
    public String getCustomerType();
    public void setCustomerType(String customerType);
    public String getCustomerName();
    public void setCustomerName(String customerName);
    public String getPhoneNumber();
    public void setPhoneNumber(String phoneNumber);
    public Double getBillAmount();
    public void setBillAmount(Double billAmount);
    public String getBillDate();
    public void setBillDate(String billDate);
    public String getAddress();
    public void setAddress(String address);
    public void validate() throws Exception;
    
    public String toString();
}
