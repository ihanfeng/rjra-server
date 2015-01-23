package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.base.enumerate.GeoStatus;
import com.hdg.rjra.base.enumerate.RecruitmentInfoStatus;
import com.hdg.rjra.base.utils.ConversionUtils;
import com.hdg.rjra.rdb.proxy.daoproxy.IAreaProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.ICityProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IProvinceProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IRecruitmentInfoProxy;
import com.hdg.rjra.rdb.proxy.domain.*;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.coordinate.GeocoderSearchResponse;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import com.hdg.rjra.server.model.bo.recruitmentinfo.RecruitmentInfoBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.CoordinateService;
import com.hdg.rjra.server.service.FileService;
import com.hdg.rjra.server.service.RecruitmentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rock on 2015/1/2 0002.
 */
@Service
public class RecruitmentInfoServiceImpl implements RecruitmentInfoService {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(RecruitmentInfoServiceImpl.class);
    @Autowired
    private IRecruitmentInfoProxy recruitmentInfoProxy;
    @Autowired
    private CompanyService companyService;
    @Autowired
    IProvinceProxy provinceProxy;
    @Autowired
    ICityProxy cityProxy;
    @Autowired
    IAreaProxy areaProxy;

    @Autowired
    FileService fileService;

    @Autowired
    CoordinateService coordinateService;


    @Override
    public List<RecruitmentInfoBo> findAllRecruitmentInfoByCompanyId(Long companyId) {
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoProxy.findAllRecruitmentInfoByCompanyId(companyId, new Integer[]{RecruitmentInfoStatus.Active.getCode(), RecruitmentInfoStatus.Pause.getCode()});
        return getRecruitmentInfoBoDetail(recruitmentInfoList, companyId);
    }

    private List<RecruitmentInfoBo> getRecruitmentInfoBoDetail(List<RecruitmentInfo> recruitmentInfoList) {
        return getRecruitmentInfoBoDetail(recruitmentInfoList, null);
    }

    private List<RecruitmentInfoBo> getRecruitmentInfoBoDetail(List<RecruitmentInfo> recruitmentInfoList, Long company) {
        List<RecruitmentInfoBo> recruitmentInfoBoList = new ArrayList<RecruitmentInfoBo>();
        Long tempCompany = -1L;
        if (null != company) {
            tempCompany = company;
        }
        for (RecruitmentInfo info : recruitmentInfoList) {
            Long[] imageInfoIds = info.getInfoImageFiles();
            List<AccountFileBo> imageInfoBos = new ArrayList<AccountFileBo>();
            if (imageInfoIds != null && imageInfoIds.length > 0) {
                imageInfoBos = fileService.findAccountFileByIds(imageInfoIds);
            }
            Long vedioFileId = info.getInfoVedioFile();
            AccountFileBo vedioFile = new AccountFileBo();
            if (vedioFileId != null) {
                List<AccountFileBo> vedioFileList = fileService.findAccountFileByIds(new Long[]{vedioFileId});
                if (vedioFileList != null && vedioFileList.size() > 0) {
                    vedioFile = vedioFileList.get(0);
                }
            }
            RecruitmentInfoBo bo = new RecruitmentInfoBo();
            bo.setInfoImageFilesDetail(imageInfoBos);
            bo.setInfoVedioFileDetail(vedioFile);
            if (!tempCompany.equals(info.getCompanyId())) {
                CompanyBo companyInfo = companyService.findCompanyByCompanyId(company);
                bo.setCompanyDetail(companyInfo);
                tempCompany = info.getCompanyId();
            }
            ConversionUtils.conversion(info, bo);
            recruitmentInfoBoList.add(bo);
        }

        return recruitmentInfoBoList;
    }

    private RecruitmentInfoBo getRecruitmentInfoBoDetail(RecruitmentInfo info) {
        if (info == null) {
            return null;
        }
        Long[] imageInfoIds = info.getInfoImageFiles();
        List<AccountFileBo> imageInfoBos = new ArrayList<AccountFileBo>();
        if (imageInfoIds != null && imageInfoIds.length > 0) {
            imageInfoBos = fileService.findAccountFileByIds(imageInfoIds);
        }
        Long vedioFileId = info.getInfoVedioFile();
        AccountFileBo vedioFile = new AccountFileBo();
        if (vedioFileId != null) {
            List<AccountFileBo> vedioFileList = fileService.findAccountFileByIds(new Long[]{vedioFileId});
            if (vedioFileList != null && vedioFileList.size() > 0) {
                vedioFile = vedioFileList.get(0);
            }
        }
        Long company = info.getCompanyId();
        CompanyBo companyInfo = companyService.findCompanyByCompanyId(company);
        RecruitmentInfoBo bo = new RecruitmentInfoBo();
        bo.setInfoImageFilesDetail(imageInfoBos);
        bo.setInfoVedioFileDetail(vedioFile);
        bo.setCompanyDetail(companyInfo);
        ConversionUtils.conversion(info, bo);
        return bo;
    }

    @Override
    public Pager<RecruitmentInfoBo> findAllRecruitmentInfoByCompanyIdPager(Long companyId, Integer status, Integer firstResult, Integer sizeNo) {
        Pager<RecruitmentInfo> recruitmentInfoList = recruitmentInfoProxy.findAllRecruitmentInfoByCompanyIdPager(companyId, new Integer[]{status}, firstResult, sizeNo);
        Pager<RecruitmentInfoBo> recruitmentInfoBoListPager = new Pager<RecruitmentInfoBo>();
        List<RecruitmentInfoBo> recruitmentInfoBoList = new ArrayList<RecruitmentInfoBo>();
        for (RecruitmentInfo info : recruitmentInfoList.getResultList()) {
            RecruitmentInfoBo bo = getRecruitmentInfoBoDetail(info);
            recruitmentInfoBoList.add(bo);
        }
        recruitmentInfoBoListPager.setResultList(recruitmentInfoBoList);
        recruitmentInfoBoListPager.setTotalSize(recruitmentInfoList.getTotalSize());
        return recruitmentInfoBoListPager;
    }

