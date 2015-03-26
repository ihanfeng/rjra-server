package com.hdg.rjra.rdb.test.geo;

/**
 * Created by Rock on 2015/1/17 0017.
 */
public class GeocoderSearchResponse {

    private Integer status;
    private String message;
    private GeoResult result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GeoResult getResult() {
        return result;
    }

    public void setResult(GeoResult result) {
        this.result = result;
    }
}
