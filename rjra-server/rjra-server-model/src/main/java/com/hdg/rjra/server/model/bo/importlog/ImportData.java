package com.hdg.rjra.server.model.bo.importlog;

import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.region.AreaBo;
import com.hdg.rjra.server.model.bo.region.CityBo;
import com.hdg.rjra.server.model.bo.region.ProvinceBo;

import java.util.List;

/**
 * Created by Administrator on 2015/3/27.
 */
public class ImportData {
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

    String workDesc;
    String needPerson;
    String wage;

    List<AreaBo> areaList;
    List<CityBo> cityList;
    List<ProvinceBo> provinceList;

    List<CompanyBo> companyPager;

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

    public String getNeedPerson() {
        return needPerson;
    }

    public void setNeedPerson(String needPerson) {
        this.needPerson = needPerson;
    }

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
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

    public List<CompanyBo> getCompanyPager() {
        return companyPager;
    }

    public void setCompanyPager(List<CompanyBo> companyPager) {
        this.companyPager = companyPager;
    }
}
