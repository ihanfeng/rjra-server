package com.hdg.rjra.rdb.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.hdg.rjra.rdb.domain.Pager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/24 0024.
 */
public class DaoTemplate {

    @Autowired
    public JdbcTemplate jdbcTemplate;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected Map<String, String> fieldColumn = new HashMap<>();

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public List<?> findByProperty(String tableName, String pName, String pValue, RowMapper rowMapper) {
        String sql = "select * from " + tableName + " where " + pName + " = ?";
        logger.debug(sql);
        logger.debug(pValue);
        return getJdbcTemplate().query(sql, new Object[]{pValue}, rowMapper);
    }

    public Object findOneByProperty(String tableName, String pName, String pValue, RowMapper rowMapper) {
        String sql = "select * from " + tableName + " where " + pName + " = ?";
        List list = findByProperty(tableName, pName, pValue, rowMapper);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public List<?> findAll(String tableName, RowMapper rowMapper) {
        String sql = "select * from " + tableName;
        return getJdbcTemplate().query(sql, rowMapper);
    }


    /**
     * 执行插入sql，返回指定的ID
     *
     * @param sql
     * @param pstAssign
     * @return
     */
    public Long saveResultId(final String sql, final PstAssign pstAssign) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                if (pstAssign != null) {
                    pstAssign.setParam(ps);
                }
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    /**
     * 执行插入sql，返回指定的ID
     *
     * @param sql
     * @param pstAssign
     * @return
     */
    public void save(final String sql, final PstAssign pstAssign) {
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql);
                if (pstAssign != null) {
                    pstAssign.setParam(ps);
                }
                return ps;
            }
        });
    }

    /**
     * 批量保存，返回批量的值
     *
     * @param sql
     * @param batchPstAssign
     * @param list
     * @return
     */
    public <T> List<Long> batchSaveResultId(final String sql, final BatchPstAssign<T> batchPstAssign, final List<T> list) {
        return getJdbcTemplate().execute(new ConnectionCallback<List<Long>>() {
                                             @Override
                                             public List<Long> doInConnection(Connection conn) throws SQLException, DataAccessException {
                                                 final int count = list.size();
                                                 List<Long> ids = new ArrayList<Long>(count);
                                                 conn.setAutoCommit(false);
                                                 PreparedStatement ps = null;
                                                 ResultSet resultSet = null;
                                                 try {
                                                     ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                                                     for (T t : list) {
                                                         batchPstAssign.setParam(ps, t);
                                                         ps.addBatch();
                                                     }
                                                     ps.executeBatch();
                                                     resultSet = ps.getGeneratedKeys();
                                                     while (resultSet.next()) {
                                                         ids.add(resultSet.getLong(1));
                                                     }
                                                     conn.commit();
                                                     resultSet.close();
                                                     return ids;
                                                 } catch (Exception e) {
                                                     conn.rollback();
                                                     throw e;
                                                 } finally {
                                                     JdbcUtils.closeResultSet(resultSet);
                                                     JdbcUtils.closeStatement(ps);
                                                 }
                                             }
                                         }
        );
    }

    public Pager findPager(String sql, Object[] params, Integer firstResult, Integer sizeNo, RowMapper rowMapper) {
        logger.debug("sql >>>> " + sql);
        logger.debug("params >>>>> " + JSON.toJSONString(params));
        logger.debug(" firstResult >>>> " + firstResult);
        logger.debug(" sizeNo >>>> " + sizeNo);
        Pager pager = new Pager();
        StringBuilder countSql = new StringBuilder("select count(1) cnt from (");
        countSql.append(sql);
        countSql.append(") as pagertable");
        if (params == null || params.length == 0) {
            Object count = getJdbcTemplate().queryForMap(countSql.toString()).get("cnt");
            logger.debug("总数 >>>> " + count.toString());
            pager.setTotalSize(Long.parseLong(count.toString()));

            sql = sql + " LIMIT ?,?";
            Object[] selectParams = new Object[]{firstResult, sizeNo};
            logger.debug("sql >>> " + sql);
            logger.debug("param >>> " + JSON.toJSONString(selectParams));
            pager.setResultList(getJdbcTemplate().query(sql, selectParams, rowMapper));
        } else {
            Object count = getJdbcTemplate().queryForMap(countSql.toString(), params).get("cnt");
            logger.debug("总数 >>>> " + count.toString());
            pager.setTotalSize(Long.parseLong(count.toString()));

            sql = sql + " LIMIT ?,?";
            Object[] selectParams = new Object[params.length + 2];
            for (int i = 0; i < params.length; i++) {
                selectParams[i] = params[i];
            }
            selectParams[params.length] = firstResult;
            selectParams[params.length + 1] = sizeNo;
            logger.debug("sql >>> " + sql);
            logger.debug("param >>> " + JSON.toJSONString(selectParams));
            pager.setResultList(getJdbcTemplate().query(sql, selectParams, rowMapper));
        }
        return pager;
    }

    public void executeSql(String sql) {
        getJdbcTemplate().execute(sql);
    }

    public void updateByfields(String tableName, List<Map<String, Object>> fields) {
        //构造sql
        StringBuilder sb = null;
        for (int i = 0; i < fields.size(); i++) {
            sb = new StringBuilder();
            List<Object[]> sqlParams = new ArrayList<>();
            sb.append("update " + tableName + " set ");
            Map<String, Object> fieldMap = fields.get(i);
            List<Object> value = new ArrayList<>();
            int k = 0;
            Object idValue = fieldMap.remove("id");
            for (Map.Entry<String, Object> stringObjectEntry : fieldMap.entrySet()) {
                String key = stringObjectEntry.getKey();
                value.add(stringObjectEntry.getValue());
                if (++k == fieldMap.size()) {
                    sb.append(" " + fieldColumn.get(key) + " = ? ");
                } else {
                    sb.append(" " + fieldColumn.get(key) + " = ? ,");
                }
            }
            value.add(idValue);
            sqlParams.add(value.toArray());
            sb.append(" where id = ?");
            getJdbcTemplate().batchUpdate(sb.toString(), sqlParams);
        }
    }

    public <T> T findOne(String sql, Object[] params, RowMapper<T> rowMapper) {
        List<T> list;
        if (params != null) {
            list = getJdbcTemplate().query(sql, params, rowMapper);
        } else {
            list = getJdbcTemplate().query(sql, rowMapper);
        }

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
}
