package com.hdg.rjra.rdb.serviceimpl.welfare;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.Welfare;
import com.hdg.rjra.rdb.service.IWelfareRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.welfare.rowmapper.WelfareRowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class WelfareRdbServiceImpl extends DaoTemplate implements IWelfareRdbService {

    @Override
    public List<Welfare> findAllWelfare() {
        String sql = "select * from base_welfare";
        return getJdbcTemplate().query(sql, new Object[]{}, new WelfareRowMapper());
    }
}
