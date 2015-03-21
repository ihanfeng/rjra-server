package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserInviteUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserInviteUserBo;
import com.hdg.rjra.server.service.UserInviteUserService;
import com.hdg.rjra.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
@Service
public class UserInviteUserServiceImpl implements UserInviteUserService {

    @Autowired
    IUserInviteUserProxy userInviteUserProxy;

    @Autowired
    UserService userService;

    @Override
    public Long saveUserInviteUser(UserInviteUserBo userInviteUserBo) {
        UserInviteUser userInviteUser = new UserInviteUser();
        ConversionUtils.conversion(userInviteUserBo, userInviteUser);
        return userInviteUserProxy.saveUserInviteUser(userInviteUser);
    }

    @Override
    public List<Long> batchSaveUserInviteUser(List<UserInviteUser> userInviteUserList) {
        return userInviteUserProxy.batchSaveUserInviteUser(userInviteUserList);
    }

    @Override
    public UserInviteUserBo findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId) {
        UserInviteUser userInviteUser = userInviteUserProxy.findUserInviteUserByUserIdAndInviteUserId(userId, inviteUserId);
        return getUserInviteUserBo(userInviteUser);
    }

    private UserInviteUserBo getUserInviteUserBo(UserInviteUser userInviteUser){
        if(null == userInviteUser) {
            return  null;
        }
        UserInviteUserBo userInviteUserBo = new UserInviteUserBo();
        ConversionUtils.conversion(userInviteUser, userInviteUserBo);
        Long userId = userInviteUser.getUserId();
        Long inviteUserId = userInviteUser.getInviteUserId();
        UserBo userBo = userService.findUserByUserId(userId);
        UserBo inviteUserUserBo = userService.findUserByUserId(inviteUserId);
        userInviteUserBo.setUserDetail(userBo);
        userInviteUserBo.setInviteUserDetail(inviteUserUserBo);
        return userInviteUserBo;
    }

    @Override
    public Pager<UserInviteUserBo> findAllUserInviteUserByInviteUserIdPager(Long inviteUserId, Integer firstResult, Integer sizeNo) {
        Pager<UserInviteUser> userInviteUserPager = userInviteUserProxy.findAllUserInviteUserByInviteUserIdPager(inviteUserId, firstResult, sizeNo);
        List<UserInviteUserBo> userInviteUserBoList = new ArrayList<UserInviteUserBo>();
        Pager<UserInviteUserBo> userInviteUserBoPager = new Pager<UserInviteUserBo>();
        for (UserInviteUser userInviteUser : userInviteUserPager.getResultList()) {
            userInviteUserBoList.add(getUserInviteUserBo(userInviteUser));
        }
        userInviteUserBoPager.setResultList(userInviteUserBoList);
        userInviteUserBoPager.setTotalSize(userInviteUserBoPager.getTotalSize());
        return userInviteUserBoPager;
    }

    @Override
    public Pager<UserInviteUserBo> findAllUserInviteUserByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        Pager<UserInviteUser> userInviteUserPager = userInviteUserProxy.findAllUserInviteUserByUserIdPager(userId, firstResult, sizeNo);
        List<UserInviteUserBo> userInviteUserBoList = new ArrayList<UserInviteUserBo>();
        Pager<UserInviteUserBo> userInviteUserBoPager = new Pager<UserInviteUserBo>();
        for (UserInviteUser userInviteUser : userInviteUserPager.getResultList()) {
            userInviteUserBoList.add(getUserInviteUserBo(userInviteUser));
        }
        userInviteUserBoPager.setResultList(userInviteUserBoList);
        userInviteUserBoPager.setTotalSize(userInviteUserBoPager.getTotalSize());
        return userInviteUserBoPager;
    }

    @Override
    public Integer deleteUserInviteUser(Long inviteId) {
        return userInviteUserProxy.deleteUserInviteUser(inviteId);
    }
}
