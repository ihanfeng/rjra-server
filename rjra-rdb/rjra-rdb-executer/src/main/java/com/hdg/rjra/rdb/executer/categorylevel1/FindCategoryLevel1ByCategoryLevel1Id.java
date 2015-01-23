package com.hdg.rjra.rdb.executer.categorylevel1;

import com.hdg.rjra.rdb.executer.AbstractExecuter;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class FindCategoryLevel1ByCategoryLevel1Id extends AbstractExecuter {

    static String sql = "select * from base_category_level1 where category_level1_id = ?";

    @Override
    public Object execute(Object[] params) {
        Long categoryLevel1Id = (Long) params[0];
        Object[] objects = new Object[]{categoryLevel1Id};
        List list = getJdbcTemplate().query(sql, objects, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
