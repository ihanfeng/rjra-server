package com.hdg.rjra.customer.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.output.OutputResult;
import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.common.utils.AESUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.common.utils.UUIDUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.customer.web.filter.SessionToken;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.param.LoginParam;
import com.hdg.rjra.server.model.param.user.ChangePwdParam;
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
import javax.servlet.http.HttpSession;


/**
 * @author Rock
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    UserService userService;

    /**
     * @param param   请求参数
     * @param request request
     * @return 响应
     * @description 登录
     * @author Administrator 创建时间 2014年7月8日 下午7:32:39
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestParam(value = "param", required = true) String param,
                                        HttpServletRequest request) {
        ErrorType errorType = ErrorType.DEFFAULT;
        UserBo data = null;
        try {
            LoginParam loginParam = JsonUtils.jsonToObject(param, LoginParam.class);
            String encryptionFactor = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
            String pwd = AESUtils.encrypt(loginParam.getPwd(), loginParam.getMobile(), encryptionFactor);
            data = userService.findUserByMobileAndPwd(loginParam.getMobile(), pwd);
            if (data == null) {
                errorType = ErrorType.USER_ALREADY_NOT_EXIST;
            } else {
                String token = UUIDUtils.randomUUID();
                data.setToken(token);
                request.getSession().setAttribute(SessionToken.TOKEN, token);
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("login user ->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    /**
     * @param request request
     * @return ResponseEntity<String>
     * @description 退出
     * @author Sisi 创建时间 2014年7月9日 下午4:42:53
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    public ResponseEntity<String> logout(HttpServletRequest request) {
        ErrorType errorType = ErrorType.DEFFAULT;
        try {
            HttpSession session = request.getSession();
            session.removeAttribute(SessionToken.TOKEN);
            session.invalidate();
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("customer logout ->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), "logout");
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
            if (count == null || count.intValue() == 0) {
                String encryptionFactor = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
                String pwd = AESUtils.encrypt(loginParam.getPwd(), loginParam.getMobile(), encryptionFactor);
                data = userService.saveUser(loginParam.getMobile(), pwd);
            } else {
                errorType = ErrorType.MOBILE_ALREADY_EXISTS;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("register->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "changePwd")
    @ResponseBody
    public ResponseEntity<String> changePwd(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        UserBo userBo = null;
        Integer data = null;
        try {
            ChangePwdParam changePwdParam = JsonUtils.jsonToObject(param, ChangePwdParam.class);
            String encryptionFactor = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
            String pwd = AESUtils.encrypt(changePwdParam.getOldPwd(), changePwdParam.getMobile(), encryptionFactor);
            userBo = userService.findUserByMobileAndPwd(changePwdParam.getMobile(), pwd);
            if (userBo == null) {
                errorType = ErrorType.USER_MOBILE_OR_PWD_IS_ERROR;
            } else {
                String newPwd = AESUtils.encrypt(changePwdParam.getNewPwd(), changePwdParam.getMobile(), encryptionFactor);
                data = userService.updateUserPwd(userBo.getUserId(), newPwd);
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("user changePwd ->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }
}
