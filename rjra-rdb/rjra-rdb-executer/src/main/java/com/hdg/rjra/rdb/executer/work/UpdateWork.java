package com.hdg.rjra.rdb.executer.work;

import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.model.thrift.ResultType;
import com.hdg.rjra.rdb.proxy.domain.Work;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class UpdateWork extends AbstractExecuter {

    String sql = "UPDATE user_work SET work_longitude=?,work_latitude=?," +
            "category_level1_id=?,category_level2_id=?,category_level3_id=?,work_area_id=?," +
            "work_city_id=?,work_province_id=?,work_address=?,work_need_person=?,work_wage_id=?," +
            "work_experience_id=?,work_welfare_ids=?,work_desc=?,work_update_time=?,work_status=? WHERE work_id=?";

    @Override
    public Object execute(Object[] params) {
        if (params != null && params.length > 0) {
            final Work work = (Work) params[0];
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
                    ps.setObject(17, work.getWorkStatus());
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }else{
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}