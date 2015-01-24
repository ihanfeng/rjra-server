package com.hdg.rjra.rdb.executer.accountfile.rowmapper;

import com.hdg.rjra.rdb.proxy.domain.AccountFile;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Rock on 2015/1/11 0011.
 */
public class AccountFileRowMapper implements RowMapper<AccountFile> {
    @Override
    public AccountFile mapRow(ResultSet rs, int rowNum) throws SQLException {
        AccountFile re = new AccountFile();
        re.setFileId(rs.getLong("file_id"));
        re.setFileType(rs.getInt("file_type"));
        re.setFileStatus(rs.getInt("file_status"));
        re.setFileName(rs.getString("file_name"));
        re.setFileUrl(rs.getString("file_url"));
        re.setFileThumbnailUrl(rs.getString("file_thumbnail_url"));
        re.setFileFormat(rs.getString("file_format"));
        re.setFileUploadTime(rs.getTimestamp("file_upload_time"));
        return re;
    }
}
