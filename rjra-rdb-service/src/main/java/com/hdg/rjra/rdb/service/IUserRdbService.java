package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.domain.User;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserRdbService extends Serializable {

    public Long saveUser(Long resumeId, Long companyId, String mobile, String pwd);

    public Integer updateUser(User user);

    public User findUserByUserId(Long userId);

    public Pager<User> findAllUserPager(Integer[] status, Integer firstResult, Integer sizeNo);

    public Integer updateUserHead(Long userId, Long fileId);

    public Pager<User> findNearUserPager(Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo);

    public User findUserByMobileAndPwd(String mobile, String pwd);

    public Integer updateUserLocation(Long userId, Double lng, Double lat);

    public Integer findUserExistsByMobile(String mobile);

}
