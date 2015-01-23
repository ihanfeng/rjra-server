package com.hdg.rjra.rdb.executer.categorylevel3;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindCategoryLevel3ByCategoryLevel3Id extends AbstractExecuter {

    static String sql = "select * from base_category_level3 where category_level3_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long categoryLevel3Id = (Long) params[0];
        Object[] objects = new Object[]{categoryLevel3Id};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
