package com.hdg.rjra.rdb.executer.resume;

import com.hdg.common.utils.CoordinateUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import com.hdg.rjra.rdb.proxy.domain.enumerate.ResumeMapping;
import com.hdg.rjra.rdb.proxy.utils.SqlUtils;
import com.hdg.rjra.rdb.proxy.utils.SqlParam;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/29 0029.
 */
public class FindNearResumeByParamPager extends AbstractExecuter {

    @Override
    public Object execute(Object[] params) {
        Map<ResumeMapping, Object> param = (Map<ResumeMapping, Object>) params[0];
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
        executeSql.append("select user_resume.*, ");
        executeSql.append("ROUND(6378.138 * 2 * ASIN(SQRT(POW(SIN((? * PI() / 180 - resume_latitude * PI() / 180) / 2),2) + COS(? * PI() / 180) * COS(resume_latitude * PI() / 180) * POW(SIN((? * PI() / 180 - resume_longitude * PI() / 180) / 2),2)))* 1000) AS distance ");
        executeSql.append("from user_resume where 1=1");
        SqlParam sqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        if (sqlParam != null) {
            executeSql.append(sqlParam.getSql());
            objects.addAll(sqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and resume_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        executeSql.append(" and resume_longitude > ?");
        executeSql.append(" and resume_longitude < ?");
        executeSql.append(" and resume_latitude > ?");
        executeSql.append(" and resume_latitude < ?");
        executeSql.append(" ORDER BY distance");
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        objects.add(doubles[0]);
        objects.add(doubles[1]);
        objects.add(doubles[2]);
        objects.add(doubles[3]);

        return findPager(executeSql.toString(), objects.toArray(), firstResult, sizeNo, new RowMapper() {
            @Override
            public Object mapRow(ResultSet rs, int i) throws SQLException {
                Resume re = new Resume();
                re.setResumeId(rs.getLong("resume_id"));
                re.setCategoryLevel1Ids(StringUtils.stringToLongArray(rs.getString("category_level1_ids")));
                re.setCategoryLevel2Ids(StringUtils.stringToLongArray(rs.getString("category_level2_ids")));
                re.setCategoryLevel3Ids(StringUtils.stringToLongArray(rs.getString("category_level3_ids")));
                re.setResumeUserName(rs.getString("resume_user_name"));
                re.setResumeUserHeadImageFile(rs.getLong("resume_user_head_image_file"));
                re.setResumeGender(rs.getInt("resume_gender"));
                re.setResumeBirthday(rs.getTimestamp("resume_birthday"));
                re.setResumeExperience(rs.getLong("resume_experience"));
                re.setResumeWage(rs.getLong("resume_wage"));
                re.setResumeWorkStatus(rs.getInt("resume_work_status"));
                re.setResumeWantWorkAreaId(rs.getLong("resume_want_work_area_id"));
                re.setResumeWantWorkCityId(rs.getLong("resume_want_work_city_id"));
                re.setResumeWantWorkProvinceId(rs.getLong("resume_want_work_province_id"));
                re.setResumeHomeAreaId(rs.getLong("resume_home_area_id"));
                re.setResumeHomeCityId(rs.getLong("resume_home_city_id"));
                re.setResumeHomeProvinceId(rs.getLong("resume_home_province_id"));
                re.setResumeHomeAddress(rs.getString("resume_home_address"));
                re.setResumeMobile(rs.getString("resume_mobile"));
                re.setResumeQQ(rs.getString("resume_qq"));
                re.setResumeDesc(rs.getString("resume_desc"));
                re.setResumeStatus(rs.getInt("resume_status"));
                re.setResumeCreateTime(rs.getTimestamp("resume_create_time"));
                re.setResumeUpdateTime(rs.getTimestamp("resume_update_time"));
                re.setResumeRefreshTime(rs.getTimestamp("resume_refresh_time"));
                re.setResumeLatitude(rs.getDouble("resume_latitude"));
                re.setResumeLongitude(rs.getDouble("resume_longitude"));
                re.setAgeId(rs.getLong("age_id"));
                re.setDistance(rs.getInt("distance"));
                return re;
            }
        });
    }
}