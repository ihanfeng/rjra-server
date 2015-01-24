package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.base.enumerate.FileStatus;
import com.hdg.rjra.base.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IAccountFileProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IResumeProxy;
import com.hdg.rjra.rdb.proxy.domain.AccountFile;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.ResumeService;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    IResumeProxy resumeProxy;

    @Autowired
    FileService fileService;
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResumeServiceImpl.class);


    @Override
    public Long createResume(String mobile) {
        return resumeProxy.createResume(mobile);
    }

    @Override
    public ResumeBo findResumeByResumeId(Long resumeId) {
        Resume resume = resumeProxy.findResumeByResumeId(resumeId);
        return getUserBo(resume);
    }

    private ResumeBo getUserBo(Resume resume) {
        if (null == resume) {
            return null;
        }
        ResumeBo bo = new ResumeBo();
        ConversionUtils.conversion(resume, bo);
        Long hadeImage = resume.getResumeUserHeadImageFile();
        if (null != hadeImage) {
            AccountFileBo userImageInfo = fileService.findAccountFileById(hadeImage);
            bo.setResumeUserHeadImageFileDetail(userImageInfo);
        }
        return bo;
    }

    @Override
    public Integer updatResumeStatus(Long resumeId, Integer status) {
        return resumeProxy.updatResumeStatus(resumeId, status);
    }

    @Override
    public Integer updateResume(ResumeBo resumeBo) {
        Resume resume = new Resume();
        ConversionUtils.conversion(resumeBo, resume);
        return resumeProxy.updateResume(resume);
    }
}
