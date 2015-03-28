package com.hdg.rjra.server.model.bo.importlog;

import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.region.AreaBo;
import com.hdg.rjra.server.model.bo.region.CityBo;
import com.hdg.rjra.server.model.bo.region.ProvinceBo;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class ImportData {
    // 导入数据模型
    String companyName;
    String contactName;
    String mobile;
    String email;
    String address;
    String category;
    String companyDesc;
    String companyType;
    String companyScale;
    String province;
    String city;
    String qq;

    /**
     * 目标企业数据
     */
    private Long companyTypeId;
    private Long companyScaleId;
    private Long companyAreaId;
    private Long companyCityId;
    private Long companyProvinceId;
    private String companyAddress;
    private String companyContact;
    private String companyContactMobile;
    private String companyEmail;
    private Integer companyDataType;
    private String companyTag;
    private Long companyExamineReviewerId;
    private String companyExamineReviewerName;
    private Date companyImportTime;
    private Long companyImportOperatorId;
    private String companyImportOperatorName;

    /**
     * 目标工作数据模型
     */
    private Double workLongitude;
    private Double workLatitude;
    private Long userId;
    private Long companyId;
    private CompanyBo companyDetail;
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
    private Long ageId;
    private Integer workGender;
    private Integer workDataType;
    private String workTag;
    private Date workImportTime;
    private Long workImportOperatorId;
    private String workImportOperatorName;

    List<AreaBo> areaList;
    List<CityBo> cityList;
    List<ProvinceBo> provinceList;
    List<CompanyBo> companyList;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public List<AreaBo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaBo> areaList) {
        this.areaList = areaList;
    }

    public List<CityBo> getCityList() {
        return cityList;
    }

    public void setCityList(List<CityBo> cityList) {
        this.cityList = cityList;
    }

    public List<ProvinceBo> getProvinceList() {
        return provinceList;
    }

    public void setProvinceList(List<ProvinceBo> provinceList) {
        this.provinceList = provinceList;
    }

    public List<CompanyBo> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CompanyBo> companyList) {
        this.companyList = companyList;
    }

    public Long getCompanyTypeId() {
        return companyTypeId;
    }

    public void setCompanyTypeId(Long companyTypeId) {
        this.companyTypeId = companyTypeId;
    }

    public Long getCompanyScaleId() {
        return companyScaleId;
    }

    public void setCompanyScaleId(Long companyScaleId) {
        this.companyScaleId = companyScaleId;
    }

    public Long getCompanyAreaId() {
        return companyAreaId;
    }

    public void setCompanyAreaId(Long companyAreaId) {
        this.companyAreaId = companyAreaId;
    }

    public Long getCompanyCityId() {
        return companyCityId;
    }

    public void setCompanyCityId(Long companyCityId) {
        this.companyCityId = companyCityId;
    }

    public Long getCompanyProvinceId() {
        return companyProvinceId;
    }

    public void setCompanyProvinceId(Long companyProvinceId) {
        this.companyProvinceId = companyProvinceId;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyContactMobile() {
        return companyContactMobile;
    }

    public void setCompanyContactMobile(String companyContactMobile) {
        this.companyContactMobile = companyContactMobile;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public Integer getCompanyDataType() {
        return companyDataType;
    }

    public void setCompanyDataType(Integer companyDataType) {
        this.companyDataType = companyDataType;
    }

    public String getCompanyTag() {
        return companyTag;
    }

    public void setCompanyTag(String companyTag) {
        this.companyTag = companyTag;
    }

    public Long getCompanyExamineReviewerId() {
        return companyExamineReviewerId;
    }

    public void setCompanyExamineReviewerId(Long companyExamineReviewerId) {
        this.companyExamineReviewerId = companyExamineReviewerId;
    }

    public String getCompanyExamineReviewerName() {
        return companyExamineReviewerName;
    }

    public void setCompanyExamineReviewerName(String companyExamineReviewerName) {
        this.companyExamineReviewerName = companyExamineReviewerName;
    }

    public Date getCompanyImportTime() {
        return companyImportTime;
    }

    public void setCompanyImportTime(Date companyImportTime) {
        this.companyImportTime = companyImportTime;
    }

    public Long getCompanyImportOperatorId() {
        return companyImportOperatorId;
    }

    public void setCompanyImportOperatorId(Long companyImportOperatorId) {
        this.companyImportOperatorId = companyImportOperatorId;
    }

    public String getCompanyImportOperatorName() {
        return companyImportOperatorName;
    }

    public void setCompanyImportOperatorName(String companyImportOperatorName) {
        this.companyImportOperatorName = companyImportOperatorName;
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

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
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
