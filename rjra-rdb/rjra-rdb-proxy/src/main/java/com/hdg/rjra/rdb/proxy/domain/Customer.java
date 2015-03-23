package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/9 0009.
 */
public class Customer implements BaseDomain {
    private Long customerId;
    private String customerName;
    private String customerPwd;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPwd() {
        return customerPwd;
    }

    public void setCustomerPwd(String customerPwd) {
        this.customerPwd = customerPwd;
    }
}
