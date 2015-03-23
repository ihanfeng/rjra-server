package com.hdg.rjra.server.web.controller.param.userbehavior;

import com.hdg.rjra.server.web.controller.param.BaseParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserApplyWorkParam extends BaseParam implements Serializable {
    private Long applyId;
    private Long workId;
    private Long userId;
    private List<Long> batchDeleteApplyIds;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getBatchDeleteApplyIds() {
        return batchDeleteApplyIds;
    }

    public void setBatchDeleteApplyIds(List<Long> batchDeleteApplyIds) {
        this.batchDeleteApplyIds = batchDeleteApplyIds;
    }
}
