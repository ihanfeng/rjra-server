package com.hdg.rjra.rdb.proxy.daoproxy;

import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Work;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/3 0003.
 */
public interface IWorkProxy extends Serializable {

    public Work findWorkByWorkId(Long workId);

    public Pager<Work> findAllWorkByParamPager(Map<WorkMapping, Object> param, Integer[] status, Integer firstResult, Integer sizeNo);

    public Pager<Work> findNearWorkByParamPager(Map<WorkMapping, Object> param, Double lng, Double lat, Integer raidus, Integer[] status, Integer firstResult, Integer sizeNo);

    public Integer updateWork(Work work);

    public Integer updateWorkStatus(Long workId, Integer status);

    public Long saveWork(Work work);

    public List<Long> findWorkIdsByUserId(Long userId);

    public Pager<Work> findAllWorkByConditionPager(Work work, Integer firstResult, Integer sizeNo);

    public List<Long> batchSaveWork(List<Work> workList);

    public Integer deleteWork(List<Long> workIds);
}
