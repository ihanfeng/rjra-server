package com.hdg.rjra.server.serviceimpl.importlog;

import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.server.model.bo.geo.AreaGeoInfo;
import com.hdg.rjra.server.model.bo.importlog.ImportData;
import com.hdg.rjra.server.serviceimpl.importlog.BaseImportLogServiceImpl;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2015/3/26.
 */
@Service
public class BaixingImportLogServiceImpl extends BaseImportLogServiceImpl {


    @Override
    protected String getTag() {
        return "baixing";
    }

    @Override
    protected void adapterImportData(ImportData importData) {
        AreaGeoInfo areaGeoInfo = getAreaGeoInfo(importData.getAddress());
        if(areaGeoInfo != null) {
            importData.setWorkLongitude(areaGeoInfo.getResult().getLocation().getLng());
            importData.setWorkLatitude(areaGeoInfo.getResult().getLocation().getLat());
            importData.setWorkAreaId(findAreaId(areaGeoInfo.getResult().getAddressComponent().getDistrict(), importData.getAreaList()));
            importData.setWorkCityId(findCityId(areaGeoInfo.getResult().getAddressComponent().getCity(), importData.getCityList()));
            importData.setWorkProvinceId(findProvinceId(areaGeoInfo.getResult().getAddressComponent().getProvince(), importData.getProvinceList()));
        }
        importData.setWorkExperienceId(1L);
        importData.setWorkWelfareIds(new Long[]{});
        importData.setWorkStatus(WorkStatus.Active.getCode());
        importData.setAgeId(1L);
    }
}
