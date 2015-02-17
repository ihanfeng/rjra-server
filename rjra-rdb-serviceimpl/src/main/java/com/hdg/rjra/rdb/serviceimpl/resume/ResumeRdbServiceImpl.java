package com.hdg.rjra.rdb.serviceimpl.resume;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.common.utils.CoordinateUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.domain.Resume;
import com.hdg.rjra.rdb.domain.enumerate.ResumeMapping;
import com.hdg.rjra.rdb.service.IResumeRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.PstAssign;
import com.hdg.rjra.rdb.serviceimpl.ResultType;
import com.hdg.rjra.rdb.serviceimpl.resume.rowmapper.ResumeRowMapper;
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
 * Created by Rock on 2015/1/8 0008.
 */
@Service
public class ResumeRdbServiceImpl extends DaoTemplate implements IResumeRdbService {

    @Override
    public Long createResume(final String mobile) {
        final String sql = "insert into user_resume(resume_mobile,resume_status, resume_create_time, resume_update_time, resume_refresh_time)" +
                " values (?,?,?,?,?)";
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, mobile);
                ps.setObject(2, CompanyStatus.Active.getCode());
                ps.setObject(3, new Date());
                ps.setObject(4, new Date());
                ps.setObject(5, new Date());
            }
        };
        return saveResultId(sql, pst);
    }

    @Override
    public Resume findResumeByResumeId(Long resumeId) {
        final String sql = "select * from user_resume where resume_id = ?";
        List list = getJdbcTemplate().query(sql, new Object[]{resumeId}, new ResumeRowMapper());
        if (list.size() > 0) {
            return (Resume) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Integer updatResumeStatus(final Long resumeId, final Integer status) {
        final String sql = "UPDATE user_resume SET " +
                "resume_status=? WHERE resume_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, status);
                    ps.setObject(2, resumeId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }catch (Exception e){
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }

    @Override
    public Integer updateResume(final Resume resume) {
        final String sql = "UPDATE user_resume SET " +
                "category_level1_ids=?,category_level2_ids=?,category_level3_ids=?,resume_user_name=?," +
                "resume_gender=?,resume_birthday=?,resume_experience=?,resume_wage=?," +
                "resume_work_status=?,resume_want_work_area_id=?,resume_want_work_city_id=?,resume_want_work_province_id=?," +
                "resume_qq=?,resume_desc=?,resume_update_time=? WHERE resume_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, StringUtils.longArrayToString(resume.getCategoryLevel1Ids()));
                    ps.setObject(2, StringUtils.longArrayToString(resume.getCategoryLevel2Ids()));
                    ps.setObject(3, StringUtils.longArrayToString(resume.getCategoryLevel3Ids()));
                    ps.setObject(4, resume.getResumeUserName());
                    ps.setObject(5, resume.getResumeGender());
                    ps.setObject(6, resume.getResumeBirthday());
                    ps.setObject(7, resume.getResumeExperience());
                    ps.setObject(8, resume.getResumeWage());
                    ps.setObject(9, resume.getResumeWorkStatus());
                    ps.setObject(10, resume.getResumeWantWorkAreaId());
                    ps.setObject(11, resume.getResumeWantWorkCityId());
                    ps.setObject(12, resume.getResumeWantWorkProvinceId());
                    if (resume.getResumeQQ() != null) {
                        ps.setObject(13, resume.getResumeQQ());
                    } else {
                        ps.setObject(13, "");
                    }
                    ps.setObject(14, resume.getResumeDesc());
                    ps.setObject(15, new Date());
                    ps.setObject(16, resume.getResumeId());
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
    public Integer updateResumeHead(final Long resumeId, final Long fileId) {
        final String sql = "UPDATE user_resume SET " +
                "resume_user_head_image_file=? WHERE resume_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, fileId);
                    ps.setObject(2, resumeId);
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
    public Pager<Resume> findAllResumeByParamPager(Map<ResumeMapping, Object> param, Integer[] status, Integer firstResult, Integer sizeNo) {
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_resume where 1=1");
        WhereAndSqlParam whereAndSqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        List<Object> objects = new ArrayList<Object>();
        if (whereAndSqlParam != null) {
            executeSql.append(whereAndSqlParam.getSql());
            objects.addAll(whereAndSqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and resume_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by resume_create_time desc");

        return findPager(executeSql.toString(), objects.toArray(), firstResult,sizeNo, new ResumeRowMapper());
    }

    @Override
    public Pager<Resume> findNearResumeByParamPager(Map<ResumeMapping, Object> param, Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo) {
        double[] doubles = CoordinateUtils.getAround(lng, lat, raidus);
        List<Object> objects = new ArrayList<Object>();
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select a.* from user_resume a left join account_user b on a.resume_id=b.resume_id where 1=1");
        WhereAndSqlParam whereAndSqlParam = SqlUtils.buildWhereAndSqlByMapParam(param);
        if (whereAndSqlParam != null) {
            executeSql.append(whereAndSqlParam.getSql());
            objects.addAll(whereAndSqlParam.getObjects());
        }
        objects.addAll(SqlUtils.arrayToList(status));
        executeSql.append(" and resume_status in (");
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

        return findPager(executeSql.toString(), objects.toArray(), firstResult, sizeNo, new ResumeRowMapper());
    }
}
