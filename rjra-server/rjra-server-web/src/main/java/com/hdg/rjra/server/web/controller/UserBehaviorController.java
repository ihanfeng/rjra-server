package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.server.model.bo.userbehavior.UserApplyWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectUserBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserCollectWorkBo;
import com.hdg.rjra.server.model.bo.userbehavior.UserInviteUserBo;
import com.hdg.rjra.server.service.UserBehaviorService;
import com.hdg.rjra.server.web.controller.param.userbehavior.UserApplyWorkParam;
import com.hdg.rjra.server.web.controller.param.userbehavior.UserCollectUserParam;
import com.hdg.rjra.server.web.controller.param.userbehavior.UserCollectWorkParam;
import com.hdg.rjra.server.web.controller.param.userbehavior.UserInviteUserParam;
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
@RequestMapping("/userbehavior")
@Controller
public class UserBehaviorController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserBehaviorController.class);

    @Autowired
    UserBehaviorService userBehaviorService;

    @RequestMapping(value = "applyWork")
    @ResponseBody
    public ResponseEntity<String> applyWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {

            UserApplyWorkParam userApplyWorkParam = JsonUtils.jsonToObject(param, UserApplyWorkParam.class);
            UserApplyWorkBo userApplyWorkBo = userBehaviorService.findUserApplyWorkByUserIdAndWorkId(userApplyWorkParam.getUserId(), userApplyWorkParam.getWorkId());
            if(userApplyWorkBo == null) {
                userApplyWorkBo = new UserApplyWorkBo();
                ConversionUtils.conversion(userApplyWorkParam, userApplyWorkBo);
                data = userBehaviorService.saveUserApplyWork(userApplyWorkBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_APPLY_WORK_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("applyWork->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "collectUser")
    @ResponseBody
    public ResponseEntity<String> collectUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {

            UserCollectUserParam userCollectUserParam = JsonUtils.jsonToObject(param, UserCollectUserParam.class);
            UserCollectUserBo userCollectUserBo = userBehaviorService.findUserCollectUserByUserIdAndCollectUserId(userCollectUserParam.getUserId(), userCollectUserParam.getCollectUserId());
            if(userCollectUserBo == null) {
                userCollectUserBo = new UserCollectUserBo();
                ConversionUtils.conversion(userCollectUserParam, userCollectUserBo);
                data = userBehaviorService.saveUserCollectUser(userCollectUserBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_COLLECTION_USER_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("collectUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
    @RequestMapping(value = "collectWork")
    @ResponseBody
    public ResponseEntity<String> collectWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {

            UserCollectWorkParam userCollectWorkParam = JsonUtils.jsonToObject(param, UserCollectWorkParam.class);
            UserCollectWorkBo userCollectWorkBo = userBehaviorService.findUserCollectWorkByUserIdAndWorkId(userCollectWorkParam.getUserId(), userCollectWorkParam.getWorkId());
            if(userCollectWorkBo == null) {
                userCollectWorkBo = new UserCollectWorkBo();
                ConversionUtils.conversion(userCollectWorkParam, userCollectWorkBo);
                data = userBehaviorService.saveUserCollectWork(userCollectWorkBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_COLLECTION_WORK_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("collectWork->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
    @RequestMapping(value = "inviteUser")
    @ResponseBody
    public ResponseEntity<String> inviteUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {

            UserInviteUserParam userInviteUserParam = JsonUtils.jsonToObject(param, UserInviteUserParam.class);
            UserInviteUserBo userInviteUserBo = userBehaviorService.findUserInviteUserByUserIdAndInviteUserId(userInviteUserParam.getUserId(), userInviteUserParam.getInviteUserId());
            if(userInviteUserBo == null) {
                userInviteUserBo = new UserInviteUserBo();
                ConversionUtils.conversion(userInviteUserParam, userInviteUserBo);
                data = userBehaviorService.saveUserInviteUser(userInviteUserBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_INVITE_WORK_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("inviteUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
