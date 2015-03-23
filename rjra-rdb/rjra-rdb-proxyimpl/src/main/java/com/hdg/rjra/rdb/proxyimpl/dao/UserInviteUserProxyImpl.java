package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserInviteUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
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
    public Pager<UserInviteUser> findAllUserInviteUserByInviteUserIdPager(Long inviteUserId, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllUserInviteUserByInviteUserIdPager",
                new Object[]{inviteUserId, firstResult, sizeNo});
    }

    @Override
    public Pager<UserInviteUser> findAllUserInviteUserByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllUserInviteUserByUserIdPager",
                new Object[]{userId, firstResult, sizeNo});
    }

    @Override
    public Integer deleteUserInviteUser(Long inviteId) {
        return daoClient.invoke(moduleName, "deleteUserInviteUser",
                new Object[]{inviteId});
    }

    @Override
    public Integer batchDeleteByInviteIds(List<Long> batchInviteIds) {
        return daoClient.invoke(moduleName, "batchDeleteByInviteIds",
                new Object[]{batchInviteIds});
    }

    @Override
    public UserInviteUser findUserInviteUserByApplyId(Long applyId) {
        return daoClient.invoke(moduleName, "findUserInviteUserByApplyId",
                new Object[]{applyId});
    }
}
