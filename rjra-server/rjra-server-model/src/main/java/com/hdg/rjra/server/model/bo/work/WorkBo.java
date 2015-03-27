package com.hdg.rjra.server.model.bo.work;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.company.CompanyBo;

import java.util.Date;

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
    private Date workCreateTime;
    private Date workUpdateTime;
    private Long ageId;
    private Integer workGender;
    private Integer workDataType;
    private String workTag;
    private Date workImportTime;
    private Long workImportOperatorId;
    private String workImportOperatorName;
    private Integer distance;

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

    public Date getWorkCreateTime() {
        return workCreateTime;
    }

    public void setWorkCreateTime(Date workCreateTime) {
        this.workCreateTime = workCreateTime;
    }

    public Date getWorkUpdateTime() {
        return workUpdateTime;
    }

    public void setWorkUpdateTime(Date workUpdateTime) {
        this.workUpdateTime = workUpdateTime;
    }

    public void setWorkImportTime(Date workImportTime) {
        this.workImportTime = workImportTime;
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }

    public Integer getWorkGender() {
        return workGender;
    }

    public void setWorkGender(Integer workGender) {
        this.workGender = workGender;
    }

    public Integer getWorkDataType() {
        return workDataType;
    }

    public void setWorkDataType(Integer workDataType) {
        this.workDataType = workDataType;
    }

    public String getWorkTag() {
        return workTag;
    }

    public void setWorkTag(String workTag) {
        this.workTag = workTag;
    }

    public Date getWorkImportTime() {
        return workImportTime;
    }

    public Long getWorkImportOperatorId() {
        return workImportOperatorId;
    }

    public void setWorkImportOperatorId(Long workImportOperatorId) {
        this.workImportOperatorId = workImportOperatorId;
    }

    public String getWorkImportOperatorName() {
        return workImportOperatorName;
    }

    public void setWorkImportOperatorName(String workImportOperatorName) {
        this.workImportOperatorName = workImportOperatorName;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }
}
