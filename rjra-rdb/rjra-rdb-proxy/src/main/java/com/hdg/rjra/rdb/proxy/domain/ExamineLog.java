package com.hdg.rjra.rdb.proxy.domain;

import com.hdg.rjra.rdb.proxy.utils.DBClass;
import com.hdg.rjra.rdb.proxy.utils.DBField;

import java.util.Date;

/**
 * Created by Administrator on 2015/3/24.
 */
@DBClass("examine_log")
public class ExamineLog implements BaseDomain {

    @DBField(value = "examine_log_id", pk = true)
    private Long examineLogId;
    @DBField(value = "examine_log_type")
    private Integer examineLogType;
    @DBField(value = "examine_log_resource")
    private Long examineLogResource;
    @DBField(value = "examine_log_status")
    private Integer examineLogStatus;
    @DBField(value = "examine_log_time")
    private Date examineLogTime;
    @DBField(value = "examine_log_result")
    private String examineLogResult;
    @DBField(value = "examine_log_reviewer")
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

    public Date getExamineLogTime() {
        return examineLogTime;
    }

    public void setExamineLogTime(Date examineLogTime) {
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
