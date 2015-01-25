package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.resume.ResumeBo;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.ResumeService;
import com.hdg.rjra.server.web.controller.param.file.FileParam;
import com.hdg.rjra.server.web.controller.param.resume.ResumeParam;
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
import java.util.List;

/**
 * @author Rock
 */
@RequestMapping("/resume")
@Controller
public class ResumeController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResumeController.class);

    @Autowired
    ResumeService resumeService;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "findResumeByResumeId")
    @ResponseBody
    public ResponseEntity<String> findResumeByResumeId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        ResumeBo data = null;
        try {
            ResumeParam resumeParam = JsonUtils.jsonToObject(param, ResumeParam.class);
            data = resumeService.findResumeByResumeId(resumeParam.getResumeId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findResumeByResumeId->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }



    @RequestMapping(value = "updatResumeStatus")
    @ResponseBody
    public ResponseEntity<String> updatResumeStatus(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            ResumeParam resumeParam = JsonUtils.jsonToObject(param, ResumeParam.class);
            data = resumeService.updatResumeStatus(resumeParam.getResumeId(), resumeParam.getResumeStatus());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updatResumeStatus->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateResume")
    @ResponseBody
    public ResponseEntity<String> updateResume(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            ResumeParam resumeParam = JsonUtils.jsonToObject(param, ResumeParam.class);
            ResumeBo bo = new ResumeBo();
            ConversionUtils.conversion(resumeParam, bo);
            data = resumeService.updateResume(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateResume->", e);
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
    @RequestMapping("updateResumeHead")
    @ResponseBody
    public ResponseEntity<String> updateResumeHead(HttpServletRequest request) {
        ErrorType errorType = null;
        Long data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("resumeHeadImageFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("updateResumeHead->contentType is " + contentType);
            } else {
                String userId = multiRequest.getParameter("resumeId");
                String resumeHeadImageFileName = multiRequest.getParameter("resumeHeadImageFileName");
                String resumeHeadImageFileType = multiRequest.getParameter("resumeHeadImageFileType");
                String resumeHeadImageFileFormat = multiRequest.getParameter("resumeHeadImageFileFormat");
                // 文件保存目录路径
                String savePath = request.getSession().getServletContext().getRealPath("/");
                data = fileService.upload(file, "user", savePath, userId, resumeHeadImageFileName, resumeHeadImageFileType, resumeHeadImageFileFormat);
                if (null == data) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    resumeService.updateResumeHead(Long.valueOf(userId), data);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateResumeHead->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

}
