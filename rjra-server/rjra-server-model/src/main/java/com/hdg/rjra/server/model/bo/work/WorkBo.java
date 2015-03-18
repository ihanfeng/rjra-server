package com.hdg.rjra.server.model.bo.work;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.company.CompanyBo;

/**
 * Created by Rock on 2015/1/28 0028.
 */
public class WorkBo {

    private Long workId;
    private Double workLongitude;
    private Double workLatitude;
    private Long userId;
    private Long companyId;
    private CompanyBo companyDetail;
    private String companyName;
    private Long categoryLevel1Id;
    private Long categoryLevel2Id;
    private Long categoryLevel3Id;
    private Long workAreaId;
    private Long workCityId;
    private Long workProvinceId;
    private String workAddress;
    private Integer workNeedPerson;
    private Long workWageId;
    private Long workExperienceId;
    private Long[] workWelfareIds;
    private String workDesc;
    private Integer workStatus;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String workCreateTime;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String workUpdateTime;

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Double getWorkLongitude() {
        return workLongitude;
    }

    public void setWorkLongitude(Double workLongitude) {
        this.workLongitude = workLongitude;
    }

    public Double getWorkLatitude() {
        return workLatitude;
    }

    public void setWorkLatitude(Double workLatitude) {
        this.workLatitude = workLatitude;
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

    public CompanyBo getCompanyDetail() {
        return companyDetail;
    }

    public void setCompanyDetail(CompanyBo companyDetail) {
        this.companyDetail = companyDetail;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getWorkAreaId() {
        return workAreaId;
    }

    public void setWorkAreaId(Long workAreaId) {
        this.workAreaId = workAreaId;
    }

    public Long getWorkCityId() {
        return workCityId;
    }

    public void setWorkCityId(Long workCityId) {
        this.workCityId = workCityId;
    }

    public Long getWorkProvinceId() {
        return workProvinceId;
    }

    public void setWorkProvinceId(Long workProvinceId) {
        this.workProvinceId = workProvinceId;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public Integer getWorkNeedPerson() {
        return workNeedPerson;
    }

    public void setWorkNeedPerson(Integer workNeedPerson) {
        this.workNeedPerson = workNeedPerson;
    }

    public Long getWorkWageId() {
        return workWageId;
    }

    public void setWorkWageId(Long workWageId) {
        this.workWageId = workWageId;
    }

    public Long getWorkExperienceId() {
        return workExperienceId;
    }

    public void setWorkExperienceId(Long workExperienceId) {
        this.workExperienceId = workExperienceId;
    }

    public Long[] getWorkWelfareIds() {
        return workWelfareIds;
    }

    public void setWorkWelfareIds(Long[] workWelfareIds) {
        this.workWelfareIds = workWelfareIds;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkCreateTime() {
        return workCreateTime;
    }

    public void setWorkCreateTime(String workCreateTime) {
        this.workCreateTime = workCreateTime;
    }

    public String getWorkUpdateTime() {
        return workUpdateTime;
    }

    public void setWorkUpdateTime(String workUpdateTime) {
        this.workUpdateTime = workUpdateTime;
    }
}
