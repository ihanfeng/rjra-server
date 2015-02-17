package com.hdg.rjra.rdb.serviceimpl.company;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.base.enumerate.CompanyExamineStatus;
import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.rdb.domain.Company;
import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.service.ICompanyRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.PstAssign;
import com.hdg.rjra.rdb.serviceimpl.ResultType;
import com.hdg.rjra.rdb.serviceimpl.company.rowmapper.CompanyRowMapper;
import com.hdg.rjra.rdb.utils.SqlUtils;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class CompanyRdbServiceImpl extends DaoTemplate implements ICompanyRdbService {

    @Override
    public Long createCompany() {
        String sql = "insert into user_company(company_status, company_create_time, company_update_time, company_examine_status)" +
                " values (?,?,?,?)";
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, CompanyStatus.Active.getCode());
                ps.setObject(2, new Date());
                ps.setObject(3, new Date());
                ps.setObject(4, CompanyExamineStatus.Pending.getCode());
            }
        };
        return saveResultId(sql, pst);
    }

    @Override
    public Integer updateCompany(final Company company) {
        final String sql = "UPDATE user_company SET " +
                "company_name=?,company_province_id=?,company_city_id=?,company_area_id=?,company_address=?," +
                "company_contact=?,company_contact_mobile=?,company_update_time=?,company_desc=? WHERE company_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, company.getCompanyName());
                    ps.setObject(2, company.getCompanyProvinceId());
                    ps.setObject(3, company.getCompanyCityId());
                    ps.setObject(4, company.getCompanyAreaId());
                    ps.setObject(5, company.getCompanyAddress());
                    ps.setObject(6, company.getCompanyContact());
                    ps.setObject(7, company.getCompanyContactMobile());
                    ps.setObject(8, new Date());
                    ps.setObject(9, company.getCompanyDesc());
                    ps.setObject(10, company.getCompanyId());
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
    public Company findCompanyByCompanyId(Long companyId) {
        String sql = "select * from user_company where company_id = ?";
        Object[] objects = new Object[]{companyId};
        List list = getJdbcTemplate().query(sql, objects, new CompanyRowMapper());
        if (list.size() > 0) {
            return (Company) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Pager<Company> findAllCompanyPager(Integer[] status, Integer firstResult, Integer sizeNo) {
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from user_company where company_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(") order by company_create_time desc");
        return findPager(executeSql.toString(), status, firstResult, sizeNo, new CompanyRowMapper());
    }

    @Override
    public Integer updateCompanyBizlicense(final Long companyId, final Long fileId) {
        final String sql = "UPDATE user_company SET " +
                "company_bizlicense_image_file=? WHERE company_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, fileId);
                    ps.setObject(2, companyId);
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
    public Integer updateCompanyLogo(final Long companyId, final Long fileId) {
        final String sql = "UPDATE user_company SET " +
                "company_logo_image_file=? WHERE company_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, fileId);
                    ps.setObject(2, companyId);
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
    public Integer updateCompanyUserIdCard(final Long companyId, final Long fileId) {
        final String sql = "UPDATE user_company SET " +
                "company_user_idcard_image_file=? WHERE company_id =?";
        try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, fileId);
                    ps.setObject(2, companyId);
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
    public Integer updateCompanyFacade(final Long companyId, final Long fileId) {
        final String sql = "UPDATE user_company SET " +
                "company_facade_image_file=? WHERE company_id =?";
            try{
            getJdbcTemplate().update(new PreparedStatementCreator()
            {
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException
                {
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setObject(1, fileId);
                    ps.setObject(2, companyId);
                    return ps;
                }
            });
            return Integer.valueOf(ResultType.SUCCESS.getCode());
        }catch (Exception e){
            e.printStackTrace();
            return Integer.valueOf(ResultType.PARAM_ERROR.getCode());
        }
    }
}
