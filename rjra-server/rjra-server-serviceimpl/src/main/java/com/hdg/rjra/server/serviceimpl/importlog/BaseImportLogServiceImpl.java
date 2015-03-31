package com.hdg.rjra.server.serviceimpl.importlog;

import com.hdg.common.constants.CommonConstants;
import com.hdg.common.utils.DateUtils;
import com.hdg.common.utils.HttpRequestUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.enumerate.ResumeStatus;
import com.hdg.rjra.base.enumerate.ResumeWorkStatus;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.proxy.daoproxy.IResumeProxy;
import com.hdg.rjra.rdb.proxy.daoproxy.IWorkProxy;
import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.domain.Pager;
import com.hdg.rjra.rdb.proxy.domain.Resume;
import com.hdg.rjra.rdb.proxy.domain.Work;
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

    protected abstract String getTag();
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
            parseImportData(importData);
            if(!importData.isHasData()){
                continue;
            }
            if (count % 50 == 0) {
                sumList.add(list);
                list = new ArrayList<Company>();
            }
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
        company.setCompanyType(companyScaleType(importData.getCompanyType()));
        company.setCompanyScale(companyScaleResolve(importData.getCompanyScale()));
        company.setCompanyAreaId(importData.getCompanyAreaId());
        company.setCompanyCityId(importData.getCompanyCityId());
        company.setCompanyProvinceId(importData.getCompanyProvinceId());
        company.setCompanyDataType(DataResourceType.Crawl.getCode());
        company.setCompanyTag(getTag());
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
        List<Work> list = new ArrayList<Work>();
        List<List<Work>> sumList = new ArrayList<List<Work>>();
        List<Company> companyList = new ArrayList<Company>();
        List<List<Company>> sumCompanyList = new ArrayList<List<Company>>();
        int count = 1;
        for (int i = 1; i < importDataList.size(); i++) {
            ImportData importData = importDataList.get(i);
            importData.setAreaList(areaList);
            importData.setCityList(cityList);
            importData.setProvinceList(provinceList);
            parseImportData(importData);
            if(!importData.isHasData()){
                continue;
            }
            if (count % 50 == 0) {
                sumList.add(list);
                list = new ArrayList<Work>();
                sumCompanyList.add(companyList);
                companyList = new ArrayList<Company>();
            }
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

        Pager<CompanyBo> companyPager = companyService.findAllCompanyByConditionPagerSimple(new CompanyBo(), 0, 999999);
        for (int i = 0; i < sumList.size(); i++) {
            List<Work> works = sumList.get(i);
            for (int j = 0; j < works.size(); j++) {
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
        work.setWorkTag(getTag());
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
        work.setWorkGender(importData.getWorkGender() == null ? 0 : importData.getWorkGender());//http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&callback=renderReverse&location=22.659210205235,114.03049680522&output=xml&pois=1
        work.setCompanyName(importData.getCompanyName());
        work.setCompanyId(importData.getCompanyId());
        work.setWorkDesc(importData.getWorkDesc() == null ? importData.getCompanyDesc() : importData.getWorkDesc());
        work.setWorkNeedPerson(importData.getWorkNeedPerson() == null ? 10 : importData.getWorkNeedPerson());
        work.setWorkWageId(importData.getWorkWageId());
        work.setWorkImportOperatorId(-1L);
        work.setWorkImportTime(new Date());
        work.setWorkImportOperatorName("admin");
        return work;
    }

    @Override
    public ImportLogBo resume(ImportLogBo importLogBo, InputStream fileInputStream) {
        List<AreaBo> areaList = regionService.findAllArea();
        List<CityBo> cityList = regionService.findAllCity();
        List<ProvinceBo> provinceList = regionService.findAllProvince();
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
                    resume.setResumeTag(DateUtils.getTimeNow(new Date(), CommonConstants.DATE_FORMAT_YYYYMMDDHHMMSS));
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
            if (geocoderSearchResponse.getStatus() == 1) {
                return null;
            }
            String findAreaRequest = "http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&location=" + geocoderSearchResponse.getResult().getLocation().getLat() + "," + geocoderSearchResponse.getResult().getLocation().getLng() + "&output=json&pois=1";
            System.out.println(findAreaRequest);
            String requestAreaJson = HttpRequestUtils.sendGet(findAreaRequest);
            System.out.println(requestAreaJson);
            areaGeoInfo = JsonUtils.jsonToObject(requestAreaJson, AreaGeoInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areaGeoInfo;
    }

    protected Long[] findCategory(String category) {
        if (StringUtils.isEmpty(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        }
        if ("产品测试人员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203305L};
        } else if ("作业人员".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201302L};
        } else if ("品检员".indexOf(category) != -1) {
            return new Long[]{110200300L, 110203300L, 110203302L};
        } else if ("工务人员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("维修人员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203310L};
        } else if ("仓管员".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202305L};
        } else if ("保安".indexOf(category) != -1) {
            return new Long[]{104200300L, 104201300L, 104201303L};
        } else if ("生产管理员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("检测分析人员".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203307L};
        } else if ("行政员".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201307L};
        } else if ("生产支援人员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201311L};
        } else if ("设备维护人员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201302L};
        } else if ("检测人员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203305L};
        } else if ("採购".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204303L};
        } else if ("测试人员".indexOf(category) != -1) {
            return new Long[]{110200300L, 110203300L, 110203303L};
        } else if ("品管员".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203309L};
        } else if ("物流员".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("工业安全管理员".indexOf(category) != -1) {
            return new Long[]{106200300L, 106201300L, 106201304L};
        } else if ("检验人员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201306L};
        } else if ("叉车".indexOf(category) != -1) {
            return new Long[]{101200300L, 101202300L, 101202305L};
        } else if ("球车司机".indexOf(category) != -1) {
            return new Long[]{101200300L, 101202300L, 101202305L};
        } else if ("炉温测试".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203307L};
        } else if ("槽液测试".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203307L};
        } else if ("资讯维护员".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204320L};
        } else if ("机台操作员".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202303L};
        } else if ("宿管员".indexOf(category) != -1) {
            return new Long[]{104200300L, 104202300L, 104202303L};
        } else if ("报关员".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204306L};
        } else if ("物控人员".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202302L};
        } else if ("运务控管员".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202302L};
        } else if ("互联网/电子商务".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("计算机软件".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("批发/零售".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("原材料和加工".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("贸易/进出口".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("娱乐休闲/餐饮/服务".indexOf(category) != -1) {
            return new Long[]{105200300L, 105201300L, 105201303L};
        } else if ("人力资源服务".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("中介/专业服务".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("房地产/物业管理".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("广告/创意".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("医疗/保健/卫生/美容".indexOf(category) != -1) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("钢铁/机械/设备/重工".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("交通/运输/物流".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("旅游/酒店".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("仪器仪表/工业自动化".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("通信/电信".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("文体/影视/艺术".indexOf(category) != -1) {
            return new Long[]{107200300L, 107204300L, 107204301L};
        } else if ("耐用消费品(家具/家电等)".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("计算机硬件".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("媒体传播".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("生物/制药/医疗器械".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("电子技术/半导体/集成电路".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("公关/市场推广/会展".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205303L};
        } else if ("建筑/建材".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("政府/非盈利机构".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("出版/印刷/造纸".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204308L};
        } else if ("农林牧渔".indexOf(category) != -1) {
            return new Long[]{110200300L, 110201300L, 110201303L};
        } else if ("快速消费品(食品/饮料等)".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("汽车/摩托车".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203303L};
        } else {
            return findAllCategory(category);
        }
    }

    protected Long[] findAllCategory(String category) {
        if (StringUtils.isEmpty(category)) {
            return new Long[]{110200300L, 110205300L, -1L};
        }
        if ("中介/专业服务".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("计算机硬件".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("旅游/酒店".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("贸易/进出口".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("娱乐休闲/餐饮/服务".indexOf(category) != -1) {
            return new Long[]{105200300L, 105201300L, 105201303L};
        } else if ("人力资源服务".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("广告/创意".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("医疗/保健/卫生/美容".indexOf(category) != -1) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("钢铁/机械/设备/重工".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("教育/科研/培训".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("交通/运输/物流".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("计算机软件".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("电子技术/半导体/集成电路".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("保险".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("生物/制药/医疗器械".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("出版/印刷/造纸".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204308L};
        } else if ("互联网/电子商务".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("房地产/物业管理".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("原材料和加工".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("快速消费品(食品/饮料等)".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("政府/非盈利机构".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("通信/电信".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("批发/零售".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("仪器仪表/工业自动化".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("公关/市场推广/会展".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205303L};
        } else if ("汽车/摩托车".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203303L};
        } else if ("建筑/建材".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("文体/影视/艺术".indexOf(category) != -1) {
            return new Long[]{107200300L, 107204300L, 107204301L};
        } else if ("耐用消费品(家具/家电等)".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("家居/室内设计/装潢".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202307L};
        } else if ("能源(电力/水利/矿产)".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202301L};
        } else if ("办公用品及设备".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("媒体传播".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("生产/制造".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("中介服务".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("贸易/交通/运输/物流".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("其它行业".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("物业管理/商业中心".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("计算机/网络/通信".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("礼品/玩具/工艺美术/收藏品".indexOf(category) != -1) {
            return new Long[]{107200300L, 107204300L, 107204301L};
        } else if ("电子·微电子公司介绍： ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("医疗设备/器械".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("检验/检测/认证".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203305L};
        } else if ("电气/电力/水利".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202301L};
        } else if ("医药/生物工程 医疗/护理/美容/保健/卫生服务 互联网/电子商务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("媒体/出版/影视/文化传播 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("互联网/电子商务 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("旅游/度假 中介服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("贸易/进出口 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("保险 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("酒店/餐饮 医疗/护理/美容/保健/卫生服务 娱乐/体育/休闲 旅游/度假 ".indexOf(category) != -1) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 通信/电信/网络设备 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("通信/电信运营、增值服务 IT服务(系统/数据/维护) 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("房地产/建筑/建材/工程 中介服务 基金/证券/期货/投资 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("电子技术/半导体/集成电路 贸易/进出口 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("房地产/建筑/建材/工程 物业管理/商业中心 贸易/进出口 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("保险 信托/担保/拍卖/典当 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 信托/担保/拍卖/典当 银行 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("教育/培训/院校 学术/科研 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 中介服务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("教育/培训/院校 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("保险 基金/证券/期货/投资 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 IT服务(系统/数据/维护) 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("银行 通信/电信运营、增值服务 外包服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("互联网/电子商务 广告/会展/公关 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("保险 基金/证券/期货/投资 银行 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("加工制造（原料加工/模具） 礼品/玩具/工艺美术/收藏品/奢侈品 互联网/电子商务 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("电子技术/半导体/集成电路 互联网/电子商务 通信/电信运营、增值服务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 教育/培训/院校 其他 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("中介服务 租赁服务 房地产/建筑/建材/工程 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("计算机软件 IT服务(系统/数据/维护) 通信/电信/网络设备 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("农/林/牧/渔 加工制造（原料加工/模具） 电气/电力/水利 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("基金/证券/期货/投资 保险 银行 信托/担保/拍卖/典当 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("外包服务 通信/电信运营、增值服务 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("互联网/电子商务 基金/证券/期货/投资 银行 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("礼品/玩具/工艺美术/收藏品/奢侈品 ".indexOf(category) != -1) {
            return new Long[]{107200300L, 107204300L, 107204301L};
        } else if ("IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203310L};
        } else if ("银行 信托/担保/拍卖/典当 基金/证券/期货/投资 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("教育/培训/院校 学术/科研 专业服务/咨询(财会/法律/人力资源等) 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("基金/证券/期货/投资 教育/培训/院校 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 银行 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("信托/担保/拍卖/典当 互联网/电子商务 基金/证券/期货/投资 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 礼品/玩具/工艺美术/收藏品/奢侈品 贸易/进出口 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("广告/会展/公关 互联网/电子商务 耐用消费品（服饰/纺织/皮革/家具/家电） 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("计算机软件 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("教育/培训/院校 互联网/电子商务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("IT服务(系统/数据/维护) 互联网/电子商务 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("计算机软件 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("物流/仓储 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("零售/批发 快速消费品（食品/饮料/烟酒/日化） 酒店/餐饮 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("媒体/出版/影视/文化传播 娱乐/体育/休闲 广告/会展/公关 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("IT服务(系统/数据/维护) 计算机硬件 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("互联网/电子商务 租赁服务 信托/担保/拍卖/典当 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("保险 基金/证券/期货/投资 其他 银行 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("互联网/电子商务 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("基金/证券/期货/投资 房地产/建筑/建材/工程 互联网/电子商务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 银行 信托/担保/拍卖/典当 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 贸易/进出口 零售/批发 酒店/餐饮 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 互联网/电子商务 耐用消费品（服饰/纺织/皮革/家具/家电） 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 银行 信托/担保/拍卖/典当 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("通信/电信运营、增值服务 互联网/电子商务 其他 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("交通/运输 互联网/电子商务 IT服务(系统/数据/维护) 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("计算机软件 计算机硬件 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("电气/电力/水利 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202301L};
        } else if ("计算机软件 外包服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("教育/培训/院校 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("娱乐/体育/休闲 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 计算机软件 IT服务(系统/数据/维护) 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("计算机软件 电子技术/半导体/集成电路 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("互联网/电子商务 专业服务/咨询(财会/法律/人力资源等) 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("医药/生物工程 医疗设备/器械 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("交通/运输 贸易/进出口 物流/仓储 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("互联网/电子商务 贸易/进出口 专业服务/咨询(财会/法律/人力资源等) 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 医药/生物工程 农/林/牧/渔 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("中介服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("房地产/建筑/建材/工程 IT服务(系统/数据/维护) 专业服务/咨询(财会/法律/人力资源等) 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 IT服务(系统/数据/维护) 电子技术/半导体/集成电路 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("基金/证券/期货/投资 保险 中介服务 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 计算机软件 IT服务(系统/数据/维护) 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("房地产/建筑/建材/工程 IT服务(系统/数据/维护) 其他 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("通信/电信/网络设备 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("IT服务(系统/数据/维护) 计算机软件 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("酒店/餐饮 娱乐/体育/休闲 旅游/度假 医疗/护理/美容/保健/卫生服务 ".indexOf(category) != -1) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("媒体/出版/影视/文化传播 其他 广告/会展/公关 娱乐/体育/休闲 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("IT服务(系统/数据/维护) 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("娱乐/体育/休闲 酒店/餐饮 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("互联网/电子商务 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 电子技术/半导体/集成电路 零售/批发 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("互联网/电子商务 IT服务(系统/数据/维护) 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("基金/证券/期货/投资 银行 保险 信托/担保/拍卖/典当 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("汽车/摩托车 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203303L};
        } else if ("媒体/出版/影视/文化传播 广告/会展/公关 娱乐/体育/休闲 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("零售/批发 礼品/玩具/工艺美术/收藏品/奢侈品 跨领域经营 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("互联网/电子商务 网络游戏 计算机软件 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("医疗/护理/美容/保健/卫生服务 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("互联网/电子商务 通信/电信运营、增值服务 网络游戏 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("IT服务(系统/数据/维护) 计算机软件 基金/证券/期货/投资 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("物流/仓储 贸易/进出口 交通/运输 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 专业服务/咨询(财会/法律/人力资源等) 旅游/度假 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("教育/培训/院校 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("加工制造（原料加工/模具） 能源/矿产/采掘/冶炼 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("贸易/进出口 汽车/摩托车 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("计算机硬件 IT服务(系统/数据/维护) 电子技术/半导体/集成电路 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("互联网/电子商务 零售/批发 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("IT服务(系统/数据/维护) 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("媒体/出版/影视/文化传播 娱乐/体育/休闲 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 广告/会展/公关 教育/培训/院校 中介服务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 中介服务 基金/证券/期货/投资 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("媒体/出版/影视/文化传播 其他 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 零售/批发 快速消费品（食品/饮料/烟酒/日化） 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 银行 互联网/电子商务 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("跨领域经营 家居/室内设计/装饰装潢 耐用消费品（服饰/纺织/皮革/家具/家电） 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("计算机软件 互联网/电子商务 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 互联网/电子商务 中介服务 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("医疗/护理/美容/保健/卫生服务 专业服务/咨询(财会/法律/人力资源等) 互联网/电子商务 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("交通/运输 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("物流/仓储 房地产/建筑/建材/工程 旅游/度假 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("物业管理/商业中心 房地产/建筑/建材/工程 互联网/电子商务 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("互联网/电子商务 媒体/出版/影视/文化传播 医药/生物工程 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("互联网/电子商务 计算机硬件 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("基金/证券/期货/投资 其他 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("计算机硬件 互联网/电子商务 IT服务(系统/数据/维护) 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("基金/证券/期货/投资 保险 专业服务/咨询(财会/法律/人力资源等) 外包服务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("跨领域经营 互联网/电子商务 房地产/建筑/建材/工程 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("中介服务 基金/证券/期货/投资 专业服务/咨询(财会/法律/人力资源等) 保险 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 计算机软件 中介服务 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("电子技术/半导体/集成电路 医疗设备/器械 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("房地产/建筑/建材/工程 中介服务 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("网络游戏 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204317L};
        } else if ("通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("家居/室内设计/装饰装潢 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202307L};
        } else if ("石油/石化/化工 能源/矿产/采掘/冶炼 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202301L};
        } else if ("广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("房地产/建筑/建材/工程 物业管理/商业中心 中介服务 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("互联网/电子商务 基金/证券/期货/投资 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("物流/仓储 交通/运输 贸易/进出口 办公用品及设备 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("石油/石化/化工 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("贸易/进出口".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("零售/批发 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("互联网/电子商务 贸易/进出口 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("学术/科研 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202305L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("农/林/牧/渔 房地产/建筑/建材/工程 物业管理/商业中心 中介服务 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203302L};
        } else if ("媒体/出版/影视/文化传播 广告/会展/公关 互联网/电子商务 娱乐/体育/休闲 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("酒店/餐饮 ".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("贸易/进出口 其他 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 贸易/进出口 仪器仪表及工业自动化 大型设备/机电设备/重工业 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 保险 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("家居/室内设计/装饰装潢 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 通信/电信/网络设备 零售/批发 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 IT服务(系统/数据/维护) 零售/批发 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("交通/运输 互联网/电子商务 贸易/进出口 物流/仓储 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 IT服务(系统/数据/维护) 互联网/电子商务 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("互联网/电子商务 IT服务(系统/数据/维护) 计算机软件 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("检验/检测/认证 电子技术/半导体/集成电路 仪器仪表及工业自动化 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("贸易/进出口 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("零售/批发 家居/室内设计/装饰装潢 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("中介服务 房地产/建筑/建材/工程 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("电子技术/半导体/集成电路 仪器仪表及工业自动化 互联网/电子商务 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("互联网/电子商务 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("交通/运输 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 专业服务/咨询(财会/法律/人力资源等) 基金/证券/期货/投资 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("房地产/建筑/建材/工程 中介服务 租赁服务 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("通信/电信/网络设备 零售/批发 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("基金/证券/期货/投资 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("房地产/建筑/建材/工程 中介服务 其他 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("保险 银行 基金/证券/期货/投资 信托/担保/拍卖/典当 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("贸易/进出口 计算机软件 办公用品及设备 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("零售/批发 互联网/电子商务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("酒店/餐饮 旅游/度假 ".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 零售/批发 贸易/进出口 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("物流/仓储 租赁服务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("房地产/建筑/建材/工程 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("物流/仓储 交通/运输 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("中介服务 外包服务 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("互联网/电子商务 外包服务 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("计算机硬件 计算机软件 IT服务(系统/数据/维护) 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("农/林/牧/渔 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110201300L, 110201303L};
        } else if ("印刷/包装/造纸 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204308L};
        } else if ("互联网/电子商务 贸易/进出口 电子技术/半导体/集成电路 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("贸易/进出口 礼品/玩具/工艺美术/收藏品/奢侈品 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 计算机硬件 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("通信/电信运营、增值服务 通信/电信/网络设备 银行 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("娱乐/体育/休闲 ".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("通信/电信运营、增值服务 IT服务(系统/数据/维护) 计算机软件 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("加工制造（原料加工/模具） 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("保险 银行 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("保险 银行 基金/证券/期货/投资 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("电子技术/半导体/集成电路 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("物业管理/商业中心 互联网/电子商务 租赁服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("广告/会展/公关 贸易/进出口 旅游/度假 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 保险 房地产/建筑/建材/工程 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("交通/运输 汽车/摩托车 交通/运输 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 专业服务/咨询(财会/法律/人力资源等) 银行 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("基金/证券/期货/投资 计算机软件 IT服务(系统/数据/维护) 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 保险 银行 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 环保 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("教育/培训/院校 专业服务/咨询(财会/法律/人力资源等) 学术/科研 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 快速消费品（食品/饮料/烟酒/日化） ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("能源/矿产/采掘/冶炼 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("环保 耐用消费品（服饰/纺织/皮革/家具/家电） 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("互联网/电子商务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 电子技术/半导体/集成电路 加工制造（原料加工/模具） 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("媒体/出版/影视/文化传播 跨领域经营 娱乐/体育/休闲 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("加工制造（原料加工/模具） 贸易/进出口 零售/批发 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 贸易/进出口 零售/批发 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 交通/运输 物流/仓储 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 IT服务(系统/数据/维护) 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("互联网/电子商务 计算机软件 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("银行 基金/证券/期货/投资 保险 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("通信/电信/网络设备 银行 基金/证券/期货/投资 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("计算机软件 IT服务(系统/数据/维护) 电子技术/半导体/集成电路 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("零售/批发 其他 礼品/玩具/工艺美术/收藏品/奢侈品 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("计算机硬件 贸易/进出口 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("礼品/玩具/工艺美术/收藏品/奢侈品 快速消费品（食品/饮料/烟酒/日化） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("贸易/进出口 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 信托/担保/拍卖/典当 基金/证券/期货/投资 银行 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("零售/批发 快速消费品（食品/饮料/烟酒/日化） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("通信/电信运营、增值服务 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("政府/公共事业/非盈利机构 专业服务/咨询(财会/法律/人力资源等) 其他 学术/科研 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 贸易/进出口 通信/电信/网络设备 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 基金/证券/期货/投资 IT服务(系统/数据/维护) 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("电子技术/半导体/集成电路 汽车/摩托车 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("信托/担保/拍卖/典当 基金/证券/期货/投资 互联网/电子商务 中介服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("广告/会展/公关 中介服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("贸易/进出口 IT服务(系统/数据/维护) 互联网/电子商务 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("物流/仓储 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("互联网/电子商务 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("房地产/建筑/建材/工程 家居/室内设计/装饰装潢 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("计算机软件 IT服务(系统/数据/维护) 计算机硬件 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("信托/担保/拍卖/典当 房地产/建筑/建材/工程 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("通信/电信/网络设备 大型设备/机电设备/重工业 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("互联网/电子商务 计算机软件 网络游戏 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("基金/证券/期货/投资 专业服务/咨询(财会/法律/人力资源等) 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 外包服务 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("环保 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 零售/批发 跨领域经营 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("保险 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("学术/科研 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("电子技术/半导体/集成电路 互联网/电子商务 仪器仪表及工业自动化 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("物业管理/商业中心 租赁服务 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("贸易/进出口 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("中介服务 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("IT服务(系统/数据/维护) 通信/电信运营、增值服务 贸易/进出口 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("加工制造（原料加工/模具） 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("贸易/进出口 快速消费品（食品/饮料/烟酒/日化） 医疗/护理/美容/保健/卫生服务 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("家居/室内设计/装饰装潢 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 互联网/电子商务 电子技术/半导体/集成电路 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 加工制造（原料加工/模具） 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("媒体/出版/影视/文化传播 广告/会展/公关 娱乐/体育/休闲 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("印刷/包装/造纸 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("零售/批发 耐用消费品（服饰/纺织/皮革/家具/家电） 礼品/玩具/工艺美术/收藏品/奢侈品 家居/室内设计/装饰装潢 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("计算机硬件 IT服务(系统/数据/维护) 通信/电信运营、增值服务 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("电子技术/半导体/集成电路 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("计算机硬件 通信/电信运营、增值服务 大型设备/机电设备/重工业 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("互联网/电子商务 贸易/进出口 礼品/玩具/工艺美术/收藏品/奢侈品 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("互联网/电子商务 家居/室内设计/装饰装潢 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("基金/证券/期货/投资 贸易/进出口 互联网/电子商务 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("计算机软件 互联网/电子商务 网络游戏 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("政府/公共事业/非盈利机构 基金/证券/期货/投资 农/林/牧/渔 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("贸易/进出口 保险 旅游/度假 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("保险 互联网/电子商务 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("办公用品及设备 耐用消费品（服饰/纺织/皮革/家具/家电） 加工制造（原料加工/模具） 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("互联网/电子商务 IT服务(系统/数据/维护) 贸易/进出口 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("贸易/进出口 耐用消费品（服饰/纺织/皮革/家具/家电） 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("物流/仓储 快速消费品（食品/饮料/烟酒/日化） IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("通信/电信运营、增值服务 贸易/进出口 零售/批发 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("政府/公共事业/非盈利机构 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("零售/批发 礼品/玩具/工艺美术/收藏品/奢侈品 媒体/出版/影视/文化传播 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("零售/批发 医药/生物工程 医疗设备/器械 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("贸易/进出口 加工制造（原料加工/模具） 电子技术/半导体/集成电路 快速消费品（食品/饮料/烟酒/日化） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 保险 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 酒店/餐饮 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("媒体/出版/影视/文化传播 房地产/建筑/建材/工程 家居/室内设计/装饰装潢 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("房地产/建筑/建材/工程 中介服务 信托/担保/拍卖/典当 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("教育/培训/院校 媒体/出版/影视/文化传播 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("能源/矿产/采掘/冶炼 石油/石化/化工 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202301L};
        } else if ("广告/会展/公关 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("电子技术/半导体/集成电路 互联网/电子商务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 贸易/进出口 能源/矿产/采掘/冶炼 电气/电力/水利 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 中介服务 基金/证券/期货/投资 学术/科研 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("物业管理/商业中心 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("大型设备/机电设备/重工业 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("房地产/建筑/建材/工程 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("房地产/建筑/建材/工程 贸易/进出口 互联网/电子商务 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 礼品/玩具/工艺美术/收藏品/奢侈品 印刷/包装/造纸 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 零售/批发 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("计算机软件 互联网/电子商务 IT服务(系统/数据/维护) 网络游戏 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("互联网/电子商务 贸易/进出口 基金/证券/期货/投资 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("计算机软件 IT服务(系统/数据/维护) 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("互联网/电子商务 计算机软件 IT服务(系统/数据/维护) 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("汽车/摩托车 加工制造（原料加工/模具） 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("加工制造（原料加工/模具） 通信/电信/网络设备 汽车/摩托车 大型设备/机电设备/重工业 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("家居/室内设计/装饰装潢 耐用消费品（服饰/纺织/皮革/家具/家电） 礼品/玩具/工艺美术/收藏品/奢侈品 ".indexOf(category) != -1) {
            return new Long[]{107200300L, 107204300L, 107204301L};
        } else if ("教育/培训/院校 媒体/出版/影视/文化传播 外包服务 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("基金/证券/期货/投资 快速消费品（食品/饮料/烟酒/日化） 零售/批发 租赁服务 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 医疗/护理/美容/保健/卫生服务 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{107200300L, 107201300L, 107201302L};
        } else if ("互联网/电子商务 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("中介服务 医药/生物工程 房地产/建筑/建材/工程 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("通信/电信/网络设备 互联网/电子商务 电子技术/半导体/集成电路 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("保险 基金/证券/期货/投资 信托/担保/拍卖/典当 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("石油/石化/化工 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202302L};
        } else if ("基金/证券/期货/投资 保险 银行 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("互联网/电子商务 礼品/玩具/工艺美术/收藏品/奢侈品 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("贸易/进出口 礼品/玩具/工艺美术/收藏品/奢侈品 互联网/电子商务 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 大型设备/机电设备/重工业 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 零售/批发 教育/培训/院校 酒店/餐饮 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("广告/会展/公关 房地产/建筑/建材/工程 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("基金/证券/期货/投资 保险 基金/证券/期货/投资 银行 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("房地产/建筑/建材/工程 其他 物业管理/商业中心 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("仪器仪表及工业自动化 大型设备/机电设备/重工业 电气/电力/水利 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 中介服务 外包服务 其他 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("礼品/玩具/工艺美术/收藏品/奢侈品 贸易/进出口 耐用消费品（服饰/纺织/皮革/家具/家电） 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("教育/培训/院校 专业服务/咨询(财会/法律/人力资源等) 互联网/电子商务 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("保险 基金/证券/期货/投资 广告/会展/公关 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("房地产/建筑/建材/工程 家居/室内设计/装饰装潢 其他 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("媒体/出版/影视/文化传播 其他 广告/会展/公关 酒店/餐饮 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("礼品/玩具/工艺美术/收藏品/奢侈品 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("电子技术/半导体/集成电路 互联网/电子商务 加工制造（原料加工/模具） 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("IT服务(系统/数据/维护) 互联网/电子商务 计算机软件 网络游戏 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 互联网/电子商务 广告/会展/公关 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("加工制造（原料加工/模具） 大型设备/机电设备/重工业 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("电子技术/半导体/集成电路 计算机硬件 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("房地产/建筑/建材/工程 互联网/电子商务 专业服务/咨询(财会/法律/人力资源等) 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("房地产/建筑/建材/工程 家居/室内设计/装饰装潢 酒店/餐饮 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("零售/批发 礼品/玩具/工艺美术/收藏品/奢侈品 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 零售/批发 农/林/牧/渔 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("交通/运输 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("大型设备/机电设备/重工业 加工制造（原料加工/模具） 农/林/牧/渔 电气/电力/水利 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("通信/电信运营、增值服务 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("能源/矿产/采掘/冶炼 环保 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("互联网/电子商务 基金/证券/期货/投资 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 其他 耐用消费品（服饰/纺织/皮革/家具/家电） 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("信托/担保/拍卖/典当 保险 银行 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 教育/培训/院校 贸易/进出口 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("贸易/进出口 快速消费品（食品/饮料/烟酒/日化） 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 中介服务 其他 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 互联网/电子商务 跨领域经营 医疗/护理/美容/保健/卫生服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("基金/证券/期货/投资 基金/证券/期货/投资 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("仪器仪表及工业自动化 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("跨领域经营 医疗/护理/美容/保健/卫生服务 贸易/进出口 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("农/林/牧/渔 物流/仓储 交通/运输 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("计算机软件 IT服务(系统/数据/维护) 教育/培训/院校 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("互联网/电子商务 医疗设备/器械 酒店/餐饮 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("房地产/建筑/建材/工程 专业服务/咨询(财会/法律/人力资源等) 旅游/度假 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("基金/证券/期货/投资 银行 保险 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("IT服务(系统/数据/维护) 计算机硬件 计算机软件 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("交通/运输 贸易/进出口 物流/仓储 中介服务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("房地产/建筑/建材/工程 物业管理/商业中心 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("基金/证券/期货/投资 保险 其他 中介服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("互联网/电子商务 跨领域经营 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("酒店/餐饮 娱乐/体育/休闲 旅游/度假 其他 ".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 中介服务 农/林/牧/渔 其他 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("家居/室内设计/装饰装潢 房地产/建筑/建材/工程 耐用消费品（服饰/纺织/皮革/家具/家电） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("电子技术/半导体/集成电路 大型设备/机电设备/重工业 加工制造（原料加工/模具） ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("广告/会展/公关 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("基金/证券/期货/投资 信托/担保/拍卖/典当 银行 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("保险 互联网/电子商务 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("基金/证券/期货/投资 互联网/电子商务 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("教育/培训/院校 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("互联网/电子商务 电子技术/半导体/集成电路 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("基金/证券/期货/投资 保险 专业服务/咨询(财会/法律/人力资源等) 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("IT服务(系统/数据/维护) 互联网/电子商务 基金/证券/期货/投资 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("房地产/建筑/建材/工程 家居/室内设计/装饰装潢 电气/电力/水利 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("电子技术/半导体/集成电路 计算机软件 计算机硬件 其他 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("政府/公共事业/非盈利机构 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("互联网/电子商务 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("广告/会展/公关 媒体/出版/影视/文化传播 互联网/电子商务 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("计算机硬件 计算机软件 零售/批发 礼品/玩具/工艺美术/收藏品/奢侈品 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("环保 外包服务 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 互联网/电子商务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 零售/批发 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 加工制造（原料加工/模具） 零售/批发 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("医疗设备/器械 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205304L};
        } else if ("仪器仪表及工业自动化 办公用品及设备 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("大型设备/机电设备/重工业 电气/电力/水利 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 农/林/牧/渔 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 广告/会展/公关 贸易/进出口 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("基金/证券/期货/投资 房地产/建筑/建材/工程 网络游戏 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 其他 互联网/电子商务 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("大型设备/机电设备/重工业 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("教育/培训/院校 专业服务/咨询(财会/法律/人力资源等) 贸易/进出口 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 计算机软件 通信/电信/网络设备 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("基金/证券/期货/投资 跨领域经营 其他 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("IT服务(系统/数据/维护) 互联网/电子商务 计算机软件 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("快速消费品（食品/饮料/烟酒/日化） 零售/批发 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("IT服务(系统/数据/维护) 计算机硬件 计算机软件 外包服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("办公用品及设备 其他 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 教育/培训/院校 中介服务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("贸易/进出口 计算机硬件 计算机软件 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("环保 房地产/建筑/建材/工程 能源/矿产/采掘/冶炼 跨领域经营 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("农/林/牧/渔 交通/运输 贸易/进出口 物流/仓储 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("教育/培训/院校 媒体/出版/影视/文化传播 广告/会展/公关 政府/公共事业/非盈利机构 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("基金/证券/期货/投资 家居/室内设计/装饰装潢 房地产/建筑/建材/工程 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("互联网/电子商务 计算机硬件 计算机软件 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("旅游/度假 ".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202309L};
        } else if ("IT服务(系统/数据/维护) 计算机硬件 计算机软件 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("教育/培训/院校 互联网/电子商务 学术/科研 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("检验/检测/认证 贸易/进出口 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("房地产/建筑/建材/工程 租赁服务 中介服务 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("办公用品及设备 互联网/电子商务 媒体/出版/影视/文化传播 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("互联网/电子商务 贸易/进出口 大型设备/机电设备/重工业 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("计算机硬件 通信/电信运营、增值服务 IT服务(系统/数据/维护) 通信/电信/网络设备 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("信托/担保/拍卖/典当 互联网/电子商务 IT服务(系统/数据/维护) 基金/证券/期货/投资 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("电子技术/半导体/集成电路 媒体/出版/影视/文化传播 广告/会展/公关 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("耐用消费品（服饰/纺织/皮革/家具/家电） 互联网/电子商务 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("计算机硬件 IT服务(系统/数据/维护) 通信/电信运营、增值服务 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("零售/批发 礼品/玩具/工艺美术/收藏品/奢侈品 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108203300L, 108203301L};
        } else if ("农/林/牧/渔 其他 电子技术/半导体/集成电路 大型设备/机电设备/重工业 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("计算机硬件 IT服务(系统/数据/维护) 通信/电信/网络设备 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("贸易/进出口 互联网/电子商务 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("互联网/电子商务 印刷/包装/造纸 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204308L};
        } else if ("仪器仪表及工业自动化 通信/电信/网络设备 租赁服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("计算机软件 互联网/电子商务 计算机硬件 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("基金/证券/期货/投资 房地产/建筑/建材/工程 银行 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("医疗/护理/美容/保健/卫生服务 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("零售/批发 贸易/进出口 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("电子技术/半导体/集成电路 计算机软件 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("酒店/餐饮 快速消费品（食品/饮料/烟酒/日化） 零售/批发 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204304L};
        } else if ("加工制造（原料加工/模具） 仪器仪表及工业自动化 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("计算机软件 电子技术/半导体/集成电路 通信/电信运营、增值服务 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("计算机硬件 IT服务(系统/数据/维护) 互联网/电子商务 汽车/摩托车 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("仪器仪表及工业自动化 电子技术/半导体/集成电路 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("互联网/电子商务 媒体/出版/影视/文化传播 广告/会展/公关 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("中介服务 专业服务/咨询(财会/法律/人力资源等) 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("计算机软件 互联网/电子商务 IT服务(系统/数据/维护) 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("政府/公共事业/非盈利机构 其他 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("通信/电信/网络设备 电子技术/半导体/集成电路 仪器仪表及工业自动化 办公用品及设备 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("媒体/出版/影视/文化传播 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("仪器仪表及工业自动化 电子技术/半导体/集成电路 计算机软件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("网络游戏 外包服务 礼品/玩具/工艺美术/收藏品/奢侈品 广告/会展/公关 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("电子技术/半导体/集成电路 通信/电信/网络设备 IT服务(系统/数据/维护) ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("互联网/电子商务 网络游戏 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("医疗/护理/美容/保健/卫生服务 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("加工制造（原料加工/模具） 医药/生物工程 环保 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("房地产/建筑/建材/工程 中介服务 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("交通/运输 物流/仓储 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 教育/培训/院校 交通/运输 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("物流/仓储 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("通信/电信运营、增值服务 互联网/电子商务 加工制造（原料加工/模具） 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("通信/电信运营、增值服务 IT服务(系统/数据/维护) 通信/电信/网络设备 房地产/建筑/建材/工程 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("交通/运输 贸易/进出口 物流/仓储 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 计算机软件 计算机硬件 通信/电信运营、增值服务 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("零售/批发 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("医药/生物工程 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205302L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 基金/证券/期货/投资 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("电子技术/半导体/集成电路 广告/会展/公关 互联网/电子商务 媒体/出版/影视/文化传播 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("互联网/电子商务 计算机软件 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("零售/批发 大型设备/机电设备/重工业 贸易/进出口 互联网/电子商务 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108204300L, 108204307L};
        } else if ("电子技术/半导体/集成电路 电子技术/半导体/集成电路 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("仪器仪表及工业自动化 环保 大型设备/机电设备/重工业 ".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("贸易/进出口 汽车/摩托车 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("教育/培训/院校 计算机软件 IT服务(系统/数据/维护) 网络游戏 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("跨领域经营 互联网/电子商务 保险 汽车/摩托车 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("基金/证券/期货/投资 银行 专业服务/咨询(财会/法律/人力资源等) 中介服务 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("礼品/玩具/工艺美术/收藏品/奢侈品 贸易/进出口 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("互联网/电子商务 媒体/出版/影视/文化传播 中介服务 教育/培训/院校 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("零售/批发 贸易/进出口 办公用品及设备 其他 ".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202301L};
        } else if ("汽车/摩托车 大型设备/机电设备/重工业 加工制造（原料加工/模具） 专业服务/咨询(财会/法律/人力资源等) ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("房地产/建筑/建材/工程 零售/批发 家居/室内设计/装饰装潢 ".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("专业服务/咨询(财会/法律/人力资源等) 基金/证券/期货/投资 房地产/建筑/建材/工程 物业管理/商业中心 ".indexOf(category) != -1) {
            return new Long[]{109200300L, 109201300L, 109201304L};
        } else if ("通信/电信/网络设备 计算机硬件 ".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204308L};
        } else if ("广告/会展/公关 房地产/建筑/建材/工程 家居/室内设计/装饰装潢 其他 ".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("生产·制造·加工(金属·建材·塑胶·玻璃·陶瓷…)".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("房地产开发·建筑与工程·服务(中介·物业·监理·设计)".indexOf(category) != -1) {
            return new Long[]{106200300L, 106202300L, 106202301L};
        } else if ("金融业(银行·保险·证券·基金·期货·投资)".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("电子·微电子".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("办公设备·用品".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("机械制造·机电·重工".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("耐腐蚀泵;电镀设备;空气净化成套设备;过滤机;油烟净化设备;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("建筑装修施工;其他维修及安装;综合性公司;创意设计;".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("广告策划;平面设计;企业形象设计;宣传品设计;商标设计;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("商业服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("服装;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204301L};
        } else if ("汽车保修设备与工具;".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203303L};
        } else if ("招聘职位;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;招聘职位;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("综合性公司;中介服务加盟;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;职业培训;人才中介;管理培训;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("投资咨询;其他广告服务;其他移动电话;家政服务;网络工程;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("其他商务服务;招聘职位;综合性公司;保险服务;金融服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("综合性公司;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;劳务输出;保险服务;人才中介;安全保卫;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("其他商务服务;咨询服务;企业日常服务;招聘职位;求职简历;中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;人才中介;其他未分类;广告制作;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("保险服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("宠物;包装机械;综合性公司;其他防盗;报警器材及系统;农产品代理加盟;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("劳务输出;招聘职位;求职简历;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("网页设计;招聘职位;广告发布;电子行业认证;人才中介;销售岗位求职;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("其他代理加盟;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("投资咨询;管理咨询;其他咨询;财务咨询;综合性公司;公司注册服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;综合性公司;财务会计岗位求职;管理岗位求职;行政人事岗位求职;销售岗位求职;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("综合性公司;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("公共广播系统;广告策划;电影放映设备;舞台设备;影视节目制作;专业音响;录音设备;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("租赁;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("工人岗位求职;招聘职位;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;人才中介;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;求职简历;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("其他商务服务;招聘职位;超市;百货;便利店;人才中介;其他岗位求职;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("图片;画册;女式T恤;招聘职位;饰品配附件;宣传品设计;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;租赁服务;公司注册服务;会计服务;法律服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;中介服务;特许经营;保险服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("劳务输出;移民签证;综合性公司;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("门禁考勤系统;IC卡;指纹识别仪;考勤机;磁卡;一卡通管理系统;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("招聘职位;广告发布;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("综合性公司;其他未分类;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("网络测试设备;综合性公司;一卡通管理系统;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("广告;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("跑步机;台球用品;其他体育运动配套产品;泳装;泳裤;室内健身服;健腹器;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204301L};
        } else if ("劳务输出;人才中介;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("IT;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204319L};
        } else if ("教育装备;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("咨询服务;招聘职位;保险服务;金融服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("投资咨询;咨询服务;管理咨询;职业培训;教育培训;管理培训;金融服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("机械工业;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("电子;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203301L};
        } else if ("广告策划;招聘职位;广告发布;公司注册服务;广告制作;广告代理;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("其他商务服务;招聘职位;厂房;租赁服务;公司注册服务;土地;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("视讯会议系统;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;中介服务;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("其他太阳能设备;太阳能热水器;综合性公司;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202301L};
        } else if ("招聘职位;求职简历;网店装修;页面图片设计;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("其他商务服务;招聘职位;中介服务;教育培训;人才中介;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("人才中介;其他代理加盟;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("网页设计;招聘职位;综合性公司;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("模具加工;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("广告促销礼品;招聘职位;综合性公司;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("咨询服务;招聘职位;求职简历;检测服务;教育培训;认证服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("其他商务服务;招聘职位;中介服务;求职简历;教育培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("包装产品代理加盟;其他未分类;塑料包装制品;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("制服;工作服;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204301L};
        } else if ("其他咨询;塑料模;模具设计;".indexOf(category) != -1) {
            return new Long[]{101200300L, 101202300L, 101202306L};
        } else if ("其他未分类;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("机械及行业设备模具加工设备及配件;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("其他广告服务;招聘职位;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("咨询服务;企业日常服务;招聘职位;中介服务;公司注册服务;会计服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("区域代理加盟;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("其他室内照明灯具;车顶灯;其他指示灯具;其他室外照明灯具;LED灯具;照明加工;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("招聘职位;中介服务加盟;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;旅行社;旅游公司;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("其他皮肤用化学品;植物提取物;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205302L};
        } else if ("管理咨询;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;招聘职位;管理培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;综合性公司;家用电器产品代理加盟;管理体系认证;财务咨询;公司注册服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("发光二极管;刹车灯;应急指示灯具;交通警示灯;信号灯;工矿灯具;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("表面处理;".indexOf(category) != -1) {
            return new Long[]{101200300L, 101205300L, 101205301L};
        } else if ("招聘职位;综合性公司;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("汽摩配件加工;汽车装潢内饰用品;二手汽车;汽车及配件设计;汽车户外用品;维修设备;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("咨询服务;招聘职位;求职简历;教育培训;广告服务;认证服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("招聘职位;中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("加脂剂;综合性公司;人才中介;泡沫塑料;塑料管;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;其他未分类;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;招聘职位;职业培训;人才中介;管理培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("工具软件;管理软件;软件开发;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("广告策划;其他广告服务;广告发布;广告制作;广告代理;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("笔记本;记事本;二手电脑及用品;数码;电脑维修安装;".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202307L};
        } else if ("女装;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204301L};
        } else if ("建材;".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("管理咨询;招聘职位;职业培训;人才中介;管理培训;拓展培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;招聘职位;人才中介;其他岗位求职;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("商务服务中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("投资咨询;管理咨询;家居用品代理加盟;招聘职位;保险服务;金融服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("招聘职位;综合性公司;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("香港酒店招聘;香港公;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("广告代理;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("咨询服务;招聘职位;中介服务;认证服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;综合性公司;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("软件开发;行业专用软件;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("数码相框;数码摄像机;主板;数码相机;游戏机及周边;笔记本电脑;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("综合性公司;保险服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108201300L, 108201314L};
        } else if ("其他商务服务;劳务输出;其他教育培训;人才中介;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;招聘职位;人才中介;管理培训;中介服务加盟;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("声讯系统;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;招聘职位;其他教育培训;职业培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("广告策划;影视节目制作;平面设计;广告发布;企业形象设计;商标设计;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("其他商务服务;招聘职位;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("礼仪服务;展台设计搭建;舞台灯具;翻译服务;".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("化学试剂;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102205300L, 102205302L};
        } else if ("劳务输出;综合性公司;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;职业培训;管理培训;法律服务;其他中介服务;其他公关服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205303L};
        } else if ("单片机;稳压IC;发光二极管;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;人才中介;认证服务;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("国外招聘;国内招聘;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("专利版权转让;劳务输出;其他咨询;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("工程塑料;综合性公司;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("劳务输出;管理培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("网页设计;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204301L};
        } else if ("天然工艺品;".indexOf(category) != -1) {
            return new Long[]{107200300L, 107204300L, 107204301L};
        } else if ("五金;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("招聘职位;网络管理岗位求职;软件开发;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
        } else if ("建筑建材工程机械;建筑机械;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("劳务输出;综合性公司;中介服务加盟;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("工人岗位求职;劳务输出;招聘职位;行政人事岗位求职;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;广告发布;综合性公司;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("招聘职位;广告服务;创意设计;软件开发;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("电话报警设备;监控摄像机;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("集装箱;国内陆运;".indexOf(category) != -1) {
            return new Long[]{103200300L,103202300L, 103202310L};
        } else if ("劳务输出;其他咨询;综合性公司;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("投资咨询;语言培训;管理咨询;招聘职位;人才中介;管理培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("工人岗位求职;其他商务服务;劳务输出;管理咨询;招聘职位;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("康复理疗设备;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201310L};
        } else if ("其他鼠标;键盘;CRT显示器;其他用途纸;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204308L};
        } else if ("通讯产品;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204321L};
        } else if ("印刷耗材;移印机;塑料印刷;UV油墨;丝印机;塑料油墨;".indexOf(category) != -1) {
            return new Long[]{102200300L,102202300L, 102202302L};
        } else if ("管理咨询;劳务输出;专业技能培训;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;劳务输出;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("商务服务;广告;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("非机动车;其他专用汽车;船舶;电子代理加盟;电脑产品代理加盟;区域代理加盟;".indexOf(category) != -1) {
            return new Long[]{103200300L, 103203300L, 103203303L};
        } else if ("监视器;活动房;监控摄像机;其他建筑装修施工;建筑护栏;".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("女式T恤;国际海运;综合性公司;其他代理加盟;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204301L};
        } else if ("招聘职位;其他建筑装修施工;综合性公司;公司注册服务;".indexOf(category) != -1) {
            return new Long[]{101200300L, 101201300L, 101201301L};
        } else if ("招聘职位;广告发布;人才中介;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("产品描述模板;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204301L};
        } else if ("管理咨询;劳务输出;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("管理咨询;招聘职位;人才中介;管理培训;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("广告发布;人才中介;其他未分类;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("工人岗位求职;劳务输出;招聘职位;人才中介;兼职简历;其他中介服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("外贸岗位求职;招聘职位;网络管理岗位求职;咨询顾问岗位求职;管理岗位求职;销售岗位求职;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("现场招聘;网络招聘;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("广告发布;国际海运;国际空运;货代;国内陆运;商检报关;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("其他商务服务;劳务输出;其他租赁;人才中介;工人招聘;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("咨询服务;招聘职位;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("库存图书;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("人才中介;市场调研;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("KTV服务;人员招聘;公关服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205303L};
        } else if ("其他商务服务;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102204300L, 102204301L};
        } else if ("能源;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110202300L, 110202301L};
        } else if ("检测服务;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102203300L, 102203305L};
        } else if ("环保包装;包装产品加工;玩具包装;手机挂件;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102201300L, 102201309L};
        } else if ("咨询服务;招聘职位;中介服务;求职简历;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("绝缘材料;其他绝缘材料;电容器;其他电容器;二极管;电烙铁;装配电动工具;其他焊接材料与附件;其他二极管;".indexOf(category) != -1) {
            return new Long[]{103200300L, 103202300L, 103202307L};
        } else if ("开关三极管;大功率继电器;其他IC;发光二极管;可控硅(晶闸管);其他连接器;".indexOf(category) != -1) {
            return new Long[]{102200300L, 102202300L, 102202302L};
        } else if ("酒店;".indexOf(category) != -1) {
            return new Long[]{105200300L, 105202300L, 105202304L};
        } else if ("其他咨询;移民签证;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("咨询服务;招聘职位;求职简历;综合性公司;广告服务;创意设计;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205308L};
        } else if ("咨询服务;招聘职位;中介服务;金融服务;".indexOf(category) != -1) {
            return new Long[]{108200300L, 108205300L, 108205307L};
        } else if ("招聘职位;软件开发;".indexOf(category) != -1) {
            return new Long[]{110200300L, 110204300L, 110204305L};
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
            if (company.getCompanyName() != null && company.getCompanyName().indexOf(companyName)!=-1) {
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
