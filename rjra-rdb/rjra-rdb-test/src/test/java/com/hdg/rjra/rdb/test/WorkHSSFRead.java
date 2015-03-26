/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package com.hdg.rjra.rdb.test;

import com.hdg.common.utils.HttpRequestUtils;
import com.hdg.common.utils.JsonUtils;
import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.enumerate.WorkStatus;
import com.hdg.rjra.rdb.proxy.domain.*;
import com.hdg.rjra.rdb.test.geo.AreaGeoInfo;
import com.hdg.rjra.rdb.test.geo.GeocoderSearchResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * File for HSSF testing/examples
 * <p/>
 * THIS IS NOT THE MAIN HSSF FILE!! This is a utility for testing functionality.
 * It does contain sample API usage that may be educational to regular API
 * users.
 *
 * @author Andrew Oliver (acoliver at apache dot org)
 */
public final class WorkHSSFRead extends AbstractTest {

    /**
     * creates an {@link org.apache.poi.hssf.usermodel.HSSFWorkbook} the specified OS filename.
     */
    private static HSSFWorkbook readFile(String filename) throws IOException {
        return new HSSFWorkbook(new FileInputStream(filename));
    }

    @Test
    public void test() {
        String fileName = "e://work.xls";
        new ArrayList<Long>();
        List<Work> workList = new ArrayList<Work>();
        List<List<Work>> sumWorkList = new ArrayList<List<Work>>();
        List<Area> areaList = rdbDefaultClient.invoke("rdb-area", "findAllArea",
                new Object[]{});
        List<City> cityList = rdbDefaultClient.invoke("rdb-city", "findAllCity",
                new Object[]{});
        List<Province> provinceList = rdbDefaultClient.invoke("rdb-province", "findAllProvince",
                new Object[]{});

        Pager<Company> companyPager = rdbDefaultClient.invoke("rdb-company", "findAllCompanyByConditionPager",
                new Object[]{new Company(),0,999999});
        int count =1;
        try {
            HSSFWorkbook wb = WorkHSSFRead.readFile(fileName);

            System.out.println("Data dump:\n");
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                HSSFSheet sheet = wb.getSheetAt(k);
                int rows = sheet.getPhysicalNumberOfRows();
                System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
                        + " row(s).");
                for (int r = 1; r < rows; r++) {
                    if (count % 50 == 0) {
                        sumWorkList.add(workList);
                        workList = new ArrayList<Work>();
                    }
                    HSSFRow row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    String REGEXP = "\\[(.+)最新招聘信息\\]诚聘(.+)([^\\d])(\\d{4})?((\\d+)人|(若干)),工作地点位于(.+),公司规模(.+),薪资待遇(不限|(.+)元),工作经验(.+),学历要求(高中|大专|不限|中专),(.+)";
                    String desc = row.getCell(9).getStringCellValue();
                    if(!desc.matches(REGEXP)){
                        continue;
                    }
                    Pattern p = Pattern.compile(REGEXP, Pattern.CASE_INSENSITIVE);
                    Matcher m = p.matcher(desc);
                    Work work = new Work();
                    work.setWorkDataType(DataResourceType.Crawl.getCode());
                    work.setWorkTag("hello");
                    String ads = "";
                    if (m.find()) {
                        String companyName = m.group(1);
                        String workDesc = m.group(14);
                        Integer needPeople = StringUtils.isEmpty(m.group(6))?1000:Integer.parseInt(m.group(6));
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
                        work.setCompanyName(companyName);
                        work.setCompanyId(findCompany(companyName, companyPager.getResultList()));
                        work.setWorkDesc(workDesc);
                        work.setWorkNeedPerson(needPeople);
                        work.setWorkWageId(findWage(wage));
                    }
                    String address =  ads + row.getCell(7).getStringCellValue();
                    //http://api.map.baidu.com/geocoder/v2/?output=json&ak=X4R0e9z7r4L3onULLOwGBdAD&address=
                    String baiduRequest = "http://api.map.baidu.com/geocoder/v2/?output=json&ak=X4R0e9z7r4L3onULLOwGBdAD&address=" + address;
                    System.out.println(baiduRequest);
                    String requestJson = HttpRequestUtils.sendGet(baiduRequest);
                    System.out.println(requestJson);
                    GeocoderSearchResponse geocoderSearchResponse = JsonUtils.jsonToObject(requestJson, GeocoderSearchResponse.class);
                    Double lat = geocoderSearchResponse.getResult().getLocation().getLat();
                    Double lng = geocoderSearchResponse.getResult().getLocation().getLng();
                    String findAreaRequest = "http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&location=" + lat + "," + lng + "&output=json&pois=1";
                    System.out.println(findAreaRequest);
                    String requestAreaJson = HttpRequestUtils.sendGet(findAreaRequest);
                    System.out.println(requestAreaJson);
                    AreaGeoInfo areaGeoInfo = JsonUtils.jsonToObject(requestAreaJson, AreaGeoInfo.class);
                    work.setWorkLongitude(lng);
                    work.setWorkLatitude(lat);
                    work.setUserId(-1L);
                    Long[] category = findCategory(row.getCell(8).getStringCellValue());
                    work.setCategoryLevel1Id(category[0]);
                    work.setCategoryLevel2Id(category[1]);
                    work.setCategoryLevel3Id(category[2]);
                    work.setWorkAreaId(findAreaId(areaGeoInfo.getResult().getAddressComponent().getDistrict(), areaList));
                    work.setWorkCityId(findCityId(areaGeoInfo.getResult().getAddressComponent().getCity(), cityList));
                    work.setWorkProvinceId(findProvinceId(areaGeoInfo.getResult().getAddressComponent().getProvince(), provinceList));
                    work.setWorkAddress(address);
                    work.setWorkExperienceId(1L);
                    work.setWorkWelfareIds(new Long[]{});
                    work.setWorkStatus(WorkStatus.Active.getCode());
                    work.setWorkCreateTime(new Date());
                    work.setWorkUpdateTime(new Date());
                    work.setAgeId(1L);
                    work.setWorkGender(2);//http://api.map.baidu.com/geocoder/v2/?ak=X4R0e9z7r4L3onULLOwGBdAD&callback=renderReverse&location=22.659210205235,114.03049680522&output=xml&pois=1
                    workList.add(work);
                    int cells = row.getPhysicalNumberOfCells();
                    System.out.println("\nROW " + row.getRowNum() + " has " + cells
                            + " cell(s).");
                    count ++;
                }
            }
            sumWorkList.add(workList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Long> deletIds = new ArrayList<Long>();
        for (int i = 0; i < sumWorkList.size(); i++) {
            deletIds.addAll((List<Long>) rdbDefaultClient.invoke("rdb-work", "batchSaveWork",
                    new Object[]{sumWorkList.get(i)}));
            System.out.println(i);
        }
        rdbDefaultClient.invoke("rdb-work", "deleteWork",
                new Object[]{deletIds});
        System.out.println("over");
    }

