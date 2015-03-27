package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.common.utils.AESUtils;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.user.UserBo;
import com.hdg.rjra.server.model.param.LocationParam;
import com.hdg.rjra.server.model.param.LoginParam;
import com.hdg.rjra.server.model.param.user.ChangePwdParam;
import com.hdg.rjra.server.model.param.user.UserParam;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.UserService;
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
            LOG.error("saveUser->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "updateUser")
    @ResponseBody
    public ResponseEntity<String> updateUser(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            UserParam userParam = JsonUtils.jsonToObject(param, UserParam.class);
            UserBo bo = new UserBo();
            ConversionUtils.conversion(userParam, bo);
            data = userService.updateUser(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateUser->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findUserByUserId")
    @ResponseBody
    public ResponseEntity<String> findUserByUserId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        UserBo data = null;
        try {
            UserParam findUserParam = JsonUtils.jsonToObject(param, UserParam.class);

            data = userService.findUserByUserId(findUserParam.getUserId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findUserByUserId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findAllUserPager")
    @ResponseBody
    public ResponseEntity<String> findAllUserPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
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
            errorType.setMessage(e.toString());
            LOG.error("findAllUserPager->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
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
        ErrorType errorType = ErrorType.DEFFAULT;
        Long fileId = null;
        AccountFileBo data = null;
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
                data = fileService.uploadFile(file.getInputStream(), "user", userId, userHeadImageFileName, userHeadImageFileType, userHeadImageFileFormat);
                fileId = fileService.saveAccountFile(data);
                data.setFileId(fileId);
                if (null == fileId) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    userService.updateUserHead(Long.valueOf(userId), fileId);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateUserHead->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findNearUserPager")
    @ResponseBody
    public ResponseEntity<String> findNearUserPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<UserBo> data = null;
        try {
            LocationParam locationParam = JsonUtils.jsonToObject(param, LocationParam.class);
            // 查询列表
            Integer sizeNo = locationParam.getSize() == null ? CommonConstants.NUM_INT_50 : locationParam
                    .getSize();
            Integer firstResult = locationParam.getPage() == null ? 0 : (locationParam.getPage() - 1) * sizeNo;
            data = userService.findNearUserPager(locationParam.getLongitude(), locationParam.getLatitude(), locationParam.getRaidus(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findNearUserPager->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
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
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            LocationParam locationParam = JsonUtils.jsonToObject(param, LocationParam.class);
            data = userService.updateUserLocation(locationParam.getUserId(), locationParam.getLongitude(), locationParam.getLatitude());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateUserLocation->", e);
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
