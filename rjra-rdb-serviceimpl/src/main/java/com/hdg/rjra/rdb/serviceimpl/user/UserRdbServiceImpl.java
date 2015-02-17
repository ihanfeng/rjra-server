package com.hdg.rjra.rdb.serviceimpl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.common.utils.CoordinateUtils;
import com.hdg.rjra.base.enumerate.UserStatus;
import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.domain.User;
import com.hdg.rjra.rdb.service.IUserRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.PstAssign;
import com.hdg.rjra.rdb.serviceimpl.ResultType;
import com.hdg.rjra.rdb.serviceimpl.user.rowmapper.UserRowMapper;
import com.hdg.rjra.rdb.utils.SqlUtils;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class UserRdbServiceImpl extends DaoTemplate implements IUserRdbService {

    @Override
    public Long saveUser(final Long resumeId, final Long companyId, final String mobile, final String pwd) {
        final String sql = "insert into account_user(resume_id,company_id,user_mobile, user_pwd, user_status, user_create_time, user_update_time) values (?,?,?,?,?,?,?)";
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, resumeId);
                ps.setObject(2, companyId);
                ps.setObject(3, mobile);
                ps.setObject(4, pwd);
                ps.setObject(5, UserStatus.Active.getCode());
                ps.setObject(6, new Date());
                ps.setObject(7, new Date());
            }
        };
        return saveResultId(sql, pst);
    }

    @Override
    public Integer updateUser(final User user) {
        final String sql = "UPDATE account_user SET user_nickname=?,user_gender=?,user_update_time=? WHERE user_id =?";
        try {
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, user.getUserNickName());
                    ps.setObject(2, user.getUserGender());
                    ps.setObject(3, new Date());
                    ps.setObject(4, user.getUserId());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }

    @Override
    public User findUserByUserId(Long userId) {
        final String sql = "select * from account_user where user_id = ?";
        Object[] objects = new Object[]{userId};
        List list = getJdbcTemplate().query(sql, objects, new UserRowMapper());
        if (list.size() > 0) {
            return (User) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Pager<User> findAllUserPager(Integer[] status, Integer firstResult, Integer sizeNo) {
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from account_user where user_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by user_create_time desc");
        return findPager(executeSql.toString(), status, firstResult, sizeNo, new UserRowMapper());
    }

    @Override
    public Integer updateUserHead(final Long userId, final Long fileId) {
        final String sql = "UPDATE account_user SET " +
                "user_head_image_file=? WHERE user_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, fileId);
                    ps.setObject(2, userId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }catch (Exception e){
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }

    @Override
    public Pager<User> findNearUserPager(Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo) {
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        List<Object> objects = new ArrayList<Object>();
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from account_user where 1=1");
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and user_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        executeSql.append(" and user_login_longitude > ?");
        executeSql.append(" and user_login_longitude < ?");
        executeSql.append(" and user_login_latitude > ?");
        executeSql.append(" and user_login_latitude < ?");
        executeSql.append(" ORDER BY ACOS( SIN((? * 3.1415) / 180) * SIN((user_login_longitude * 3.1415) / 180) + COS((? * 3.1415) / 180) * COS((user_login_latitude * 3.1415) / 180) * COS((? * 3.1415) / 180 - (user_login_longitude * 3.1415) / 180)) * 6380 ");
        objects.add(doubles[0]);
        objects.add(doubles[1]);
        objects.add(doubles[2]);
        objects.add(doubles[3]);
        objects.add(lat);
        objects.add(lat);
        objects.add(lng);
        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, new UserRowMapper());
    }

    @Override
    public User findUserByMobileAndPwd(String mobile, String pwd) {
        String sql = "select * from account_user where user_mobile = ? and user_pwd = ?";
        List list = getJdbcTemplate().query(sql, new Object[]{mobile, pwd}, new UserRowMapper());
        if (list.size() > 0) {
            return (User) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer updateUserLocation(final Long userId, final Double lng, final Double lat) {
        final String sql = "UPDATE account_user SET user_login_longitude=?,user_login_latitude=?," +
                "user_update_time=? WHERE user_id =?";
        try {
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, lng);
                    ps.setObject(2, lat);
                    ps.setObject(3, new Date());
                    ps.setObject(4, userId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }

    @Override
    public Integer findUserExistsByMobile(String mobile) {
        String sql = "select count(1) cnt from account_user where user_mobile = ?";
        Object count = getJdbcTemplate().queryForMap(sql, new Object[]{mobile}).get("cnt");
        return Integer.parseInt(String.valueOf(count));
    }
}
