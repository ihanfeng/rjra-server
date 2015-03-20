package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Customer;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface ICustomerProxy extends Serializable {

    public Customer findCustomerByNameAndPwd(String name, String pwd);

    public Integer updateCustomerPwd(Long customerId, String pwd);

}
