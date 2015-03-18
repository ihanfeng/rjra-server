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
    private Date inviteDate;

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

    public Date getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(Date inviteDate) {
        this.inviteDate = inviteDate;
    }
}
