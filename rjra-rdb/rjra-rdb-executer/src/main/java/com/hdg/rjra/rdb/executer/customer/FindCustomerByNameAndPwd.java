package com.hdg.rjra.rdb.executer.customer;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Administrator on 2015/3/20.
 */
public class FindCustomerByNameAndPwd extends AbstractExecuter {
    static String sql = "select * from account_customer where customer_name = ? and customer_pwd = ?";

    @Override
    public Object execute(Object[] params) {
        List list = getJdbcTemplate().query(sql, params, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
