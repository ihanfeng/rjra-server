package com.hdg.rjra.server.model.param.userbehavior;

import com.hdg.rjra.server.model.param.BaseParam;

import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserInviteUserParam extends BaseParam {
    private Long inviteId;
    private Long userId;
    private Long inviteUserId;
    private Date interviewTime;
    private String interviewAddress;
    private String interviewer;
    private String interviewMobile;
    private String interviewDesc;
    private Long applyId;
    private Long workId;
    private Integer userInviteUserStatus;
    private List<Long> batchDeleteInviteIds;

    public Long getInviteId() {
        return inviteId;
    }

    public void setInviteId(Long inviteId) {
        this.inviteId = inviteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInviteUserId() {
        return inviteUserId;
    }

    public void setInviteUserId(Long inviteUserId) {
        this.inviteUserId = inviteUserId;
    }

    public Date getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(Date interviewTime) {
        this.interviewTime = interviewTime;
    }

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getInterviewMobile() {
        return interviewMobile;
    }

    public void setInterviewMobile(String interviewMobile) {
        this.interviewMobile = interviewMobile;
    }

    public String getInterviewDesc() {
        return interviewDesc;
    }

    public void setInterviewDesc(String interviewDesc) {
        this.interviewDesc = interviewDesc;
    }

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Integer getUserInviteUserStatus() {
        return userInviteUserStatus;
    }

    public void setUserInviteUserStatus(Integer userInviteUserStatus) {
        this.userInviteUserStatus = userInviteUserStatus;
    }

    public List<Long> getBatchDeleteInviteIds() {
        return batchDeleteInviteIds;
    }

    public void setBatchDeleteInviteIds(List<Long> batchDeleteInviteIds) {
        this.batchDeleteInviteIds = batchDeleteInviteIds;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }
}
