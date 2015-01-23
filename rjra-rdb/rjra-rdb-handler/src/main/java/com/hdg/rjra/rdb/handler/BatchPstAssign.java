package com.hdg.rjra.rdb.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rock on 2014/10/27.
 */
public interface BatchPstAssign<T> {
    void setParam(PreparedStatement ps, T t) throws SQLException;
}
