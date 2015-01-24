package com.hdg.rjra.server.model.bo.geo;

/**
 * Created by Rock on 2015/1/17 0017.
 */
public class GeoResult {
    private GeoLocation location;
    private Integer precise;
    private Integer confidence;
    private String level;

    public GeoLocation getLocation() {
        return location;
    }

    public void setLocation(GeoLocation location) {
        this.location = location;
    }

    public Integer getPrecise() {
        return precise;
    }

    public void setPrecise(Integer precise) {
        this.precise = precise;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
