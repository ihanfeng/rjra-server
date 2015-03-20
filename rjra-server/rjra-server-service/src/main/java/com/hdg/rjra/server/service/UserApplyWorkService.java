package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.userbehavior.UserApplyWorkBo;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public interface UserApplyWorkService {

    public Long saveUserApplyWork(UserApplyWorkBo userApplyWorkBo);

    public UserApplyWorkBo findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId);

    public Pager<UserApplyWorkBo> findAllUserApplyWorkByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Pager<UserApplyWorkBo> findAllUserApplyWorkByWorkUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Integer deleteUserApplyWork(Long applyId);

}
