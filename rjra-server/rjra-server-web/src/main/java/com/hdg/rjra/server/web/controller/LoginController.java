package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import com.hdg.rjra.base.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.base.utils.AESUtils;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.UserService;
import com.hdg.rjra.server.web.controller.param.LoginParam;
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
    CompanyService companyService;
    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "company")
    @ResponseBody
    public ResponseEntity<String> company(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        CompanyBo data = null;
        try {
            LoginParam loginParam = JsonUtils.jsonToObject(param, LoginParam.class);
            String encryptionFactor  = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
            String pwd = AESUtils.encrypt(loginParam.getPwd(), loginParam.getMobile(), encryptionFactor);
            data = companyService.findCompanyByMobileAndPwd(loginParam.getMobile(),pwd);
            if (data == null) {
                errorType = ErrorType.COMPANY_ALREADY_NOT_EXIST;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("login company ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "user")
    @ResponseBody
    public ResponseEntity<String> user(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        UserBo data = null;
        try {
            LoginParam loginParam = JsonUtils.jsonToObject(param, LoginParam.class);
            String encryptionFactor  = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
            String pwd = AESUtils.encrypt(loginParam.getPwd(), loginParam.getMobile(), encryptionFactor);
            data = userService.findUserByMobileAndPwd(loginParam.getMobile(), pwd);
            if (data == null) {
                errorType = ErrorType.USER_ALREADY_NOT_EXIST;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("login user ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
