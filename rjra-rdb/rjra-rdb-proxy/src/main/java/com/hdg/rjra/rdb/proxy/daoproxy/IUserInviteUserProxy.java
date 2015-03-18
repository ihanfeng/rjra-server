package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserInviteUserProxy extends Serializable {

    public Long saveUserInviteUser(UserInviteUser userInviteUser);

    public UserInviteUser findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId);
}
