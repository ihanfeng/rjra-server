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
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.server.model.bo.work.WorkBo;
import com.hdg.rjra.server.service.WorkService;
import com.hdg.rjra.server.web.controller.param.work.WorkParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rock
 */
@RequestMapping("/work")
@Controller
public class WorkController {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(WorkController.class);

    @Autowired
    WorkService workService;

    @RequestMapping(value = "findWorkByWorkId")
    @ResponseBody
    public ResponseEntity<String> findWorkByWorkId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        WorkBo data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            data = workService.findWorkByWorkId(workParam.getWorkId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findWorkByWorkId ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findAllWorkByParamPager")
    @ResponseBody
    public ResponseEntity<String> findAllWorkByParamPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<WorkBo> data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            // 查询列表
            Integer sizeNo = workParam.getSize() == null ? CommonConstants.NUM_INT_50 : workParam
                    .getSize();
            Integer firstResult = workParam.getPage() == null ? 0 : (workParam.getPage() - 1) * sizeNo;
            Map<WorkMapping, Object> mapParam = getMapParam(workParam);
            data = workService.findAllWorkByParamPager(mapParam, firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findAllWorkByParamPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "findNearWorkByParamPager")
    @ResponseBody
    public ResponseEntity<String> findNearWorkByParamPager(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Pager<WorkBo> data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            // 查询列表
            Integer sizeNo = workParam.getSize() == null ? CommonConstants.NUM_INT_50 : workParam
                    .getSize();
            Integer firstResult = workParam.getPage() == null ? 0 : (workParam.getPage() - 1) * sizeNo;
            Map<WorkMapping, Object> mapParam = getMapParam(workParam);
            data = workService.findNearWorkByParamPager(mapParam, workParam.getWorkLongitude(), workParam.getWorkLatitude(), workParam.getWorkRaidus(), firstResult, sizeNo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("findNearWorkByParamPager ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateWork")
    @ResponseBody
    public ResponseEntity<String> updateWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            WorkBo bo = new WorkBo();
            ConversionUtils.conversion(workParam, bo);
            data = workService.updateWork(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateWork ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    @RequestMapping(value = "updateWorkStatus")
    @ResponseBody
    public ResponseEntity<String> updateWorkStatus(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Integer data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            data = workService.updateWorkStatus(workParam.getWorkId(), workParam.getWorkStatus());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("updateWorkStatus ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }


    @RequestMapping(value = "saveWork")
    @ResponseBody
    public ResponseEntity<String> saveWork(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        Long data = null;
        try {
            WorkParam workParam = JsonUtils.jsonToObject(param, WorkParam.class);
            WorkBo bo = new WorkBo();
            ConversionUtils.conversion(workParam, bo);
            data = workService.saveWork(bo);
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.getMessage());
            LOG.error("saveWork ->", e);
        }
        OutputResult outputResult = ResponseUtils.bulidOutputResult(errorType.getResponseError(), data);
        return ResponseUtils.returnJsonWithUTF8(JsonUtils.objectToJson(outputResult));
    }

    private Map<WorkMapping,Object> getMapParam(WorkParam workParam) {
        if (workParam == null) {
            return null;
        }
        else {
            Map<WorkMapping,Object> mapParam = new HashMap<WorkMapping,Object>();

            if (workParam.getWorkId() != null) {
                mapParam.put(WorkMapping.WorkId, workParam.getWorkId());
            }
            if (workParam.getCompanyId() != null) {
                mapParam.put(WorkMapping.CompanyId, workParam.getCompanyId());
            }
            if (workParam.getUserId() != null) {
                mapParam.put(WorkMapping.UserId, workParam.getUserId());
            }
            if (workParam.getCategoryLevel1Ids() != null) {
                mapParam.put(WorkMapping.CategoryLevel1Id, workParam.getCategoryLevel1Ids());
            }
            if (workParam.getCategoryLevel2Ids() != null) {
                mapParam.put(WorkMapping.CategoryLevel2Id, workParam.getCategoryLevel2Ids());
            }
            if (workParam.getCategoryLevel3Ids() != null) {
                mapParam.put(WorkMapping.CategoryLevel3Id, workParam.getCategoryLevel3Ids());
            }
            if (workParam.getWorkAreaId() != null) {
                mapParam.put(WorkMapping.WorkAreaId, workParam.getWorkAreaId());
            }
            if (workParam.getWorkCityId() != null) {
                mapParam.put(WorkMapping.WorkCityId, workParam.getWorkCityId());
            }
            if (workParam.getWorkProvinceId() != null) {
                mapParam.put(WorkMapping.WorkProvinceId, workParam.getWorkProvinceId());
            }
            if (workParam.getWorkAddress() != null) {
                mapParam.put(WorkMapping.WorkAddress, workParam.getWorkAddress());
            }
            if (workParam.getWorkDesc() != null) {
                mapParam.put(WorkMapping.WorkDesc, workParam.getWorkDesc());
            }
            if (workParam.getWorkNeedPerson() != null) {
                mapParam.put(WorkMapping.WorkNeedPerson, workParam.getWorkNeedPerson());
            }
            if (workParam.getWorkExperienceId() != null) {
                mapParam.put(WorkMapping.WorkExperienceId, workParam.getWorkExperienceId());
            }
            if (workParam.getWorkWageId() != null) {
                mapParam.put(WorkMapping.WorkWageId, workParam.getWorkWageId());
            }
            if (workParam.getWorkWelfareIds() != null) {
                mapParam.put(WorkMapping.WorkWelfareIds, workParam.getWorkWelfareIds());
            }
            if (workParam.getWorkStatus() != null) {
                mapParam.put(WorkMapping.WorkStatus, workParam.getWorkStatus());
            }
            if (workParam.getAgeId() != null) {
                mapParam.put(WorkMapping.AgeId, workParam.getAgeId());
            }
            if (workParam.getWorkGender() != null) {
                mapParam.put(WorkMapping.WorkGender, workParam.getWorkGender());
            }
            return mapParam;
        }
    }
}
