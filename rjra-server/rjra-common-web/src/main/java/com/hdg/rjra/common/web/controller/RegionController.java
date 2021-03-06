package com.hdg.rjra.common.web.controller;

import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.ResponseUtils;
import com.hdg.rjra.base.error.ErrorType;
import com.hdg.rjra.rdb.proxy.domain.Area;
import com.hdg.rjra.rdb.proxy.domain.City;
import com.hdg.rjra.rdb.proxy.domain.Province;
import com.hdg.rjra.server.model.bo.region.AreaBo;
import com.hdg.rjra.server.model.bo.region.CityBo;
import com.hdg.rjra.server.model.bo.region.ProvinceBo;
import com.hdg.rjra.server.model.param.region.RegionParam;
import com.hdg.rjra.server.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2015/3/23.
 */
@RequestMapping("/region")
@Controller
public class RegionController {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    RegionService regionService;

    @RequestMapping(value = "findAreaByAreaId")
    @ResponseBody
    public ResponseEntity<String> findAreaByAreaId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        AreaBo data = null;
        try {
            RegionParam regionParam = JsonUtils.jsonToObject(param, RegionParam.class);
            data = regionService.findAreaByAreaId(regionParam.getAreaId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findAreaByAreaId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findAreaByCityId")
    @ResponseBody
    public ResponseEntity<String> findAreaByCityId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<AreaBo> data = null;
        try {
            RegionParam regionParam = JsonUtils.jsonToObject(param, RegionParam.class);
            data = regionService.findAreaByCityId(regionParam.getCityId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findAreaByCityId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findCityByCityId")
    @ResponseBody
    public ResponseEntity<String> findCityByCityId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        CityBo data = null;
        try {
            RegionParam regionParam = JsonUtils.jsonToObject(param, RegionParam.class);
            data = regionService.findCityByCityId(regionParam.getCityId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findCityByCityId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findCityByProvinceId")
    @ResponseBody
    public ResponseEntity<String> findCityByProvinceId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<CityBo> data = null;
        try {
            RegionParam regionParam = JsonUtils.jsonToObject(param, RegionParam.class);
            data = regionService.findCityByProvinceId(regionParam.getProvinceId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findCityByProvinceId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findProvinceByProvinceId")
    @ResponseBody
    public ResponseEntity<String> findProvinceByProvinceId(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        ProvinceBo data = null;
        try {
            RegionParam regionParam = JsonUtils.jsonToObject(param, RegionParam.class);
            data = regionService.findProvinceByProvinceId(regionParam.getProvinceId());
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findProvinceByProvinceId->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }

    @RequestMapping(value = "findAllProvince")
    @ResponseBody
    public ResponseEntity<String> findAllProvince(HttpServletRequest request, @RequestParam(value = "param", required = true) String param) {
        ErrorType errorType = ErrorType.DEFFAULT;
        List<ProvinceBo> data = null;
        try {
            data = regionService.findAllProvince();
        } catch (Exception e) {
            errorType = ErrorType.UNKNOW_ERROR;
            errorType.setMessage(e.toString());
            LOG.error("findAllProvince->", e);
        }
        return ResponseUtils.returnResponseEntity(errorType.getResponseError(), data);
    }
}
