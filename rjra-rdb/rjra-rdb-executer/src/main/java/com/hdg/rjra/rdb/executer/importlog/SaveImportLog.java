package com.hdg.rjra.rdb.executer.importlog;

import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.ImportLog;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Administrator on 2015/3/26.
 */
public class SaveImportLog extends AbstractExecuter {

    String sql = "insert into import_log(import_log_type, import_log_operator_id, import_log_operator_name, import_log_status, " +
            "import_log_time, import_log_desc, import_log_count, import_log_success_count, import_log_file_name)" +
            " values (?,?,?,?,?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final ImportLog importLog = (ImportLog) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, importLog.getImportLogType());
                ps.setObject(2, importLog.getImportLogOperatorId());
                ps.setObject(3, importLog.getImportLogOperatorName());
                ps.setObject(4, importLog.getImportLogStatus());
                ps.setObject(5, new Date());
                ps.setObject(6, importLog.getImportLogDesc());
                ps.setObject(7, importLog.getImportLogCount());
                ps.setObject(8, importLog.getImportLogSuccessCount());
                ps.setObject(9, importLog.getImportLogFileName());
            }
        };
        return saveResultId(sql, pst);
    }
}