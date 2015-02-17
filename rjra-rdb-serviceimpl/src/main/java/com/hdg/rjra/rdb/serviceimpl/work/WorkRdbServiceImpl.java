package com.hdg.rjra.rdb.serviceimpl.work;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.common.utils.CoordinateUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.domain.Work;
import com.hdg.rjra.rdb.domain.enumerate.WorkMapping;
import com.hdg.rjra.rdb.service.IWorkRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.PstAssign;
import com.hdg.rjra.rdb.serviceimpl.ResultType;
import com.hdg.rjra.rdb.serviceimpl.work.rowmapper.WorkRowMapper;
import com.hdg.rjra.rdb.utils.SqlUtils;
import com.hdg.rjra.rdb.utils.WhereAndSqlParam;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/27 0027.
 */
@Service
public class WorkRdbServiceImpl extends DaoTemplate implements IWorkRdbService {

    @Override
    public Work findWorkByWorkId(Long workId) {
        String sql = "select * from user_work where work_id = ?";
        List list = getJdbcTemplate().query(sql, new Object[]{workId}, new WorkRowMapper());
        if (list.size() > 0) {
            return (Work) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Pager<Work> findAllWorkByParamPager(Map<WorkMapping, Object> param, Integer[] status, Integer firstResult, Integer sizeNo) {
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_work where 1=1");
        WhereAndSqlParam whereAndSqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        List<Object> objects = new ArrayList<Object>();
        if (whereAndSqlParam != null) {
            executeSql.append(whereAndSqlParam.getSql());
            objects.addAll(whereAndSqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and work_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by work_create_time desc");

        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, new WorkRowMapper());
    }

    @Override
    public Pager<Work> findNearWorkByParamPager(Map<WorkMapping, Object> param, Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo) {
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        List<Object> objects = new ArrayList<Object>();
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_work where 1=1");
        WhereAndSqlParam whereAndSqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        if (whereAndSqlParam != null) {
            executeSql.append(whereAndSqlParam.getSql());
            objects.addAll(whereAndSqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and work_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        executeSql.append(" and work_longitude > ?");
        executeSql.append(" and work_longitude < ?");
        executeSql.append(" and work_latitude > ?");
        executeSql.append(" and work_latitude < ?");
        executeSql.append(" ORDER BY ACOS( SIN((? * 3.1415) / 180) * SIN((work_longitude * 3.1415) / 180) + COS((? * 3.1415) / 180) * COS((work_latitude * 3.1415) / 180) * COS((? * 3.1415) / 180 - (work_longitude * 3.1415) / 180)) * 6380 ");
        objects.add(doubles[0]);
        objects.add(doubles[1]);
        objects.add(doubles[2]);
        objects.add(doubles[3]);
        objects.add(lat);
        objects.add(lat);
        objects.add(lng);

        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, new WorkRowMapper());
    }

    @Override
    public Integer updateWork(final Work work) {
        final String sql = "UPDATE user_work SET work_longitude=?,work_latitude=?," +
                "category_level1_id=?,category_level2_id=?,category_level3_id=?,work_area_id=?," +
                "work_city_id=?,work_province_id=?,work_address=?,work_need_person=?,work_wage_id=?," +
                "work_experience_id=?,work_welfare_ids=?,work_desc=?,work_update_time=? WHERE work_id=?";
        try {
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, work.getWorkLongitude());
                    ps.setObject(2, work.getWorkLatitude());
                    ps.setObject(3, work.getCategoryLevel1Id());
                    ps.setObject(4, work.getCategoryLevel2Id());
                    ps.setObject(5, work.getCategoryLevel3Id());
                    ps.setObject(6, work.getWorkAreaId());
                    ps.setObject(7, work.getWorkCityId());
                    ps.setObject(8, work.getWorkProvinceId());
                    ps.setObject(9, work.getWorkAddress());
                    ps.setObject(10, work.getWorkNeedPerson());
                    ps.setObject(11, work.getWorkWageId());
                    ps.setObject(12, work.getWorkExperienceId());
                    ps.setObject(13, StringUtils.longArrayToString(work.getWorkWelfareIds()));
                    ps.setObject(14, work.getWorkDesc());
                    ps.setObject(15, new Date());
                    ps.setObject(16, work.getWorkId());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        } catch (Exception e){
            e.printStackTrace();
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }

    @Override
    public Integer updateWorkStatus(final Long workId, final Integer status) {
        final String sql = "UPDATE user_work SET " +
                "work_status=? WHERE work_id =?";
        try {
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, status);
                    ps.setObject(2, workId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        } catch (Exception e){
            e.printStackTrace();
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }

    @Override
    public Long saveWork(final Work work) {
        final String sql = "insert into user_work (work_longitude,work_latitude,user_id," +
                "company_id,company_name,category_level1_id,category_level2_id,category_level3_id,work_area_id," +
                "work_city_id,work_province_id,work_address,work_need_person,work_wage_id," +
                "work_experience_id,work_welfare_ids,work_desc,work_status,work_create_time," +
                "work_update_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, work.getWorkLongitude());
                ps.setObject(2, work.getWorkLatitude());
                ps.setObject(3, work.getUserId());
                ps.setObject(4, work.getCompanyId());
                ps.setObject(5, work.getCompanyName());
                ps.setObject(6, work.getCategoryLevel1Id());
                ps.setObject(7, work.getCategoryLevel2Id());
                ps.setObject(8, work.getCategoryLevel3Id());
                ps.setObject(9, work.getWorkAreaId());
                ps.setObject(10, work.getWorkCityId());
                ps.setObject(11, work.getWorkProvinceId());
                ps.setObject(12, work.getWorkAddress());
                ps.setObject(13, work.getWorkNeedPerson());
                ps.setObject(14, work.getWorkWageId());
                ps.setObject(15, work.getWorkExperienceId());
                ps.setObject(16, StringUtils.longArrayToString(work.getWorkWelfareIds()));
                ps.setObject(17, work.getWorkDesc());
                ps.setObject(18, WorkStatus.Active.getCode());
                ps.setObject(19, new Date());
                ps.setObject(20, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
