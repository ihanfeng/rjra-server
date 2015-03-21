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
public class UserApplyWorkBo implements Serializable {
    private Long applyId;
    private Long workId;
    private WorkBo workDetail;
    private Long userId;
    private UserBo userDetail;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String applyTime;
    private Integer userApplyWorkStatus;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getUserApplyWorkStatus() {
        return userApplyWorkStatus;
    }

    public void setUserApplyWorkStatus(Integer userApplyWorkStatus) {
        this.userApplyWorkStatus = userApplyWorkStatus;
    }

    public WorkBo getWorkDetail() {
        return workDetail;
    }

    public void setWorkDetail(WorkBo workDetail) {
        this.workDetail = workDetail;
    }

    public UserBo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserBo userDetail) {
        this.userDetail = userDetail;
    }
}
