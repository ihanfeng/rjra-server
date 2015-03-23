package com.hdg.rjra.server.model.param.userbehavior;

import com.hdg.rjra.server.model.param.BaseParam;

import java.io.Serializable;

/**
 * Created by Rock on 2015/3/18 0018.
 */
public class UserScanUserParam extends BaseParam {
    private Long scanId;
    private Long userId;
    private Long scanUserId;

    public Long getScanId() {
        return scanId;
    }

    public void setScanId(Long scanId) {
        this.scanId = scanId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScanUserId() {
        return scanUserId;
    }

    public void setScanUserId(Long scanUserId) {
        this.scanUserId = scanUserId;
    }
}
