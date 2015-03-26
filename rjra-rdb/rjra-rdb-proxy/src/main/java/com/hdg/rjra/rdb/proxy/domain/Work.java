package com.hdg.rjra.rdb.proxy.domain;

import com.hdg.rjra.rdb.proxy.utils.DBClass;
import com.hdg.rjra.rdb.proxy.utils.DBField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/27 0027.
 */
@DBClass("user_work")
public class Work implements BaseDomain {

    @DBField(value = "work_id", pk = true)
    private Long workId;
    @DBField("work_longitude")
    private Double workLongitude;
    @DBField("work_latitude")
    private Double workLatitude;
    private Long userId;
    private Long companyId;
    private String companyName;
    @DBField("category_level1_id")
    private Long categoryLevel1Id;
    @DBField("category_level2_id")
    private Long categoryLevel2Id;
    @DBField("category_level3_id")
    private Long categoryLevel3Id;
    @DBField("work_area_id")
    private Long workAreaId;
    @DBField("work_city_id")
    private Long workCityId;
    @DBField("work_province_id")
    private Long workProvinceId;
    @DBField("work_address")
    private String workAddress;
    @DBField("work_need_person")
    private Integer workNeedPerson;
    @DBField("work_wage_id")
    private Long workWageId;
    @DBField("work_experience_id")
    private Long workExperienceId;
    @DBField("work_welfare_ids")
    private Long[] workWelfareIds;
    @DBField("work_desc")
    private String workDesc;
    @DBField("work_status")
    private Integer workStatus;
    private Date workCreateTime;
    @DBField("work_update_time")
    private Date workUpdateTime;
    @DBField("age_id")
    private Long ageId;
    @DBField("work_gender")
    private Integer workGender;
    @DBField("work_data_type")
    private Integer workDataType;
    @DBField("work_tag")
    private String workTag;
    @DBField("work_import_time")
    private Date workImportTime;
    @DBField("work_import_operator_id")
    private Long workImportOperatorId;
    @DBField("work_import_operator_name")
    private String workImportOperatorName;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public void setWorkImportTime(Date workImportTime) {
        this.workImportTime = workImportTime;
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
}
