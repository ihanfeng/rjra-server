package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.UserCollectWork;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserCollectWorkProxy extends Serializable {

    public Integer saveUserCollectWork(UserCollectWork userCollectWork);

    public UserCollectWork findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId);
}
