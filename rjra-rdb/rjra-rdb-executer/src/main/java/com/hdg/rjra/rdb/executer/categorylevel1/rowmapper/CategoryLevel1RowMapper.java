package com.hdg.rjra.rdb.executer.categorylevel1.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.CategoryLevel1;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/24.
 */
public class CategoryLevel1RowMapper implements RowMapper<CategoryLevel1> {
    @Override
    public CategoryLevel1 mapRow(ResultSet rs, int rowNum) throws SQLException {
        CategoryLevel1 re = new CategoryLevel1();
        re.setCategoryLevel1Id(rs.getLong("category_level1_id"));
        re.setCategoryLevel1Name(rs.getString("category_level1_name"));
        re.setCategoryLevel1Status(rs.getInt("category_level1_status"));
        re.setCategoryLevel1Desc(rs.getString("category_level1_desc"));
        return re;
    }
}
