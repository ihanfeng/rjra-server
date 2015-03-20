package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.userbehavior.UserApplyWorkBo;
import com.hdg.rjra.server.service.UserApplyWorkService;
import com.hdg.rjra.server.web.controller.param.userbehavior.UserApplyWorkParam;
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
@RequestMapping("/userApplyWork")
@Controller
public class UserApplyWorkController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserApplyWorkController.class);

    @Autowired
    UserApplyWorkService applyWorkService;

    @RequestMapping(value = "saveUserApplyWork")
    @ResponseBody
    public ResponseEntity<String> saveUserApplyWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long data = null;
        try {

            UserApplyWorkParam userApplyWorkParam = JsonUtils.jsonToObject(param, UserApplyWorkParam.class);
            UserApplyWorkBo userApplyWorkBo = applyWorkService.findUserApplyWorkByUserIdAndWorkId(userApplyWorkParam.getUserId(), userApplyWorkParam.getWorkId());
            if(userApplyWorkBo == null) {
                userApplyWorkBo = new UserApplyWorkBo();
                ConversionUtils.conversion(userApplyWorkParam, userApplyWorkBo);
                data = applyWorkService.saveUserApplyWork(userApplyWorkBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_APPLY_WORK_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveUserApplyWork->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 查询我申请的工作信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllUserApplyWorkByUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserApplyWorkByUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserApplyWorkBo> data = null;
        try {
            UserApplyWorkParam userApplyWorkParam = JsonUtils.jsonToObject(param, UserApplyWorkParam.class);
            // 查询列表
            Integer sizeNo = userApplyWorkParam.getSize() == null ? CommonConstants.NUM_INT_50 : userApplyWorkParam
                    .getSize();
            Integer firstResult = userApplyWorkParam.getPage() == null ? 0 : (userApplyWorkParam.getPage() - 1) * sizeNo;
            data = applyWorkService.findAllUserApplyWorkByUserIdPager(userApplyWorkParam.getUserId(),firstResult,sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllUserApplyWorkByUserIdPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 查询向我申请的
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllUserApplyWorkByWorkUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserApplyWorkByWorkUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserApplyWorkBo> data = null;
        try {
            UserApplyWorkParam userApplyWorkParam = JsonUtils.jsonToObject(param, UserApplyWorkParam.class);
            // 查询列表
            Integer sizeNo = userApplyWorkParam.getSize() == null ? CommonConstants.NUM_INT_50 : userApplyWorkParam
                    .getSize();
            Integer firstResult = userApplyWorkParam.getPage() == null ? 0 : (userApplyWorkParam.getPage() - 1) * sizeNo;
            data = applyWorkService.findAllUserApplyWorkByWorkUserIdPager(userApplyWorkParam.getUserId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllUserApplyWorkByWorkUserIdPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 删除我的申请信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "deleteUserApplyWork")
    @ResponseBody
    public ResponseEntity<String> deleteUserApplyWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            UserApplyWorkParam userApplyWorkParam = JsonUtils.jsonToObject(param, UserApplyWorkParam.class);
            data = applyWorkService.deleteUserApplyWork(userApplyWorkParam.getApplyId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("deleteUserApplyWork->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
