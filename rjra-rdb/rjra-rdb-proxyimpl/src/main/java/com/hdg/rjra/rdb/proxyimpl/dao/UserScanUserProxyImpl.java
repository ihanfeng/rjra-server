package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserScanUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserScanUser;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class UserScanUserProxyImpl extends BaseProxy implements IUserScanUserProxy {

    private static String moduleName = "rdb-user-scan-user";

    @Override
    public Long saveUserScanUser(UserScanUser userScanUser) {
        return daoClient.invoke(moduleName, "saveUserScanUser",
                new Object[]{userScanUser});
    }

    @Override
    public UserScanUser findUserScanUserByUserIdAndScanUserId(Long userId, Long scanUserId) {
        return daoClient.invoke(moduleName, "findUserScanUserByUserIdAndScanUserId",
                new Object[]{userId, scanUserId});
    }

    @Override
    public Pager<UserScanUser> findAllUserScanUserByScanUserIdPager(Long scanUserId, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllUserScanUserByScanUserIdPager",
                new Object[]{scanUserId, firstResult, sizeNo});
    }
}
