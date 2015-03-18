package com.hdg.rjra.server.web.controller.param.userbehavior;

import com.hdg.common.annotation.DateTimeFormat;
import com.hdg.common.constants.CommonConstants;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserInviteUserParam implements Serializable {
    private Long inviteId;
    private Long userId;
    private Long inviteUserId;

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
}
