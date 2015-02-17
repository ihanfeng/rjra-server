package com.hdg.rjra.rdb.serviceimpl.area;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.Area;
import com.hdg.rjra.rdb.service.IAreaRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.area.rowmapper.AreaRowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class AreaRdbServiceImpl extends DaoTemplate implements IAreaRdbService {

    @Override
    public Area findAreaByAreaId(Long areaId) {
        String sql = "select * from base_area where area_id = ?";
        Object[] objects = new Object[]{areaId};
        List list = getJdbcTemplate().query(sql, objects, new AreaRowMapper());
        if (list.size() > 0) {
            return (Area) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Area> findAreaByCityId(Long cityId) {
        String sql = "select * from base_area where city_id = ?";
        return getJdbcTemplate().query(sql, new Object[]{cityId}, new AreaRowMapper());
    }
}
