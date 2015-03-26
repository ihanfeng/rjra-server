package com.hdg.rjra.manager.web.controller;

import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.manager.web.filter.SessionToken;
import com.hdg.rjra.server.model.bo.examine.ExamineLogBo;
import com.hdg.rjra.server.model.bo.manager.ManagerBo;
import com.hdg.rjra.server.model.param.examine.ExamineParam;
import com.hdg.rjra.server.service.ExamineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/3/24.
 */
@RequestMapping("/examine")
@Controller
public class ExamineController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    ExamineService examineService;

    @RequestMapping(value = "examineResource")
    @ResponseBody
    public ResponseEntity<String> examineResource(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long data = null;
        try {
            HttpSession session = request.getSession();
            String token = (String) session.getAttribute(SessionToken.TOKEN);
            ManagerBo managerBo = (ManagerBo) session.getAttribute(token);

            ExamineParam examineParam = JsonUtils.jsonToObject(param, ExamineParam.class);
            ExamineLogBo bo = new ExamineLogBo();
            bo.setExamineLogReviewerId(managerBo.getManagerId());
            bo.setExamineLogReviewerName(managerBo.getManagerName());
            bo.setExamineLogResult(examineParam.getExamineResult());
            bo.setExamineLogType(examineParam.getExamineType());
            bo.setExamineLogStatus(examineParam.getExamineStatus());
            bo.setExamineLogResource(examineParam.getExamineResource());
            data = examineService.examineResource(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("examineResource->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
