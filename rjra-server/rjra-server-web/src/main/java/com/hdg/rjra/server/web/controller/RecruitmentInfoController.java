package com.hdg.rjra.server.web.controller;

/**
 * Created by Rock on 2015/1/8 0008.
 */

import com.hdg.rjra.base.constants.CommonConstants;
import com.hdg.rjra.base.enumerate.RecruitmentInfoStatus;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.base.output.OutputResult;
import com.hdg.rjra.base.utils.DateUtils;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.recruitmentinfo.RecruitmentInfoBo;
import com.hdg.rjra.server.service.RecruitmentInfoService;
import com.hdg.rjra.server.web.controller.param.LocationParam;
import com.hdg.rjra.server.web.controller.param.recruitmentinfo.RecruitmentInfoParam;
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
import java.util.Date;
import java.util.List;

/**
 * @author Rock
 */
@RequestMapping("/info")
@Controller
public class RecruitmentInfoController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(RecruitmentInfoController.class);

    @Autowired
    RecruitmentInfoService recruitmentInfoService;

    @RequestMapping(value = "findAllRecruitmentInfoByCompanyId")
    @ResponseBody
    public ResponseEntity<String> findAllRecruitmentInfoByCompanyId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        List<RecruitmentInfoBo> data = null;
        try {
            RecruitmentInfoParam findRecruitmentInfoParam = JsonUtils.jsonToObject(param, RecruitmentInfoParam.class);
            data = recruitmentInfoService.findAllRecruitmentInfoByCompanyId(findRecruitmentInfoParam.getCompanyId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllRecruitmentInfoByCompanyId->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findAllRecruitmentInfoByCompanyIdPager")
    @ResponseBody
    public ResponseEntity<String> findAllRecruitmentInfoByCompanyIdPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<RecruitmentInfoBo> data = null;
        try {
            RecruitmentInfoParam findRecruitmentInfoParam = JsonUtils.jsonToObject(param, RecruitmentInfoParam.class);
            // 查询列表
            Integer sizeNo = findRecruitmentInfoParam.getSize() == null ? CommonConstants.NUM_INT_50 : findRecruitmentInfoParam
                    .getSize();
            Integer firstResult = findRecruitmentInfoParam.getPage() == null ? 0 : (findRecruitmentInfoParam.getPage() - 1) * sizeNo;
            data = recruitmentInfoService.findAllRecruitmentInfoByCompanyIdPager(findRecruitmentInfoParam.getCompanyId(), findRecruitmentInfoParam.getInfoStatus(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllRecruitmentInfoByCompanyIdPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findRecruitmentInfoByRecruitmentInfoId")
    @ResponseBody
    public ResponseEntity<String> findRecruitmentInfoByRecruitmentInfoId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        RecruitmentInfoBo data = null;
        try {
            RecruitmentInfoParam findRecruitmentInfoParam = JsonUtils.jsonToObject(param, RecruitmentInfoParam.class);
            data = recruitmentInfoService.findRecruitmentInfoByRecruitmentInfoId(findRecruitmentInfoParam.getInfoId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findRecruitmentInfoByRecruitmentInfoId->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findAllRecruitmentInfo")
    @ResponseBody
    public ResponseEntity<String> findAllRecruitmentInfo(HttpServletRequest request) {
        ErrorType errorType = null;
        List<RecruitmentInfoBo> data = null;
        try {
            data = recruitmentInfoService.findAllRecruitmentInfo();
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllRecruitmentInfo->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findAllRecruitmentInfoPager")
    @ResponseBody
    public ResponseEntity<String> findAllRecruitmentInfoPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<RecruitmentInfoBo> data = null;
        try {
            RecruitmentInfoParam findRecruitmentInfoParam = JsonUtils.jsonToObject(param, RecruitmentInfoParam.class);
            // 查询列表
            Integer sizeNo = findRecruitmentInfoParam.getSize() == null ? CommonConstants.NUM_INT_50 : findRecruitmentInfoParam
                    .getSize();
            Integer firstResult = findRecruitmentInfoParam.getPage() == null ? 0 : (findRecruitmentInfoParam.getPage() - 1) * sizeNo;
            data = recruitmentInfoService.findAllRecruitmentInfoPager(findRecruitmentInfoParam.getInfoStatus(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllRecruitmentInfoPager->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "deleteRecruitmentInfo")
    @ResponseBody
    public ResponseEntity<String> deleteRecruitmentInfo(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            RecruitmentInfoParam findRecruitmentInfoParam = JsonUtils.jsonToObject(param, RecruitmentInfoParam.class);
            data = recruitmentInfoService.updateRecruitmentInfoStatus(findRecruitmentInfoParam.getInfoId(), RecruitmentInfoStatus.Delete.getCode());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("deleteRecruitmentInfo->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "saveRecruitmentInfo")
    @ResponseBody
    public ResponseEntity<String> saveRecruitmentInfo(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Long data = null;
        try {
            RecruitmentInfoParam recruitmentInfoParam = JsonUtils.jsonToObject(param, RecruitmentInfoParam.class);
            recruitmentInfoService.saveRecruitmentInfo(votobo(recruitmentInfoParam));
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveRecruitmentInfo->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateRecruitmentInfo")
    @ResponseBody
    public ResponseEntity<String> updateRecruitmentInfo(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
            RecruitmentInfoParam recruitmentInfoParam = JsonUtils.jsonToObject(param, RecruitmentInfoParam.class);
            recruitmentInfoService.updateRecruitmentInfo(votobo(recruitmentInfoParam));
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateRecruitmentInfo->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateRecruitmentInfoImage")
    @ResponseBody
    public ResponseEntity<String> updateRecruitmentInfoImage(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Integer data = null;
        try {
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateRecruitmentInfoImage->", e);
        }

        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    private RecruitmentInfoBo votobo(RecruitmentInfoParam param) {
        if (param == null) {
            return null;
        }
        //todo 为验证通过后续需要教研正确数据
        RecruitmentInfoBo bo = new RecruitmentInfoBo();
        bo.setInfoId(param.getInfoId());
        bo.setCompanyId(param.getCompanyId());
        bo.setCategoryLevel1Id(param.getCategoryLevel1Id());
        bo.setCategoryLevel2Id(param.getCategoryLevel2Id());
        bo.setCategoryLevel3Id(param.getCategoryLevel3Id());
        bo.setAreaId(param.getAreaId());
        bo.setCityId(param.getCityId());
        bo.setProvinceId(param.getProvinceId());
        bo.setWageId(param.getWageId());
        bo.setExperienceId(param.getExperienceId());
        bo.setEducationId(param.getEducationId());
        bo.setInfoWelfare(param.getInfoWelfare());
        bo.setInfoType(param.getInfoType());
        bo.setInfoImageFiles(null);
        bo.setInfoVedioFile(null);
        bo.setInfoVedioContent(null);
        bo.setInfoWorkAddress(param.getInfoWorkAddress());
        bo.setInfoNeedPerson(param.getInfoNeedPerson());
        bo.setInfoUpperAgeDemand(param.getInfoUpperAgeDemand());
        bo.setInfoLowerAgeDemand(param.getInfoLowerAgeDemand());
        bo.setInfoWorkLongitude(param.getInfoWorkLongitude());
        bo.setInfoWorkLatitude(param.getInfoWorkLatitude());
        bo.setInfoStatus(param.getInfoStatus());
        bo.setInfoCreateTime(DateUtils.getTimeString(new Date(), CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS, "GMT+8"));
        bo.setInfoUpdateTime(DateUtils.getTimeString(new Date(), CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS, "GMT+8"));
        bo.setInfoDesc(param.getInfoDesc());
//        bo.setExamineTime(null);
//        bo.setExamineStatus(ExamineStatus.Pending.getCode());
//        bo.setExamineResultInfo("");
        return bo;
    }

    @RequestMapping(value = "findNearRecruitmentInfoByLocationPager")
    @ResponseBody
    public ResponseEntity<String> findNearRecruitmentInfoByLocationPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = null;
        Pager<RecruitmentInfoBo> data = null;
        try {
            LocationParam locationParam = JsonUtils.jsonToObject(param, LocationParam.class);
            // 查询列表
            Integer sizeNo = locationParam.getSize() == null ? CommonConstants.NUM_INT_50 : locationParam
                    .getSize();
            Integer firstResult = locationParam.getPage() == null ? 0 : (locationParam.getPage() - 1) * sizeNo;
            data = recruitmentInfoService.findNearRecruitmentInfoByLocationPager(locationParam.getLongitude(), locationParam.getLatitude(), locationParam.getRaidus(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findNearRecruitmentInfoByLocationPager->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType, data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }
}
