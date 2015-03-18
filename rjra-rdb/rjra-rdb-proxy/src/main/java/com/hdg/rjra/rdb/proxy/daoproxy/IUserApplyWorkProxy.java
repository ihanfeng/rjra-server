package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserApplyWorkProxy extends Serializable {

    public Long saveUserApplyWork(UserApplyWork userApplyWork);

    public UserApplyWork findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId);
}
