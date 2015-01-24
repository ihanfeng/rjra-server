package com.hdg.rjra.rdb.proxyimpl.dao;

import com.hdg.rjra.rdb.client.Client;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.User;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public class UserProxyImpl extends BaseProxy implements IUserProxy {

    private static String moduleName = "rdb-user";

    @Override
    public Long saveUser(Long resumeId, Long companyId, String mobile, String pwd) {
        return daoClient.invoke(moduleName, "saveUser",
                new Object[]{resumeId, companyId, mobile, pwd});
    }

    @Override
    public Integer updateUser(User user) {
        return daoClient.invoke(moduleName, "updateUser",
                new Object[]{user});
    }

    @Override
    public User findUserByUserId(Long userId) {
        return daoClient.invoke(moduleName, "findUserByUserId",
                new Object[]{userId});
    }

    @Override
    public Pager<User> findAllUserPager(Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findAllUserPager",
                new Object[]{status, firstResult, sizeNo});
    }

    @Override
    public Integer updateUserHead(Long userId, Long fileId) {
        return daoClient.invoke(moduleName, "updateUserHead",
                new Object[]{userId, fileId});
    }

    @Override
    public Pager<User> findNearUserPager(Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo) {
        return daoClient.invoke(moduleName, "findNearUserPager",
                new Object[]{lng, lat, raidus, status, firstResult, sizeNo});
    }

    @Override
    public User findUserByMobileAndPwd(String mobile, String pwd) {
        return daoClient.invoke(moduleName, "findUserByMobileAndPwd",
                new Object[]{mobile, pwd});
    }

    @Override
    public Integer updateUserLocation(Long userId, Double lng, Double lat) {
        return daoClient.invoke(moduleName, "updateUserLocation",
                new Object[]{userId, lng, lat});
    }

    @Override
    public Integer findUserExistsByMobile(String mobile) {
        return daoClient.invoke(moduleName, "findUserExistsByMobile",
                new Object[]{mobile});
    }
}
