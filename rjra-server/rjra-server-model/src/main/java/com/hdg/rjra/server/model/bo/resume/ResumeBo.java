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
    private Long categoryLevel1Id;
    private Long categoryLevel2Id;
    private Long categoryLevel3Id;
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

    public Long getCategoryLevel1Id() {
        return categoryLevel1Id;
    }

    public void setCategoryLevel1Id(Long categoryLevel1Id) {
        this.categoryLevel1Id = categoryLevel1Id;
    }

    public Long getCategoryLevel2Id() {
        return categoryLevel2Id;
    }

    public void setCategoryLevel2Id(Long categoryLevel2Id) {
        this.categoryLevel2Id = categoryLevel2Id;
    }

    public Long getCategoryLevel3Id() {
        return categoryLevel3Id;
    }

    public void setCategoryLevel3Id(Long categoryLevel3Id) {
        this.categoryLevel3Id = categoryLevel3Id;
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
}
