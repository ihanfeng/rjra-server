package com.hdg.rjra.rdb.serviceimpl.categorylevel1;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.CategoryLevel1;
import com.hdg.rjra.rdb.service.ICategoryLevel1RdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.categorylevel1.rowmapper.CategoryLevel1RowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
@Service
public class CategoryLevel1RdbServiceImpl extends DaoTemplate implements ICategoryLevel1RdbService {

    @Override
    public CategoryLevel1 findCategoryLevel1ByCategoryLevel1Id(Long categoryLevel1Id) {
        String sql = "select * from base_category_level1 where category_level1_id = ?";
        Object[] objects = new Object[]{categoryLevel1Id};
        List list = getJdbcTemplate().query(sql, objects, new CategoryLevel1RowMapper());
        if (list.size() > 0) {
            return (CategoryLevel1) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<CategoryLevel1> findAllCategoryLevel1() {
        String sql = "select * from base_category_level1";
        return getJdbcTemplate().query(sql, new Object[]{}, new CategoryLevel1RowMapper());
    }
}
