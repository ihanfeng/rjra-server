package com.hdg.rjra.server.serviceimpl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hdg.common.utils.ConversionUtils;
import com.hdg.rjra.base.enumerate.GeoStatus;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.domain.*;
import com.hdg.rjra.rdb.domain.enumerate.WorkMapping;
import com.hdg.rjra.rdb.service.*;
import com.hdg.rjra.server.model.bo.geo.GeocoderSearchResponse;
import com.hdg.rjra.server.model.bo.work.WorkBo;
import com.hdg.rjra.server.service.GeoService;
import com.hdg.rjra.server.service.WorkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.ArrayList;
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
    IWorkRdbService workRdbService;
    @Autowired
    ICompanyRdbService companyRdbService;

    @Autowired
    IProvinceRdbService provinceRdbService;
    @Autowired
    ICityRdbService cityRdbService;
    @Autowired
    IAreaRdbService areaRdbService;

    @Autowired
    GeoService geoService;

    @Override
    public WorkBo findWorkByWorkId(Long workId) {
        Work work = workRdbService.findWorkByWorkId(workId);
        return getWorkBo(work);
    }
    private WorkBo getWorkBo(Work work) {
        if (work == null) {
            return null;
        }
        WorkBo bo = new WorkBo();
        ConversionUtils.conversion(work, bo);
        return bo;
    }
    @Override
    public Pager<WorkBo> findAllWorkByParamPager(Map<WorkMapping, Object> param, Integer firstResult, Integer sizeNo) {
        Pager<Work> workPager =  workRdbService.findAllWorkByParamPager(param, new Integer[]{WorkStatus.Active.getCode(), WorkStatus.Pause.getCode()}, firstResult, sizeNo);
        Pager<WorkBo> workBoPager = new Pager<WorkBo>();
        List<WorkBo> workBoList = new ArrayList<WorkBo>();
        for (Work work : workPager.getResultList()) {
            workBoList.add(getWorkBo(work));
        }
        workBoPager.setResultList(workBoList);
        workBoPager.setTotalSize(workPager.getTotalSize());
        return workBoPager;
    }

    @Override
    public Pager<WorkBo> findNearWorkByParamPager(Map<WorkMapping, Object> param, Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo) {
        Pager<Work> workPager =  workRdbService.findNearWorkByParamPager(param, lng, lat, raidus, new Integer[]{WorkStatus.Active.getCode()}, firstResult, sizeNo);
        Pager<WorkBo> workBoPager = new Pager<WorkBo>();
        List<WorkBo> workBoList = new ArrayList<WorkBo>();
        for (Work work : workPager.getResultList()) {
            workBoList.add(getWorkBo(work));
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
        return workRdbService.updateWork(work);
    }

    @Override
    public Integer updateWorkStatus(Long workId, Integer status) {
        return workRdbService.updateWorkStatus(workId, status);
    }

    @Override
    public Long saveWork(WorkBo workBo) {
        Work work = new Work();
        ConversionUtils.conversion(workBo, work);
        getGeoLocation(work);
        Company company = companyRdbService.findCompanyByCompanyId(work.getCompanyId());
        work.setCompanyName(company.getCompanyName());
        return workRdbService.saveWork(work);
    }

    private void getGeoLocation(Work work) {
        if (work.getWorkLongitude() == null || work.getWorkLatitude() == null) {
            Province province = provinceRdbService.findProvinceByProvinceId(work.getWorkProvinceId());
            City city = cityRdbService.findCityByCityId(work.getWorkCityId());
            Area area = areaRdbService.findAreaByAreaId(work.getWorkAreaId());

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
