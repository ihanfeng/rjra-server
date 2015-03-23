package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectUser implements BaseDomain {
    private Long collectId;
    private Long userId;
    private Long collectUserId;
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
}
