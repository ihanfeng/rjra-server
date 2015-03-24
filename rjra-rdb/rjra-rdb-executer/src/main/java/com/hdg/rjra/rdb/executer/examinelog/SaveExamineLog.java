package com.hdg.rjra.rdb.executer.examinelog;

import com.hdg.rjra.base.enumerate.CompanyExamineStatus;
import com.hdg.rjra.base.enumerate.CompanyStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.ExamineLog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Administrator on 2015/3/24.
 */
public class SaveExamineLog extends AbstractExecuter {

    String sql = "insert into examine_log(examine_log_type, examine_log_resource, examine_log_status, " +
            "examine_log_time, examine_log_result, examine_log_reviewer_id, examine_log_reviewer_name)" +
            " values (?,?,?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final ExamineLog examineLog = (ExamineLog) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, examineLog.getExamineLogType());
                ps.setObject(2, examineLog.getExamineLogResource());
                ps.setObject(3, examineLog.getExamineLogStatus());
                ps.setObject(4, new Date());
                ps.setObject(5, examineLog.getExamineLogResult());
                ps.setObject(6, examineLog.getExamineLogReviewerId());
                ps.setObject(7, examineLog.getExamineLogReviewerName());
            }
        };
        return saveResultId(sql, pst);
    }
}