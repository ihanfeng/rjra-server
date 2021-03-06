package com.hdg.rjra.server.model.bo.userbehavior;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.user.UserBo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectUserBo implements Serializable {
    private Long collectId;
    private Long userId;
    private UserBo userDetail;
    private Long collectUserId;
    private UserBo collectUserDetail;
    private Date collectTime;
    private Integer userCollectUserStatus;

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCollectUserId() {
        return collectUserId;
    }

    public void setCollectUserId(Long collectUserId) {
        this.collectUserId = collectUserId;
    }

    public Date getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(Date collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getUserCollectUserStatus() {
        return userCollectUserStatus;
    }

    public void setUserCollectUserStatus(Integer userCollectUserStatus) {
        this.userCollectUserStatus = userCollectUserStatus;
    }

    public UserBo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserBo userDetail) {
        this.userDetail = userDetail;
    }

    public UserBo getCollectUserDetail() {
        return collectUserDetail;
    }

    public void setCollectUserDetail(UserBo collectUserDetail) {
        this.collectUserDetail = collectUserDetail;
    }
}
