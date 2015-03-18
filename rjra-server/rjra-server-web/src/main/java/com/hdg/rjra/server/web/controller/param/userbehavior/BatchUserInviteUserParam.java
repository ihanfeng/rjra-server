package com.hdg.rjra.server.web.controller.param.userbehavior;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class BatchUserInviteUserParam implements Serializable {
    private List<UserInviteUserParam> batchUserInvite;

    public List<UserInviteUserParam> getBatchUserInvite() {
        return batchUserInvite;
    }

    public void setBatchUserInvite(List<UserInviteUserParam> batchUserInvite) {
        this.batchUserInvite = batchUserInvite;
    }
}
