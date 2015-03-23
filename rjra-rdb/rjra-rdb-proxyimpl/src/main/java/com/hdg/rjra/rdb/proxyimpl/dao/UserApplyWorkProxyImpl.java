package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserApplyWorkProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class UserApplyWorkProxyImpl extends BaseProxy implements IUserApplyWorkProxy {

    private static String moduleName = "rdb-user-apply-work";

    @Override
    public Long saveUserApplyWork(UserApplyWork userApplyWork) {
        return daoClient.invoke(moduleName, "saveUserApplyWork",
                new Object[]{userApplyWork});
    }

    @Override
    public UserApplyWork findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId) {
        return daoClient.invoke(moduleName, "findUserApplyWorkByUserIdAndWorkId",
                new Object[]{userId, workId});
    }

    @Override
    public UserApplyWork findUserApplyWorkByApplyId(Long applyId) {
        return daoClient.invoke(moduleName, "findUserApplyWorkByApplyId",
                new Object[]{applyId});
    }

    @Override
    public Pager<UserApplyWork> findAllUserApplyWorkByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllUserApplyWorkByUserIdPager",
                new Object[]{userId, firstResult, sizeNo});
    }

    @Override
    public Pager<UserApplyWork> findAllUserApplyWorkByWorkIdsPager(List<Long> workIds, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllUserApplyWorkByWorkIdsPager",
                new Object[]{workIds, firstResult, sizeNo});
    }

    @Override
    public Integer deleteUserApplyWork(Long applyId) {
        return daoClient.invoke(moduleName, "deleteUserApplyWork",
                new Object[]{applyId});
    }

    @Override
    public Integer batchDeleteByApplyIds(List<Long> batchApplyIds) {
        return daoClient.invoke(moduleName, "batchDeleteByApplyIds",
                new Object[]{batchApplyIds});
    }
}
