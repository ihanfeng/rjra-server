package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.userbehavior.UserScanUserBo;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public interface UserScanUserService {

    public Long saveUserScanUser(UserScanUserBo userScanUser);

    public UserScanUserBo findUserScanUserByUserIdAndScanUserId(Long userId, Long scanUserId);

    public Pager<UserScanUserBo> findAllUserScanUserByScanUserIdPager(Long scanUserId, Integer firstResult, Integer sizeNo);

}
