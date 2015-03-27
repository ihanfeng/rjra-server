package com.hdg.rjra.common.web.controller;

import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.CompanyScale;
import com.hdg.rjra.server.service.CompanyScaleService;
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
@RequestMapping("/companyScale")
@Controller
public class CompanyScaleController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CompanyScaleController.class);

    @Autowired
    CompanyScaleService companyScaleService;

    @RequestMapping(value = "findAllCompanyScale")
    @ResponseBody
    public ResponseEntity<String> findAllCompanyScale(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<CompanyScale> data = null;
        try {
            data = companyScaleService.findAllCompanyScale();
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findAllCompanyScale->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }
}
