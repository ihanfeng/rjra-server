package com.hdg.rjra.server.model.bo.employ;

import com.hdg.rjra.base.annotation.DateTimeFormat;
import com.hdg.rjra.base.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.recruitmentinfo.RecruitmentInfoBo;
import com.hdg.rjra.server.model.bo.user.UserBo;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public class EmployBo {
    private Long employId;
    private Long infoId;
    private RecruitmentInfoBo infoDetail;
    private Long userId;
    private UserBo userDetail;
    private Long companyId;
    private Integer employType;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String employTime;
    private Integer employResultStatus;
    private String employResultInfo;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String employReportPlanTime;
    private Integer employReportStatus;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String employEntryPlanTime;
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

    public String getEmployTime() {
        return employTime;
    }

    public void setEmployTime(String employTime) {
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

    public String getEmployReportPlanTime() {
        return employReportPlanTime;
    }

    public void setEmployReportPlanTime(String employReportPlanTime) {
        this.employReportPlanTime = employReportPlanTime;
    }

    public Integer getEmployReportStatus() {
        return employReportStatus;
    }

    public void setEmployReportStatus(Integer employReportStatus) {
        this.employReportStatus = employReportStatus;
    }

    public String getEmployEntryPlanTime() {
        return employEntryPlanTime;
    }

    public void setEmployEntryPlanTime(String employEntryPlanTime) {
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

    public RecruitmentInfoBo getInfoDetail() {
        return infoDetail;
    }

    public void setInfoDetail(RecruitmentInfoBo infoDetail) {
        this.infoDetail = infoDetail;
    }

    public UserBo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserBo userDetail) {
        this.userDetail = userDetail;
    }
}
