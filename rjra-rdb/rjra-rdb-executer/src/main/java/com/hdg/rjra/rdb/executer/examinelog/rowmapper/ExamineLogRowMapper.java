package com.hdg.rjra.rdb.executer.examinelog.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.ExamineLog;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2015/3/24.
 */
public class ExamineLogRowMapper implements RowMapper<ExamineLog> {
    @Override
    public ExamineLog mapRow(ResultSet rs, int rowNum) throws SQLException {
        ExamineLog re = new ExamineLog();
        re.setExamineLogId(rs.getLong("examine_log_id"));
        re.setExamineLogType(rs.getInt("examine_log_type"));
        re.setExamineLogResource(rs.getLong("examine_log_resource"));
        re.setExamineLogStatus(rs.getInt("examine_log_status"));
        re.setExamineLogTime(rs.getTimestamp("examine_log_time"));
        re.setExamineLogResult(rs.getString("examine_log_result"));
        re.setExamineLogReviewerId(rs.getLong("examine_log_reviewer_id"));
        re.setExamineLogReviewerName(rs.getString("examine_log_reviewer_name"));
        return re;
    }
}
