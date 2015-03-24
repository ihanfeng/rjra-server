package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.ExamineResourceType;
import com.hdg.rjra.rdb.proxy.daoproxy.ICompanyProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IExamineLogProxy;
import com.hdg.rjra.rdb.proxy.domain.BaseDomain;
import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.domain.ExamineLog;
import com.hdg.rjra.server.model.bo.examine.ExamineLogBo;
import com.hdg.rjra.server.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.hdg.rjra.base.enumerate.ExamineResourceType.Company;

/**
 * Created by Administrator on 2015/3/24.
 */
@Service
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    ICompanyProxy companyProxy;

    @Autowired
    IExamineLogProxy examineLogProxy;

    @Override
    public Long examineResource(ExamineLogBo examineLogBo) {
        Date nowDate = new Date();

        Integer examineLogType = examineLogBo.getExamineLogType();
        if(ExamineResourceType.Company.getCode() == examineLogType.intValue()){
            Company company = new Company();
            company.setCompanyId(examineLogBo.getExamineLogResource());
            company.setCompanyExamineReviewerId(examineLogBo.getExamineLogReviewerId());
            company.setCompanyExamineReviewerName(examineLogBo.getExamineLogReviewerName());
            company.setCompanyExamineResult(examineLogBo.getExamineLogResult());
            company.setCompanyExamineStatus(examineLogBo.getExamineLogStatus());
            company.setCompanyExamineTime(nowDate);
            companyProxy.updateCompany(company);
        }
        ExamineLog examineLog = new ExamineLog();
        ConversionUtils.conversion(examineLogBo, examineLog);
        examineLog.setExamineLogTime(nowDate);
        return examineLogProxy.saveExamineLog(examineLog);
    }
}
