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
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectWorkBo;
import com.hdg.rjra.server.model.param.userbehavior.UserCollectWorkParam;
import com.hdg.rjra.server.service.UserCollectWorkService;
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
@RequestMapping("/userCollectWork")
@Controller
public class UserCollectWorkController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserCollectWorkController.class);

    @Autowired
    UserCollectWorkService userCollectWorkService;

    @RequestMapping(value = "saveUserCollectWork")
    @ResponseBody
    public ResponseEntity<String> saveUserCollectWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long data = null;
        try {

            UserCollectWorkParam userCollectWorkParam = JsonUtils.jsonToObject(param, UserCollectWorkParam.class);
            UserCollectWorkBo userCollectWorkBo = userCollectWorkService.findUserCollectWorkByUserIdAndWorkId(userCollectWorkParam.getUserId(), userCollectWorkParam.getWorkId());
            if(userCollectWorkBo == null) {
                userCollectWorkBo = new UserCollectWorkBo();
                ConversionUtils.conversion(userCollectWorkParam, userCollectWorkBo);
                data = userCollectWorkService.saveUserCollectWork(userCollectWorkBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_COLLECTION_WORK_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("saveUserCollectWork->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 查询我收藏的工作信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllUserCollectWorkByUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserCollectWorkByUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserCollectWorkBo> data = null;
        try {
            UserCollectWorkParam userCollectWorkParam = JsonUtils.jsonToObject(param, UserCollectWorkParam.class);
            // 查询列表
            Integer sizeNo = userCollectWorkParam.getSize() == null ? CommonConstants.NUM_INT_50 : userCollectWorkParam
                    .getSize();
            Integer firstResult = userCollectWorkParam.getPage() == null ? 0 : (userCollectWorkParam.getPage() - 1) * sizeNo;
            data = userCollectWorkService.findAllUserCollectWorkByUserIdPager(userCollectWorkParam.getUserId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findAllUserCollectWorkByUserIdPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 删除我收藏的工作信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "deleteUserCollectWork")
    @ResponseBody
    public ResponseEntity<String> deleteUserCollectWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            UserCollectWorkParam userCollectWorkParam = JsonUtils.jsonToObject(param, UserCollectWorkParam.class);
            data = userCollectWorkService.deleteUserCollectWork(userCollectWorkParam.getCollectId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("deleteUserCollectWork->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 批量删除我收藏的工作信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "batchDeleteCollectWorkByCollectIds")
    @ResponseBody
    public ResponseEntity<String> batchDeleteCollectWorkByCollectIds(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            UserCollectWorkParam userCollectWorkParam = JsonUtils.jsonToObject(param, UserCollectWorkParam.class);
            data = userCollectWorkService.batchDeleteCollectWorkByCollectIds(userCollectWorkParam.getBatchDeleteCollectIds());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("batchDeleteCollectWorkByCollectIds->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
