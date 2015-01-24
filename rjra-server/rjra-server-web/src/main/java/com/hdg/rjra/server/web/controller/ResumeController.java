package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.ResumeService;
import com.hdg.rjra.server.web.controller.param.file.FileParam;
import com.hdg.rjra.server.web.controller.param.resume.ResumeParam;
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
@RequestMapping("/resume")
@Controller
public class ResumeController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    ResumeService resumeService;

    @RequestMapping(value = "findResumeByResumeId")
    @ResponseBody
    public ResponseEntity<String> findResumeByResumeId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        ResumeBo data = null;
        try {
            ResumeParam resumeParam = JsonUtils.jsonToObject(param, ResumeParam.class);
            data = resumeService.findResumeByResumeId(resumeParam.getResumeId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findResumeByResumeId->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }



    @RequestMapping(value = "updatResumeStatus")
    @ResponseBody
    public ResponseEntity<String> updatResumeStatus(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            ResumeParam resumeParam = JsonUtils.jsonToObject(param, ResumeParam.class);
            data = resumeService.updatResumeStatus(resumeParam.getResumeId(), resumeParam.getResumeStatus());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updatResumeStatus->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateResume")
    @ResponseBody
    public ResponseEntity<String> updateResume(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            ResumeParam resumeParam = JsonUtils.jsonToObject(param, ResumeParam.class);
            ResumeBo bo = new ResumeBo();
            ConversionUtils.conversion(resumeParam, bo);
            data = resumeService.updateResume(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateResume->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
