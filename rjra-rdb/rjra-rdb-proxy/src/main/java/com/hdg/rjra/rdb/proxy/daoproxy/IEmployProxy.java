package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Employ;
import com.hdg.rjra.rdb.proxy.domain.Pager;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IEmployProxy extends Serializable {

    public Employ findEmployByEmployId(Long employId);

    public Pager<Employ> findEmployByUserIdPager(Long userId, Integer employUserStatus, Integer firstResult, Integer sizeNo);

    public Pager<Employ> findEmployByCompanyIdPager(Long companyId, Integer employCompanyStatus, Integer firstResult, Integer sizeNo);

    public Long saveEmploy(Employ employ);

    public Integer updateEmployResult(Long employId, Integer employResultStatus, String employResultInfo);

    public Integer updateEmployEntryPlanTime(Long employId, Date planTime);

    public Integer updateEmployReport(Long employId, Date reportTime, Integer reportStatus, String reportInfo);

    public Integer updateEmployUserStatus(Long employId, Integer status);

    public Integer updateEmployCompanyStatus(Long employId, Integer status);

}