    @Override
    public RecruitmentInfoBo findRecruitmentInfoByRecruitmentInfoId(Long recruitmentInfoId) {
        RecruitmentInfo recruitmentInfo = recruitmentInfoProxy.findRecruitmentInfoByRecruitmentInfoId(recruitmentInfoId);
        RecruitmentInfoBo bo = getRecruitmentInfoBoDetail(recruitmentInfo);
        return bo;
    }

    @Override
    public List<RecruitmentInfoBo> findAllRecruitmentInfo() {
        List<RecruitmentInfo> recruitmentInfoList = recruitmentInfoProxy.findAllRecruitmentInfo(new Integer[]{RecruitmentInfoStatus.Active.getCode(), RecruitmentInfoStatus.Pause.getCode()});
        List<RecruitmentInfoBo> recruitmentInfoBoList = new ArrayList<RecruitmentInfoBo>();
        for (RecruitmentInfo info : recruitmentInfoList) {
            RecruitmentInfoBo bo = getRecruitmentInfoBoDetail(info);
            recruitmentInfoBoList.add(bo);
        }
        return recruitmentInfoBoList;
    }

    @Override
    public Pager<RecruitmentInfoBo> findAllRecruitmentInfoPager(Integer status, Integer firstResult, Integer sizeNo) {
        Pager<RecruitmentInfo> recruitmentInfoList = recruitmentInfoProxy.findAllRecruitmentInfoPager(new Integer[]{status}, firstResult, sizeNo);
        Pager<RecruitmentInfoBo> recruitmentInfoBoListPager = new Pager<RecruitmentInfoBo>();
        List<RecruitmentInfoBo> recruitmentInfoBoList = new ArrayList<RecruitmentInfoBo>();
        for (RecruitmentInfo info : recruitmentInfoList.getResultList()) {
            RecruitmentInfoBo bo = getRecruitmentInfoBoDetail(info);
            recruitmentInfoBoList.add(bo);
        }
        recruitmentInfoBoListPager.setResultList(recruitmentInfoBoList);
        recruitmentInfoBoListPager.setTotalSize(recruitmentInfoList.getTotalSize());
        return recruitmentInfoBoListPager;
    }

    @Override
    public Integer updateRecruitmentInfoStatus(Long recruitmentInfoId, Integer status) {
        return recruitmentInfoProxy.updateRecruitmentInfoStatus(recruitmentInfoId, status);
    }

    @Override
    public Long saveRecruitmentInfo(RecruitmentInfoBo recruitmentInfoBo) {
        RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
        ConversionUtils.conversion(recruitmentInfoBo, recruitmentInfo);
        getGeoLocation(recruitmentInfo);
        return recruitmentInfoProxy.saveRecruitmentInfo(recruitmentInfo);
    }

    private void getGeoLocation(RecruitmentInfo recruitmentInfo) {
        if (recruitmentInfo.getInfoWorkLatitude() == null || recruitmentInfo.getInfoWorkLongitude() == null) {
            Province province = provinceProxy.findProvinceByProvinceId(recruitmentInfo.getProvinceId());
            City city = cityProxy.findCityByCityId(recruitmentInfo.getCityId());
            Area area = areaProxy.findAreaByAreaId(recruitmentInfo.getAreaId());

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
            if (recruitmentInfo.getInfoWorkAddress() != null) {
                address.append(recruitmentInfo.getInfoWorkAddress());
                GeocoderSearchResponse geo = coordinateService.getCoordinate(address.toString());
                if (geo.getStatus().equals(GeoStatus.Success.getCode())) {
                    recruitmentInfo.setInfoWorkLatitude(geo.getResult().getLocation().getLat());
                    recruitmentInfo.setInfoWorkLongitude(geo.getResult().getLocation().getLng());
                } else {
                    LOG.error(geo.getMessage() + " >>>>>> " + address);
                }
            }
        }
    }

    @Override
    public Integer updateRecruitmentInfo(RecruitmentInfoBo recruitmentInfoBo) {
        RecruitmentInfo recruitmentInfo = new RecruitmentInfo();
        ConversionUtils.conversion(recruitmentInfoBo, recruitmentInfo);
        getGeoLocation(recruitmentInfo);
        return recruitmentInfoProxy.updateRecruitmentInfo(recruitmentInfo);
    }

    @Override
    public Pager<RecruitmentInfoBo> findNearRecruitmentInfoByLocationPager(Double lng, Double lat, Integer raidus, Integer firstResult, Integer sizeNo) {
        Pager<RecruitmentInfo> recruitmentInfoList = recruitmentInfoProxy.findNearRecruitmentInfoByLocationPager(lng, lat, raidus, new Integer[]{RecruitmentInfoStatus.Active.getCode()}, firstResult, sizeNo);
        Pager<RecruitmentInfoBo> recruitmentInfoBoListPager = new Pager<RecruitmentInfoBo>();
        List<RecruitmentInfoBo> recruitmentInfoBoList = new ArrayList<RecruitmentInfoBo>();
        for (RecruitmentInfo info : recruitmentInfoList.getResultList()) {
            RecruitmentInfoBo bo = getRecruitmentInfoBoDetail(info);
            recruitmentInfoBoList.add(bo);
        }
        recruitmentInfoBoListPager.setResultList(recruitmentInfoBoList);
        recruitmentInfoBoListPager.setTotalSize(recruitmentInfoList.getTotalSize());
        return recruitmentInfoBoListPager;
    }
}
