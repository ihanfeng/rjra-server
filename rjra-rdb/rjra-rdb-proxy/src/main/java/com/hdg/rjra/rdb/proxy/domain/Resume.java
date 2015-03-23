package com.hdg.rjra.rdb.proxy.domain;

import com.hdg.rjra.rdb.proxy.utils.DBClass;
import com.hdg.rjra.rdb.proxy.utils.DBField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
@DBClass("user_resume")
public class Resume implements BaseDomain {

    @DBField(value = "resume_id", pk = true)
    private Long resumeId;
    @DBField("category_level1_ids")
    private Long[] categoryLevel1Ids;
    @DBField("category_level2_ids")
    private Long[] categoryLevel2Ids;
    @DBField("category_level3_ids")
    private Long[] categoryLevel3Ids;
    @DBField("resume_user_name")
    private String resumeUserName;
    @DBField("resume_mobile")
    private String resumeMobile;
    @DBField("resume_qq")
    private String resumeQQ;
    @DBField("resume_desc")
    private String resumeDesc;
    private Long resumeUserHeadImageFile;
    @DBField("resume_gender")
    private Integer resumeGender;
    @DBField("resume_birthday")
    private Date resumeBirthday;
    @DBField("resume_wage")
    private Long resumeWage;
    @DBField("resume_experience")
    private Long resumeExperience;
    @DBField("resume_work_status")
    private Integer resumeWorkStatus;
    @DBField("resume_status")
    private Integer resumeStatus;
    @DBField("resume_want_work_area_id")
    private Long resumeWantWorkAreaId;
    @DBField("resume_want_work_city_id")
    private Long resumeWantWorkCityId;
    @DBField("resume_want_work_province_id")
    private Long resumeWantWorkProvinceId;
    @DBField("resume_home_area_id")
    private Long resumeHomeAreaId;
    @DBField("resume_home_city_id")
    private Long resumeHomeCityId;
    @DBField("resume_home_province_id")
    private Long resumeHomeProvinceId;
    @DBField("resume_home_address")
    private String resumeHomeAddress;
    private Date resumeCreateTime;
    @DBField("resume_update_time")
    private Date resumeUpdateTime;
    @DBField("resume_refresh_time")
    private Date resumeRefreshTime;

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
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

    public Long getResumeUserHeadImageFile() {
        return resumeUserHeadImageFile;
    }

    public void setResumeUserHeadImageFile(Long resumeUserHeadImageFile) {
        this.resumeUserHeadImageFile = resumeUserHeadImageFile;
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

    public Integer getResumeStatus() {
        return resumeStatus;
    }

    public void setResumeStatus(Integer resumeStatus) {
        this.resumeStatus = resumeStatus;
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

    public Date getResumeCreateTime() {
        return resumeCreateTime;
    }

    public void setResumeCreateTime(Date resumeCreateTime) {
        this.resumeCreateTime = resumeCreateTime;
    }

    public Date getResumeUpdateTime() {
        return resumeUpdateTime;
    }

    public void setResumeUpdateTime(Date resumeUpdateTime) {
        this.resumeUpdateTime = resumeUpdateTime;
    }

    public Date getResumeRefreshTime() {
        return resumeRefreshTime;
    }

    public void setResumeRefreshTime(Date resumeRefreshTime) {
        this.resumeRefreshTime = resumeRefreshTime;
    }

    public Long getResumeWage() {
        return resumeWage;
    }

    public void setResumeWage(Long resumeWage) {
        this.resumeWage = resumeWage;
    }

    public Long getResumeHomeAreaId() {
        return resumeHomeAreaId;
    }

    public void setResumeHomeAreaId(Long resumeHomeAreaId) {
        this.resumeHomeAreaId = resumeHomeAreaId;
    }

    public Long getResumeHomeCityId() {
        return resumeHomeCityId;
    }

    public void setResumeHomeCityId(Long resumeHomeCityId) {
        this.resumeHomeCityId = resumeHomeCityId;
    }

    public Long getResumeHomeProvinceId() {
        return resumeHomeProvinceId;
    }

    public void setResumeHomeProvinceId(Long resumeHomeProvinceId) {
        this.resumeHomeProvinceId = resumeHomeProvinceId;
    }

    public String getResumeHomeAddress() {
        return resumeHomeAddress;
    }

    public void setResumeHomeAddress(String resumeHomeAddress) {
        this.resumeHomeAddress = resumeHomeAddress;
    }
}
