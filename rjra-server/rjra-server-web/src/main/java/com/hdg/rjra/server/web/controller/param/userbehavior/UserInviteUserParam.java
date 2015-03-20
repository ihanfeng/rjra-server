package com.hdg.rjra.server.web.controller.param.userbehavior;

import com.hdg.rjra.server.web.controller.param.BaseParam;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserInviteUserParam extends BaseParam implements Serializable {
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
