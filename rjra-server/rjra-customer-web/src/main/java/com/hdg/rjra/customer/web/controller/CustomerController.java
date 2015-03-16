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
import com.hdg.rjra.customer.web.controller.param.manager.ChangePwdParam;
import com.hdg.rjra.customer.web.controller.param.manager.ManagerParam;
import com.hdg.rjra.customer.web.filter.SessionToken;
import com.hdg.rjra.server.model.bo.manager.ManagerBo;
import com.hdg.rjra.server.service.ManagerService;
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
}
