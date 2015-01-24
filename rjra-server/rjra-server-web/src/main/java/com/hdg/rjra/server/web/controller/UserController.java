package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.rjra.base.constants.CommonConstants;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import com.hdg.rjra.base.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.base.utils.AESUtils;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.UserService;
import com.hdg.rjra.server.web.controller.param.LoginParam;
import com.hdg.rjra.server.web.controller.param.LocationParam;
import com.hdg.rjra.server.web.controller.param.user.UserParam;
import com.hdg.rjra.server.web.utils.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author Rock
 */
@RequestMapping("/user")
@Controller
public class UserController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "saveUser")
    @ResponseBody
    public ResponseEntity<String> saveUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Long data = null;
        try {

            LoginParam loginParam = JsonUtils.jsonToObject(param, LoginParam.class);
            Integer count = userService.findUserExistsByMobile(loginParam.getMobile());
            if(count == null || count.intValue() == 0) {
                String encryptionFactor  = CustomizedPropertyConfigurer.getContextPropertyForString("encryptionFactor");
                String pwd = AESUtils.encrypt(loginParam.getPwd(), loginParam.getMobile(), encryptionFactor);
                data = userService.saveUser(loginParam.getMobile(), pwd);
            } else {
                errorType = ErrorType.MOBILE_ALREADY_EXISTS;
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateUser")
    @ResponseBody
    public ResponseEntity<String> updateUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            UserParam userParam = JsonUtils.jsonToObject(param, UserParam.class);
            UserBo bo = new UserBo();
            ConversionUtils.conversion(userParam, bo);
            data = userService.updateUser(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateUser->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findUserByUserId")
    @ResponseBody
    public ResponseEntity<String> findUserByUserId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        UserBo data = null;
        try {
            UserParam findUserParam = JsonUtils.jsonToObject(param, UserParam.class);

            data = userService.findUserByUserId(findUserParam.getUserId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findUserByUserId->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findAllUserPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<UserBo> data = null;
        try {
            UserParam findRecruitmentInfoParam = JsonUtils.jsonToObject(param, UserParam.class);
            // 查询列表
            Integer sizeNo = findRecruitmentInfoParam.getSize() == null ? CommonConstants.NUM_INT_50 : findRecruitmentInfoParam
                    .getSize();
            Integer firstResult = findRecruitmentInfoParam.getPage() == null ? 0 : (findRecruitmentInfoParam.getPage() - 1) * sizeNo;
            data = userService.findAllUserPager(firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllUserPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 上传头像
     *
     * @param request
     * @return
     */
    @RequestMapping("updateUserHead")
    @ResponseBody
    public ResponseEntity<String> updateUserHead(HttpServletRequest request) {
        ErrorType errorType = null;
        Long data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("userHeadImageFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("updateUserHead->contentType is " + contentType);
            } else {
                String userId = multiRequest.getParameter("userId");
                String userHeadImageFileName = multiRequest.getParameter("userHeadImageFileName");
                String userHeadImageFileType = multiRequest.getParameter("userHeadImageFileType");
                String userHeadImageFileFormat = multiRequest.getParameter("userHeadImageFileFormat");
                // 文件保存目录路径
                String savePath = request.getSession().getServletContext().getRealPath("/");
                data = fileService.upload(file, "user", savePath, userId, userHeadImageFileName, userHeadImageFileType, userHeadImageFileFormat);
                if (null == data) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    userService.updateUserHead(Long.valueOf(userId), data);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateUserHead->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findNearUserPager")
    @ResponseBody
    public ResponseEntity<String> findNearUserPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<UserBo> data = null;
        try {
            LocationParam locationParam = JsonUtils.jsonToObject(param, LocationParam.class);
            // 查询列表
            Integer sizeNo = locationParam.getSize() == null ? CommonConstants.NUM_INT_50 : locationParam
                    .getSize();
            Integer firstResult = locationParam.getPage() == null ? 0 : (locationParam.getPage() - 1) * sizeNo;
            data = userService.findNearUserPager(locationParam.getLatitude(), locationParam.getLongitude(), locationParam.getRaidus(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findNearUserPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 更新用户地理位置信息
     *
     * @param request
     * @return
     */
    @RequestMapping("updateUserLocation")
    @ResponseBody
    public ResponseEntity<String> updateUserLocation(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            LocationParam locationParam = JsonUtils.jsonToObject(param, LocationParam.class);
            data = userService.updateUserLocation(locationParam.getUserId(),locationParam.getLongitude(),locationParam.getLatitude());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateUserLocation->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

}
