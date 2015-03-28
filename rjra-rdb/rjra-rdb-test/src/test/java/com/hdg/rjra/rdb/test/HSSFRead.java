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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File for HSSF testing/examples
 * <p/>
 * THIS IS NOT THE MAIN HSSF FILE!! This is a utility for testing functionality.
 * It does contain sample API usage that may be educational to regular API
 * users.
 *
 * @author Andrew Oliver (acoliver at apache dot org)
 * @see #main
 */
public final class HSSFRead {
    static List<Map<Integer, Object>> dataMap = new ArrayList<Map<Integer, Object>>();

    /**
     * creates an {@link org.apache.poi.hssf.usermodel.HSSFWorkbook} the specified OS filename.
     */
    private static HSSFWorkbook readFile(String filename) throws IOException {
        return new HSSFWorkbook(new FileInputStream(filename));
    }


    public static void main(String[] args) {

        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("E:\\excel\\赶集.xls"));

            System.out.println("Data dump:\n");
            int count = 1;
            for (int k = 0; k < wb.getNumberOfSheets(); k++) {
                HSSFSheet sheet = wb.getSheetAt(k);
                int rows = sheet.getPhysicalNumberOfRows();
                System.out.println("Sheet " + k + " \"" + wb.getSheetName(k) + "\" has " + rows
                        + " row(s).");
                for (int r = 0; r < rows; r++) {
                    HSSFRow row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }

                    int cells = row.getPhysicalNumberOfCells();
                    Map<Integer, Object> data = new HashMap<Integer, Object>();

                    for (int c = 0; c < cells; c++) {
                        HSSFCell cell = row.getCell(c);
                        String value = null;

                        switch (cell.getCellType()) {

                            case HSSFCell.CELL_TYPE_FORMULA:
                                value = cell.getCellFormula();
                                break;

                            case HSSFCell.CELL_TYPE_NUMERIC:
                                value = String.valueOf(cell.getNumericCellValue());
                                break;

                            case HSSFCell.CELL_TYPE_STRING:
                                value = cell.getStringCellValue();
                                break;

                            default:
                        }
                        data.put(c, value);
                        System.out.println("CELL col=" + cell.getColumnIndex() + " VALUE="
                                + value);
                    }
                    dataMap.add(data);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (int i = 0; i < dataMap.size(); i++) {
            Map<Integer,Object> data = dataMap.get(i);
            String companyName = (String) data.get(0);
            String contactName = (String) data.get(1);
            String mobile = (String) (data.get(5) == null ? data.get(4): data.get(5));
            String email = (String) data.get(7);
            String category = (String) data.get(12);
            String companyDesc = (String) data.get(15);
            String companyType = (String) data.get(16);
            String companyScale = (String) data.get(18);
            String province = (String) data.get(20);
            String city = (String) data.get(21);
            String qq = (String) data.get(29);
            System.out.println(companyName);
            System.out.println(contactName);
            System.out.println(mobile);
            System.out.println(email);
            System.out.println(category);
            System.out.println(companyDesc);
            System.out.println(companyType);
            System.out.println(companyScale);
            System.out.println(province);
            System.out.println(city);
            System.out.println(qq);
        }
    }
}