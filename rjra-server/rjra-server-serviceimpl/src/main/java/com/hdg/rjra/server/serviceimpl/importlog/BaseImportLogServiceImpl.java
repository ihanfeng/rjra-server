package com.hdg.rjra.server.serviceimpl.importlog;

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.utils.DateUtils;
import com.hdg.common.utils.HttpRequestUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.enumerate.ResumeStatus;
import com.hdg.rjra.base.enumerate.ResumeWorkStatus;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.*;
import com.hdg.rjra.rdb.proxy.domain.*;
import com.hdg.rjra.rdb.proxy.utils.CommonUtils;
import com.hdg.rjra.server.model.bo.company.CompanyBo;
import com.hdg.rjra.server.model.bo.geo.AreaGeoInfo;
import com.hdg.rjra.server.model.bo.geo.GeocoderSearchResponse;
import com.hdg.rjra.server.model.bo.importlog.ImportData;
import com.hdg.rjra.server.model.bo.importlog.ImportLogBo;
import com.hdg.rjra.server.model.bo.region.AreaBo;
import com.hdg.rjra.server.model.bo.region.CityBo;
import com.hdg.rjra.server.model.bo.region.ProvinceBo;
import com.hdg.rjra.server.service.CompanyService;
import com.hdg.rjra.server.service.ImportLogService;
import com.hdg.rjra.server.service.RegionService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/3/28.
 */
public abstract class BaseImportLogServiceImpl implements ImportLogService {
    @Autowired
    IResumeProxy resumeProxy;
    @Autowired
    RegionService regionService;
    @Autowired
    CompanyService companyService;
    @Autowired
    IWorkProxy workProxy;

    @Override
    public ImportLogBo company(List<ImportData> importDataList) throws IOException {
        List<AreaBo> areaList = regionService.findAllArea();
        List<CityBo> cityList = regionService.findAllCity();
        List<ProvinceBo> provinceList = regionService.findAllProvince();
        List<Company> list = new ArrayList<Company>();
        List<List<Company>> sumList = new ArrayList<List<Company>>();
        int count = 1;
        for (int i = 0; i < importDataList.size(); i++) {
            ImportData importData = importDataList.get(i);
            importData.setAreaList(areaList);
            importData.setCityList(cityList);
            importData.setProvinceList(provinceList);
            if (count % 50 == 0) {
                sumList.add(list);
                list = new ArrayList<Company>();
            }
            parseImportData(importData);
            Company company = buildCompany(importData);
            list.add(company);
            count++;
        }

        sumList.add(list);
        List<Long> deletIds = new ArrayList<Long>();
        for (int i = 0; i < sumList.size(); i++) {
            deletIds.addAll((List<Long>) companyService.batchSaveCompany(sumList.get(i)));
            System.out.println(i);
        }
//            companyProxy.deleteCompany(deletIds);
        System.out.println("over");
        return null;
    }

    private Company buildCompany(ImportData importData) {
        Company company = new Company();
        company.setCompanyName(importData.getCompanyName());
        company.setCompanyContact(importData.getContactName());
        company.setCompanyContactMobile(importData.getMobile());
        company.setCompanyEmail(importData.getEmail());
        company.setCompanyAddress(importData.getAddress());
        company.setCompanyDesc(importData.getCompanyDesc());
        company.setCompanyType(importData.getCompanyScaleId());
        company.setCompanyScale(importData.getCompanyScaleId());
        company.setCompanyAreaId(importData.getCompanyAreaId());
        company.setCompanyCityId(importData.getCompanyCityId());
        company.setCompanyProvinceId(importData.getCompanyProvinceId());
        company.setCompanyDataType(DataResourceType.Crawl.getCode());
        company.setCompanyTag(DateUtils.getTimeNow(new Date(), CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS));
        company.setCompanyImportOperatorId(-1L);
        company.setCompanyImportTime(new Date());
        company.setCompanyImportOperatorName("admin");
        return company;
    }

    protected void parseImportData(ImportData importData) {
        adapterImportData(importData);
        Long[] category = findCategory(importData.getCategory());
        importData.setCategoryLevel1Id(category[0]);
        importData.setCategoryLevel2Id(category[1]);
        importData.setCategoryLevel3Id(category[2]);
    }

