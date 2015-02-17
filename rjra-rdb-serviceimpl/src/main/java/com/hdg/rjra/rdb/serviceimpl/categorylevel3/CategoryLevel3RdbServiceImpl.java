package com.hdg.rjra.rdb.serviceimpl.categorylevel3;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.CategoryLevel3;
import com.hdg.rjra.rdb.service.ICategoryLevel3RdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.categorylevel3.rowmapper.CategoryLevel3RowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
@Service
public class CategoryLevel3RdbServiceImpl extends DaoTemplate implements ICategoryLevel3RdbService {

    @Override
    public CategoryLevel3 findCategoryLevel3ByCategoryLevel3Id(Long categoryLevel3Id) {
        String sql = "select * from base_category_level3 where category_level3_id = ?";
        Object[] objects = new Object[]{categoryLevel3Id};
        List list = getJdbcTemplate().query(sql, objects, new CategoryLevel3RowMapper());
        if (list.size() > 0) {
            return (CategoryLevel3) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<CategoryLevel3> findCategoryLevel3ByCategoryLevel2Id(Long categoryLevel2Id) {
        String sql = "select * from base_category_level3 where category_level2_id = ?";
        return getJdbcTemplate().query(sql, new Object[]{categoryLevel2Id},  new CategoryLevel3RowMapper());
    }
}
