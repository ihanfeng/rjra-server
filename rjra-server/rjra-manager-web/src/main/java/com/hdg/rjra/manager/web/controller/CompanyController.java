package com.hdg.rjra.manager.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.param.company.CompanyParam;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rock
 */
@RequestMapping("/company")
@Controller
public class CompanyController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "findCompanyByCompanyId")
    @ResponseBody
    public ResponseEntity<String> findCompanyByCompanyId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        CompanyBo data = null;
        try {
            CompanyParam findCompanyParam = JsonUtils.jsonToObject(param, CompanyParam.class);
            data = companyService.findCompanyByCompanyId(findCompanyParam.getCompanyId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findCompanyByCompanyId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    /**
     * 多条件查询企业信息
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllCompanyByConditionPager")
    @ResponseBody
    public ResponseEntity<String> findAllCompanyByManagerPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<CompanyBo> data = null;
        try {
            CompanyParam companyParam = JsonUtils.jsonToObject(param, CompanyParam.class);
            // 查询列表
            Integer sizeNo = companyParam.getSize() == null ? CommonConstants.NUM_INT_50 : companyParam
                    .getSize();
            Integer firstResult = companyParam.getPage() == null ? 0 : (companyParam.getPage() - 1) * sizeNo;
            CompanyBo bo = new CompanyBo();
            ConversionUtils.conversion(companyParam, bo);
            data = companyService.findAllCompanyByConditionPager(bo, firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findAllCompanyPager->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }
}
