package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.userbehavior.UserApplyWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectUserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserInviteUserBo;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public interface UserBehaviorService {

    public Long saveUserApplyWork(UserApplyWorkBo userApplyWorkBo);

    public UserApplyWorkBo findUserApplyWorkByUserIdAndWorkId(Long userId, Long workId);

    public Long saveUserCollectUser(UserCollectUserBo userCollectUserBo);

    public UserCollectUserBo findUserCollectUserByUserIdAndCollectUserId(Long userId, Long collectUserId);

    public Long saveUserCollectWork(UserCollectWorkBo userCollectWorkBo);

    public UserCollectWorkBo findUserCollectWorkByUserIdAndWorkId(Long userId, Long workId);

    public Long saveUserInviteUser(UserInviteUserBo userInviteUserBo);

    public List<Long> batchSaveUserInviteUser(List<UserInviteUserBo> userInviteUserBoList);

    public UserInviteUserBo findUserInviteUserByUserIdAndInviteUserId(Long userId, Long inviteUserId);
}
