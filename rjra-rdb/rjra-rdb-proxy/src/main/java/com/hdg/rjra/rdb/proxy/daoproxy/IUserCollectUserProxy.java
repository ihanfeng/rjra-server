package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserCollectUserProxy extends Serializable {

    public Long saveUserCollectUser(UserCollectUser userCollectUser);

    public UserCollectUser findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId);

    public Pager<UserCollectUser> findAllUserCollectUserByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Integer deleteUserCollectUser(Long collectId);

    public Integer batchDeleteCollectUserByCollectIds(List<Long> batchCollectIds);
}
