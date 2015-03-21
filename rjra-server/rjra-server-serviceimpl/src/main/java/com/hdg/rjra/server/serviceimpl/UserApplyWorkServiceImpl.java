package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserApplyWorkProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IWorkProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;
import com.hdg.rjra.server.model.bo.userbehavior.UserApplyWorkBo;
import com.hdg.rjra.server.service.UserApplyWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
@Service
public class UserApplyWorkServiceImpl implements UserApplyWorkService {

    @Autowired
    IUserApplyWorkProxy userApplyWorkProxy;

    @Autowired
    IWorkProxy workProxy;

    @Override
    public Long saveUserApplyWork(UserApplyWorkBo userApplyWorkBo) {
        UserApplyWork userApplyWork = new UserApplyWork();
        ConversionUtils.conversion(userApplyWorkBo, userApplyWork);
        return userApplyWorkProxy.saveUserApplyWork(userApplyWork);
    }

    @Override
    public UserApplyWorkBo findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId) {
        UserApplyWork userApplyWork = userApplyWorkProxy.findUserApplyWorkByUserIdAndWorkId(userId, workId);
        return getUserApplyWorkBo(userApplyWork);
    }

    private UserApplyWorkBo getUserApplyWorkBo(UserApplyWork userApplyWork){
        if (userApplyWork == null){
            return null;
        }
        UserApplyWorkBo userApplyWorkBo = new UserApplyWorkBo();
        ConversionUtils.conversion(userApplyWork, userApplyWorkBo);
        Long userId = userApplyWork.getUserId();
        Long workId = userApplyWork.getWorkId();

        return userApplyWorkBo;
    }

    @Override
    public Pager<UserApplyWorkBo> findAllUserApplyWorkByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        Pager<UserApplyWork> userApplyWorkPager = userApplyWorkProxy.findAllUserApplyWorkByUserIdPager(userId, firstResult, sizeNo);
        Pager<UserApplyWorkBo> userApplyWorkBoPager = new Pager<UserApplyWorkBo>();
        List<UserApplyWorkBo> userApplyWorkBoList = new ArrayList<UserApplyWorkBo>();
        for (UserApplyWork userApplyWork : userApplyWorkPager.getResultList()) {
            userApplyWorkBoList.add(getUserApplyWorkBo(userApplyWork));
        }
        userApplyWorkBoPager.setResultList(userApplyWorkBoList);
        userApplyWorkBoPager.setTotalSize(userApplyWorkPager.getTotalSize());
        return userApplyWorkBoPager;
    }

    @Override
    public Pager<UserApplyWorkBo> findAllUserApplyWorkByWorkUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        List<Long> workIds = workProxy.findWorkIdsByUserId(userId, new Integer[]{WorkStatus.Active.getCode()});
        Pager<UserApplyWorkBo> userApplyWorkBoPager = new Pager<UserApplyWorkBo>();
        if(workIds != null && workIds.size() > 0) {
            Pager<UserApplyWork> userApplyWorkPager = userApplyWorkProxy.findAllUserApplyWorkByWorkIdsPager(workIds, firstResult, sizeNo);
            List<UserApplyWorkBo> userApplyWorkBoList = new ArrayList<UserApplyWorkBo>();
            for (UserApplyWork userApplyWork : userApplyWorkPager.getResultList()) {
                userApplyWorkBoList.add(getUserApplyWorkBo(userApplyWork));
            }
            userApplyWorkBoPager.setResultList(userApplyWorkBoList);
            userApplyWorkBoPager.setTotalSize(userApplyWorkPager.getTotalSize());
        }
        return userApplyWorkBoPager;
    }

    @Override
    public Integer deleteUserApplyWork(Long applyId) {
        return userApplyWorkProxy.deleteUserApplyWork(applyId);
    }
}
