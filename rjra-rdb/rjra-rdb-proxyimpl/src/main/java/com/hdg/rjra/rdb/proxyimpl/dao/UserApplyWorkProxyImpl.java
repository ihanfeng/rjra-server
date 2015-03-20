package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.proxy.daoproxy.IUserApplyWorkProxy;
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
    public List<UserApplyWork> findAllUserApplyWorkByUserId(Long userId, Integer userApplyWorkStatus) {
        return daoClient.invoke(moduleName, "findAllWorkByUserId",
                new Object[]{userId, userApplyWorkStatus});
    }

    @Override
    public List<UserApplyWork> findAllUserApplyWorkByWorkIds(List<Long> workIds, Integer userApplyWorkStatus) {
        return daoClient.invoke(moduleName, "findAllUserApplyWorkByWorkIds",
                new Object[]{workIds, userApplyWorkStatus});
    }

    @Override
    public Integer deleteUserApplyWork(Long applyId) {
        return daoClient.invoke(moduleName, "deleteUserApplyWork",
                new Object[]{applyId});
    }
}
