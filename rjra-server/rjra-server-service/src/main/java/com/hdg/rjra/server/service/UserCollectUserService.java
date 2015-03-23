package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectUserBo;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public interface UserCollectUserService {

    public Long saveUserCollectUser(UserCollectUserBo userCollectUserBo);

    public UserCollectUserBo findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId);

    public Pager<UserCollectUserBo> findAllUserCollectUserByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Integer deleteUserCollectUser(Long collectId);

    public Integer batchDeleteCollectUserByCollectIds(List<Long> batchCollectIds);

}
