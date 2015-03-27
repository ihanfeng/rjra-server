package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.CoordinateUtils;
import com.hdg.common.utils.DateUtils;
import com.hdg.rjra.base.enumerate.ResumeStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.IResumeProxy;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import com.hdg.rjra.rdb.proxy.domain.enumerate.ResumeMapping;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.ResumeService;
import com.hdg.rjra.server.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    IResumeProxy resumeProxy;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;

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
        UserBo userBo = userService.findUserByResumeId(resume.getResumeId());
        if (userBo != null) {
            bo.setUserDetail(userBo);
            bo.setUserId(userBo.getUserId());
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

    @Override
    public Integer updateResumeHead(Long resumeId, Long fileId) {
        return resumeProxy.updateResumeHead(resumeId, fileId);
    }

    @Override
    public Pager<ResumeBo> findAllResumeByParamPager(Map<ResumeMapping, Object> param, Integer firstResult, Integer sizeNo) {
        Pager<Resume> resumePager = resumeProxy.findAllResumeByParamPager(param, new Integer[]{ResumeStatus.Active.getCode(), ResumeStatus.Pause.getCode()}, firstResult, sizeNo);
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
        Pager<Resume> resumePager = resumeProxy.findNearResumeByParamPager(param, lng, lat, raidus, new Integer[]{ResumeStatus.Active.getCode()}, firstResult, sizeNo);
        Pager<ResumeBo> resumeBoPager = new Pager<ResumeBo>();
        List<ResumeBo> resumeBoList = new ArrayList<ResumeBo>();
        for (Resume resume : resumePager.getResultList()) {
            ResumeBo resumeBo = getResumeBo(resume);
            Double distance = CoordinateUtils.distance(lng,lat,resume.getResumeLongitude(),resume.getResumeLatitude());
            if (null != distance) {
                resumeBo.setDistance(distance.intValue());
            }
            resumeBoList.add(resumeBo);
        }
        resumeBoPager.setResultList(resumeBoList);
        resumeBoPager.setTotalSize(resumePager.getTotalSize());
        return resumeBoPager;
    }
}