    private Long findCompany(String companyName, List<Company> companyList) {
        for (Company company : companyList) {
            if (company.getCompanyName() != null && company.getCompanyName().trim().equals(companyName)) {
                return company.getCompanyId();
            }
        }
        return -1L;
    }

    private static Long findWage(String wage) {

        if ("1500元以下".equals(wage)) {
            return 2L;
        } else if ("1500-2000".equals(wage)) {
            return 3L;
        } else if ("2000-3000".equals(wage)) {
            return 4L;
        } else if ("3000-5000".equals(wage)) {
            return 5L;
        }else if ("5000-8000".equals(wage)) {
            return 6L;
        }else if ("8000-12000".equals(wage)) {
            return 7L;
        }else if ("12000-15000".equals(wage)) {
            return 8L;
        }else if ("15000-20000".equals(wage)) {
            return 9L;
        }else if ("20000元以上".equals(wage)) {
            return 10L;
        }
        return 1L;
    }

    private static Long[] findCategory(String category) {
        if (category == null) {
            return new Long[]{110200300L,110205300L,-1L};
        }
        category = category.trim();
        if ("金融/银行".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("互联网/电子商务".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("计算机软件".equals(category)) {
            return new Long[]{110200300L,110204300L,110204305L};
        } else if ("教育/科研/培训".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("批发/零售".equals(category)) {
            return new Long[]{108200300L,108203300L,108203301L};
        } else if ("原材料和加工".equals(category)) {
            return new Long[]{102200300L,102201300L,102201309L};
        } else if ("贸易/进出口".equals(category)) {
            return new Long[]{103200300L,103202300L,103202301L};
        } else if ("娱乐休闲/餐饮/服务".equals(category)) {
            return new Long[]{105200300L,105201300L,105201303L};
        } else if ("人力资源服务".equals(category)) {
            return new Long[]{109200300L,109201300L,109201304L};
        } else if ("中介/专业服务".equals(category)) {
            return new Long[]{108200300L,108205300L,108205307L};
        } else if ("财务/审计".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("房地产/物业管理".equals(category)) {
            return new Long[]{106200300L,106202300L,106202301L};
        } else if ("广告/创意".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("医疗/保健/卫生/美容".equals(category)) {
            return new Long[]{107200300L,107201300L,107201302L};
        } else if ("钢铁/机械/设备/重工".equals(category)) {
            return new Long[]{102200300L,102201300L,102201310L};
        } else if ("交通/运输/物流".equals(category)) {
            return new Long[]{103200300L,103202300L,103202301L};
        } else if ("旅游/酒店".equals(category)) {
            return new Long[]{105200300L,105202300L,105202304L};
        } else if ("仪器仪表/工业自动化".equals(category)) {
            return new Long[]{102200300L,102202300L,102202302L};
        } else if ("通信/电信".equals(category)) {
            return new Long[]{110200300L,110204300L,110204321L};
        } else if ("文体/影视/艺术".equals(category)) {
            return new Long[]{107200300L,107204300L,107204301L};
        } else if ("耐用消费品(家具/家电等)".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("计算机硬件".equals(category)) {
            return new Long[]{110200300L,110204300L,110204308L};
        } else if ("媒体传播".equals(category)) {
            return new Long[]{108200300L,108205300L,108205308L};
        } else if ("生物/制药/医疗器械".equals(category)) {
            return new Long[]{102200300L,102205300L,102205304L};
        } else if ("电子技术/半导体/集成电路".equals(category)) {
            return new Long[]{102200300L,102203300L,102203301L};
        } else if ("公关/市场推广/会展".equals(category)) {
            return new Long[]{108200300L,108205300L,108205303L};
        } else if ("建筑/建材".equals(category)) {
            return new Long[]{101200300L,101201300L,101201301L};
        } else if ("政府/非盈利机构".equals(category)) {
            return new Long[]{110200300L,110205300L,-1L};
        } else if ("出版/印刷/造纸".equals(category)) {
            return new Long[]{102200300L,102204300L,102204308L};
        } else if ("农林牧渔".equals(category)) {
            return new Long[]{110200300L,110201300L,110201303L};
        } else if ("快速消费品(食品/饮料等)".equals(category)) {
            return new Long[]{102200300L,102204300L,102204304L};
        } else if ("汽车/摩托车".equals(category)) {
            return new Long[]{103200300L,103203300L,103203303L};
        } else {
            return new Long[]{110200300L,110205300L,-1L};
        }
    }

    private static Long companyScaleResolve(String scale) {
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

    private static Long companyScaleType(String type) {
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
}