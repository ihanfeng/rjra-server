package com.hdg.rjra.server.web.controller.param.work;

import com.hdg.rjra.server.web.controller.param.BaseParam;

/**
 * Created by Rock on 2015/1/28 0028.
 */
public class WorkParam extends BaseParam {
    private Long workId;
    private Double workLongitude;
    private Double workLatitude;
    private Integer workRaidus;
    private Long userId;
    private Long companyId;
    private Long categoryLeve1Id;
    private Long categoryLeve2Id;
    private Long categoryLeve3Id;
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

    public Long getCategoryLeve1Id() {
        return categoryLeve1Id;
    }

    public void setCategoryLeve1Id(Long categoryLeve1Id) {
        this.categoryLeve1Id = categoryLeve1Id;
    }

    public Long getCategoryLeve2Id() {
        return categoryLeve2Id;
    }

    public void setCategoryLeve2Id(Long categoryLeve2Id) {
        this.categoryLeve2Id = categoryLeve2Id;
    }

    public Long getCategoryLeve3Id() {
        return categoryLeve3Id;
    }

    public void setCategoryLeve3Id(Long categoryLeve3Id) {
        this.categoryLeve3Id = categoryLeve3Id;
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

    public Integer getWorkRaidus() {
        return workRaidus;
    }

    public void setWorkRaidus(Integer workRaidus) {
        this.workRaidus = workRaidus;
    }
}
