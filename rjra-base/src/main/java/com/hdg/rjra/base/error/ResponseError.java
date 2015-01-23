package com.hdg.rjra.base.error;

import com.hdg.rjra.base.utils.JsonUtils;

import java.io.Serializable;

/**
 * Created by Sisi on 14-9-9.
 */
public class ResponseError implements Serializable {
    /**
     * type
     */
    private String type;

    /**
     * message
     */
    private String message;

    /**
     * code
     */
    private Long code;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return JsonUtils.objectToJson(this);
    }
}
