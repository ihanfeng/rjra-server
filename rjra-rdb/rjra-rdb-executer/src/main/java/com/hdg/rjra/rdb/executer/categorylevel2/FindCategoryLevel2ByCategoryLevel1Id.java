package com.hdg.rjra.rdb.executer.categorylevel2;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindCategoryLevel2ByCategoryLevel1Id extends AbstractExecuter {

    static String sql = "select * from base_category_level2 where category_level1_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long categoryLevel1Id = (Long) params[0];
        return getJdbcTemplate().query(sql, new Object[]{categoryLevel1Id}, rowMapper);
    }
}
