package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.param.company.CompanyImageParam;
import com.hdg.rjra.server.model.param.company.CompanyParam;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.FileService;
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
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            CompanyParam companyParam = JsonUtils.jsonToObject(param, CompanyParam.class);
            CompanyBo bo = new CompanyBo();
            ConversionUtils.conversion(companyParam, bo);
            data = companyService.updateCompany(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateCompany->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findCompanyByCompanyId")
    @ResponseBody
    public ResponseEntity<String> findCompanyByCompanyId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        CompanyBo data = null;
        try {
            CompanyParam findCompanyParam = JsonUtils.jsonToObject(param, CompanyParam.class);
            data = companyService.findCompanyByCompanyId(findCompanyParam.getCompanyId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findCompanyByCompanyId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findAllCompanyPager")
    @ResponseBody
    public ResponseEntity<String> findAllCompanyPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
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
            errorType.setMessage(e.toString());
            LOG.error("findAllCompanyPager->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
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
        ErrorType errorType = ErrorType.DEFFAULT;
        Long fileId = null;
        AccountFileBo data = null;
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
                data = fileService.uploadFile(file.getInputStream(), "company", companyId, companyLogoImageFileName, companyLogoImageFileType, companyLogoImageFileFormat);
                fileId = fileService.saveAccountFile(data);
                data.setFileId(fileId);
                if (null == fileId) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyLogo(Long.valueOf(companyId), fileId);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateCompanyLogo->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
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
        ErrorType errorType = ErrorType.DEFFAULT;
        Long fileId = null;
        AccountFileBo data = null;
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

                data = fileService.uploadFile(file.getInputStream(), "company", companyId, companyBizlicenseImageFileName, companyBizlicenseImageFileType, companyBizlicenseImageFileFormat);
                fileId = fileService.saveAccountFile(data);
                data.setFileId(fileId);
                if (null == fileId) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyBizlicense(Long.valueOf(companyId), fileId);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateCompanyBizlicense->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
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
        ErrorType errorType = ErrorType.DEFFAULT;
        Long fileId = null;
        AccountFileBo data = null;
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
                data = fileService.uploadFile(file.getInputStream(), "company", companyId, companyUserIdCardImageFileName, companyUserIdCardImageFileType, companyUserIdCardImageFileFormat);
                fileId = fileService.saveAccountFile(data);
                data.setFileId(fileId);
                if (null == fileId) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyUserIdCard(Long.valueOf(companyId), fileId);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateCompanyUserIdCard->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
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
        ErrorType errorType = ErrorType.DEFFAULT;
        Long fileId = null;
        AccountFileBo data = null;

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
                data = fileService.uploadFile(file.getInputStream(), "company", companyId, companyFacadeImageFileName, companyFacadeImageFileType, companyFacadeImageFileFormat);
                fileId = fileService.saveAccountFile(data);
                data.setFileId(fileId);
                if (null == fileId) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    companyService.updateCompanyFacade(Long.valueOf(companyId), fileId);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateCompanyFacade->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    /**
     * 上传企业图片
     *
     * @param request
     * @return
     */
    @RequestMapping("updateCompanyImages")
    @ResponseBody
    public ResponseEntity<String> updateCompanyImages(HttpServletRequest request) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long fileId = null;
        AccountFileBo data = null;
        try {
            MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            MultipartHttpServletRequest multiRequest = resolver.resolveMultipart(request);
            // 获取内容类型
            String contentType = request.getContentType();
            // 获得上传文件列表
            MultipartFile file = multiRequest.getFile("companyImageFile");
            if (file == null || contentType == null || !contentType.startsWith("multipart")) {
                errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                LOG.error("updateCompanyImages->contentType is " + contentType);
            } else {
                String companyId = multiRequest.getParameter("companyId");
                String companyFacadeImageFileName = multiRequest.getParameter("companyImageFileName");
                String companyFacadeImageFileType = multiRequest.getParameter("companyImageFileType");
                String companyFacadeImageFileFormat = multiRequest.getParameter("companyImageFileFormat");
                // 文件保存目录路径
                data = fileService.uploadFile(file.getInputStream(), "company", companyId, companyFacadeImageFileName, companyFacadeImageFileType, companyFacadeImageFileFormat);
                fileId = fileService.saveAccountFile(data);
                data.setFileId(fileId);
                if (null == fileId) {
                    errorType = ErrorType.UPLOAD_IMAGE_FAIL;
                } else {
                    CompanyBo companyBo = companyService.findCompanyByCompanyId(Long.valueOf(companyId));
                    Long[] companyImages = companyBo.getCompanyImages();
                    Long[] newCompanyImages = new Long[companyImages.length + 1];
                    for (int i = 0; i < companyImages.length; i++) {
                        newCompanyImages[i] = companyImages[i];
                    }
                    newCompanyImages[companyImages.length] = fileId;
                    companyService.updateCompanyImages(Long.valueOf(companyId), newCompanyImages);
                }
            }
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("updateCompanyImages->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    /**
     * 上传企业图片
     *
     * @param request
     * @return
     */
    @RequestMapping("deleteCompanyImages")
    @ResponseBody
    public ResponseEntity<String> deleteCompanyImages(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            CompanyImageParam companyImageParam = JsonUtils.jsonToObject(param, CompanyImageParam.class);
            CompanyBo companyBo = companyService.findCompanyByCompanyId(companyImageParam.getCompanyId());
            Long[] companyImages = companyBo.getCompanyImages();
            int j = 0;
            for (int i = 0; i < companyImages.length; i++) {
                if (companyImages[i].equals(companyImageParam.getCompanyDeleteImageId())) {
                    j++;
                    continue;
                }
            }
            int k = 0;
            Long[] newCompanyImages = new Long[companyImages.length - j];
            for (int i = 0; i < companyImages.length; i++) {
                if (companyImages[i].equals(companyImageParam.getCompanyDeleteImageId())) {
                    continue;
                }
                newCompanyImages[k] = companyImages[i];
                k++;
            }
            data = companyService.updateCompanyImages(companyImageParam.getCompanyId(), newCompanyImages);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("deleteCompanyImages->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }
}
