package com.hdg.rjra.server.web.controller.param.userbehavior;

import com.hdg.rjra.server.web.controller.param.BaseParam;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectWorkParam extends BaseParam implements Serializable {
    private Long collectId;
    private Long userId;
    private Long workId;

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }
}
