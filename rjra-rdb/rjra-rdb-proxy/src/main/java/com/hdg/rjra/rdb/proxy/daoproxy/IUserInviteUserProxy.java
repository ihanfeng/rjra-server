package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IUserInviteUserProxy extends Serializable {

    public Long saveUserInviteUser(UserInviteUser userInviteUser);

    public List<Long> batchSaveUserInviteUser(List<UserInviteUser> userInviteUserList);

    public UserInviteUser findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId);

    public Pager<UserInviteUser> findAllUserInviteUserByInviteUserIdPager(Long inviteUserId, Integer firstResult, Integer sizeNo);

    public Pager<UserInviteUser> findAllUserInviteUserByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Integer deleteUserInviteUser(Long inviteId);

    public Integer batchDeleteByInviteIds(List<Long> batchInviteIds);
}
