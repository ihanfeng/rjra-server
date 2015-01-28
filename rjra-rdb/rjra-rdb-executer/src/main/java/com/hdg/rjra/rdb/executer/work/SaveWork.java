package com.hdg.rjra.rdb.executer.work;

import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.base.utils.StringUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.Work;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class SaveWork  extends AbstractExecuter {

    String sql = "insert into user_work (work_longitude,work_latitude,user_id," +
            "company_id,category_leve1_id,category_leve2_id,category_leve3_id,work_area_id," +
            "work_city_id,work_province_id,work_address,work_need_person,work_wage_id," +
            "work_experience_id,work_welfare_ids,work_desc,work_status,work_create_time," +
            "work_update_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final Work work = (Work) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, work.getWorkLongitude());
                ps.setObject(2, work.getWorkLatitude());
                ps.setObject(3, work.getUserId());
                ps.setObject(4, work.getCompanyId());
                ps.setObject(5, work.getCategoryLeve1Id());
                ps.setObject(6, work.getCategoryLeve2Id());
                ps.setObject(7, work.getCategoryLeve3Id());
                ps.setObject(8, work.getWorkAreaId());
                ps.setObject(9, work.getWorkCityId());
                ps.setObject(10, work.getWorkProvinceId());
                ps.setObject(11, work.getWorkAddress());
                ps.setObject(12, work.getWorkNeedPerson());
                ps.setObject(13, work.getWorkWageId());
                ps.setObject(14, work.getWorkExperienceId());
                ps.setObject(15, StringUtils.longArrayToString(work.getWorkWelfareIds()));
                ps.setObject(16, work.getWorkDesc());
                ps.setObject(17, WorkStatus.Active.getCode());
                ps.setObject(18, new Date());
                ps.setObject(19, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
