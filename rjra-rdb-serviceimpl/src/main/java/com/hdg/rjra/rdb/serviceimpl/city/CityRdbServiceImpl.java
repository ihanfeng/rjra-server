package com.hdg.rjra.rdb.serviceimpl.city;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.City;
import com.hdg.rjra.rdb.service.ICityRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.city.rowmapper.CityRowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class CityRdbServiceImpl extends DaoTemplate implements ICityRdbService {

    @Override
    public City findCityByCityId(Long cityId) {
        String sql = "select * from base_city where city_id = ?";
        Object[] objects = new Object[]{cityId};
        List list = getJdbcTemplate().query(sql, objects, new CityRowMapper());
        if (list.size() > 0) {
            return (City) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<City> findCityByProcinceId(Long provinceId) {
        String sql = "select * from base_city where province_id = ?";
        return getJdbcTemplate().query(sql, new Object[]{provinceId}, new CityRowMapper());
    }
}
