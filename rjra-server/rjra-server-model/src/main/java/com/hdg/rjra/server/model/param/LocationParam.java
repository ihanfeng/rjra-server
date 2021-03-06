package com.hdg.rjra.server.model.param;

/**
 * Created by Rock on 2015/1/17 0017.
 */
public class LocationParam extends BaseParam {

    private Double longitude;
    private Double latitude;
    private Integer raidus;
    private Long userId;
    private Long resumeId;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getRaidus() {
        return raidus;
    }

    public void setRaidus(Integer raidus) {
        this.raidus = raidus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }
}
