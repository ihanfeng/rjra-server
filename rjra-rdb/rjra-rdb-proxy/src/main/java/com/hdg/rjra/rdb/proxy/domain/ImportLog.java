package com.hdg.rjra.rdb.proxy.domain;

import com.hdg.rjra.rdb.proxy.utils.DBField;

import java.util.Date;

/**
 * Created by Administrator on 2015/3/26.
 */
public class ImportLog implements BaseDomain {

    @DBField(value = "import_log_id", pk = true)
    private Long importLogId;
    @DBField(value = "import_log_type")
    private Integer importLogType;
    @DBField(value = "import_log_operator_id")
    private Long importLogOperatorId;
    @DBField(value = "import_log_operator_name")
    private String importLogOperatorName;
    @DBField(value = "import_log_time")
    private Date importLogTime;
    @DBField(value = "import_log_status")
    private Integer importLogStatus;
    @DBField(value = "import_log_desc")
    private String importLogDesc;
    @DBField(value = "import_log_count")
    private Integer importLogCount;
    @DBField(value = "import_log_success_count")
    private Integer importLogSuccessCount;
    @DBField(value = "import_log_file_name")
    private String importLogFileName;

    public Long getImportLogId() {
        return importLogId;
    }

    public void setImportLogId(Long importLogId) {
        this.importLogId = importLogId;
    }

    public Integer getImportLogType() {
        return importLogType;
    }

    public void setImportLogType(Integer importLogType) {
        this.importLogType = importLogType;
    }

    public Long getImportLogOperatorId() {
        return importLogOperatorId;
    }

    public void setImportLogOperatorId(Long importLogOperatorId) {
        this.importLogOperatorId = importLogOperatorId;
    }

    public String getImportLogOperatorName() {
        return importLogOperatorName;
    }

    public void setImportLogOperatorName(String importLogOperatorName) {
        this.importLogOperatorName = importLogOperatorName;
    }

    public Date getImportLogTime() {
        return importLogTime;
    }

    public void setImportLogTime(Date importLogTime) {
        this.importLogTime = importLogTime;
    }

    public Integer getImportLogStatus() {
        return importLogStatus;
    }

    public void setImportLogStatus(Integer importLogStatus) {
        this.importLogStatus = importLogStatus;
    }

    public String getImportLogDesc() {
        return importLogDesc;
    }

    public void setImportLogDesc(String importLogDesc) {
        this.importLogDesc = importLogDesc;
    }

    public Integer getImportLogCount() {
        return importLogCount;
    }

    public void setImportLogCount(Integer importLogCount) {
        this.importLogCount = importLogCount;
    }

    public Integer getImportLogSuccessCount() {
        return importLogSuccessCount;
    }

    public void setImportLogSuccessCount(Integer importLogSuccessCount) {
        this.importLogSuccessCount = importLogSuccessCount;
    }

    public String getImportLogFileName() {
        return importLogFileName;
    }

    public void setImportLogFileName(String importLogFileName) {
        this.importLogFileName = importLogFileName;
    }
}
