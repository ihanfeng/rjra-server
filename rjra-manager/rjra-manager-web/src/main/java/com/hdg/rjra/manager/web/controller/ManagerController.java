package com.hdg.rjra.manager.web.controller;

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
import com.hdg.rjra.manager.model.bo.manager.ManagerBo;
import com.hdg.rjra.manager.service.ManagerService;
import com.hdg.rjra.manager.web.controller.param.manager.ManagerParam;
import com.hdg.rjra.manager.web.filter.SessionToken;
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
@RequestMapping("/manager")
@Controller
public class ManagerController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ManagerController.class);

    @Autowired
    ManagerService managerService;

    /**
     *
     * @description 登录
     * @author Administrator 创建时间 2014年7月8日 下午7:32:39
     * @param param
     *            请求参数
     * @param request
     *            request
     * @return 响应
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestParam(value = "param", required = true) String param,
                                        HttpServletRequest request) {
        ErrorType errorType = ErrorType.DEFFAULT;
        ManagerBo managerBo = null;
        String data = null;
        try {
            ManagerParam managerParam = JsonUtils.jsonToObject(param, ManagerParam.class);
            String encryptionFactor  = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
            String pwd = AESUtils.encrypt(managerParam.getManagerPwd(), managerParam.getManagerName(), encryptionFactor);
            managerBo = managerService.findManagerByNameAndPwd(managerParam.getManagerName(), pwd);
            if (managerBo == null) {
                errorType = ErrorType.MANAGER_ALREADY_NOT_EXIST;
            }
            else {
                data = UUIDUtils.randomUUID();
                request.getSession().setAttribute(SessionToken.TOKEN, data);
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("manager login ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     *
     * @description 退出
     * @author Sisi 创建时间 2014年7月9日 下午4:42:53
     * @param request
     *            request
     * @return ResponseEntity<String>
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
            errorType.setMessage(e.getMessage());
            LOG.error("manager logout ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), "logout");
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
