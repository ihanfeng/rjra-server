package com.hdg.rjra.rdb.executer.work;

import com.hdg.common.utils.CoordinateUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.Work;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import com.hdg.rjra.rdb.proxy.utils.SqlParam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/27 0027.
 */
public class FindNearWorkByParamPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Map<WorkMapping, Object> param = (Map<WorkMapping, Object>) params[0];
        Double lng = (Double) params[1];
        Double lat = (Double) params[2];
        Integer raidus = (Integer) params[3];
        Integer[] status = (Integer[]) params[4];
        Integer firstResult = (Integer) params[5];
        Integer sizeNo = (Integer) params[6];
        List<Object> objects = new ArrayList<Object>();
        objects.add(lat);
        objects.add(lat);
        objects.add(lng);
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select user_work.*, ");
        executeSql.append("ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((? * PI() / 180 - work_longitude * PI() / 180) / 2),2) + COS(? * PI() / 180) * COS(work_longitude * PI() / 180) * POW(SIN((? * PI() / 180 - work_longitude * PI() / 180) / 2),2)))) AS distance ");
        executeSql.append(" from user_work where 1=1");
        SqlParam sqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        if (sqlParam != null) {
            executeSql.append(sqlParam.getSql());
            objects.addAll(sqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and work_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        executeSql.append(" and work_longitude > ?");
        executeSql.append(" and work_longitude < ?");
        executeSql.append(" and work_latitude > ?");
        executeSql.append(" and work_latitude < ?");
        executeSql.append(" ORDER BY distance ");
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        objects.add(doubles[0]);
        objects.add(doubles[1]);
        objects.add(doubles[2]);
        objects.add(doubles[3]);

        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
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
                re.setAgeId(rs.getLong("age_id"));
                re.setWorkGender(rs.getInt("work_gender"));
                re.setDistance(rs.getInt("distance"));
                return re;
            }
        });
    }
}