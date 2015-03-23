package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectWorkBo;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public interface UserCollectWorkService {

    public Long saveUserCollectWork(UserCollectWorkBo userCollectWorkBo);

    public UserCollectWorkBo findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId);

    public Pager<UserCollectWorkBo> findAllUserCollectWorkByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Integer deleteUserCollectWork(Long collectId);

    public Integer batchDeleteCollectWorkByCollectIds(List<Long> batchCollectIds);
}
