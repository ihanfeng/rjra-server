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
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectUserBo;
import com.hdg.rjra.server.service.UserCollectUserService;
import com.hdg.rjra.server.web.controller.param.userbehavior.UserCollectUserParam;
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
@RequestMapping("/userCollectUser")
@Controller
public class UserCollectUserController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserCollectUserController.class);

    @Autowired
    UserCollectUserService userCollectUserService;

    @RequestMapping(value = "saveUserCollectUser")
    @ResponseBody
    public ResponseEntity<String> saveUserCollectUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long data = null;
        try {

            UserCollectUserParam userCollectUserParam = JsonUtils.jsonToObject(param, UserCollectUserParam.class);
            UserCollectUserBo userCollectUserBo = userCollectUserService.findUserCollectUserByUserIdAndCollectUserId(userCollectUserParam.getUserId(), userCollectUserParam.getCollectUserId());
            if(userCollectUserBo == null) {
                userCollectUserBo = new UserCollectUserBo();
                ConversionUtils.conversion(userCollectUserParam, userCollectUserBo);
                data = userCollectUserService.saveUserCollectUser(userCollectUserBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_COLLECTION_USER_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveUserCollectUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 查询我收藏的人才信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllUserCollectUserByUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserCollectUserByUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserCollectUserBo> data = null;
        try {
            UserCollectUserParam userCollectUserParam = JsonUtils.jsonToObject(param, UserCollectUserParam.class);
            // 查询列表
            Integer sizeNo = userCollectUserParam.getSize() == null ? CommonConstants.NUM_INT_50 : userCollectUserParam
                    .getSize();
            Integer firstResult = userCollectUserParam.getPage() == null ? 0 : (userCollectUserParam.getPage() - 1) * sizeNo;
            data = userCollectUserService.findAllUserCollectUserByUserIdPager(userCollectUserParam.getUserId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllUserCollectUserByUserIdPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 删除我收藏的人才信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "deleteUserCollectUser")
    @ResponseBody
    public ResponseEntity<String> deleteUserCollectUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            UserCollectUserParam userCollectUserParam = JsonUtils.jsonToObject(param, UserCollectUserParam.class);
            data = userCollectUserService.deleteUserCollectUser(userCollectUserParam.getCollectId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("deleteUserCollectUser->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
