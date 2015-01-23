package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Province;

import java.io.Serializable;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IProvinceProxy extends Serializable {

    public Province findProvinceByProvinceId(Long provinceId);

    public Province findAllProvince();
}
