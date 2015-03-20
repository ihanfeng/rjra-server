package com.hdg.rjra.server.service;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.server.model.bo.work.WorkBo;

import java.util.Map;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface WorkService {

    public WorkBo findWorkByWorkId(Long workId);

    public Pager<WorkBo> findAllWorkByParamPager(Map<WorkMapping, Object> param, Integer firstResult, Integer sizeNo);

    public Pager<WorkBo> findNearWorkByParamPager(Map<WorkMapping, Object> param, Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo);

    public Integer updateWork(WorkBo workBo);

    public Integer updateWorkStatus(Long workId, Integer status);

    public Long saveWork(WorkBo workBo);
}
