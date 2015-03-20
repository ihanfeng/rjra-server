package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;
import com.hdg.rjra.rdb.proxy.domain.UserScanUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserScanUserProxy extends Serializable {

    public Long saveUserScanUser(UserScanUser userScanUser);

    public UserScanUser findUserScanUserByUserIdAndScanUserId(Long userId, Long scanUserId);

    public Pager<UserScanUser> findAllUserScanUserByScanUserIdPager(Long scanUserId, Integer firstResult, Integer sizeNo);
}
