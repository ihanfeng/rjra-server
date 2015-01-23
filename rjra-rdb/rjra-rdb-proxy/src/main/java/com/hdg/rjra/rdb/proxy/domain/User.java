package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class User implements Serializable {
    private Long userId;
    private String userMobile;
    private String userPwd;
    private String userName;
    private Long userHeadImageFile;
    private String userEmail;
    private Integer userWorkStatus;
    private Long userProvinceId;
    private Long userCityId;
    private Long userAreaId;
    private String userAddress;
    private Long userNowProvinceId;
    private Long userNowCityId;
    private Long userNowAreaId;
    private String userNowAddress;
    private Long userHopeProvinceId;
    private Long userHopeCityId;
    private Long userHopeAreaId;
    private Long[] userSkillsLevel1;
    private Long[] userSkillsLevel2;
    private Long[] userSkillsLevel3;
    private String userIdcard;
    private Long userIdcardImageFile;
    private Double userLoginLongitude;
    private Double userLoginLatitude;
    private String userStatus;
    private Date userCreateTime;
    private Date userUpdateTime;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserHeadImageFile() {
        return userHeadImageFile;
    }

    public void setUserHeadImageFile(Long userHeadImageFile) {
        this.userHeadImageFile = userHeadImageFile;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    public Long getUserIdcardImageFile() {
        return userIdcardImageFile;
    }

    public void setUserIdcardImageFile(Long userIdcardImageFile) {
        this.userIdcardImageFile = userIdcardImageFile;
    }

    public Double getUserLoginLongitude() {
        return userLoginLongitude;
    }

    public void setUserLoginLongitude(Double userLoginLongitude) {
        this.userLoginLongitude = userLoginLongitude;
    }

    public Double getUserLoginLatitude() {
        return userLoginLatitude;
    }

    public void setUserLoginLatitude(Double userLoginLatitude) {
        this.userLoginLatitude = userLoginLatitude;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getUserWorkStatus() {
        return userWorkStatus;
    }

    public void setUserWorkStatus(Integer userWorkStatus) {
        this.userWorkStatus = userWorkStatus;
    }

    public Long getUserProvinceId() {
        return userProvinceId;
    }

    public void setUserProvinceId(Long userProvinceId) {
        this.userProvinceId = userProvinceId;
    }

    public Long getUserCityId() {
        return userCityId;
    }

    public void setUserCityId(Long userCityId) {
        this.userCityId = userCityId;
    }

    public Long getUserAreaId() {
        return userAreaId;
    }

    public void setUserAreaId(Long userAreaId) {
        this.userAreaId = userAreaId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Long getUserNowProvinceId() {
        return userNowProvinceId;
    }

    public void setUserNowProvinceId(Long userNowProvinceId) {
        this.userNowProvinceId = userNowProvinceId;
    }

    public Long getUserNowCityId() {
        return userNowCityId;
    }

    public void setUserNowCityId(Long userNowCityId) {
        this.userNowCityId = userNowCityId;
    }

    public Long getUserNowAreaId() {
        return userNowAreaId;
    }

    public void setUserNowAreaId(Long userNowAreaId) {
        this.userNowAreaId = userNowAreaId;
    }

    public String getUserNowAddress() {
        return userNowAddress;
    }

    public void setUserNowAddress(String userNowAddress) {
        this.userNowAddress = userNowAddress;
    }

    public Long getUserHopeProvinceId() {
        return userHopeProvinceId;
    }

    public void setUserHopeProvinceId(Long userHopeProvinceId) {
        this.userHopeProvinceId = userHopeProvinceId;
    }

    public Long getUserHopeCityId() {
        return userHopeCityId;
    }

    public void setUserHopeCityId(Long userHopeCityId) {
        this.userHopeCityId = userHopeCityId;
    }

    public Long getUserHopeAreaId() {
        return userHopeAreaId;
    }

    public void setUserHopeAreaId(Long userHopeAreaId) {
        this.userHopeAreaId = userHopeAreaId;
    }

    public Long[] getUserSkillsLevel1() {
        return userSkillsLevel1;
    }

    public void setUserSkillsLevel1(Long[] userSkillsLevel1) {
        this.userSkillsLevel1 = userSkillsLevel1;
    }

    public Long[] getUserSkillsLevel2() {
        return userSkillsLevel2;
    }

    public void setUserSkillsLevel2(Long[] userSkillsLevel2) {
        this.userSkillsLevel2 = userSkillsLevel2;
    }

    public Long[] getUserSkillsLevel3() {
        return userSkillsLevel3;
    }

    public void setUserSkillsLevel3(Long[] userSkillsLevel3) {
        this.userSkillsLevel3 = userSkillsLevel3;
    }
}
