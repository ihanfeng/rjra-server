package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.HttpRequestUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.enumerate.ResumeStatus;
import com.hdg.rjra.base.enumerate.ResumeWorkStatus;
import com.hdg.rjra.rdb.proxy.domain.Area;
import com.hdg.rjra.rdb.proxy.domain.City;
import com.hdg.rjra.rdb.proxy.domain.Province;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import com.hdg.rjra.server.model.bo.geo.AreaGeoInfo;
import com.hdg.rjra.server.model.bo.geo.GeocoderSearchResponse;
import com.hdg.rjra.server.model.bo.importlog.ImportLogBo;
import com.hdg.rjra.server.service.ImportLogService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/26.
 */
@Service
public class ImportLogLogServiceImpl implements ImportLogService {

    @Override
    public ImportLogBo company(ImportLogBo importLogBo, InputStream fileInputStream) throws IOException {
//        List<Company> companyList = new ArrayList<Company>();
//        List<List<Company>> sumCompanyList = new ArrayList<List<Company>>();
//        try {
//            HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
//            System.out.println("Data dump:\n");
//            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
//                HSSFSheet sheet = wb.getSheetAt(k);
//                int rows = sheet.getPhysicalNumberOfRows();
//                System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
//                        + " row(s).");
//                for (int r = 1; r < rows; r++) {
//                    if(r % 50 == 0){
//                        sumCompanyList.add(companyList);
//                        companyList = new ArrayList<Company>();
//                    }
//                    HSSFRow row = sheet.getRow(r);
//                    if (row == null) {
//                        continue;
//                    }
//                    Company company = new Company();
//                    company.setCompanyName(row.getCell(0).getStringCellValue());
//                    company.setCompanyContact(row.getCell(1).getStringCellValue());
//                    company.setCompanyContactMobile(StringUtils.isEmpty(row.getCell(3).getStringCellValue()) ? row.getCell(2).getStringCellValue() : row.getCell(3).getStringCellValue());
//                    company.setCompanyEmail(row.getCell(4).getStringCellValue());
//                    company.setCompanyAddress(row.getCell(5).getStringCellValue());
//                    company.setCompanyDesc(row.getCell(6).getStringCellValue());
//                    company.setCompanyType(companyScaleType(row.getCell(7).getStringCellValue()));
//                    company.setCompanyScale(companyScaleResolve(row.getCell(8).getStringCellValue()));
//                    company.setCompanyDataType(DataResourceType.Crawl.getCode());
//                    company.setCompanyTag("hhh");
//                    companyList.add(company);
//                    int cells = row.getPhysicalNumberOfCells();
//                    System.out.println("\nROW " + row.getRowNum() + " has " + cells
//                            + " cell(s).");
//                }
//            }
//            sumCompanyList.add(companyList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        List<Long> deletIds = new ArrayList<Long>();
//        for (int i = 0; i < sumCompanyList.size(); i++) {
//            deletIds.addAll((List<Long>) rdbDefaultClient.invoke("rdb-company", "batchSaveCompany",
//                    new Object[]{sumCompanyList.get(i)}));
//            System.out.println(i);
//        }
////        rdbDefaultClient.invoke("rdb-company", "deleteCompany",
////                new Object[]{deletIds});
//        System.out.println("over");
        return null;
    }

    @Override
    public ImportLogBo work(ImportLogBo importLogBo, InputStream fileInputStream) {
        return null;
    }

