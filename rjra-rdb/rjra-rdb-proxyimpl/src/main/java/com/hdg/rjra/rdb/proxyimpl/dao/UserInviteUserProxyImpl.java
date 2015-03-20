package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserInviteUserProxy;
import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class UserInviteUserProxyImpl extends BaseProxy implements IUserInviteUserProxy {

    private static String moduleName = "rdb-user-invite-user";

    @Override
    public Long saveUserInviteUser(UserInviteUser userInviteUser) {
        return daoClient.invoke(moduleName, "saveUserInviteUser",
                new Object[]{userInviteUser});
    }

    @Override
    public List<Long> batchSaveUserInviteUser(List<UserInviteUser> userInviteUserList) {
        return daoClient.invoke(moduleName, "batchSaveUserInviteUser",
                new Object[]{userInviteUserList});
    }

    @Override
    public UserInviteUser findUserInviteUserByUserIdAndInviteUserId(Long userId, Long collectUserId) {
        return daoClient.invoke(moduleName, "findUserInviteUserByUserIdAndInviteUserId",
                new Object[]{userId, collectUserId});
    }

    @Override
    public List<UserInviteUser> findAllUserInviteUserByInviteUserId(Long inviteUserId, Integer userInviteUserStatus) {
        return daoClient.invoke(moduleName, "findAllUserInviteUserByInviteUserId",
                new Object[]{inviteUserId, userInviteUserStatus});
    }

    @Override
    public List<UserInviteUser> findAllUserInviteUserByUserId(Long userId, Integer userInviteUserStatus) {
        return daoClient.invoke(moduleName, "findAllUserInviteUserByUserId",
                new Object[]{userId, userInviteUserStatus});
    }
}
