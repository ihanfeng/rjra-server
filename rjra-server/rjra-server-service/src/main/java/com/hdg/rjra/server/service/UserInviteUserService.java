package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.UserInviteUser;
import com.hdg.rjra.server.model.bo.userbehavior.UserInviteUserBo;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public interface UserInviteUserService {
    public Long saveUserInviteUser(UserInviteUserBo userInviteUserBo);

    public List<Long> batchSaveUserInviteUser(List<UserInviteUser> userInviteUserList);

    public UserInviteUserBo findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId);

    public UserInviteUserBo findUserInviteUserByApplyId(Long apply);

    public Pager<UserInviteUserBo> findAllUserInviteUserByInviteUserIdPager(Long inviteUserId, Integer firstResult, Integer sizeNo);

    public Pager<UserInviteUserBo> findAllUserInviteUserByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Integer deleteUserInviteUser(Long inviteId);

    public Integer batchDeleteByInviteIds(List<Long> batchInviteIds);
}
