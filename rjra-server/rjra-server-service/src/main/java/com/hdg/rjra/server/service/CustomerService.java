package com.hdg.rjra.server.service;


import com.hdg.rjra.server.model.bo.customer.CustomerBo;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface CustomerService {

    public CustomerBo findCustomerByNameAndPwd(String name, String pwd);

    public Integer updateCustomerPwd(Long customerId, String pwd);
}
