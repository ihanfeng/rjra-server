package com.hdg.rjra.rdb.serviceimpl.categorylevel3.rowmapper;

import com.hdg.rjra.rdb.domain.CategoryLevel3;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class CategoryLevel3RowMapper implements RowMapper<CategoryLevel3> {
    @Override
    public CategoryLevel3 mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryLevel3 re = new CategoryLevel3();
        re.setCategoryLevel3Id(rs.getLong("category_level3_id"));
        re.setCategoryLevel2Id(rs.getLong("category_level2_id"));
        re.setCategoryLevel3Name(rs.getString("category_level3_name"));
        re.setCategoryLevel3Status(rs.getInt("category_level3_status"));
        re.setCategoryLevel3Desc(rs.getString("category_level3_desc"));
        return re;
    }
}
