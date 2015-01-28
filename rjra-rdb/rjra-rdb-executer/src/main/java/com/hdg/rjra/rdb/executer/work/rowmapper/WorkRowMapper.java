package com.hdg.rjra.rdb.executer.work.rowmapper;

import com.hdg.rjra.base.utils.StringUtils;
import com.hdg.rjra.rdb.proxy.domain.Work;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class WorkRowMapper implements RowMapper<Work> {

    @Override
    public Work mapRow(ResultSet rs, int rowNum) throws SQLException {
        Work re = new Work();
        re.setWorkId(rs.getLong("work_id"));
        re.setWorkLongitude(rs.getDouble("work_longitude"));
        re.setWorkLatitude(rs.getDouble("work_latitude"));
        re.setUserId(rs.getLong("user_id"));
        re.setCompanyId(rs.getLong("company_id"));
        re.setCompanyName(rs.getString("company_name"));
        re.setCategoryLevel1Id(rs.getLong("category_level1_id"));
        re.setCategoryLevel2Id(rs.getLong("category_level2_id"));
        re.setCategoryLevel3Id(rs.getLong("category_level3_id"));
        re.setWorkAreaId(rs.getLong("work_area_id"));
        re.setWorkCityId(rs.getLong("work_city_id"));
        re.setWorkProvinceId(rs.getLong("work_province_id"));
        re.setWorkAddress(rs.getString("work_address"));
        re.setWorkNeedPerson(rs.getInt("work_need_person"));
        re.setWorkWageId(rs.getLong("work_wage_id"));
        re.setWorkExperienceId(rs.getLong("work_experience_id"));
        re.setWorkWelfareIds(StringUtils.stringToLongArray(rs.getString("work_welfare_ids")));
        re.setWorkDesc(rs.getString("work_desc"));
        re.setWorkStatus(rs.getInt("work_status"));
        re.setWorkCreateTime(rs.getTimestamp("work_create_time"));
        re.setWorkUpdateTime(rs.getTimestamp("work_update_time"));
        return re;
    }
}