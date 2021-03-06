package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserCollectWorkProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectWorkBo;
import com.hdg.rjra.server.model.bo.work.WorkBo;
import com.hdg.rjra.server.service.UserCollectWorkService;
import com.hdg.rjra.server.service.UserService;
import com.hdg.rjra.server.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
@Service
public class UserCollectWorkServiceImpl implements UserCollectWorkService {

    @Autowired
    IUserCollectWorkProxy userCollectWorkProxy;

    @Autowired
    WorkService workService;

    @Autowired
    UserService userService;

    @Override
    public Long saveUserCollectWork(UserCollectWorkBo userCollectWorkBo) {
        UserCollectWork userCollectWork = new UserCollectWork();
        ConversionUtils.conversion(userCollectWorkBo, userCollectWork);
        return userCollectWorkProxy.saveUserCollectWork(userCollectWork);
    }

    @Override
    public UserCollectWorkBo findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId) {
        UserCollectWork userCollectWork = userCollectWorkProxy.findUserCollectWorkByUserIdAndWorkId(userId, workId);
        return getUserCollectWorkBo(userCollectWork);
    }

    private UserCollectWorkBo getUserCollectWorkBo(UserCollectWork userCollectWork) {
        if (null == userCollectWork) {
            return null;
        }
        UserCollectWorkBo userCollectWorkBo = new UserCollectWorkBo();
        ConversionUtils.conversion(userCollectWork, userCollectWorkBo);
        Long userId = userCollectWork.getUserId();
        Long workId = userCollectWork.getWorkId();
        UserBo userBo = userService.findUserByUserId(userId);
        WorkBo workBo = workService.findWorkByWorkId(workId);
        userCollectWorkBo.setUserDetail(userBo);
        userCollectWorkBo.setWorkDetail(workBo);
        return userCollectWorkBo;
    }

    @Override
    public Pager<UserCollectWorkBo> findAllUserCollectWorkByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        Pager<UserCollectWork> userCollectWorkPager = userCollectWorkProxy.findAllUserCollectWorkByUserIdPager(userId, firstResult, sizeNo);
        List<UserCollectWorkBo> userCollectWorkBoList = new ArrayList<UserCollectWorkBo>();
        Pager<UserCollectWorkBo> userCollectWorkBoPager = new Pager<UserCollectWorkBo>();
        for (UserCollectWork userCollectWork : userCollectWorkPager.getResultList()) {
            userCollectWorkBoList.add(getUserCollectWorkBo(userCollectWork));
        }
        userCollectWorkBoPager.setResultList(userCollectWorkBoList);
        userCollectWorkBoPager.setTotalSize(userCollectWorkBoPager.getTotalSize());
        return userCollectWorkBoPager;
    }

    @Override
    public Integer deleteUserCollectWork(Long collectId) {
        return userCollectWorkProxy.deleteUserCollectWork(collectId);
    }

    @Override
    public Integer batchDeleteCollectWorkByCollectIds(List<Long> batchCollectIds) {
        return userCollectWorkProxy.batchDeleteCollectWorkByCollectIds(batchCollectIds);
    }
}
