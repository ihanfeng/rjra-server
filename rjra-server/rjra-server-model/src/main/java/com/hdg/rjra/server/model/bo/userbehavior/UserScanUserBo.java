package com.hdg.rjra.server.model.bo.userbehavior;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.user.UserBo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserScanUserBo implements Serializable {
    private Long scanId;
    private Long userId;
    private UserBo userDetail;
    private Long scanUserId;
    private UserBo scanUserDetail;
    private Date scanTime;

    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScanUserId() {
        return scanUserId;
    }

    public void setScanUserId(Long scanUserId) {
        this.scanUserId = scanUserId;
    }

    public Date getScanTime() {
        return scanTime;
    }

    public void setScanTime(Date scanTime) {
        this.scanTime = scanTime;
    }

    public UserBo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserBo userDetail) {
        this.userDetail = userDetail;
    }

    public UserBo getScanUserDetail() {
        return scanUserDetail;
    }

    public void setScanUserDetail(UserBo scanUserDetail) {
        this.scanUserDetail = scanUserDetail;
    }
}
