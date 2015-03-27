package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.common.utils.AESUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.param.LoginParam;
import com.hdg.rjra.server.service.MMSService;
import com.hdg.rjra.server.service.UserService;
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
@RequestMapping("/login")
@Controller
public class LoginController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserService userService;

    @Autowired
    MMSService mmsService;

    /**
     * 发送短信
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "sendMsg")
    @ResponseBody
    public ResponseEntity<String> sendMessage(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        String data = null;
        try {
            LoginParam loginParam = JsonUtils.jsonToObject(param, LoginParam.class);
            String code = StringUtils.randomCode(4);
            StringBuffer msg = new StringBuffer();
            msg.append("【工多多】亲爱的用户，您的手机验证码是");
            msg.append(code);
            msg.append(",此验证码半小时内有效，请尽快完成验证。");
            String result = mmsService.sendMessage(new String[]{loginParam.getMobile()}, msg.toString());
            if ("0".equals(result)) {
                data = code;
                request.getSession().setAttribute("code", data);
            } else {
                data = result;
                errorType = ErrorType.MMS_SEND_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("sendMessage->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    /**
     * 注册
     *
     * @param request
     * @param param
     * @return
     */
    @RequestMapping(value = "register")
    @ResponseBody
    public ResponseEntity<String> saveUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        UserBo data = null;
        try {

            LoginParam loginParam = JsonUtils.jsonToObject(param, LoginParam.class);
            Integer count = userService.findUserExistsByMobile(loginParam.getMobile());
            if (loginParam.getCode() != null) {
                if (count == null || count.intValue() == 0) {
                    String encryptionFactor = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
                    String pwd = AESUtils.encrypt(loginParam.getPwd(), loginParam.getMobile(), encryptionFactor);
                    data = userService.saveUser(loginParam.getMobile(), pwd);
                } else {
                    errorType = ErrorType.MOBILE_ALREADY_EXISTS;
                }
            } else {
                //提交的code为空或者未发送过code
                errorType = ErrorType.USER_REGISTER_CODE_ERROR;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("register->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }
}
