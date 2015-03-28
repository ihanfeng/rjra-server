package com.hdg.rjra.server.serviceimpl.importlog;

import com.hdg.common.utils.HttpRequestUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.proxy.domain.*;
import com.hdg.rjra.server.model.bo.geo.AreaGeoInfo;
import com.hdg.rjra.server.model.bo.geo.GeocoderSearchResponse;
import com.hdg.rjra.server.model.bo.importlog.ImportData;
import com.hdg.rjra.server.model.bo.importlog.ImportLogBo;
import com.hdg.rjra.server.service.ImportLogService;

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
public class TongChengImportLogServiceImpl extends BaseImportLogServiceImpl {

    @Override
    protected Long parseCompanyProvinceId(ImportData importData) {
        return null;
    }

    @Override
    protected Long parseCompanyCityId(ImportData importData) {
        return null;
    }

    @Override
    protected Long parseCompanyAreaId(ImportData importData) {
        return null;
    }

    @Override
    protected Long parseCompanyScale(ImportData importData) {
        return null;
    }

    @Override
    protected Long parseCompanyType(ImportData importData) {
        return null;
    }

    @Override
    protected String parseCompanyDesc(ImportData importData) {
        return null;
    }

    @Override
    protected String parseCompanyAddress(ImportData importData) {
        return null;
    }

    @Override
    protected String parseCompanyContactEmail(ImportData importData) {
        return null;
    }

    @Override
    protected String parseCompanyContactMobile(ImportData importData) {
        return null;
    }

    @Override
    protected String parseCompanyContact(ImportData importData) {
        return null;
    }

    @Override
    protected String parseCompanyName(ImportData importData) {
        return null;
    }

    @Override
    public ImportLogBo work(List< ImportData > importDataList) {
        List<Work> list = new ArrayList<Work>();
        List<List<Work>> sumList = new ArrayList<List<Work>>();
        List<Area> areaList = areaProxy.findAllArea();
        List<City> cityList = cityProxy.findAllCity();
        List<Province> provinceList = provinceProxy.findAllProvince();

        Pager<Company> companyPager = companyProxy.findAllCompanyByConditionPager(new Company(), 0, 999999);
        int count = 1;
        for (int i = 0; i < importDataList.size(); i++) {
            if (count % 50 == 0) {
                sumList.add(list);
                list = new ArrayList<Work>();
            }
            String REGEXP = "\\[(.+)最新招聘信息\\]诚聘(.+)([^\\d])(\\d{4})?((\\d+)人|(若干)),工作地点位于(.+),公司规模(.+),薪资待遇(不限|(.+)元),工作经验(.+),学历要求(高中|大专|不限|中专),(.+)";
////            String desc = row.getCell(9).getStringCellValue();
////            if (!desc.matches(REGEXP)) {
////                continue;
////            }
////            Pattern p = Pattern.compile(REGEXP, Pattern.CASE_INSENSITIVE);
////            Matcher m = p.matcher(desc);
////            Work work = new Work();
////            work.setWorkDataType(DataResourceType.Crawl.getCode());
////            work.setWorkTag("hello");
////            String ads = "";
////            if (m.find()) {
////                String companyName = m.group(1);
////                String workDesc = m.group(14);
////                Integer needPeople = StringUtils.isEmpty(m.group(6)) ? 1000 : Integer.parseInt(m.group(6));
////                ads = m.group(8);
////                String wage = m.group(12);
////                System.out.println(m.group(0));
////                System.out.println(m.group(1));
////                System.out.println(m.group(2));
////                System.out.println(m.group(3));
////                System.out.println(m.group(4));
////                System.out.println(m.group(5));
////                System.out.println(m.group(6));
////                System.out.println(m.group(7));
////                System.out.println(m.group(8));
////                System.out.println(m.group(9));
////                System.out.println(m.group(10));
////                System.out.println(m.group(11));
////                System.out.println(m.group(12));
////                System.out.println(m.group(13));
////                System.out.println(m.group(14));
////                work.setCompanyName(companyName);
////                work.setCompanyId(findCompany(companyName, companyPager.getResultList()));
////                work.setWorkDesc(workDesc);
////                work.setWorkNeedPerson(needPeople);
////                work.setWorkWageId(findWage(wage));
////            }
////            String address = ads + row.getCell(7).getStringCellValue();
////            //http://api.map.baidu.com/geocoder/v2/?output=json&ak=X4R0e9z7r4L3onULLOwGBdAD&address=
////            String baiduRequest = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=X4R0e9z7r4L3onULLOwGBdAD&address=" + address;
////            System.out.println(baiduRequest);
////            String requestJson = HttpRequestUtils.sendGet(baiduRequest);
////            System.out.println(requestJson);
////            GeocoderSearchResponse geocoderSearchResponse = JsonUtils.jsonToObject(requestJson, GeocoderSearchResponse.class);
////            Double lat = geocoderSearchResponse.getResult().getLocation().getLat();
////            Double lng = geocoderSearchResponse.getResult().getLocation().getLng();
////            String findAreaRequest = "http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&location=" + lat + "," + lng + "&output=json&pois=1";
////            System.out.println(findAreaRequest);
////            String requestAreaJson = HttpRequestUtils.sendGet(findAreaRequest);
////            System.out.println(requestAreaJson);
////            AreaGeoInfo areaGeoInfo = JsonUtils.jsonToObject(requestAreaJson, AreaGeoInfo.class);
////            work.setWorkLongitude(lng);
////            work.setWorkLatitude(lat);
////            work.setUserId(-1L);
////            Long[] category = findWorkCategory(row.getCell(8).getStringCellValue());
////            work.setCategoryLevel1Id(category[0]);
////            work.setCategoryLevel2Id(category[1]);
////            work.setCategoryLevel3Id(category[2]);
////            work.setWorkAreaId(findAreaId(areaGeoInfo.getResult().getAddressComponent().getDistrict(), areaList));
////            work.setWorkCityId(findCityId(areaGeoInfo.getResult().getAddressComponent().getCity(), cityList));
////            work.setWorkProvinceId(findProvinceId(areaGeoInfo.getResult().getAddressComponent().getProvince(), provinceList));
////            work.setWorkAddress(address);
////            work.setWorkExperienceId(1L);
////            work.setWorkWelfareIds(new Long[]{});
////            work.setWorkStatus(WorkStatus.Active.getCode());
////            work.setWorkCreateTime(new Date());
////            work.setWorkUpdateTime(new Date());
////            work.setAgeId(1L);
////            work.setWorkGender(2);//http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&callback=renderReverse&location=22.659210205235,114.03049680522&output=xml&pois=1
//            list.add(work);
            count++;
        }
        sumList.add(list);

        List<Long> deletIds = new ArrayList<Long>();
        for (int i = 0; i < sumList.size(); i++) {
            deletIds.addAll((List<Long>) workProxy.batchSaveWork(sumList.get(i)));
            System.out.println(i);
        }
//            workProxy.deleteWork(deletIds);
        return null;
    }
}
