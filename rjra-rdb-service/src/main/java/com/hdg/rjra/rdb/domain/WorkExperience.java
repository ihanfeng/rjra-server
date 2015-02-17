package com.hdg.rjra.rdb.domain;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public class WorkExperience implements Serializable {

    private Long workExperienceId;

    private String workExperienceInfo;

    public Long getWorkExperienceId() {
        return workExperienceId;
    }

    public void setWorkExperienceId(Long workExperienceId) {
        this.workExperienceId = workExperienceId;
    }

    public String getWorkExperienceInfo() {
        return workExperienceInfo;
    }

    public void setWorkExperienceInfo(String workExperienceInfo) {
        this.workExperienceInfo = workExperienceInfo;
    }
}
