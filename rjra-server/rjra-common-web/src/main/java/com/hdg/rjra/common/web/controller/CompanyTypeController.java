package com.hdg.rjra.common.web.controller;

import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.CompanyType;
import com.hdg.rjra.server.service.CompanyTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
@RequestMapping("/companyType")
@Controller
public class CompanyTypeController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CompanyTypeController.class);

    @Autowired
    CompanyTypeService companyTypeService;

    @RequestMapping(value = "findAllCompanyType")
    @ResponseBody
    public ResponseEntity<String> findAllCompanyType(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<CompanyType> data = null;
        try {
            data = companyTypeService.findAllCompanyType();
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findAllCompanyType->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }
}
