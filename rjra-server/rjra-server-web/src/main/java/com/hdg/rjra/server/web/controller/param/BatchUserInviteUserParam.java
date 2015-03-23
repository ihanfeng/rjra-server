package com.hdg.rjra.server.web.controller.param;

import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class BatchUserInviteUserParam implements Serializable {
    private List<UserInviteUser> batchUserInvite;

    public List<UserInviteUser> getBatchUserInvite() {
        return batchUserInvite;
    }

    public void setBatchUserInvite(List<UserInviteUser> batchUserInvite) {
        this.batchUserInvite = batchUserInvite;
    }
}
