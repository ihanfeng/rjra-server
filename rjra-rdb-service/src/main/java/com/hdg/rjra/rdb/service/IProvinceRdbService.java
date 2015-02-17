package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.Province;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IProvinceRdbService extends Serializable {

    public Province findProvinceByProvinceId(Long provinceId);

    public List<Province> findAllProvince();
}
