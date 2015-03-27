package com.hdg.rjra.server.model.param.userbehavior;

import com.hdg.rjra.server.model.param.BaseParam;

import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectWorkParam extends BaseParam {
    private Long collectId;
    private Long userId;
    private Long workId;
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

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public List<Long> getBatchDeleteCollectIds() {
        return batchDeleteCollectIds;
    }

    public void setBatchDeleteCollectIds(List<Long> batchDeleteCollectIds) {
        this.batchDeleteCollectIds = batchDeleteCollectIds;
    }
}
