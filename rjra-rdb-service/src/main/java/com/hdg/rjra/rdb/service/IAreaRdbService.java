package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.Area;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IAreaRdbService extends Serializable {

    public Area findAreaByAreaId(Long areaId);

    public List<Area> findAreaByCityId(Long cityId);
}
