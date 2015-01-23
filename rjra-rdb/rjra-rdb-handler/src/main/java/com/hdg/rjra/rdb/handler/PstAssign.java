package com.hdg.rjra.rdb.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/22.
 */
public interface PstAssign {
    void setParam(PreparedStatement ps) throws SQLException;
}
