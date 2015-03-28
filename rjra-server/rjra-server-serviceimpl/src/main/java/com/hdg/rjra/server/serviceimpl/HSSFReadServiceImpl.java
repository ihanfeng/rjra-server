package com.hdg.rjra.server.serviceimpl;

import com.hdg.rjra.server.model.bo.importlog.ImportData;
import com.hdg.rjra.server.service.HSSFReadService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/3/28.
 */
@Service
public class HSSFReadServiceImpl implements HSSFReadService {

    @Override
    public List<ImportData> readExcel(InputStream in) throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook(in);
        List<Map<Integer, Object>> dataMap = new ArrayList<Map<Integer, Object>>();

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
        List<ImportData> importDataList = new ArrayList<ImportData>();
        for (int i = 0; i < dataMap.size(); i++) {
            Map<Integer, Object> data = dataMap.get(i);
            String companyName = (String) data.get(0);
            String contactName = (String) data.get(1);
            String mobile = (String) (data.get(5) == null ? data.get(4) : data.get(5));
            String email = (String) data.get(7);
            String address = (String) data.get(8);
            String category = (String) data.get(12);
            String companyDesc = (String) data.get(15);
            String companyType = (String) data.get(16);
            String companyScale = (String) data.get(18);
            String province = (String) data.get(20);
            String city = (String) data.get(21);
            String qq = (String) data.get(29);
            ImportData importData = new ImportData();
            importData.setCompanyName(companyName);
            importData.setContactName(contactName);
            importData.setMobile(mobile);
            importData.setEmail(email);
            importData.setAddress(address);
            importData.setCategory(category);
            importData.setCompanyDesc(companyDesc);
            importData.setCompanyType(companyType);
            importData.setCompanyScale(companyScale);
            importData.setProvince(province);
            importData.setCity(city);
            importData.setQq(qq);
            importDataList.add(importData);
        }
        return importDataList;
    }
}
