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
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.web.controller.param.company.CompanyParam;
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
@RequestMapping("/company")
@Controller
public class CompanyController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    CompanyService companyService;

    @Autowired
    FileService fileService;

    @RequestMapping(value = "updateCompany")
    @ResponseBody
    public ResponseEntity<String> updateCompany(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            CompanyParam companyParam = JsonUtils.jsonToObject(param, CompanyParam.class);
            CompanyBo bo = new CompanyBo();
            ConversionUtils.conversion(companyParam, bo);
            data = companyService.updateCompany(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateCompany->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findCompanyByCompanyId")
    @ResponseBody
    public ResponseEntity<String> findCompanyByCompanyId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        CompanyBo data = null;
        try {
            CompanyParam findCompanyParam = JsonUtils.jsonToObject(param, CompanyParam.class);
            data = companyService.findCompanyByCompanyId(findCompanyParam.getCompanyId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findCompanyByCompanyId->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findAllCompanyPager")
    @ResponseBody
    public ResponseEntity<String> findAllCompanyPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<CompanyBo> data = null;
        try {
            CompanyParam findRecruitmentInfoParam = JsonUtils.jsonToObject(param, CompanyParam.class);
            // 查询列表
            Integer sizeNo = findRecruitmentInfoParam.getSize() == null ? CommonConstants.NUM_INT_50 : findRecruitmentInfoParam
                    .getSize();
            Integer firstResult = findRecruitmentInfoParam.getPage() == null ? 0 : (findRecruitmentInfoParam.getPage() - 1) * sizeNo;
            data = companyService.findAllCompanyPager(firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllCompanyPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }


    /**
     * 上传头像
     *
     * @param request
     * @return
     */
    @RequestMapping("updateCompanyLogo")
    @ResponseBody
    public ResponseEntity<String> updateCompanyLogo(HttpServletRequest request) {
        ErrorType errorType = null;
        Long data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("companyLogoImageFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("updateCompanyLogo->contentType is " + contentType);
            } else {
                String companyId = multiRequest.getParameter("companyId");
                String companyLogoImageFileName = multiRequest.getParameter("companyLogoImageFileName");
                String companyLogoImageFileType = multiRequest.getParameter("companyLogoImageFileType");
                String companyLogoImageFileFormat = multiRequest.getParameter("companyLogoImageFileFormat");
                // 文件保存目录路径
                String savePath = request.getSession().getServletContext().getRealPath("/");
                data = fileService.upload(file, "company", savePath, companyId, companyLogoImageFileName, companyLogoImageFileType, companyLogoImageFileFormat);
                if (null == data) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyLogo(Long.valueOf(companyId), data);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateCompanyLogo->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 上传营业执照
     *
     * @param request
     * @return
     */
    @RequestMapping("updateCompanyBizlicense")
    @ResponseBody
    public ResponseEntity<String> updateCompanyBizlicense(HttpServletRequest request) {
        ErrorType errorType = null;
        Long data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("companyBizlicenseImageFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("updateCompanyBizlicense->contentType is " + contentType);
            } else {
                String companyId = multiRequest.getParameter("companyId");
                String companyBizlicenseImageFileName = multiRequest.getParameter("companyBizlicenseImageFileName");
                String companyBizlicenseImageFileType = multiRequest.getParameter("companyBizlicenseImageFileType");
                String companyBizlicenseImageFileFormat = multiRequest.getParameter("companyBizlicenseImageFileFormat");
                // 文件保存目录路径
                String savePath = request.getSession().getServletContext().getRealPath("/");
                data = fileService.upload(file, "company", savePath, companyId, companyBizlicenseImageFileName, companyBizlicenseImageFileType, companyBizlicenseImageFileFormat);

                if (null == data) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyBizlicense(Long.valueOf(companyId), data);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateCompanyBizlicense->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 上传身份证
     *
     * @param request
     * @return
     */
    @RequestMapping("updateCompanyUserIdCard")
    @ResponseBody
    public ResponseEntity<String> updateCompanyUserIdCard(HttpServletRequest request) {
        ErrorType errorType = null;
        Long data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("companyUserIdCardImageFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("updateCompanyUserIdCard->contentType is " + contentType);
            } else {
                String companyId = multiRequest.getParameter("companyId");
                String companyUserIdCardImageFileName = multiRequest.getParameter("companyUserIdCardImageFileName");
                String companyUserIdCardImageFileType = multiRequest.getParameter("companyUserIdCardImageFileType");
                String companyUserIdCardImageFileFormat = multiRequest.getParameter("companyUserIdCardImageFileFormat");
                // 文件保存目录路径
                String savePath = request.getSession().getServletContext().getRealPath("/");
                data = fileService.upload(file, "company", savePath, companyId, companyUserIdCardImageFileName, companyUserIdCardImageFileType, companyUserIdCardImageFileFormat);

                if (null == data) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyUserIdCard(Long.valueOf(companyId), data);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateCompanyUserIdCard->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    /**
     * 上传门头照
     *
     * @param request
     * @return
     */
    @RequestMapping("updateCompanyFacade")
    @ResponseBody
    public ResponseEntity<String> updateCompanyFacade(HttpServletRequest request) {
        ErrorType errorType = null;
        Long data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("companyFacadeImageFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("updateCompanyFacade->contentType is " + contentType);
            } else {
                String companyId = multiRequest.getParameter("companyId");
                String companyFacadeImageFileName = multiRequest.getParameter("companyFacadeImageFileName");
                String companyFacadeImageFileType = multiRequest.getParameter("companyFacadeImageFileType");
                String companyFacadeImageFileFormat = multiRequest.getParameter("companyFacadeImageFileFormat");
                // 文件保存目录路径
                String savePath = request.getSession().getServletContext().getRealPath("/");
                data = fileService.upload(file, "company", savePath, companyId, companyFacadeImageFileName, companyFacadeImageFileType, companyFacadeImageFileFormat);

                if (null == data) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyFacade(Long.valueOf(companyId), data);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateCompanyFacade->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
