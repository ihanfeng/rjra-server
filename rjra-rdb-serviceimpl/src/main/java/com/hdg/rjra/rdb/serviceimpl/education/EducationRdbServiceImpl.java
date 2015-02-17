package com.hdg.rjra.rdb.serviceimpl.education;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.Education;
import com.hdg.rjra.rdb.service.IEducationRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.education.rowmapper.EducationRowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class EducationRdbServiceImpl extends DaoTemplate implements IEducationRdbService {

    @Override
    public List<Education> findAllEducation() {
        String sql = "select * from base_education";
        return getJdbcTemplate().query(sql, new Object[]{}, new EducationRowMapper());
    }
}
