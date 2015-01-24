package com.hdg.rjra.rdb.executer.accountfile;

import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.base.enumerate.UserStatus;
import com.hdg.rjra.rdb.executer.AbstractExecuter;
import com.hdg.rjra.rdb.handler.PstAssign;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Rock on 2015/1/11 0011.
 */
public class SaveAccountFile extends AbstractExecuter {

    static String sql = "insert into account_file(file_type, file_status, file_name, file_url, file_thumbnail_url, file_format, file_upload_time) values (?,?,?,?,?,?,?)";

    @Override
    public Object execute(Object[] params) {
        final AccountFile file = (AccountFile) params[0];
        PstAssign pst = new PstAssign() {
            @Override
            public void setParam(PreparedStatement ps) throws SQLException {
                ps.setObject(1, file.getFileType());
                ps.setObject(2, FileStatus.Display.getCode());
                ps.setObject(3, file.getFileName());
                ps.setObject(4, file.getFileUrl());
                ps.setObject(5, file.getFileThumbnailUrl());
                ps.setObject(6, file.getFileFormat());
                ps.setObject(7, new Date());
            }
        };
        return saveResultId(sql, pst);
    }
}