    protected abstract void adapterImportData(ImportData importData);
    @Override
    public ImportLogBo work(List<ImportData> importDataList) {
        List<AreaBo> areaList = regionService.findAllArea();
        List<CityBo> cityList = regionService.findAllCity();
        List<ProvinceBo> provinceList = regionService.findAllProvince();
        Pager<CompanyBo> companyPager = companyService.findAllCompanyByConditionPagerSimple(new CompanyBo(), 0, 999999);
        List<Work> list = new ArrayList<Work>();
        List<List<Work>> sumList = new ArrayList<List<Work>>();
        List<Company> companyList = new ArrayList<Company>();
        List<List<Company>> sumCompanyList = new ArrayList<List<Company>>();
        int count = 1;
        for (int i = 0; i < importDataList.size(); i++) {
            ImportData importData = importDataList.get(i);
            importData.setAreaList(areaList);
            importData.setCityList(cityList);
            importData.setProvinceList(provinceList);
            importData.setCompanyList(companyPager.getResultList());
            if (count % 50 == 0) {
                sumList.add(list);
                list = new ArrayList<Work>();
            }
            parseImportData(importData);
            Work work = buildWork(importData);
            list.add(work);
            Company company = buildCompany(importData);
            companyList.add(company);
            count++;
        }
        sumCompanyList.add(companyList);
        List<Long> companyDeleteIds = new ArrayList<Long>();
        for (int i = 0; i < sumCompanyList.size(); i++) {
            companyDeleteIds.addAll((List<Long>) companyService.batchSaveCompany(sumCompanyList.get(i)));
            System.out.println(i);
        }
//            companyProxy.deleteCompany(companyDeleteIds);

        sumList.add(list);

        for (int i = 0; i < sumList.size(); i++) {
            List<Work> works = sumList.get(i);
            for (int j = 0; j <works.size(); j++) {
                Work work = works.get(j);
                work.setCompanyId(findCompany(work.getCompanyName(), companyPager.getResultList()));
            }
        }
        List<Long> deletIds = new ArrayList<Long>();
        for (int i = 0; i < sumList.size(); i++) {
            deletIds.addAll((List<Long>) workProxy.batchSaveWork(sumList.get(i)));
            System.out.println(i);
        }
//            workProxy.deleteWork(deletIds);
        return null;
    }

