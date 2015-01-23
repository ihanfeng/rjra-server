package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class RecruitmentInfo implements Serializable {

    private Long infoId;
    private Long companyId;
    private Long categoryLevel1Id;
    private Long categoryLevel2Id;
    private Long categoryLevel3Id;
    private Long areaId;
    private Long cityId;
    private Long provinceId;
    private Long wageId;
    private Long experienceId;
    private Long educationId;
    private Long[] infoWelfare;
    private Integer infoType;
    private Long[] infoImageFiles;
    private Long infoVedioFile;
    private String infoVedioContent;
    private String infoWorkAddress;
    private Integer infoNeedPerson;
    private Integer infoUpperAgeDemand;
    private Integer infoLowerAgeDemand;
    private Double infoWorkLongitude;
    private Double infoWorkLatitude;
    private Integer infoStatus;
    private Date infoCreateTime;
    private Date infoUpdateTime;
    private String infoDesc;
    private Integer examineStatus;
    private String examineResultInfo;
    private Date examineTime;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getWageId() {
        return wageId;
    }

    public void setWageId(Long wageId) {
        this.wageId = wageId;
    }

    public Long getExperienceId() {
        return experienceId;
    }

    public void setExperienceId(Long experienceId) {
        this.experienceId = experienceId;
    }

    public Long getEducationId() {
        return educationId;
    }

    public void setEducationId(Long educationId) {
        this.educationId = educationId;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Long getInfoVedioFile() {
        return infoVedioFile;
    }

    public void setInfoVedioFile(Long infoVedioFile) {
        this.infoVedioFile = infoVedioFile;
    }

    public String getInfoVedioContent() {
        return infoVedioContent;
    }

    public void setInfoVedioContent(String infoVedioContent) {
        this.infoVedioContent = infoVedioContent;
    }

    public String getInfoWorkAddress() {
        return infoWorkAddress;
    }

    public void setInfoWorkAddress(String infoWorkAddress) {
        this.infoWorkAddress = infoWorkAddress;
    }

    public Integer getInfoNeedPerson() {
        return infoNeedPerson;
    }

    public void setInfoNeedPerson(Integer infoNeedPerson) {
        this.infoNeedPerson = infoNeedPerson;
    }

    public Integer getInfoUpperAgeDemand() {
        return infoUpperAgeDemand;
    }

    public void setInfoUpperAgeDemand(Integer infoUpperAgeDemand) {
        this.infoUpperAgeDemand = infoUpperAgeDemand;
    }

    public Integer getInfoLowerAgeDemand() {
        return infoLowerAgeDemand;
    }

    public void setInfoLowerAgeDemand(Integer infoLowerAgeDemand) {
        this.infoLowerAgeDemand = infoLowerAgeDemand;
    }

    public Double getInfoWorkLongitude() {
        return infoWorkLongitude;
    }

    public void setInfoWorkLongitude(Double infoWorkLongitude) {
        this.infoWorkLongitude = infoWorkLongitude;
    }

    public Double getInfoWorkLatitude() {
        return infoWorkLatitude;
    }

    public void setInfoWorkLatitude(Double infoWorkLatitude) {
        this.infoWorkLatitude = infoWorkLatitude;
    }

    public Integer getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(Integer infoStatus) {
        this.infoStatus = infoStatus;
    }

    public Date getInfoCreateTime() {
        return infoCreateTime;
    }

    public void setInfoCreateTime(Date infoCreateTime) {
        this.infoCreateTime = infoCreateTime;
    }

    public Date getInfoUpdateTime() {
        return infoUpdateTime;
    }

    public void setInfoUpdateTime(Date infoUpdateTime) {
        this.infoUpdateTime = infoUpdateTime;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineResultInfo() {
        return examineResultInfo;
    }

    public void setExamineResultInfo(String examineResultInfo) {
        this.examineResultInfo = examineResultInfo;
    }

    public Date getExamineTime() {
        return examineTime;
    }

    public void setExamineTime(Date examineTime) {
        this.examineTime = examineTime;
    }

    public Long[] getInfoWelfare() {
        return infoWelfare;
    }

    public void setInfoWelfare(Long[] infoWelfare) {
        this.infoWelfare = infoWelfare;
    }

    public Long[] getInfoImageFiles() {
        return infoImageFiles;
    }

    public void setInfoImageFiles(Long[] infoImageFiles) {
        this.infoImageFiles = infoImageFiles;
    }
}
