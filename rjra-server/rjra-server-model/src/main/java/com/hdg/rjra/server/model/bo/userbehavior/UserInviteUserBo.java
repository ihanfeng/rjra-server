package com.hdg.rjra.server.model.bo.userbehavior;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;
import com.hdg.rjra.server.model.bo.user.UserBo;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserInviteUserBo implements Serializable {
    private Long inviteId;
    private Long userId;
    private UserBo userDetail;
    private Long inviteUserId;
    private UserBo inviteUserDetail;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String inviteTime;
    @DateTimeFormat(pattern = CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS)
    private String interviewTime;
    private String interviewAddress;
    private String interviewer;
    private String interviewMobile;
    private String interviewDesc;
    private Long applyId;
    private Long workId;
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

    public String getInviteTime() {
        return inviteTime;
    }

    public void setInviteTime(String inviteTime) {
        this.inviteTime = inviteTime;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
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

    public UserBo getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserBo userDetail) {
        this.userDetail = userDetail;
    }

    public UserBo getInviteUserDetail() {
        return inviteUserDetail;
    }

    public void setInviteUserDetail(UserBo inviteUserDetail) {
        this.inviteUserDetail = inviteUserDetail;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }
}
