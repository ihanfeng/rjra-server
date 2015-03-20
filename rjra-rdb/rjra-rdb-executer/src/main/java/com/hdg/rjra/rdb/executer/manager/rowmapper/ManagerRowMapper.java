package com.hdg.rjra.rdb.executer.manager.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.Manager;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/3/9 0009.
 */
public class ManagerRowMapper implements RowMapper<Manager> {

    @Override
    public Manager mapRow(ResultSet rs, int rowNum) throws SQLException {
        Manager re = new Manager();
        re.setManagerId(rs.getLong("manager_id"));
        re.setManagerName(rs.getString("manager_name"));
        re.setManagerPwd(rs.getString("manager_pwd"));
        return re;
    }
}