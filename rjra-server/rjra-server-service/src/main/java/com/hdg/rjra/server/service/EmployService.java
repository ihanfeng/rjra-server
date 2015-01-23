package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.server.model.bo.employ.EmployBo;

import java.util.Date;

/**
 * Created by Rock on 2015/1/13 0013.
 */
public interface EmployService {

    public EmployBo findEmployByEmployId(Long employId);

    public Pager<EmployBo> findEmployByUserIdPager(Long userId, Integer firstResult, Integer sizeNo);

    public Pager<EmployBo> findEmployByCompanyIdPager(Long companyId, Integer firstResult, Integer sizeNo);

    public Long saveEmploy(EmployBo employ);

    public Integer updateEmployResult(Long employId, Integer employResultStatus, String employResultInfo);

    public Integer updateEmployEntryPlanTime(Long employId, Date planTime);

    public Integer updateEmployReport(Long employId, Date reportTime, Integer reportStatus, String reportInfo);

    public Integer deleteUserEmploy(Long employId);

    public Integer deleteCompanyEmploy(Long employId);
}
