package com.hdg.rjra.server.model.bo.importlog;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;

import java.util.Date;

/**
 * Created by Administrator on 2015/3/26.
 */
public class ImportLogBo {
    private Long importLogId;
    private Integer importLogType;
    private Long importLogOperatorId;
    private String importLogOperatorName;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String importLogTime;
    private Integer importLogStatus;
    private String importLogDesc;
    private Integer importLogCount;
    private Integer importLogSuccessCount;
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

    public String getImportLogTime() {
        return importLogTime;
    }

    public void setImportLogTime(String importLogTime) {
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
