package com.hdg.rjra.rdb.proxy.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserCollectUser implements Serializable {
    private Long collectId;
    private Long userId;
    private Long collectUserId;
    private Date collectDate;

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

    public Date getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Date collectDate) {
        this.collectDate = collectDate;
    }
}
