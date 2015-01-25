package com.hdg.rjra.rdb.executer.accountfile;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;

import java.util.List;

/**
 * Created by Rock on 2015/1/11 0011.
 */
public class FindAccountFileById extends AbstractExecuter {
    @Override
    public Object execute(Object[] params) {
        String sql = "select * from account_file where file_id =?";
        List list = getJdbcTemplate().query(sql, params, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
