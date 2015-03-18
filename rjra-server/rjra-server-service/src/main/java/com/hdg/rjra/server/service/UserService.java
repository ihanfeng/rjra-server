package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.User;
import com.hdg.rjra.server.model.bo.user.UserBo;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface UserService {

    public UserBo saveUser(String mobile, String pwd);

    public Integer updateUser(UserBo user);

    public UserBo findUserByUserId(Long userId);

    public UserBo findUserByResumeId(Long resumeId);

    public Pager<UserBo> findAllUserPager(Integer firstResult, Integer sizeNo);

    public Integer updateUserHead(Long userId, Long fileId);

    public Pager<UserBo> findNearUserPager(Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo);

    public UserBo findUserByMobileAndPwd(String mobile, String pwd);

    public Integer updateUserPwd(Long userId, String pwd);

    public Integer updateUserLocation(Long userId, Double lng, Double lat);

    public Integer findUserExistsByMobile(String mobile);

}
