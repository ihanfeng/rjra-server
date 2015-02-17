package com.hdg.rjra.rdb.serviceimpl.accountfile;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.rdb.domain.AccountFile;
import com.hdg.rjra.rdb.service.IAccountFileRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.PstAssign;
import com.hdg.rjra.rdb.serviceimpl.accountfile.rowmapper.AccountFileRowMapper;
import com.hdg.rjra.rdb.utils.SqlUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class AccountFileRdbServiceImpl extends DaoTemplate implements IAccountFileRdbService {

    @Override
    public List<AccountFile> findAccountFileByIds(Long[] fileIds, Integer[] status) {
        StringBuffer executeSql = new StringBuffer();
        executeSql.append("select * from account_file where file_id in(");
        executeSql.append(SqlUtils.appendPlaceholder(fileIds.length));
        executeSql.append(") and file_status in (");
        executeSql.append(SqlUtils.appendPlaceholder(status.length));
        executeSql.append(")");
        int fileIdsCount = fileIds.length;
        int count = status.length;
        Object[] objects = new Object[count + fileIdsCount];
        for (int i = 0; i < fileIdsCount; i++) {
            objects[i] = fileIds[i];
        }
        for (int i = fileIdsCount; i < count+fileIdsCount ; i++) {
            objects[i] = status[i-fileIdsCount];
        }
        return getJdbcTemplate().query(executeSql.toString(), objects, new AccountFileRowMapper());
    }

    @Override
    public AccountFile findAccountFileById(Long fileId) {
        String sql = "select * from account_file where file_id =?";
        List list = getJdbcTemplate().query(sql, new Object[]{fileId}, new AccountFileRowMapper());
        if (list.size() > 0) {
            return (AccountFile) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Long saveAccountFile(final AccountFile file) {
        String sql = "insert into account_file(file_type, file_status, file_name, file_url, file_thumbnail_url, file_format, file_upload_time) values (?,?,?,?,?,?,?)";
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
