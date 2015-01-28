package com.hdg.rjra.server.web.controller.param.resume;

import com.hdg.rjra.server.web.controller.param.BaseParam;

import java.util.Date;

/**
 * Created by Rock on 2015/1/25 0025.
 */
public class ResumeParam extends BaseParam {
    private Long resumeId;;
    private Double resumeLongitude;
    private Double resumeLatitude;
    private Integer resumeRaidus;
    private Long[] categoryLevel1Ids;
    private Long[] categoryLevel2Ids;
    private Long[] categoryLevel3Ids;
    private String resumeUserName;
    private String resumeMobile;
    private String resumeQQ;
    private String resumeDesc;
    private Integer resumeGender;
    private Date resumeBirthday;
    private Long resumeExperience;
    private Integer resumeWorkStatus;
    private Integer resumeStatus;
    private Long resumeWantWorkAreaId;
    private Long resumeWantWorkCityId;
    private Long resumeWantWorkProvinceId;

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Double getResumeLongitude() {
        return resumeLongitude;
    }

    public void setResumeLongitude(Double resumeLongitude) {
        this.resumeLongitude = resumeLongitude;
    }

    public Double getResumeLatitude() {
        return resumeLatitude;
    }

    public void setResumeLatitude(Double resumeLatitude) {
        this.resumeLatitude = resumeLatitude;
    }

    public Integer getResumeRaidus() {
        return resumeRaidus;
    }

    public void setResumeRaidus(Integer resumeRaidus) {
        this.resumeRaidus = resumeRaidus;
    }

    public Long[] getCategoryLevel1Ids() {
        return categoryLevel1Ids;
    }

    public void setCategoryLevel1Ids(Long[] categoryLevel1Ids) {
        this.categoryLevel1Ids = categoryLevel1Ids;
    }

    public Long[] getCategoryLevel2Ids() {
        return categoryLevel2Ids;
    }

    public void setCategoryLevel2Ids(Long[] categoryLevel2Ids) {
        this.categoryLevel2Ids = categoryLevel2Ids;
    }

    public Long[] getCategoryLevel3Ids() {
        return categoryLevel3Ids;
    }

    public void setCategoryLevel3Ids(Long[] categoryLevel3Ids) {
        this.categoryLevel3Ids = categoryLevel3Ids;
    }

    public String getResumeUserName() {
        return resumeUserName;
    }

    public void setResumeUserName(String resumeUserName) {
        this.resumeUserName = resumeUserName;
    }

    public String getResumeMobile() {
        return resumeMobile;
    }

    public void setResumeMobile(String resumeMobile) {
        this.resumeMobile = resumeMobile;
    }

    public String getResumeQQ() {
        return resumeQQ;
    }

    public void setResumeQQ(String resumeQQ) {
        this.resumeQQ = resumeQQ;
    }

    public String getResumeDesc() {
        return resumeDesc;
    }

    public void setResumeDesc(String resumeDesc) {
        this.resumeDesc = resumeDesc;
    }

    public Integer getResumeGender() {
        return resumeGender;
    }

    public void setResumeGender(Integer resumeGender) {
        this.resumeGender = resumeGender;
    }

    public Date getResumeBirthday() {
        return resumeBirthday;
    }

    public void setResumeBirthday(Date resumeBirthday) {
        this.resumeBirthday = resumeBirthday;
    }

    public Long getResumeExperience() {
        return resumeExperience;
    }

    public void setResumeExperience(Long resumeExperience) {
        this.resumeExperience = resumeExperience;
    }

    public Integer getResumeWorkStatus() {
        return resumeWorkStatus;
    }

    public void setResumeWorkStatus(Integer resumeWorkStatus) {
        this.resumeWorkStatus = resumeWorkStatus;
    }

    public Long getResumeWantWorkAreaId() {
        return resumeWantWorkAreaId;
    }

    public void setResumeWantWorkAreaId(Long resumeWantWorkAreaId) {
        this.resumeWantWorkAreaId = resumeWantWorkAreaId;
    }

    public Long getResumeWantWorkCityId() {
        return resumeWantWorkCityId;
    }

    public void setResumeWantWorkCityId(Long resumeWantWorkCityId) {
        this.resumeWantWorkCityId = resumeWantWorkCityId;
    }

    public Long getResumeWantWorkProvinceId() {
        return resumeWantWorkProvinceId;
    }

    public void setResumeWantWorkProvinceId(Long resumeWantWorkProvinceId) {
        this.resumeWantWorkProvinceId = resumeWantWorkProvinceId;
    }

    public Integer getResumeStatus() {
        return resumeStatus;
    }

    public void setResumeStatus(Integer resumeStatus) {
        this.resumeStatus = resumeStatus;
    }
}
