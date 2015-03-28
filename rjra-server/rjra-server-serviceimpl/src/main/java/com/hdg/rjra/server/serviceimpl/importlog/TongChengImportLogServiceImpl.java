package com.hdg.rjra.server.serviceimpl.importlog;

import com.hdg.common.utils.HttpRequestUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.proxy.domain.*;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.geo.AreaGeoInfo;
import com.hdg.rjra.server.model.bo.geo.GeocoderSearchResponse;
import com.hdg.rjra.server.model.bo.importlog.ImportData;
import com.hdg.rjra.server.model.bo.importlog.ImportLogBo;
import com.hdg.rjra.server.model.bo.region.AreaBo;
import com.hdg.rjra.server.model.bo.region.CityBo;
import com.hdg.rjra.server.model.bo.region.ProvinceBo;
import com.hdg.rjra.server.service.ImportLogService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/3/28.
 */
@Service
public class TongChengImportLogServiceImpl extends BaseImportLogServiceImpl {

    @Override
    protected void adapterImportData(ImportData importData) {

        String REGEXP = "\\[(.+)最新招聘信息\\]诚聘(.+)([^\\d])(\\d{4})?((\\d+)人|(若干)),工作地点位于(.+),公司规模(.+),薪资待遇(不限|(.+)元),工作经验(.+),学历要求(高中|大专|不限|中专),(.+)";
            String desc =importData.getCompanyDesc();
            if (!desc.matches(REGEXP)) {
                return ;
            }
            Pattern p = Pattern.compile(REGEXP, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(desc);
            String ads = "";
            if (m.find()) {
                String companyName = m.group(1);
                String workDesc = m.group(14);
                Integer needPeople = StringUtils.isEmpty(m.group(6)) ? 1000 : Integer.parseInt(m.group(6));
                ads = m.group(8);
                String wage = m.group(12);
                System.out.println(m.group(0));
                System.out.println(m.group(1));
                System.out.println(m.group(2));
                System.out.println(m.group(3));
                System.out.println(m.group(4));
                System.out.println(m.group(5));
                System.out.println(m.group(6));
                System.out.println(m.group(7));
                System.out.println(m.group(8));
                System.out.println(m.group(9));
                System.out.println(m.group(10));
                System.out.println(m.group(11));
                System.out.println(m.group(12));
                System.out.println(m.group(13));
                System.out.println(m.group(14));
                importData.setCompanyName(companyName);
                importData.setWorkDesc(workDesc);
                importData.setWorkNeedPerson(needPeople);
                importData.setWorkWageId(findWage(wage));
            }
            importData.setAddress(ads + importData.getAddress());
            AreaGeoInfo areaGeoInfo = getAreaGeoInfo(importData.getAddress());
            //http://api.map.baidu.com/geocoder/v2/?output=json&ak=X4R0e9z7r4L3onULLOwGBdAD&address=
            if(areaGeoInfo != null) {
                importData.setWorkLongitude(areaGeoInfo.getResult().getLocation().getLng());
                importData.setWorkLatitude(areaGeoInfo.getResult().getLocation().getLat());
                importData.setWorkAreaId(findAreaId(areaGeoInfo.getResult().getAddressComponent().getDistrict(), importData.getAreaList()));
                importData.setWorkCityId(findCityId(areaGeoInfo.getResult().getAddressComponent().getCity(), importData.getCityList()));
                importData.setWorkProvinceId(findProvinceId(areaGeoInfo.getResult().getAddressComponent().getProvince(), importData.getProvinceList()));
            }
            importData.setUserId(-1L);
            importData.setWorkExperienceId(1L);
            importData.setWorkWelfareIds(new Long[]{});
            importData.setWorkStatus(WorkStatus.Active.getCode());
            importData.setAgeId(1L);
            importData.setWorkGender(2);//http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&callback=renderReverse&location=22.659210205235,114.03049680522&output=xml&pois=1
    }
}
