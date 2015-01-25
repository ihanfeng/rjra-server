package com.hdg.rjra.rdb.executer.accountfile;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

/**
 * Created by Rock on 2015/1/11 0011.
 */
public class FindAccountFileById extends AbstractExecuter {
    @Override
    public Object execute(Object[] params) {
        String sql = "select * from account_file where file_id =?";
        return getJdbcTemplate().query(sql, params, rowMapper);
    }
}
