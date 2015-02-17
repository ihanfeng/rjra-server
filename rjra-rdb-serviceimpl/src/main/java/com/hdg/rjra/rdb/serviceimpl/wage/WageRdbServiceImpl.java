package com.hdg.rjra.rdb.serviceimpl.wage;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.Wage;
import com.hdg.rjra.rdb.service.IWageRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.wage.rowmapper.WageRowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class WageRdbServiceImpl extends DaoTemplate implements IWageRdbService {

    @Override
    public List<Wage> findAllWage() {
        String sql = "select * from base_wage";
        return getJdbcTemplate().query(sql, new Object[]{}, new WageRowMapper());
    }
}
