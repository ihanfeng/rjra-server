package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserApplyWork;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserApplyWorkProxy extends Serializable {

    public Long saveUserApplyWork(UserApplyWork userApplyWork);

    public UserApplyWork findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId);

    public Pager<UserApplyWork> findAllUserApplyWorkByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Pager<UserApplyWork> findAllUserApplyWorkByWorkIdsPager(List<Long> workIds, Integer firstResult, Integer sizeNo);

    public Integer deleteUserApplyWork(Long applyId);
}
