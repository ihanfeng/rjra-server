package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.coordinate.GeocoderSearchResponse;

/**
 * Created by Rock on 2015/1/17 0017.
 */
public interface CoordinateService {

    /**
     * http://api.map.baidu.com/geocoder/v2/?address=%E5%8C%97%E4%BA%AC&output=json&ak=vTcHmi5g6WG06zgf958zdGpH&callback=showLocation
     * @param address
     * @return
     */
    public GeocoderSearchResponse getCoordinate(String address);
}