    private Work buildWork(ImportData importData) {
        Work work = new Work();
        work.setWorkDataType(DataResourceType.Crawl.getCode());
        work.setWorkTag(DateUtils.getTimeNow(new Date(), CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS));
        work.setWorkLongitude(importData.getWorkLongitude());
        work.setWorkLatitude(importData.getWorkLatitude());
        work.setUserId(-1L);
        work.setCategoryLevel1Id(importData.getCategoryLevel1Id());
        work.setCategoryLevel2Id(importData.getCategoryLevel2Id());
        work.setCategoryLevel3Id(importData.getCategoryLevel3Id());
        work.setWorkAreaId(importData.getWorkAreaId());
        work.setWorkCityId(importData.getWorkCityId());
        work.setWorkProvinceId(importData.getWorkProvinceId());
        work.setWorkAddress(importData.getAddress());
        work.setWorkExperienceId(importData.getWorkExperienceId());
        work.setWorkWelfareIds(importData.getWorkWelfareIds());
        work.setWorkStatus(WorkStatus.Active.getCode());
        work.setWorkCreateTime(new Date());
        work.setWorkUpdateTime(new Date());
        work.setAgeId(importData.getAgeId());
        work.setWorkGender(importData.getWorkGender() == null ? 0:1);//http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&callback=renderReverse&location=22.659210205235,114.03049680522&output=xml&pois=1
        work.setCompanyName(importData.getCompanyName());
        work.setCompanyId(importData.getCompanyId());
        work.setWorkDesc(importData.getWorkDesc() == null?importData.getCompanyDesc(): importData.getWorkDesc());
        work.setWorkNeedPerson(importData.getWorkNeedPerson());
        work.setWorkWageId(importData.getWorkWageId());
        work.setWorkImportOperatorId(-1L);
        work.setWorkImportTime(new Date());
        work.setWorkImportOperatorName("admin");
        return work;
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
                    Long[] category = findCategory(row.getCell(9).getStringCellValue());
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

            List<Long> deletIds = new ArrayList<Long>();
            for (int i = 0; i < sumResumeList.size(); i++) {
                deletIds.addAll((List<Long>) resumeProxy.batchSaveResume(sumResumeList.get(i)));
                System.out.println(i);
            }
//            resumeProxy.deleteResume(deletIds);
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

    protected AreaGeoInfo getAreaGeoInfo(String address) {
        String baiduRequest = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=X4R0e9z7r4L3onULLOwGBdAD&address=" + address;
        System.out.println(baiduRequest);
        String requestJson = HttpRequestUtils.sendGet(baiduRequest);
        System.out.println(requestJson);
        AreaGeoInfo areaGeoInfo = null;
        try {
            GeocoderSearchResponse geocoderSearchResponse = JsonUtils.jsonToObject(requestJson, GeocoderSearchResponse.class);
            if(geocoderSearchResponse.getStatus() == 1){
                return null;
            }
            String findAreaRequest = "http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&location=" + geocoderSearchResponse.getResult().getLocation().getLat() + "," + geocoderSearchResponse.getResult().getLocation().getLng() + "&output=json&pois=1";
            System.out.println(findAreaRequest);
            String requestAreaJson = HttpRequestUtils.sendGet(findAreaRequest);
            System.out.println(requestAreaJson);
            areaGeoInfo = JsonUtils.jsonToObject(requestAreaJson, AreaGeoInfo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return areaGeoInfo;
    }

    protected Long[] findCategory(String category) {
        if ("产品测试人员".equals(category)) {
            return new Long[]{102200300L, 102203300L, 102203305L};
        } else if ("作业人员".equals(category)) {
            return new Long[]{101200300L, 101201300L, 101201302L};
        } else if ("品检员".equals(category)) {
            return new Long[]{110200300L, 110203300L, 110203302L};
        } else if ("工务人员".equals(category)) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("维修人员".equals(category)) {
            return new Long[]{102200300L, 102203300L, 102203310L};
        } else if ("仓管员".equals(category)) {
            return new Long[]{103200300L, 103202300L, 103202305L};
        } else if ("保安".equals(category)) {
            return new Long[]{104200300L, 104201300L, 104201303L};
        } else if ("生产管理员".equals(category)) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("检测分析人员".equals(category)) {
            return new Long[]{103200300L, 103203300L, 103203307L};
        } else if ("行政员".equals(category)) {
            return new Long[]{109200300L, 109201300L, 109201307L};
        } else if ("生产支援人员".equals(category)) {
            return new Long[]{102200300L, 102201300L, 102201311L};
        } else if ("设备维护人员".equals(category)) {
            return new Long[]{102200300L, 102201300L, 102201302L};
        } else if ("检测人员".equals(category)) {
            return new Long[]{102200300L, 102203300L, 102203305L};
        } else if ("採购".equals(category)) {
            return new Long[]{108200300L, 108204300L, 108204303L};
        } else if ("测试人员".equals(category)) {
            return new Long[]{110200300L, 110203300L, 110203303L};
        } else if ("帐务员".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("品管员".equals(category)) {
            return new Long[]{108200300L, 108203300L, 108203309L};
        } else if ("物流员".equals(category)) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("工业安全管理员".equals(category)) {
            return new Long[]{106200300L, 106201300L, 106201304L};
        } else if ("检验人员".equals(category)) {
            return new Long[]{102200300L, 102201300L, 102201306L};
        } else if ("叉车".equals(category)) {
            return new Long[]{101200300L, 101202300L, 101202305L};
        } else if ("球车司机".equals(category)) {
            return new Long[]{101200300L, 101202300L, 101202305L};
        } else if ("炉温测试".equals(category)) {
            return new Long[]{103200300L, 103203300L, 103203307L};
        } else if ("槽液测试".equals(category)) {
            return new Long[]{103200300L, 103203300L, 103203307L};
        } else if ("资讯维护员".equals(category)) {
            return new Long[]{110200300L, 110204300L, 110204320L};
        } else if ("机台操作员".equals(category)) {
            return new Long[]{102200300L, 102202300L, 102202303L};
        } else if ("宿管员".equals(category)) {
            return new Long[]{104200300L, 104202300L, 104202303L};
        } else if ("报关员".equals(category)) {
            return new Long[]{108200300L, 108204300L, 108204306L};
        } else if ("物控人员".equals(category)) {
            return new Long[]{103200300L, 103202300L, 103202302L};
        } else if ("运务控管员".equals(category)) {
            return new Long[]{103200300L, 103202300L, 103202302L};
        } else if ("SCM".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("金融/银行".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("互联网/电子商务".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("计算机软件".equals(category)) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("教育/科研/培训".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("批发/零售".equals(category)) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("原材料和加工".equals(category)) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("贸易/进出口".equals(category)) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("娱乐休闲/餐饮/服务".equals(category)) {
            return new Long[]{105200300L, 105201300L, 105201303L};
        } else if ("人力资源服务".equals(category)) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("中介/专业服务".equals(category)) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("财务/审计".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("房地产/物业管理".equals(category)) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("广告/创意".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("医疗/保健/卫生/美容".equals(category)) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("钢铁/机械/设备/重工".equals(category)) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("交通/运输/物流".equals(category)) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("旅游/酒店".equals(category)) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("仪器仪表/工业自动化".equals(category)) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("通信/电信".equals(category)) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("文体/影视/艺术".equals(category)) {
            return new Long[]{107200300L, 107204300L, 107204301L};
        } else if ("耐用消费品(家具/家电等)".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("计算机硬件".equals(category)) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("媒体传播".equals(category)) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("生物/制药/医疗器械".equals(category)) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("电子技术/半导体/集成电路".equals(category)) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("公关/市场推广/会展".equals(category)) {
            return new Long[]{108200300L, 108205300L, 108205303L};
        } else if ("建筑/建材".equals(category)) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("政府/非盈利机构".equals(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        } else if ("出版/印刷/造纸".equals(category)) {
            return new Long[]{102200300L, 102204300L, 102204308L};
        } else if ("农林牧渔".equals(category)) {
            return new Long[]{110200300L, 110201300L, 110201303L};
        } else if ("快速消费品(食品/饮料等)".equals(category)) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("汽车/摩托车".equals(category)) {
            return new Long[]{103200300L, 103203300L, 103203303L};
        } else {
            return new Long[]{110200300L, 110205300L, -1L};
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

    protected static Long findAreaId(String areaName, List<AreaBo> areaList) {
        for (AreaBo area : areaList) {
            if (area.getAreaName().equals(areaName)) {
                return area.getAreaId();
            }
        }
        return -1L;
    }

    protected static Long findCityId(String cityName, List<CityBo> cityList) {
        for (CityBo city : cityList) {
            if (city.getCityName().equals(cityName)) {
                return city.getCityId();
            }
        }
        return -1L;
    }

    protected static Long findProvinceId(String provinceName, List<ProvinceBo> provinceList) {
        for (ProvinceBo province : provinceList) {
            if (province.getProvinceName().equals(provinceName)) {
                return province.getProvinceId();
            }
        }
        return -1L;
    }

    protected static Long companyScaleResolve(String scale) {
        if (scale == null) {
            return -1L;
        }
        scale = scale.trim();
        if ("1-49人".equals(scale)) {
            return 1L;
        } else if ("50-99人".equals(scale)) {
            return 2L;
        } else if ("100-499人".equals(scale)) {
            return 4L;
        } else if ("500-999人".equals(scale)) {
            return 6L;
        } else if ("1000人以上".equals(scale)) {
            return 7L;
        } else {
            return -1L;
        }
    }

    protected static Long companyScaleType(String type) {
        if (type == null) {
            return -1L;
        }
        type = type.trim();
        if ("私营".equals(type)) {
            return 1L;
        } else if ("国有".equals(type)) {
            return 2L;
        } else if ("外商独资".equals(type)) {
            return 3L;
        } else if ("个人企业".equals(type)) {
            return 4L;
        } else if ("非盈利机构".equals(type)) {
            return 5L;
        } else if ("股份制".equals(type)) {
            return 6L;
        } else if ("上市公司".equals(type)) {
            return 7L;
        } else if ("事业单位".equals(type)) {
            return 8L;
        } else if ("政府机关".equals(type)) {
            return 9L;
        } else if ("中外合资".equals(type)) {
            return 10L;
        } else {
            return -1L;
        }
    }

    protected Long findCompany(String companyName, List<CompanyBo> companyList) {
        for (CompanyBo company : companyList) {
            if (company.getCompanyName() != null && company.getCompanyName().trim().equals(companyName)) {
                return company.getCompanyId();
            }
        }
        return -1L;
    }

    protected static Long findWage(String wage) {

        if ("1500元以下".equals(wage)) {
            return 2L;
        } else if ("1500-2000".equals(wage)) {
            return 3L;
        } else if ("2000-3000".equals(wage)) {
            return 4L;
        } else if ("3000-5000".equals(wage)) {
            return 5L;
        } else if ("5000-8000".equals(wage)) {
            return 6L;
        } else if ("8000-12000".equals(wage)) {
            return 7L;
        } else if ("12000-15000".equals(wage)) {
            return 8L;
        } else if ("15000-20000".equals(wage)) {
            return 9L;
        } else if ("20000元以上".equals(wage)) {
            return 10L;
        }
        return 1L;
    }
}
