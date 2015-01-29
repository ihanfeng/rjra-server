package com.hdg.rjra.server.model.bo.resume;

import com.hdg.rjra.base.annotation.DateTimeFormat;
import com.hdg.rjra.base.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;

import java.util.Date;

/**
 * Created by Rock on 2015/1/25 0025.
 */
public class ResumeBo {

    private Long resumeId;
    private Long[] categoryLevel1Ids;
    private Long[] categoryLevel2Ids;
    private Long[] categoryLevel3Ids;
    private String resumeUserName;
    private String resumeMobile;
    private String resumeQQ;
    private String resumeDesc;
    private Long resumeUserHeadImageFile;
    private AccountFileBo resumeUserHeadImageFileDetail;
    private Integer resumeGender;
    private Date resumeBirthday;
    private String birthday;
    private Long resumeExperience;
    private Long resumeWage;
    private Integer resumeWorkStatus;
    private Integer resumeStatus;
    private Long resumeWantWorkAreaId;
    private Long resumeWantWorkCityId;
    private Long resumeWantWorkProvinceId;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String resumeCreateTime;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String resumeUpdateTime;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String resumeRefreshTime;

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

    public AccountFileBo getResumeUserHeadImageFileDetail() {
        return resumeUserHeadImageFileDetail;
    }

    public void setResumeUserHeadImageFileDetail(AccountFileBo resumeUserHeadImageFileDetail) {
        this.resumeUserHeadImageFileDetail = resumeUserHeadImageFileDetail;
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

    public String getResumeCreateTime() {
        return resumeCreateTime;
    }

    public void setResumeCreateTime(String resumeCreateTime) {
        this.resumeCreateTime = resumeCreateTime;
    }

    public String getResumeUpdateTime() {
        return resumeUpdateTime;
    }

    public void setResumeUpdateTime(String resumeUpdateTime) {
        this.resumeUpdateTime = resumeUpdateTime;
    }

    public String getResumeRefreshTime() {
        return resumeRefreshTime;
    }

    public void setResumeRefreshTime(String resumeRefreshTime) {
        this.resumeRefreshTime = resumeRefreshTime;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Long getResumeWage() {
        return resumeWage;
    }

    public void setResumeWage(Long resumeWage) {
        this.resumeWage = resumeWage;
    }
}
