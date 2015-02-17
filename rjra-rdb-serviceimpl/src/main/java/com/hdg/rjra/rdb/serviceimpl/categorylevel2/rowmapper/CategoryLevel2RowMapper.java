package com.hdg.rjra.rdb.serviceimpl.categorylevel2.rowmapper;

import com.hdg.rjra.rdb.domain.CategoryLevel2;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class CategoryLevel2RowMapper implements RowMapper<CategoryLevel2> {
    @Override
    public CategoryLevel2 mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryLevel2 re = new CategoryLevel2();
        re.setCategoryLevel1Id(rs.getLong("category_level1_id"));
        re.setCategoryLevel2Id(rs.getLong("category_level2_id"));
        re.setCategoryLevel2Name(rs.getString("category_level2_name"));
        re.setCategoryLevel2Status(rs.getInt("category_level2_status"));
        re.setCategoryLevel2Desc(rs.getString("category_level2_desc"));
        return re;
    }
}
