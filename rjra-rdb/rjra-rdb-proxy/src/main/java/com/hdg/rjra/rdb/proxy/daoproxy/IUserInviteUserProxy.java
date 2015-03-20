package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserInviteUserProxy extends Serializable {

    public Long saveUserInviteUser(UserInviteUser userInviteUser);

    public List<Long> batchSaveUserInviteUser(List<UserInviteUser> userInviteUserList);

    public UserInviteUser findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId);

    public List<UserInviteUser> findAllUserInviteUserByInviteUserId(Long inviteUserId, Integer userInviteUserStatus);

    public List<UserInviteUser> findAllUserInviteUserByUserId(Long userId, Integer userInviteUserStatus);

    public Integer deleteUserInviteUser(Long collectId);
}
