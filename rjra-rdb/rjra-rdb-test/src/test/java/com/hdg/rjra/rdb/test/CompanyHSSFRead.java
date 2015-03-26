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

import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.rdb.proxy.domain.Company;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * File for HSSF testing/examples
 * <p/>
 * THIS IS NOT THE MAIN HSSF FILE!! This is a utility for testing functionality.
 * It does contain sample API usage that may be educational to regular API
 * users.
 *
 * @author Andrew Oliver (acoliver at apache dot org)
 */
public final class CompanyHSSFRead extends  AbstractTest{

    /**
     * creates an {@link org.apache.poi.hssf.usermodel.HSSFWorkbook} the specified OS filename.
     */
    private static HSSFWorkbook readFile(String filename) throws IOException {
        return new HSSFWorkbook(new FileInputStream(filename));
    }

    @Test
    public void test() {
        String fileName = "e://company3.xls";
        new ArrayList<Long>();
        List<Company> companyList = new ArrayList<Company>();
        List<List<Company>> sumCompanyList = new ArrayList<List<Company>>();
        try {
            HSSFWorkbook wb = CompanyHSSFRead.readFile(fileName);

            System.out.println("Data dump:\n");
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                HSSFSheet sheet = wb.getSheetAt(k);
                int rows = sheet.getPhysicalNumberOfRows();
                System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
                        + " row(s).");
                for (int r = 1; r < rows; r++) {
                    if(r % 50 == 0){
                        sumCompanyList.add(companyList);
                        companyList = new ArrayList<Company>();
                    }
                    HSSFRow row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    Company company = new Company();
                    company.setCompanyName(row.getCell(0).getStringCellValue());
                    company.setCompanyContact(row.getCell(1).getStringCellValue());
                    company.setCompanyContactMobile(StringUtils.isEmpty(row.getCell(3).getStringCellValue()) ? row.getCell(2).getStringCellValue() : row.getCell(3).getStringCellValue());
                            company.setCompanyEmail(row.getCell(4).getStringCellValue());
                    company.setCompanyAddress(row.getCell(5).getStringCellValue());
                    company.setCompanyDesc(row.getCell(6).getStringCellValue());
                    company.setCompanyType(companyScaleType(row.getCell(7).getStringCellValue()));
                    company.setCompanyScale(companyScaleResolve(row.getCell(8).getStringCellValue()));
                    company.setCompanyDataType(DataResourceType.Crawl.getCode());
                    company.setCompanyTag("hhh");
                    companyList.add(company);
                    int cells = row.getPhysicalNumberOfCells();
                    System.out.println("\nROW " + row.getRowNum() + " has " + cells
                            + " cell(s).");
                }
            }
            sumCompanyList.add(companyList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Long> deletIds = new ArrayList<Long>();
        for (int i = 0; i < sumCompanyList.size(); i++) {
            deletIds.addAll((List<Long>) rdbDefaultClient.invoke("rdb-company", "batchSaveCompany",
                    new Object[]{sumCompanyList.get(i)}));
            System.out.println(i);
        }
//        rdbDefaultClient.invoke("rdb-company", "deleteCompany",
//                new Object[]{deletIds});
        System.out.println("over");
    }

    private static Long companyScaleResolve(String scale){
        if (scale == null) {
            return -1L;
        }
        scale = scale.trim();
        if("1-49人".equals(scale)){
            return 1L;
        }else  if("50-99人".equals(scale)){
            return 2L;
        }else if("100-499人".equals(scale)){
            return 4L;
        }else if("500-999人".equals(scale)){
            return 6L;
        }else if("1000人以上".equals(scale)){
            return 7L;
        }else {
            return -1L;
        }
    }

    private static Long companyScaleType(String type){
        if (type == null) {
            return -1L;
        }
        type = type.trim();
        if("私营".equals(type)){
            return 1L;
        }else  if("国有".equals(type)){
            return 2L;
        }else if("外商独资".equals(type)){
            return 3L;
        }else if("个人企业".equals(type)){
            return 4L;
        }else if("非盈利机构".equals(type)){
            return 5L;
        }else if("股份制".equals(type)){
            return 6L;
        }else if("上市公司".equals(type)){
            return 7L;
        }else if("事业单位".equals(type)){
            return 8L;
        }else if("政府机关".equals(type)){
            return 9L;
        }else if("中外合资".equals(type)){
            return 10L;
        }else {
            return -1L;
        }
    }
}