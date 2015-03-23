package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserCollectUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectUserBo;
import com.hdg.rjra.server.service.UserCollectUserService;
import com.hdg.rjra.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
@Service
public class UserCollectUserServiceImpl implements UserCollectUserService {

    @Autowired
    IUserCollectUserProxy userCollectUserProxy;

    @Autowired
    UserService userService;

    @Override
    public Long saveUserCollectUser(UserCollectUserBo userCollectUserBo) {
        UserCollectUser userCollectUser = new UserCollectUser();
        ConversionUtils.conversion(userCollectUserBo, userCollectUser);
        return userCollectUserProxy.saveUserCollectUser(userCollectUser);
    }

    @Override
    public UserCollectUserBo findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId) {
        UserCollectUser userCollectUser = userCollectUserProxy.findUserCollectUserByUserIdAndCollectUserId(userId, collectUserId);
        return getUserCollectUserBo(userCollectUser);
    }

    private UserCollectUserBo getUserCollectUserBo(UserCollectUser userCollectUser){
        if(null == userCollectUser) {
            return  null;
        }
        UserCollectUserBo userCollectUserBo = new UserCollectUserBo();
        ConversionUtils.conversion(userCollectUser, userCollectUserBo);
        Long userId = userCollectUser.getUserId();
        Long collectUserId = userCollectUser.getCollectUserId();
        UserBo userBo = userService.findUserByUserId(userId);
        UserBo collectUserBo = userService.findUserByUserId(collectUserId);
        userCollectUserBo.setUserDetail(userBo);
        userCollectUserBo.setCollectUserDetail(collectUserBo);
        return userCollectUserBo;
    }

    @Override
    public Pager<UserCollectUserBo> findAllUserCollectUserByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        Pager<UserCollectUser> userCollectUserPager = userCollectUserProxy.findAllUserCollectUserByUserIdPager(userId, firstResult, sizeNo);
        List<UserCollectUserBo> userCollectUserBoList = new ArrayList<UserCollectUserBo>();
        Pager<UserCollectUserBo> userCollectUserBoPager = new Pager<UserCollectUserBo>();
        for (UserCollectUser userCollectUser : userCollectUserPager.getResultList()) {
            userCollectUserBoList.add(getUserCollectUserBo(userCollectUser));
        }
        userCollectUserBoPager.setTotalSize(userCollectUserPager.getTotalSize());
        userCollectUserBoPager.setResultList(userCollectUserBoList);
        return userCollectUserBoPager;
    }

    @Override
    public Integer deleteUserCollectUser(Long collectId) {
        return userCollectUserProxy.deleteUserCollectUser(collectId);
    }

    @Override
    public Integer batchDeleteCollectUserByCollectIds(List<Long> batchCollectIds) {
        return userCollectUserProxy.batchDeleteCollectUserByCollectIds(batchCollectIds);
    }
}