    @Override
    public ImportLogBo resume(ImportLogBo importLogBo, InputStream fileInputStream) {
        List<Resume> resumeList = new ArrayList<Resume>();
        List<List<Resume>> sumResumeList = new ArrayList<List<Resume>>();
        try {
            HSSFWorkbook wb = new HSSFWorkbook(fileInputStream);
            System.out.println("Data dump:\n");
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                HSSFSheet sheet = wb.getSheetAt(k);
                int rows = sheet.getPhysicalNumberOfRows();
                System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
                        + " row(s).");
                for (int r = 1; r < rows; r++) {
                    if (r % 50 == 0) {
                        sumResumeList.add(resumeList);
                        resumeList = new ArrayList<Resume>();
                    }
                    HSSFRow row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    Resume resume = new Resume();
                    Long[] category = getResumeCategory(row.getCell(9).getStringCellValue());
                    resume.setCategoryLevel1Ids(new Long[]{category[0]});
                    resume.setCategoryLevel2Ids(new Long[]{category[1]});
                    resume.setCategoryLevel3Ids(new Long[]{category[2]});
                    //resume.setResumeBirthday(null);
                    resume.setResumeCreateTime(new Date());
                    resume.setResumeDesc(row.getCell(6).getStringCellValue());
                    resume.setResumeExperience(-1L);
                    resume.setResumeGender(getGender(row.getCell(2).getStringCellValue()));
                    resume.setResumeHomeAddress(row.getCell(10).getStringCellValue());
                    resume.setResumeHomeAreaId(-1L);
                    resume.setResumeHomeCityId(-1L);
                    resume.setResumeHomeProvinceId(-1L);
                    AreaGeoInfo areaGeoInfo = getAreaGeoInfo(row.getCell(10).getStringCellValue());

                    resume.setResumeLatitude(areaGeoInfo.getResult().getLocation().getLat());
                    resume.setResumeLongitude(areaGeoInfo.getResult().getLocation().getLng());
                    resume.setResumeMobile((row.getCell(12).getStringCellValue() + "").replace("'", "").trim());
                    resume.setResumeQQ(String.valueOf(row.getCell(13).toString()));
                    resume.setResumeRefreshTime(new Date());
                    resume.setResumeStatus(ResumeStatus.Active.getCode());
                    resume.setResumeUpdateTime(new Date());
                    resume.setResumeUserName(row.getCell(1).getStringCellValue());
                    resume.setResumeWage(1L);
                    resume.setResumeWorkStatus(ResumeWorkStatus.Idle.getCode());
                    resume.setResumeWantWorkAreaId(-1L);
                    resume.setResumeWantWorkCityId(-1L);
                    resume.setResumeWantWorkProvinceId(-1L);
                    resume.setResumeDataType(DataResourceType.Crawl.getCode());
                    resume.setAgeId(getAge(row.getCell(11).getNumericCellValue()));
                    resume.setResumeTag("hhh");
                    resumeList.add(resume);
                    int cells = row.getPhysicalNumberOfCells();
                    System.out.println("\nROW " + row.getRowNum() + " has " + cells
                            + " cell(s).");
                }
            }
            sumResumeList.add(resumeList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Long getAge(Double stringCellValue) {
        if (stringCellValue > 18 && stringCellValue <= 22) {
            return 2L;
        } else if (stringCellValue > 22 && stringCellValue <= 25) {
            return 2L;
        } else if (stringCellValue > 25 && stringCellValue <= 28) {
            return 2L;
        } else if (stringCellValue > 28 && stringCellValue <= 35) {
            return 2L;
        } else if (stringCellValue > 35 && stringCellValue <= 45) {
            return 2L;
        } else if (stringCellValue > 45 && stringCellValue <= 55) {
            return 2L;
        } else {
            return 1L;
        }
    }

    private AreaGeoInfo getAreaGeoInfo(String address) {
        String baiduRequest = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=X4R0e9z7r4L3onULLOwGBdAD&address=" + address;
        System.out.println(baiduRequest);
        String requestJson = HttpRequestUtils.sendGet(baiduRequest);
        System.out.println(requestJson);
        GeocoderSearchResponse geocoderSearchResponse = JsonUtils.jsonToObject(requestJson, GeocoderSearchResponse.class);
        String findAreaRequest = "http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&location=" + geocoderSearchResponse.getResult().getLocation().getLat() + "," + geocoderSearchResponse.getResult().getLocation().getLng() + "&output=json&pois=1";
        System.out.println(findAreaRequest);
        String requestAreaJson = HttpRequestUtils.sendGet(findAreaRequest);
        System.out.println(requestAreaJson);
        AreaGeoInfo areaGeoInfo = JsonUtils.jsonToObject(requestAreaJson, AreaGeoInfo.class);
        return areaGeoInfo;
    }

    private Long[] getResumeCategory(String category) {
        if ("产品测试人员".equals(category)) {
            return new Long[]{102200300L,102203300L,102203305L};
        } else if ("作业人员".equals(category)) {
            return new Long[]{101200300L,101201300L,101201302L};
        } else if ("品检员".equals(category)) {
            return new Long[]{110200300L,110203300L,110203302L};
        } else if ("工务人员".equals(category)) {
            return new Long[]{102200300L,102203300L,102203301L};
        } else if ("维修人员".equals(category)) {
            return new Long[]{102200300L,102203300L,102203310L};
        } else if ("仓管员".equals(category)) {
            return new Long[]{103200300L,103202300L,103202305L};
        } else if ("保安".equals(category)) {
            return new Long[]{104200300L,104201300L,104201303L};
        } else if ("生产管理员".equals(category)) {
            return new Long[]{102200300L,102204300L,102204304L};
        } else if ("检测分析人员".equals(category)) {
            return new Long[]{103200300L,103203300L,103203307L};
        } else if ("行政员".equals(category)) {
            return new Long[]{109200300L,109201300L,109201307L};
        } else if ("生产支援人员".equals(category)) {
            return new Long[]{102200300L,102201300L,102201311L};
        } else if ("设备维护人员".equals(category)) {
            return new Long[]{102200300L,102201300L,102201302L};
        } else if ("检测人员".equals(category)) {
            return new Long[]{102200300L,102203300L,102203305L};
        } else if ("採购".equals(category)) {
            return new Long[]{108200300L,108204300L,108204303L};
        } else if ("测试人员".equals(category)) {
            return new Long[]{110200300L,110203300L,110203303L};
        } else if ("帐务员".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("品管员".equals(category)) {
            return new Long[]{108200300L,108203300L,108203309L};
        } else if ("物流员".equals(category)) {
            return new Long[]{103200300L,103202300L,103202301L};
        } else if ("工业安全管理员".equals(category)) {
            return new Long[]{106200300L,106201300L,106201304L};
        } else if ("检验人员".equals(category)) {
            return new Long[]{102200300L,102201300L,102201306L};
        } else if ("叉车".equals(category)) {
            return new Long[]{101200300L,101202300L,101202305L};
        } else if ("球车司机".equals(category)) {
            return new Long[]{101200300L,101202300L,101202305L};
        } else if ("炉温测试".equals(category)) {
            return new Long[]{103200300L,103203300L,103203307L};
        } else if ("槽液测试".equals(category)) {
            return new Long[]{103200300L,103203300L,103203307L};
        } else if ("资讯维护员".equals(category)) {
            return new Long[]{110200300L,110204300L,110204320L};
        } else if ("机台操作员".equals(category)) {
            return new Long[]{102200300L,102202300L,102202303L};
        } else if ("宿管员".equals(category)) {
            return new Long[]{104200300L,104202300L,104202303L};
        } else if ("报关员".equals(category)) {
            return new Long[]{108200300L,108204300L,108204306L};
        } else if ("物控人员".equals(category)) {
            return new Long[]{103200300L,103202300L,103202302L};
        } else if ("运务控管员".equals(category)) {
            return new Long[]{103200300L,103202300L,103202302L};
        } else if ("SCM".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else {
            return new Long[]{110200300L,110205300L,-1L};
        }
    }

    private Integer getGender(String gender) {
        if ("男".equals(gender)) {
            return 0;
        } else if ("女".equals(gender)) {
            return 1;
        }
        return 0;

    }

    private static Long findAreaId(String areaName, List<Area> areaList) {
        for (Area area : areaList) {
            if (area.getAreaName().equals(areaName)) {
                return area.getAreaId();
            }
        }
        return -1L;
    }

    private static Long findCityId(String cityName, List<City> cityList) {
        for (City city : cityList) {
            if (city.getCityName().equals(cityName)) {
                return city.getCityId();
            }
        }
        return -1L;
    }

    private static Long findProvinceId(String provinceName, List<Province> provinceList) {
        for (Province province : provinceList) {
            if (province.getProvinceName().equals(provinceName)) {
                return province.getProvinceId();
            }
        }
        return -1L;
    }
}
