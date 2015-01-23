package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class Employ implements Serializable {
    private Long employId;
    private Long infoId;
    private Long userId;
    private Long companyId;
    private Integer employType;
    private Date employTime;
    private Integer employResultStatus;
    private String employResultInfo;
    private Date employReportPlanTime;
    private Integer employReportStatus;
    private Date employEntryPlanTime;
    private Integer employStatus;
    private Integer employCompanyStatus;
    private Integer employUserStatus;
    private String employReportInfo;

    public Long getEmployId() {
        return employId;
    }

    public void setEmployId(Long employId) {
        this.employId = employId;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getEmployType() {
        return employType;
    }

    public void setEmployType(Integer employType) {
        this.employType = employType;
    }

    public Date getEmployTime() {
        return employTime;
    }

    public void setEmployTime(Date employTime) {
        this.employTime = employTime;
    }

    public Integer getEmployResultStatus() {
        return employResultStatus;
    }

    public void setEmployResultStatus(Integer employResultStatus) {
        this.employResultStatus = employResultStatus;
    }

    public String getEmployResultInfo() {
        return employResultInfo;
    }

    public void setEmployResultInfo(String employResultInfo) {
        this.employResultInfo = employResultInfo;
    }

    public Date getEmployReportPlanTime() {
        return employReportPlanTime;
    }

    public void setEmployReportPlanTime(Date employReportPlanTime) {
        this.employReportPlanTime = employReportPlanTime;
    }

    public Integer getEmployReportStatus() {
        return employReportStatus;
    }

    public void setEmployReportStatus(Integer employReportStatus) {
        this.employReportStatus = employReportStatus;
    }

    public Date getEmployEntryPlanTime() {
        return employEntryPlanTime;
    }

    public void setEmployEntryPlanTime(Date employEntryPlanTime) {
        this.employEntryPlanTime = employEntryPlanTime;
    }

    public Integer getEmployStatus() {
        return employStatus;
    }

    public void setEmployStatus(Integer employStatus) {
        this.employStatus = employStatus;
    }

    public Integer getEmployCompanyStatus() {
        return employCompanyStatus;
    }

    public void setEmployCompanyStatus(Integer employCompanyStatus) {
        this.employCompanyStatus = employCompanyStatus;
    }

    public Integer getEmployUserStatus() {
        return employUserStatus;
    }

    public void setEmployUserStatus(Integer employUserStatus) {
        this.employUserStatus = employUserStatus;
    }

    public String getEmployReportInfo() {
        return employReportInfo;
    }

    public void setEmployReportInfo(String employReportInfo) {
        this.employReportInfo = employReportInfo;
    }
}
