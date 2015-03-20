package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserCollectUserProxy;
import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class UserCollectUserProxyImpl extends BaseProxy implements IUserCollectUserProxy {

    private static String moduleName = "rdb-user-collect-user";

    @Override
    public Long saveUserCollectUser(UserCollectUser userCollectUser) {
        return daoClient.invoke(moduleName, "saveUserCollectUser",
                new Object[]{userCollectUser});
    }

    @Override
    public UserCollectUser findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId) {
        return daoClient.invoke(moduleName, "findUserCollectUserByUserIdAndCollectUserId",
                new Object[]{userId, collectUserId});
    }

    @Override
    public List<UserCollectUser> findAllUserCollectUserByUserId(Long userId, Integer userCollectUserStatus) {
        return daoClient.invoke(moduleName, "findAllUserCollectUserByUserId",
                new Object[]{userId, userCollectUserStatus});
    }

    @Override
    public Integer deleteUserCollectUser(Long collectId) {
        return daoClient.invoke(moduleName, "deleteUserCollectUser",
                new Object[]{collectId});
    }
}
