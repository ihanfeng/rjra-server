package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserCollectWorkProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;

import java.util.List;

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

    @Override
    public Pager<UserCollectWork> findAllUserCollectWorkByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllUserCollectWorkByUserIdPager",
                new Object[]{userId, firstResult, sizeNo});
    }

    @Override
    public Integer deleteUserCollectWork(Long collectId) {
        return daoClient.invoke(moduleName, "deleteUserCollectWork",
                new Object[]{collectId});
    }

    @Override
    public Integer batchDeleteCollectWorkByCollectIds(List<Long> batchCollectIds) {
        return daoClient.invoke(moduleName, "batchDeleteCollectWorkByCollectIds",
                new Object[]{batchCollectIds});
    }
}
