package com.hdg.rjra.server.model.bo.userbehavior;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.bo.work.WorkBo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectWorkBo implements Serializable {
    private Long collectId;
    private Long userId;
    private UserBo userDetail;
    private Long workId;
    private WorkBo workDetail;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String  collectTime;
    private Integer userCollectWorkStatus;

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

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(String collectTime) {
        this.collectTime = collectTime;
    }

    public Integer getUserCollectWorkStatus() {
        return userCollectWorkStatus;
    }

    public void setUserCollectWorkStatus(Integer userCollectWorkStatus) {
        this.userCollectWorkStatus = userCollectWorkStatus;
    }

    public UserBo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserBo userDetail) {
        this.userDetail = userDetail;
    }

    public WorkBo getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(WorkBo workDetail) {
        this.workDetail = workDetail;
    }
}
