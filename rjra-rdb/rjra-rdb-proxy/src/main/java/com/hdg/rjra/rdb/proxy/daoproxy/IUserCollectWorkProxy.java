package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserCollectWorkProxy extends Serializable {

    public Long saveUserCollectWork(UserCollectWork userCollectWork);

    public UserCollectWork findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId);

    public List<UserCollectWork> findAllUserCollectWorkByUserId(Long userId, Integer userCollectWorkStatus);

    public Integer deleteUserCollectWork(Long collectId);
}
