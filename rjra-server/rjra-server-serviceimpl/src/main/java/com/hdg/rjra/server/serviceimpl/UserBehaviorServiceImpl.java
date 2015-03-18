package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserApplyWorkProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserCollectUserProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserCollectWorkProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserInviteUserProxy;
import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;
import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;
import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;
import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;
import com.hdg.rjra.server.model.bo.userbehavior.UserApplyWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectUserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserInviteUserBo;
import com.hdg.rjra.server.service.UserBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Rock on 2015/3/18 0018.
 */
@Service
public class UserBehaviorServiceImpl implements UserBehaviorService {

    @Autowired
    IUserApplyWorkProxy userApplyWorkProxy;
    @Autowired
    IUserCollectUserProxy userCollectUserProxy;
    @Autowired
    IUserCollectWorkProxy userCollectWorkProxy;
    @Autowired
    IUserInviteUserProxy userInviteUserProxy;

    @Override
    public Long saveUserApplyWork(UserApplyWorkBo userApplyWorkBo) {
        UserApplyWork userApplyWork = new UserApplyWork();
        ConversionUtils.conversion(userApplyWorkBo, userApplyWork);
        return userApplyWorkProxy.saveUserApplyWork(userApplyWork);
    }

    @Override
    public UserApplyWorkBo findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId) {
        UserApplyWork userApplyWork = userApplyWorkProxy.findUserApplyWorkByUserIdAndWorkId(userId, workId);
        if(null == userApplyWork) {
            return  null;
        }
        UserApplyWorkBo userApplyWorkBo = new UserApplyWorkBo();
        ConversionUtils.conversion(userApplyWork, userApplyWorkBo);
        return userApplyWorkBo;
    }

    @Override
    public Long saveUserCollectUser(UserCollectUserBo userCollectUserBo) {
        UserCollectUser userCollectUser = new UserCollectUser();
        ConversionUtils.conversion(userCollectUserBo, userCollectUser);
        return userCollectUserProxy.saveUserCollectUser(userCollectUser);
    }

    @Override
    public UserCollectUserBo findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId) {
        UserCollectUser userCollectUser = userCollectUserProxy.findUserCollectUserByUserIdAndCollectUserId(userId, collectUserId);
        if(null == userCollectUser) {
            return  null;
        }
        UserCollectUserBo userCollectUserBo = new UserCollectUserBo();
        ConversionUtils.conversion(userCollectUser, userCollectUserBo);
        return userCollectUserBo;
    }

    @Override
    public Long saveUserCollectWork(UserCollectWorkBo userCollectWorkBo) {
        UserCollectWork userCollectWork = new UserCollectWork();
        ConversionUtils.conversion(userCollectWorkBo, userCollectWork);
        return userCollectWorkProxy.saveUserCollectWork(userCollectWork);
    }

    @Override
    public UserCollectWorkBo findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId) {
        UserCollectWork userCollectWork = userCollectWorkProxy.findUserCollectWorkByUserIdAndWorkId(userId, workId);
        if(null == userCollectWork) {
            return  null;
        }
        UserCollectWorkBo userCollectWorkBo = new UserCollectWorkBo();
        ConversionUtils.conversion(userCollectWork, userCollectWorkBo);
        return userCollectWorkBo;
    }

    @Override
    public Long saveUserInviteUser(UserInviteUserBo userInviteUserBo) {
        UserInviteUser userInviteUser = new UserInviteUser();
        ConversionUtils.conversion(userInviteUserBo, userInviteUser);
        return userInviteUserProxy.saveUserInviteUser(userInviteUser);
    }

    @Override
    public UserInviteUserBo findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId) {
        UserInviteUser userInviteUser = userInviteUserProxy.findUserInviteUserByUserIdAndInviteUserId(userId, inviteUserId);
        if(null == userInviteUser) {
            return  null;
        }
        UserInviteUserBo userInviteUserBo = new UserInviteUserBo();
        ConversionUtils.conversion(userInviteUser, userInviteUserBo);
        return userInviteUserBo;
    }
}
