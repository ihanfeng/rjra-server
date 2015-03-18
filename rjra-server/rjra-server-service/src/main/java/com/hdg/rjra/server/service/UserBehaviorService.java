package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.userbehavior.UserApplyWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectUserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserInviteUserBo;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public interface UserBehaviorService {

    public Integer saveUserApplyWork(UserApplyWorkBo userApplyWorkBo);

    public UserApplyWorkBo findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId);

    public Integer saveUserCollectUser(UserCollectUserBo userCollectUserBo);

    public UserCollectUserBo findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId);

    public Integer saveUserCollectWork(UserCollectWorkBo userCollectWorkBo);

    public UserCollectWorkBo findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId);

    public Integer saveUserInviteUser(UserInviteUserBo userInviteUserBo);

    public UserInviteUserBo findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId);
}
