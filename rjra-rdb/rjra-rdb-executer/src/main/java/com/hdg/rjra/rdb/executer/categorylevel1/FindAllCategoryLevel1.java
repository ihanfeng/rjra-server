package com.hdg.rjra.rdb.executer.categorylevel1;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindAllCategoryLevel1 extends AbstractExecuter {

    static String sql = "select * from base_category_level1";

    @Override
    public Object execute(Object[] params) {
        return getJdbcTemplate().query(sql, new Object[]{}, rowMapper);
    }
}
