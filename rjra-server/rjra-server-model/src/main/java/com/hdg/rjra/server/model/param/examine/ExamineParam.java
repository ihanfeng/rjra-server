package com.hdg.rjra.server.model.param.examine;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Administrator on 2015/3/24.
 */
public class ExamineParam extends BaseParam {
    private Integer examineType;
    private Long examineResource;
    private Integer examineStatus;
    private String examineResult;

    public Integer getExamineType() {
        return examineType;
    }

    public void setExamineType(Integer examineType) {
        this.examineType = examineType;
    }

    public Long getExamineResource() {
        return examineResource;
    }

    public void setExamineResource(Long examineResource) {
        this.examineResource = examineResource;
    }

    public Integer getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(Integer examineStatus) {
        this.examineStatus = examineStatus;
    }

    public String getExamineResult() {
        return examineResult;
    }

    public void setExamineResult(String examineResult) {
        this.examineResult = examineResult;
    }
}
