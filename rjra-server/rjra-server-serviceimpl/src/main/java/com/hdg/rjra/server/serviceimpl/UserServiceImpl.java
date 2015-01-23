package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.base.enumerate.UserStatus;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IUserProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.User;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    IUserProxy userProxy;

    @Autowired
    FileService fileService;

    @Override
    public Long saveUser(String mobile, String pwd) {
        return userProxy.saveUser(mobile, pwd);
    }

    @Override
    public Integer updateUser(UserBo userBo) {
        User user = new User();
        ConversionUtils.conversion(userBo, user);
        return userProxy.updateUser(user);
    }

    @Override
    public UserBo findUserByUserId(Long userId) {
        User user = userProxy.findUserByUserId(userId);
        return getUserBo(user);
    }

    private UserBo getUserBo(User user) {
        if (null == user) {
            return null;
        }
        UserBo userBo = new UserBo();
        Long hadeImage = user.getUserHeadImageFile();
        Long idCardImageFile = user.getUserIdcardImageFile();
        if (null != hadeImage) {

            List<AccountFileBo> userImageInfo = fileService.findAccountFileByIds(new Long[]{hadeImage});
            if (userImageInfo != null && userImageInfo.size() > 0) {
                userBo.setUserHeadImageFileDetail(userImageInfo.get(0));
            }
        }
        if (null != idCardImageFile) {
            List<AccountFileBo> userImageInfo = fileService.findAccountFileByIds(new Long[]{idCardImageFile});
            if (userImageInfo != null && userImageInfo.size() > 0) {
                userBo.setUserIdcardImageFileDetail(userImageInfo.get(0));
            }
        }
        ConversionUtils.conversion(user, userBo);
        return userBo;
    }

    @Override
    public Pager<UserBo> findAllUserPager(Integer firstResult, Integer sizeNo) {
        Pager<User> userPager = userProxy.findAllUserPager(new Integer[]{UserStatus.Active.getCode(), UserStatus.Pause.getCode()}, firstResult, sizeNo);
        Pager<UserBo> userBoPager = new Pager<UserBo>();
        List<UserBo> userBoList = new ArrayList<UserBo>();
        for (User user : userPager.getResultList()) {
            userBoList.add(getUserBo(user));
        }
        userBoPager.setResultList(userBoList);
        userBoPager.setTotalSize(userPager.getTotalSize());
        return userBoPager;
    }

    @Override
    public Integer updateUserHead(Long userId, Long fileId) {
        return userProxy.updateUserHead(userId, fileId);
    }

    @Override
    public Integer updateUserIdcard(Long userId, Long fileId) {
        return userProxy.updateUserIdcard(userId, fileId);
    }

    @Override
    public Pager<UserBo> findNearUserPager(Double lat, Double lng, Integer raidus, Integer firstResult, Integer sizeNo) {
        Pager<User> userPager = userProxy.findNearUserPager(lat, lng, raidus, new Integer[]{UserStatus.Active.getCode()}, firstResult, sizeNo);
        Pager<UserBo> userBoPager = new Pager<UserBo>();
        List<UserBo> userBoList = new ArrayList<UserBo>();
        for (User user : userPager.getResultList()) {
            userBoList.add(getUserBo(user));
        }
        userBoPager.setResultList(userBoList);
        userBoPager.setTotalSize(userPager.getTotalSize());
        return userBoPager;
    }

    @Override
    public UserBo findUserByMobileAndPwd(String mobile, String pwd) {
        User user = userProxy.findUserByMobileAndPwd(mobile, pwd);
        return getUserBo(user);
    }

    @Override
    public Integer updateUserLocation(Long userId, Double lng, Double lat) {
        return userProxy.updateUserLocation(userId, lng, lat);
    }

    @Override
    public Integer findUserExistsByMobile(String mobile) {
        return userProxy.findUserExistsByMobile(mobile);
    }
}
