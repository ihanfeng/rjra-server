package com.hdg.rjra.rdb.serviceimpl.workexperience;

import com.alibaba.dubbo.config.annotation.Service;
import com.hdg.rjra.rdb.domain.WorkExperience;
import com.hdg.rjra.rdb.service.IWorkExperienceRdbService;
import com.hdg.rjra.rdb.serviceimpl.DaoTemplate;
import com.hdg.rjra.rdb.serviceimpl.workexperience.rowmapper.WorkExperienceRowMapper;

import java.util.List;

/**
 * Created by Rock on 2015/1/3 0003.
 */
@Service
public class WorkExperienceRdbServiceImpl extends DaoTemplate implements IWorkExperienceRdbService {

    @Override
    public List<WorkExperience> findAllWorkExperience() {
        String sql = "select * from base_work_experience";
        return getJdbcTemplate().query(sql, new Object[]{}, new WorkExperienceRowMapper());
    }
}
