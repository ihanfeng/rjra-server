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
import com.hdg.rjra.server.model.bo.userbehavior.UserInviteUserBo;
import com.hdg.rjra.server.service.UserInviteUserService;
import com.hdg.rjra.server.web.controller.param.userbehavior.BatchUserInviteUserParam;
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
import java.util.List;


/**
 * @author Rock
 */
@RequestMapping("/userInviteUser")
@Controller
public class UserInviteUserController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserInviteUserController.class);

    @Autowired
    UserInviteUserService userInviteUserService;

    @RequestMapping(value = "saveUserInviteUser")
    @ResponseBody
    public ResponseEntity<String> saveUserInviteUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long data = null;
        try {

            UserInviteUserParam userInviteUserParam = JsonUtils.jsonToObject(param, UserInviteUserParam.class);
            UserInviteUserBo userInviteUserBo = userInviteUserService.findUserInviteUserByUserIdAndInviteUserId(userInviteUserParam.getUserId(), userInviteUserParam.getInviteUserId());
            if(userInviteUserBo == null) {
                userInviteUserBo = new UserInviteUserBo();
                ConversionUtils.conversion(userInviteUserParam, userInviteUserBo);
                data = userInviteUserService.saveUserInviteUser(userInviteUserBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_INVITE_WORK_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveUserInviteUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "batchInviteUser")
    @ResponseBody
    public ResponseEntity<String> batchInviteUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<Long> data = null;
        try {
            BatchUserInviteUserParam batchUserInviteUserParam = JsonUtils.jsonToObject(param, BatchUserInviteUserParam.class);
            data = userInviteUserService.batchSaveUserInviteUser(batchUserInviteUserParam.getBatchUserInvite());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("batchInviteUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
    
    /**
     * 查询邀请我的
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllUserInviteUserByInviteUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserInviteUserByInviteUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserInviteUserBo> data = null;
        try {
            UserInviteUserParam userInviteUserParam = JsonUtils.jsonToObject(param, UserInviteUserParam.class);
            // 查询列表
            Integer sizeNo = userInviteUserParam.getSize() == null ? CommonConstants.NUM_INT_50 : userInviteUserParam
                    .getSize();
            Integer firstResult = userInviteUserParam.getPage() == null ? 0 : (userInviteUserParam.getPage() - 1) * sizeNo;
            data = userInviteUserService.findAllUserInviteUserByInviteUserIdPager(userInviteUserParam.getInviteUserId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllUserInviteUserByInviteUserIdPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
    
    /**
     * 查询我邀请的
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllUserInviteUserByUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserInviteUserByUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserInviteUserBo> data = null;
        try {
            UserInviteUserParam userInviteUserParam = JsonUtils.jsonToObject(param, UserInviteUserParam.class);
            // 查询列表
            Integer sizeNo = userInviteUserParam.getSize() == null ? CommonConstants.NUM_INT_50 : userInviteUserParam
                    .getSize();
            Integer firstResult = userInviteUserParam.getPage() == null ? 0 : (userInviteUserParam.getPage() - 1) * sizeNo;
            data = userInviteUserService.findAllUserInviteUserByUserIdPager(userInviteUserParam.getUserId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllUserInviteUserByUserIdPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
    
    /**
     * 删除我邀请的数据
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "deleteUserInviteUser")
    @ResponseBody
    public ResponseEntity<String> deleteUserInviteUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            UserInviteUserParam userInviteUserParam = JsonUtils.jsonToObject(param, UserInviteUserParam.class);
            data = userInviteUserService.deleteUserInviteUser(userInviteUserParam.getInviteId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("deleteUserInviteUser->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
