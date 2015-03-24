package com.hdg.rjra.server.model.bo.examine;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;

/**
 * Created by Administrator on 2015/3/24.
 */
public class ExamineLogBo {
    private Long examineLogId;
    private Integer examineLogType;
    private Long examineLogResource;
    private Integer examineLogStatus;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String examineLogTime;
    private String examineLogResult;
    private Long examineLogReviewerId;
    private String examineLogReviewerName;

    public Long getExamineLogId() {
        return examineLogId;
    }

    public void setExamineLogId(Long examineLogId) {
        this.examineLogId = examineLogId;
    }

    public Integer getExamineLogType() {
        return examineLogType;
    }

    public void setExamineLogType(Integer examineLogType) {
        this.examineLogType = examineLogType;
    }

    public Long getExamineLogResource() {
        return examineLogResource;
    }

    public void setExamineLogResource(Long examineLogResource) {
        this.examineLogResource = examineLogResource;
    }

    public Integer getExamineLogStatus() {
        return examineLogStatus;
    }

    public void setExamineLogStatus(Integer examineLogStatus) {
        this.examineLogStatus = examineLogStatus;
    }

    public String getExamineLogTime() {
        return examineLogTime;
    }

    public void setExamineLogTime(String examineLogTime) {
        this.examineLogTime = examineLogTime;
    }

    public String getExamineLogResult() {
        return examineLogResult;
    }

    public void setExamineLogResult(String examineLogResult) {
        this.examineLogResult = examineLogResult;
    }

    public Long getExamineLogReviewerId() {
        return examineLogReviewerId;
    }

    public void setExamineLogReviewerId(Long examineLogReviewerId) {
        this.examineLogReviewerId = examineLogReviewerId;
    }

    public String getExamineLogReviewerName() {
        return examineLogReviewerName;
    }

    public void setExamineLogReviewerName(String examineLogReviewerName) {
        this.examineLogReviewerName = examineLogReviewerName;
    }
}
