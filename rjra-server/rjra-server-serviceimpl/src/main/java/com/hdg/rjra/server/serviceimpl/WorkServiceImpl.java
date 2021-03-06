package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.ConversionUtils;
import com.hdg.common.utils.CoordinateUtils;
import com.hdg.rjra.base.enumerate.GeoStatus;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.*;
import com.hdg.rjra.rdb.proxy.domain.*;
import com.hdg.rjra.rdb.proxy.domain.enumerate.WorkMapping;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.geo.GeocoderSearchResponse;
import com.hdg.rjra.server.model.bo.work.WorkBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.GeoService;
import com.hdg.rjra.server.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Rock on 2015/1/28 0028.
 */

@Service
public class WorkServiceImpl implements WorkService {
    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(WorkServiceImpl.class);

    @Autowired
    IUserCollectWorkProxy userCollectWorkProxy;

    @Autowired
    IUserApplyWorkProxy userApplyWorkProxy;

    @Autowired
    IWorkProxy workProxy;
    @Autowired
    CompanyService companyService;

    @Autowired
    IProvinceProxy provinceProxy;
    @Autowired
    ICityProxy cityProxy;
    @Autowired
    IAreaProxy areaProxy;

    @Autowired
    GeoService geoService;

    @Override
    public WorkBo findWorkByWorkId(Long workId) {
        Work work = workProxy.findWorkByWorkId(workId);
        return getWorkBo(work);
    }

    private WorkBo getWorkBo(Work work) {
        if (work == null) {
            return null;
        }
        WorkBo bo = new WorkBo();
        ConversionUtils.conversion(work, bo);
        if (work.getCompanyId() != null) {
            CompanyBo companyBo = companyService.findCompanyByCompanyId(work.getCompanyId());
            bo.setCompanyDetail(companyBo);
        }
        return bo;
    }

    private WorkBo getWorkBo(Work work, Object userId) {
        if (work == null) {
            return null;
        }
        WorkBo bo = new WorkBo();
        ConversionUtils.conversion(work, bo);
        if (work.getCompanyId() != null) {
            CompanyBo companyBo = companyService.findCompanyByCompanyId(work.getCompanyId());
            bo.setCompanyDetail(companyBo);
        }
        if(userId != null){
            UserCollectWork userCollectWork = userCollectWorkProxy.findUserCollectWorkByUserIdAndWorkId((Long) userId, work.getWorkId());
            if(userCollectWork != null){
                bo.setCollectWork(true);
            }
            UserApplyWork userApplyWork = userApplyWorkProxy.findUserApplyWorkByUserIdAndWorkId((Long) userId, work.getWorkId());
            if(userApplyWork != null){
                bo.setApplyWork(true);
            }
        }
        return bo;
    }

    @Override
    public Pager<WorkBo> findAllWorkByParamPager(Map<WorkMapping, Object> param, Integer firstResult, Integer sizeNo) {
        Pager<Work> workPager = workProxy.findAllWorkByParamPager(param, new Integer[]{WorkStatus.Active.getCode(), WorkStatus.Pause.getCode()}, firstResult, sizeNo);
        Pager<WorkBo> workBoPager = new Pager<WorkBo>();
        List<WorkBo> workBoList = new ArrayList<WorkBo>();
        for (Work work : workPager.getResultList()) {
            workBoList.add(getWorkBo(work, param.get(WorkMapping.WorkId)));
        }
        workBoPager.setResultList(workBoList);
        workBoPager.setTotalSize(workPager.getTotalSize());
        return workBoPager;
    }

    @Override
    public Pager<WorkBo> findNearWorkByParamPager(Map<WorkMapping, Object> param, Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo) {
        Pager<Work> workPager = workProxy.findNearWorkByParamPager(param, lng, lat, raidus, new Integer[]{WorkStatus.Active.getCode(), WorkStatus.Pause.getCode()}, firstResult, sizeNo);
        Pager<WorkBo> workBoPager = new Pager<WorkBo>();
        List<WorkBo> workBoList = new ArrayList<WorkBo>();
        for (Work work : workPager.getResultList()) {
            WorkBo workBo = getWorkBo(work);
            workBoList.add(workBo);
        }
        workBoPager.setResultList(workBoList);
        workBoPager.setTotalSize(workPager.getTotalSize());
        return workBoPager;
    }

    @Override
    public Integer updateWork(WorkBo workBo) {
        Work work = new Work();
        ConversionUtils.conversion(workBo, work);
        getGeoLocation(work);
        work.setWorkUpdateTime(new Date());
        return workProxy.updateWork(work);
    }

    @Override
    public Integer updateWorkStatus(Long workId, Integer status) {
        return workProxy.updateWorkStatus(workId, status);
    }

    @Override
    public Long saveWork(WorkBo workBo) {
        Work work = new Work();
        ConversionUtils.conversion(workBo, work);
        getGeoLocation(work);
        CompanyBo companyBo = companyService.findCompanyByCompanyId(work.getCompanyId());
        work.setCompanyName(companyBo.getCompanyName());
        return workProxy.saveWork(work);
    }

    @Override
    public Pager<WorkBo> findAllWorkByConditionPager(WorkBo workBo, Integer firstResult, Integer sizeNo) {
        Work work = new Work();
        ConversionUtils.conversion(workBo, work);
        Pager<Work> workPager = workProxy.findAllWorkByConditionPager(work, firstResult, sizeNo);
        Pager<WorkBo> workBoPager = new Pager<WorkBo>();
        List<WorkBo> workBoList = new ArrayList<WorkBo>();
        for (Work outWork : workPager.getResultList()) {
            WorkBo outWorkBo = getWorkBo(outWork);
            workBoList.add(outWorkBo);
        }
        workBoPager.setResultList(workBoList);
        workBoPager.setTotalSize(workPager.getTotalSize());
        return workBoPager;
    }

    private void getGeoLocation(Work work) {
        if (work.getWorkLongitude() == null || work.getWorkLatitude() == null) {
            Province province = provinceProxy.findProvinceByProvinceId(work.getWorkProvinceId());
            City city = cityProxy.findCityByCityId(work.getWorkCityId());
            Area area = areaProxy.findAreaByAreaId(work.getWorkAreaId());

            StringBuffer address = new StringBuffer();
            if (province != null) {
                address.append(province.getProvinceName());
            }
            if (city != null) {
                address.append(city.getCityName());
            }
            if (area != null) {
                address.append(area.getAreaName());
            }
            if (work.getWorkAddress() != null) {
                address.append(work.getWorkAddress());
                String sendAddress = address.toString();
                LOG.info("fetch address >>>>> " + sendAddress);
                GeocoderSearchResponse geo = geoService.getCoordinate(sendAddress);
                if (geo.getStatus().equals(GeoStatus.Success.getCode())) {
                    work.setWorkLatitude(geo.getResult().getLocation().getLat());
                    work.setWorkLongitude(geo.getResult().getLocation().getLng());
                } else {
                    LOG.error(geo.getMessage() + " >>>>>> " + address);
                }
            }
        }
    }
}
