package com.hdg.rjra.rdb.proxy.utils;

/**
 * 拼装sql的时候使用
 * Created by Rock on 2014/10/30.
 */
public abstract class SqlUtils {

    /**
     * number = 2 返回值 ?,?
     * number = 4  返回 ?,?,?,?
     *
     * @param number
     * @return
     */
    public static String appendPlaceholder(int number) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < number; i++) {
            if (i + 1 < number) {
                sql.append("?,");
            } else {
                sql.append("?");
            }
        }
        return sql.toString();
    }
}
