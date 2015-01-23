package com.hdg.rjra.rdb.executer.categorylevel3;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindCategoryLevel3ByCategoryLevel2Id extends AbstractExecuter {

    static String sql = "select * from base_category_level3 where category_level2_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long categoryLevel2Id = (Long) params[0];
        return getJdbcTemplate().query(sql, new Object[]{categoryLevel2Id}, rowMapper);
    }
}
