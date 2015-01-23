package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.rjra.base.constants.CommonConstants;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.employ.EmployBo;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.service.EmployService;
import com.hdg.rjra.server.web.controller.param.employ.EmployParam;
import com.hdg.rjra.server.web.controller.param.file.FileParam;
import com.hdg.rjra.server.web.utils.ResponseUtils;
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
 * @author Rock
 */
@RequestMapping("/employ")
@Controller
public class EmployController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(EmployController.class);

    @Autowired
    EmployService employService;

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findEmployByEmployId")
    @ResponseBody
    public ResponseEntity<String> findEmployByEmployId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        EmployBo data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            data = employService.findEmployByEmployId(employParam.getEmployId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findEmployByEmployId->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findEmployByUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findEmployByUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<EmployBo> data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            // 查询列表
            Integer sizeNo = employParam.getSize() == null ? CommonConstants.NUM_INT_50 : employParam
                    .getSize();
            Integer firstResult = employParam.getPage() == null ? 0 : (employParam.getPage() - 1) * sizeNo;
            data = employService.findEmployByUserIdPager(employParam.getUserId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findEmployByUserIdPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findEmployByCompanyIdPager")
    @ResponseBody
    public ResponseEntity<String> findEmployByCompanyIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<EmployBo> data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            // 查询列表
            Integer sizeNo = employParam.getSize() == null ? CommonConstants.NUM_INT_50 : employParam
                    .getSize();
            Integer firstResult = employParam.getPage() == null ? 0 : (employParam.getPage() - 1) * sizeNo;
            data = employService.findEmployByCompanyIdPager(employParam.getCompanyId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findEmployByCompanyIdPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "saveEmploy")
    @ResponseBody
    public ResponseEntity<String> saveEmploy(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Long data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            EmployBo bo = new EmployBo();
            ConversionUtils.conversion(employParam, bo);
            data = employService.saveEmploy(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveEmploy->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "updateEmployResult")
    @ResponseBody
    public ResponseEntity<String> updateEmployResult(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            data = employService.updateEmployResult(employParam.getEmployId(), employParam.getEmployResultStatus(), employParam.getEmployResultInfo());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateEmployResult->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "updateEmployEntryPlanTime")
    @ResponseBody
    public ResponseEntity<String> updateEmployEntryPlanTime(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            data = employService.updateEmployEntryPlanTime(employParam.getEmployId(), employParam.getEmployEntryPlanTime());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateEmployEntryPlanTime->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "updateEmployReport")
    @ResponseBody
    public ResponseEntity<String> updateEmployReport(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            data = employService.updateEmployReport(employParam.getEmployId(), employParam.getEmployReportPlanTime(), employParam.getEmployReportStatus(), employParam.getEmployReportInfo());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateEmployReport->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "deleteUserEmploy")
    @ResponseBody
    public ResponseEntity<String> deleteUserEmploy(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            data = employService.deleteUserEmploy(employParam.getEmployId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("deleteUserEmploy->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "deleteCompanyEmploy")
    @ResponseBody
    public ResponseEntity<String> deleteCompanyEmploy(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            EmployParam employParam = JsonUtils.jsonToObject(param, EmployParam.class);
            data = employService.deleteCompanyEmploy(employParam.getEmployId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("deleteCompanyEmploy->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
