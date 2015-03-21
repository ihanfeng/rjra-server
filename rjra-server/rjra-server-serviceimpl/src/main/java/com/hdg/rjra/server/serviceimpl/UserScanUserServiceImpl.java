package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserScanUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserScanUser;
import com.hdg.rjra.rdb.proxyimpl.dao.UserScanUserProxyImpl;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserScanUserBo;
import com.hdg.rjra.server.service.UserScanUserService;
import com.hdg.rjra.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
@Service
public class UserScanUserServiceImpl implements UserScanUserService {

    @Autowired
    IUserScanUserProxy userScanUserProxy;

    @Autowired
    UserService userService;

    @Override
    public Long saveUserScanUser(UserScanUserBo userScanUserBo) {
        UserScanUser userScanUser = new UserScanUser();
        ConversionUtils.conversion(userScanUserBo, userScanUser);
        return userScanUserProxy.saveUserScanUser(userScanUser);
    }

    @Override
    public UserScanUserBo findUserScanUserByUserIdAndScanUserId(Long userId, Long scanUserId) {
        UserScanUser userScanUser = userScanUserProxy.findUserScanUserByUserIdAndScanUserId(userId, scanUserId);
        return getUserScanUserBo(userScanUser);
    }

    private UserScanUserBo getUserScanUserBo(UserScanUser userScanUser){
        if(null == userScanUser) {
            return  null;
        }
        UserScanUserBo userScanUserBo = new UserScanUserBo();
        ConversionUtils.conversion(userScanUser, userScanUserBo);
        Long userId = userScanUser.getUserId();
        Long scanUserId = userScanUser.getScanUserId();
        UserBo userBo = userService.findUserByUserId(userId);
        UserBo scanUserBo = userService.findUserByUserId(scanUserId);
        userScanUserBo.setUserDetail(userBo);
        userScanUserBo.setScanUserDetail(scanUserBo);
        return userScanUserBo;
    }

    @Override
    public Pager<UserScanUserBo> findAllUserScanUserByScanUserIdPager(Long scanUserId, Integer firstResult, Integer sizeNo) {
        Pager<UserScanUser> userScanUserPager = userScanUserProxy.findAllUserScanUserByScanUserIdPager(scanUserId, firstResult, sizeNo);
        List<UserScanUserBo> userScanUserBoList = new ArrayList<UserScanUserBo>();
        Pager<UserScanUserBo> userScanUserBoPager = new Pager<UserScanUserBo>();
        for (UserScanUser userScanUser : userScanUserPager.getResultList()) {
            userScanUserBoList.add(getUserScanUserBo(userScanUser));
        }
        userScanUserBoPager.setTotalSize(userScanUserPager.getTotalSize());
        userScanUserBoPager.setResultList(userScanUserBoList);
        return userScanUserBoPager;
    }
}
