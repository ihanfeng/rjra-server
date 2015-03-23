package com.hdg.rjra.server.model.param.userbehavior;

import com.hdg.rjra.server.model.param.BaseParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectUserParam extends BaseParam implements Serializable {
    private Long collectId;
    private Long userId;
    private Long collectUserId;
    private List<Long> batchDeleteCollectIds;

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

    public Long getCollectUserId() {
        return collectUserId;
    }

    public void setCollectUserId(Long collectUserId) {
        this.collectUserId = collectUserId;
    }

    public List<Long> getBatchDeleteCollectIds() {
        return batchDeleteCollectIds;
    }

    public void setBatchDeleteCollectIds(List<Long> batchDeleteCollectIds) {
        this.batchDeleteCollectIds = batchDeleteCollectIds;
    }
}
