package com.hdg.rjra.customer.web.controller.param.customer;

/**
 * Created by Rock on 2015/1/10 0010.
 */
public class CustomerParam {

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
