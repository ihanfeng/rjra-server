package com.hdg.rjra.rdb.serviceimpl.categorylevel2;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.CategoryLevel2;
import com.hdg.rjra.rdb.service.ICategoryLevel2RdbSercice;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.categorylevel2.rowmapper.CategoryLevel2RowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
@Service
public class CategoryLevel2RdbServiceImpl extends DaoTemplate implements ICategoryLevel2RdbSercice {

    @Override
    public CategoryLevel2 findCategoryLevel2ByCategoryLevel2Id(Long categoryLevel2Id) {
        String sql = "select * from base_category_level2 where category_level2_id = ?";
        Object[] objects = new Object[]{categoryLevel2Id};
        List list = getJdbcTemplate().query(sql, objects, new CategoryLevel2RowMapper());
        if (list.size() > 0) {
            return (CategoryLevel2) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<CategoryLevel2> findCategoryLevel2ByCategoryLevel1Id(Long categoryLevel1Id) {
        String sql = "select * from base_category_level2 where category_level1_id = ?";
        return getJdbcTemplate().query(sql, new Object[]{categoryLevel1Id}, new CategoryLevel2RowMapper());
    }
}
