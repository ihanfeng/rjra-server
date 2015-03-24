package com.hdg.rjra.common.web.controller;

import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.WorkExperience;
import com.hdg.rjra.server.service.WorkExperienceService;
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
@RequestMapping("/workExperience")
@Controller
public class WorkExperienceController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(WorkExperienceController.class);

    @Autowired
    WorkExperienceService workExperienceService;

    @RequestMapping(value = "findAllWorkExperience")
    @ResponseBody
    public ResponseEntity<String> findAllWorkExperience(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<WorkExperience> data = null;
        try {
            data = workExperienceService.findAllWorkExperience();
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllWorkExperience->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
