package com.hdg.rjra.server.model.param.file;

import com.hdg.rjra.server.model.param.BaseParam;

/**
 * Created by Rock on 2015/1/11 0011.
 */
public class FileParam extends BaseParam {

    private Long[] fileIds;

    public Long[] getFileIds() {
        return fileIds;
    }

    public void setFileIds(Long[] fileIds) {
        this.fileIds = fileIds;
    }
}
