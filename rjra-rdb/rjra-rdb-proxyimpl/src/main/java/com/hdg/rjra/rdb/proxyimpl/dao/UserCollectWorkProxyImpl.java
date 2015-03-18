package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserCollectWorkProxy;
import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class UserCollectWorkProxyImpl extends BaseProxy implements IUserCollectWorkProxy {

    private static String moduleName = "rdb-user-collect-work";

    @Override
    public Long saveUserCollectWork(UserCollectWork userCollectWork) {
        return daoClient.invoke(moduleName, "saveUserCollectWork",
                new Object[]{userCollectWork});
    }

    @Override
    public UserCollectWork findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId) {
        return daoClient.invoke(moduleName, "findUserCollectWorkByUserIdAndWorkId",
                new Object[]{userId, workId});
    }
}
