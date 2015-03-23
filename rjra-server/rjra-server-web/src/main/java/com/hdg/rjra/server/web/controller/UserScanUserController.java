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
import com.hdg.rjra.server.model.bo.userbehavior.UserScanUserBo;
import com.hdg.rjra.server.model.param.userbehavior.UserScanUserParam;
import com.hdg.rjra.server.service.UserScanUserService;
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
@RequestMapping("/userScanUser")
@Controller
public class UserScanUserController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserScanUserController.class);

    @Autowired
    UserScanUserService userScanUserService;

    @RequestMapping(value = "saveUserScanUser")
    @ResponseBody
    public ResponseEntity<String> saveUserScanUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long data = null;
        try {

            UserScanUserParam userScanUserParam = JsonUtils.jsonToObject(param, UserScanUserParam.class);
            UserScanUserBo userScanUserBo = userScanUserService.findUserScanUserByUserIdAndScanUserId(userScanUserParam.getUserId(), userScanUserParam.getScanUserId());
            if(userScanUserBo == null) {
                userScanUserBo = new UserScanUserBo();
                ConversionUtils.conversion(userScanUserParam, userScanUserBo);
                data = userScanUserService.saveUserScanUser(userScanUserBo);
            } else {
                errorType = ErrorType.USER_BEHAVIOR_COLLECTION_USER_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveUserScanUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 查询浏览我的用户信息
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "findAllUserScanUserByScanUserIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserScanUserByScanUserIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserScanUserBo> data = null;
        try {
            UserScanUserParam userScanUserParam = JsonUtils.jsonToObject(param, UserScanUserParam.class);
            // 查询列表
            Integer sizeNo = userScanUserParam.getSize() == null ? CommonConstants.NUM_INT_50 : userScanUserParam
                    .getSize();
            Integer firstResult = userScanUserParam.getPage() == null ? 0 : (userScanUserParam.getPage() - 1) * sizeNo;
            data = userScanUserService.findAllUserScanUserByScanUserIdPager(userScanUserParam.getScanUserId(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllUserScanUserByScanUserIdPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
