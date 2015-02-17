package com.hdg.rjra.rdb.serviceimpl.province;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.Province;
import com.hdg.rjra.rdb.service.IProvinceRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.province.rowmapper.ProvinceRowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class ProvinceRdbServiceImpl extends DaoTemplate implements IProvinceRdbService {

    @Override
    public Province findProvinceByProvinceId(Long provinceId) {
        String sql = "select * from base_province where province_id = ?";
        Object[] objects = new Object[]{provinceId};
        List list = getJdbcTemplate().query(sql, objects, new ProvinceRowMapper());
        if (list.size() > 0) {
            return (Province) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Province> findAllProvince() {
        String sql = "select * from base_province";
        return getJdbcTemplate().query(sql, new Object[]{}, new ProvinceRowMapper());
    }
}
