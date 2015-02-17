package com.hdg.rjra.rdb.serviceimpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yeahmobi on 2014/10/27.
 */
public interface BatchPstAssign<T> {
    void setParam(PreparedStatement ps, T t) throws SQLException;
}
