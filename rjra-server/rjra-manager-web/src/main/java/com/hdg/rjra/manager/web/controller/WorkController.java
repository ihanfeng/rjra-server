package com.hdg.rjra.manager.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.server.model.bo.work.WorkBo;
import com.hdg.rjra.server.model.bo.work.WorkBo;
import com.hdg.rjra.server.model.param.work.WorkParam;
import com.hdg.rjra.server.model.param.work.WorkParam;
import com.hdg.rjra.server.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rock
 */
@RequestMapping("/work")
@Controller
public class WorkController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    WorkService workService;

    @RequestMapping(value = "findWorkByWorkId")
    @ResponseBody
    public ResponseEntity<String> findWorkByWorkId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        WorkBo data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            data = workService.findWorkByWorkId(workParam.getWorkId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findWorkByWorkId ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 多条件查询岗位信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllWorkByConditionPager")
    @ResponseBody
    public ResponseEntity<String> findAllWorkByManagerPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<WorkBo> data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            // 查询列表
            Integer sizeNo = workParam.getSize() == null ? CommonConstants.NUM_INT_50 : workParam
                    .getSize();
            Integer firstResult = workParam.getPage() == null ? 0 : (workParam.getPage() - 1) * sizeNo;
            WorkBo bo = new WorkBo();
            ConversionUtils.conversion(workParam, bo);
            data = workService.findAllWorkByConditionPager(bo, firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllWorkPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateWork")
    @ResponseBody
    public ResponseEntity<String> updateWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            WorkBo bo = new WorkBo();
            ConversionUtils.conversion(workParam, bo);
            data = workService.updateWork(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateWork ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
