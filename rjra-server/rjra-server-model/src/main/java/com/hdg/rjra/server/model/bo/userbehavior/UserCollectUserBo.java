package com.hdg.rjra.server.model.bo.userbehavior;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectUserBo implements Serializable {
    private Long collectId;
    private Long userId;
    private Long collectUserId;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String collectTime;
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

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getUserCollectUserStatus() {
        return userCollectUserStatus;
    }

    public void setUserCollectUserStatus(Integer userCollectUserStatus) {
        this.userCollectUserStatus = userCollectUserStatus;
    }
}
