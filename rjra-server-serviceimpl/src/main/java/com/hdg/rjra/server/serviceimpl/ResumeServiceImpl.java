package com.hdg.rjra.server.serviceimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hdg.common.constants.CommonConstants;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.DateUtils;
import com.hdg.rjra.base.enumerate.ResumeStatus;
import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.domain.Resume;
import com.hdg.rjra.rdb.domain.enumerate.ResumeMapping;
import com.hdg.rjra.rdb.service.IResumeRdbService;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.ResumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    IResumeRdbService resumeRdbService;

    @Autowired
    FileService fileService;
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResumeServiceImpl.class);


    @Override
    public Long createResume(String mobile) {
        return resumeRdbService.createResume(mobile);
    }

    @Override
    public ResumeBo findResumeByResumeId(Long resumeId) {
        Resume resume = resumeRdbService.findResumeByResumeId(resumeId);
        return getResumeBo(resume);
    }

    private ResumeBo getResumeBo(Resume resume) {
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
        if (resume.getResumeBirthday() != null) {
            bo.setBirthday(DateUtils.getTimeNow(resume.getResumeBirthday(), CommonConstants.DATE_FORMAT_YYYYMMDD));
        }
        return bo;
    }

    @Override
    public Integer updatResumeStatus(Long resumeId, Integer status) {
        return resumeRdbService.updatResumeStatus(resumeId, status);
    }

    @Override
    public Integer updateResume(ResumeBo resumeBo) {
        Resume resume = new Resume();
        ConversionUtils.conversion(resumeBo, resume);
        return resumeRdbService.updateResume(resume);
    }

    @Override
    public Integer updateResumeHead(Long resumeId, Long fileId) {
        return resumeRdbService.updateResumeHead(resumeId, fileId);
    }

    @Override
    public Pager<ResumeBo> findAllResumeByParamPager(Map<ResumeMapping, Object> param, Integer firstResult, Integer sizeNo) {
        Pager<Resume> resumePager =  resumeRdbService.findAllResumeByParamPager(param, new Integer[]{ResumeStatus.Active.getCode(), ResumeStatus.Pause.getCode()}, firstResult, sizeNo);
        Pager<ResumeBo> resumeBoPager = new Pager<ResumeBo>();
        List<ResumeBo> resumeBoList = new ArrayList<ResumeBo>();
        for (Resume resume : resumePager.getResultList()) {
            resumeBoList.add(getResumeBo(resume));
        }
        resumeBoPager.setResultList(resumeBoList);
        resumeBoPager.setTotalSize(resumePager.getTotalSize());
        return resumeBoPager;
    }

    @Override
    public Pager<ResumeBo> findNearResumeByParamPager(Map<ResumeMapping, Object> param, Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo) {
        Pager<Resume> resumePager =  resumeRdbService.findNearResumeByParamPager(param, lng, lat, raidus, new Integer[]{ResumeStatus.Active.getCode()}, firstResult, sizeNo);
        Pager<ResumeBo> resumeBoPager = new Pager<ResumeBo>();
        List<ResumeBo> resumeBoList = new ArrayList<ResumeBo>();
        for (Resume resume : resumePager.getResultList()) {
            resumeBoList.add(getResumeBo(resume));
        }
        resumeBoPager.setResultList(resumeBoList);
        resumeBoPager.setTotalSize(resumePager.getTotalSize());
        return resumeBoPager;
    }
}
