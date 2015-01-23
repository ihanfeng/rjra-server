package com.hdg.rjra.rdb.executer.employ.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.Employ;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class EmployRowMapper implements RowMapper<Employ> {
    @Override
    public Employ mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employ re = new Employ();
        re.setEmployId(rs.getLong("employ_id"));
        re.setInfoId(rs.getLong("info_id"));
        re.setUserId(rs.getLong("user_id"));
        re.setCompanyId(rs.getLong("company_id"));
        re.setEmployType(rs.getInt("employ_type"));
        re.setEmployTime(rs.getTimestamp("employ_time"));
        re.setEmployResultStatus(rs.getInt("employ_result_status"));
        re.setEmployResultInfo(rs.getString("employ_result_info"));
        re.setEmployReportPlanTime(rs.getTimestamp("employ_report_plan_time"));
        re.setEmployReportStatus(rs.getInt("employ_report_status"));
        re.setEmployEntryPlanTime(rs.getTimestamp("employ_entry_plan_time"));
        re.setEmployStatus(rs.getInt("employ_status"));
        re.setEmployCompanyStatus(rs.getInt("employ_company_status"));
        re.setEmployUserStatus(rs.getInt("employ_user_status"));
        re.setEmployReportInfo(rs.getString("employ_report_info"));
        return re;
    }
}
