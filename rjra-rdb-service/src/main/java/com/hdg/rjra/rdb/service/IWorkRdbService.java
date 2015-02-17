package com.hdg.rjra.rdb.service;

import com.hdg.rjra.rdb.domain.Pager;
import com.hdg.rjra.rdb.domain.Work;
import com.hdg.rjra.rdb.domain.enumerate.WorkMapping;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IWorkRdbService extends Serializable {

    public Work findWorkByWorkId(Long workId);

    public Pager<Work> findAllWorkByParamPager(Map<WorkMapping, Object> param, Integer[] status, Integer firstResult, Integer sizeNo);

    public Pager<Work> findNearWorkByParamPager(Map<WorkMapping, Object> param, Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo);

    public Integer updateWork(Work work);

    public Integer updateWorkStatus(Long workId, Integer status);

    public Long saveWork(Work work);
}
