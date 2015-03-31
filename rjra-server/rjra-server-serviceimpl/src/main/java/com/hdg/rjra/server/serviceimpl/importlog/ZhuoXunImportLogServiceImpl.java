package com.hdg.rjra.server.serviceimpl.importlog;

import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.server.model.bo.geo.AreaGeoInfo;
import com.hdg.rjra.server.model.bo.importlog.ImportData;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2015/3/26.
 */
@Service
public class ZhuoXunImportLogServiceImpl extends BaseImportLogServiceImpl {


    @Override
    protected String getTag() {
        return "zhuoxun";
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
        importData.setWorkGender(2);

//        String REGEXP = "\\[(.+)最新招聘信息\\]诚聘(.+)([^\\d])(\\d{4})?((\\d+)人|(若干)),工作地点位于(.+),公司规模(.+),薪资待遇(不限|(.+)元),工作经验(.+),学历要求(高中|大专|不限|中专),(.+)";
//        String desc =importData.getCompanyDesc();
//        if (!desc.matches(REGEXP)) {
//            return ;
//        }
//        Pattern p = Pattern.compile(REGEXP, Pattern.CASE_INSENSITIVE);
//        Matcher m = p.matcher(desc);
//        String ads = "";
//        if (m.find()) {
//
//        }
    }
}
