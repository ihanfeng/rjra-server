package com.hdg.rjra.rdb.executer.customer.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/3/20.
 */
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer re = new Customer();
        re.setCustomerId(rs.getLong("customer_id"));
        re.setCustomerName(rs.getString("customer_name"));
        re.setCustomerPwd(rs.getString("customer_pwd"));
        return re;
    }
}
