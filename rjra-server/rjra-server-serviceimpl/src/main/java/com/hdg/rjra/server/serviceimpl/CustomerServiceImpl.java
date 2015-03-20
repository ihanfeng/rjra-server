package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.ICustomerProxy;
import com.hdg.rjra.rdb.proxy.domain.Customer;
import com.hdg.rjra.server.model.bo.customer.CustomerBo;
import com.hdg.rjra.server.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    ICustomerProxy customerProxy;

    private CustomerBo getCustomerBo(Customer customer) {
        if (null == customer) {
            return null;
        }
        CustomerBo customerBo = new CustomerBo();
        ConversionUtils.conversion(customer, customerBo);
        return customerBo;
    }

    @Override
    public CustomerBo findCustomerByNameAndPwd(String name, String pwd) {
        Customer customer = customerProxy.findCustomerByNameAndPwd(name, pwd);
        return getCustomerBo(customer);
    }

    @Override
    public Integer updateCustomerPwd(Long customerId, String pwd) {
        return customerProxy.updateCustomerPwd(customerId, pwd);
    }
}
