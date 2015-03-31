package com.hdg.rjra.server.model.bo.resume;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.user.UserBo;

import java.util.Date;

/**
 * Created by Rock on 2015/1/25 0025.
 */
public class ResumeBo {

    private Long resumeId;
    private Long userId;
    private UserBo userDetail;
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
    private Long resumeHomeAreaId;
    private Long resumeHomeCityId;
    private Long resumeHomeProvinceId;
    private String resumeHomeAddress;
    private Date resumeCreateTime;
    private Date resumeUpdateTime;
    private Date resumeRefreshTime;
    private Integer distance;
    private Integer resumeDataType;
    private String resumeTag;
    private Long ageId;
    private Boolean collectUser;
    private Boolean inviteUser;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserBo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserBo userDetail) {
        this.userDetail = userDetail;
    }

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

    public Date getResumeCreateTime() {

        return resumeCreateTime;
    }

    public void setResumeCreateTime(Date resumeCreateTime) {
        this.resumeCreateTime = resumeCreateTime;
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

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getResumeDataType() {
        return resumeDataType;
    }

    public void setResumeDataType(Integer resumeDataType) {
        this.resumeDataType = resumeDataType;
    }

    public String getResumeTag() {
        return resumeTag;
    }

    public void setResumeTag(String resumeTag) {
        this.resumeTag = resumeTag;
    }

    public Long getAgeId() {
        return ageId;
    }

    public void setAgeId(Long ageId) {
        this.ageId = ageId;
    }

    public Boolean getCollectUser() {
        return collectUser;
    }

    public void setCollectUser(Boolean collectUser) {
        this.collectUser = collectUser;
    }

    public Boolean getInviteUser() {
        return inviteUser;
    }

    public void setInviteUser(Boolean inviteUser) {
        this.inviteUser = inviteUser;
    }
}
