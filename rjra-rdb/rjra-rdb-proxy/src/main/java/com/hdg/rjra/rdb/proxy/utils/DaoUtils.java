package com.hdg.rjra.rdb.proxy.utils;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Hermen on 2014/10/21.
 */
public class DaoUtils {

    private static JdbcTemplate jdbcTemplate;

    public static JdbcTemplate getInstance() {
        return jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
