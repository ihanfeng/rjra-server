package com.hdg.rjra.rdb.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/11 0011.
 */
public class AccountFile implements Serializable {
    private Long fileId;
    private Integer fileType;
    private Integer fileStatus;
    private String fileName;
    private String fileUrl;
    private String fileThumbnailUrl;
    private String fileFormat;
    private Date fileUploadTime;

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(Integer fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public Date getFileUploadTime() {
        return fileUploadTime;
    }

    public void setFileUploadTime(Date fileUploadTime) {
        this.fileUploadTime = fileUploadTime;
    }

    public String getFileThumbnailUrl() {
        return fileThumbnailUrl;
    }

    public void setFileThumbnailUrl(String fileThumbnailUrl) {
        this.fileThumbnailUrl = fileThumbnailUrl;
    }
}
