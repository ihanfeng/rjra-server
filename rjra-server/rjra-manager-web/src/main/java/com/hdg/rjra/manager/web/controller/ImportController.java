package com.hdg.rjra.manager.web.controller;

import com.hdg.common.output.OutputResult;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.enumerate.ImportResourceType;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.manager.web.filter.SessionToken;
import com.hdg.rjra.server.model.bo.importlog.ImportLogBo;
import com.hdg.rjra.server.model.bo.manager.ManagerBo;
import com.hdg.rjra.server.service.ImportLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;

/**
 * Created by Administrator on 2015/3/24.
 */
@RequestMapping("/import")
@Controller
public class ImportController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    ImportLogService importService;

    /**
     * 上传门头照
     *
     * @param request
     * @return
     */
    @RequestMapping("file")
    @ResponseBody
    public ResponseEntity<String> file(HttpServletRequest request) {
        ErrorType errorType = ErrorType.DEFFAULT;
        ImportLogBo data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("importFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("file->contentType is " + contentType);
            } else {
                HttpSession session = request.getSession();
                String token = (String) session.getAttribute(SessionToken.TOKEN);
                ManagerBo managerBo = (ManagerBo) session.getAttribute(token);
                String importType = multiRequest.getParameter("importType");
                ImportLogBo bo = new ImportLogBo();
                bo.setImportLogFileName(file.getName());
                bo.setImportLogOperatorId(managerBo.getManagerId());
                bo.setImportLogOperatorName(managerBo.getManagerName());
                bo.setImportLogType(Integer.parseInt(importType));
                if(ImportResourceType.Company.getCode() == bo.getImportLogType().intValue()){
                    data = importService.company(bo, (FileInputStream) file.getInputStream());
                } else if(ImportResourceType.Work.getCode() == bo.getImportLogType().intValue()){
                    data = importService.work(bo, (FileInputStream) file.getInputStream());
                } else if(ImportResourceType.Resume.getCode() == bo.getImportLogType().intValue()){
                    data = importService.resume(bo, (FileInputStream) file.getInputStream());
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("file->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
