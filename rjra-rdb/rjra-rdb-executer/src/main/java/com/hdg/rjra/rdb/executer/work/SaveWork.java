package com.hdg.rjra.rdb.executer.work;

import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.WorkStatus;
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
            "company_id,company_name,category_level1_id,category_level2_id,category_level3_id,work_area_id," +
            "work_city_id,work_province_id,work_address,work_need_person,work_wage_id," +
            "work_experience_id,work_welfare_ids,work_desc,work_status,work_create_time," +
            "work_update_time,age_id,work_gender,work_data_type,work_tag,work_import_time," +
            "work_import_operator_id,work_import_operator_name) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
                ps.setObject(18, work.getWorkStatus());
                ps.setObject(19, new Date());
                ps.setObject(20, new Date());
                ps.setObject(21, work.getAgeId());
                ps.setObject(22, work.getWorkGender());
                ps.setObject(23, work.getWorkDataType());
                ps.setObject(24, work.getWorkTag());
                ps.setObject(25, work.getWorkImportTime());
                ps.setObject(26, work.getWorkImportOperatorId());
                ps.setObject(27, work.getWorkImportOperatorName());
            }
        };
        return saveResultId(sql, pst);
    }
}
