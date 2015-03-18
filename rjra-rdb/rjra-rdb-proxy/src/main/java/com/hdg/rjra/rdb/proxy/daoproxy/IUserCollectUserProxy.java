package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.UserCollectUser;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserCollectUserProxy extends Serializable {

    public Long saveUserCollectUser(UserCollectUser userCollectUser);

    public UserCollectUser findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId);
}
