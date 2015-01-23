package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.base.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.base.utils.HttpRequestUtils;
import com.hdg.rjra.base.utils.JsonUtils;
import com.hdg.rjra.server.model.bo.coordinate.GeocoderSearchResponse;
import com.hdg.rjra.server.service.CoordinateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Rock on 2015/1/10 0010.
 */
@Service
public class CoordinateServiceImpl implements CoordinateService {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(CoordinateServiceImpl.class);

    @Override
    public GeocoderSearchResponse getCoordinate(String address) {
        String baiduRequest = CustomizedPropertyConfigurer.getContextPropertyForString("geo_api") + address;

        String requestJson = HttpRequestUtils.sendGet(baiduRequest);
        System.out.println(requestJson);
        GeocoderSearchResponse geocoderSearchResponse = JsonUtils.jsonToObject(requestJson, GeocoderSearchResponse.class);
        return geocoderSearchResponse;
    }
}
