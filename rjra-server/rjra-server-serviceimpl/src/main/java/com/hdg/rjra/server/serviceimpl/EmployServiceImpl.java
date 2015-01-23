package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.base.enumerate.EmployCompanyStatus;
import com.hdg.rjra.base.enumerate.EmployUserStatus;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IEmployProxy;
import com.hdg.rjra.rdb.proxy.domain.Employ;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.employ.EmployBo;
import com.hdg.rjra.server.model.bo.recruitmentinfo.RecruitmentInfoBo;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class EmployServiceImpl implements EmployService {

    @Autowired
    IEmployProxy employProxy;

    @Autowired
    RecruitmentInfoService recruitmentInfoService;

    @Autowired
    CompanyService companyService;

    @Autowired
    UserService userService;


    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(EmployServiceImpl.class);


    @Override
    public EmployBo findEmployByEmployId(Long employId) {
        Employ employ = employProxy.findEmployByEmployId(employId);
        EmployBo bo = getEmployBo(employ);
        return bo;
    }

    private EmployBo getEmployBo(Employ employ) {
        if (employ == null) {
            return null;
        }
        EmployBo bo = new EmployBo();
        Long userId = employ.getUserId();
        UserBo userBo = userService.findUserByUserId(userId);
        Long infoId = employ.getInfoId();
        RecruitmentInfoBo infoBo = recruitmentInfoService.findRecruitmentInfoByRecruitmentInfoId(infoId);
        bo.setUserDetail(userBo);
        bo.setInfoDetail(infoBo);
        ConversionUtils.conversion(employ, bo);
        return bo;
    }

    @Override
    public Pager<EmployBo> findEmployByUserIdPager(Long userId, Integer firstResult, Integer sizeNo) {
        Pager<Employ> employPager = employProxy.findEmployByUserIdPager(userId, EmployUserStatus.Active.getCode(), firstResult, sizeNo);
        Pager<EmployBo> employBoPager = new Pager<EmployBo>();
        List<EmployBo> employBoList = new ArrayList<EmployBo>();
        for (Employ employ : employPager.getResultList()) {
            EmployBo bo = getEmployBo(employ);
            employBoList.add(bo);
        }
        employBoPager.setResultList(employBoList);
        employBoPager.setTotalSize(employPager.getTotalSize());
        return employBoPager;
    }

    @Override
    public Pager<EmployBo> findEmployByCompanyIdPager(Long companyId, Integer firstResult, Integer sizeNo) {
        Pager<Employ> employPager = employProxy.findEmployByCompanyIdPager(companyId, EmployCompanyStatus.Active.getCode(), firstResult, sizeNo);
        Pager<EmployBo> employBoPager = new Pager<EmployBo>();
        List<EmployBo> employBoList = new ArrayList<EmployBo>();
        for (Employ employ : employPager.getResultList()) {
            EmployBo bo = getEmployBo(employ);
            employBoList.add(bo);
        }
        employBoPager.setResultList(employBoList);
        employBoPager.setTotalSize(employPager.getTotalSize());
        return employBoPager;
    }

    @Override
    public Long saveEmploy(EmployBo employBo) {
        Employ employ = new Employ();
        ConversionUtils.conversion(employBo, employ);
        return employProxy.saveEmploy(employ);
    }

    @Override
    public Integer updateEmployResult(Long employId, Integer employResultStatus, String employResultInfo) {
        return employProxy.updateEmployResult(employId, employResultStatus, employResultInfo);
    }

    @Override
    public Integer updateEmployEntryPlanTime(Long employId, Date planTime) {
        return employProxy.updateEmployEntryPlanTime(employId, planTime);
    }

    @Override
    public Integer updateEmployReport(Long employId, Date reportTime, Integer reportStatus, String reportInfo) {
        return employProxy.updateEmployReport(employId, reportTime, reportStatus, reportInfo);
    }

    @Override
    public Integer deleteUserEmploy(Long employId) {
        return employProxy.updateEmployUserStatus(employId, EmployUserStatus.Delete.getCode());
    }

    @Override
    public Integer deleteCompanyEmploy(Long employId) {
        return employProxy.updateEmployCompanyStatus(employId, EmployCompanyStatus.Delete.getCode());
    }
}
