package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserInviteUser implements Serializable {
    private Long inviteId;
    private Long userId;
    private Long inviteUserId;
    private Date inviteTime;
    private Date interviewTime;
    private String interviewAddress;
    private String interviewer;
    private String interviewMobile;
    private String interviewDesc;
    private Long applyId;
    private Integer userInviteUserStatus;

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

    public Date getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(Date inviteTime) {
        this.inviteTime = inviteTime;
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
}
