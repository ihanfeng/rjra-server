package com.hdg.rjra.server.serviceimpl;

import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.base.enumerate.DataResourceType;
import com.hdg.rjra.base.enumerate.ResumeStatus;
import com.hdg.rjra.base.enumerate.ResumeWorkStatus;
import com.hdg.rjra.rdb.proxy.domain.Company;
import com.hdg.rjra.rdb.proxy.domain.Resume;
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
                    if(r % 50 == 0){
                        sumResumeList.add(resumeList);
                        resumeList = new ArrayList<Resume>();
                    }
                    HSSFRow row = sheet.getRow(r);
                    if (row == null) {
                        continue;
                    }
                    Resume resume = new Resume();
//                    resume.setCategoryLevel1Ids();
//                    resume.setCategoryLevel2Ids();
//                    resume.setCategoryLevel3Ids();
//                    resume.setResumeBirthday();
//                    resume.setResumeCreateTime(new Date());
//                    resume.setResumeDesc();
//                    resume.setResumeExperience();
//                    resume.setResumeGender();
//                    resume.setResumeHomeAddress();
//                    resume.setResumeHomeAreaId(-1L);
//                    resume.setResumeHomeCityId(-1L);
//                    resume.setResumeHomeProvinceId(-1L);
//                    resume.setResumeLatitude();
//                    resume.setResumeLongitude();
//                    resume.setResumeMobile((row.getCell(13).getStringCellValue()+"").replace("'","").trim());
//                    resume.setResumeQQ(row.getCell(13).getStringCellValue());
//                    resume.setResumeRefreshTime(new Date());
//                    resume.setResumeStatus(ResumeStatus.Active.getCode());
//                    resume.setResumeUpdateTime(new Date());
//                    resume.setResumeUserName(row.getCell(1).getStringCellValue());
//                    resume.setResumeWage(1L);
//                    resume.setResumeWorkStatus(ResumeWorkStatus.Idle.getCode());
//                    resume.setResumeWantWorkAreaId(-1L);
//                    resume.setResumeWantWorkCityId(-1L);
//                    resume.setResumeWantWorkProvinceId(-1L);
//                    resume.setResumeDataType(DataResourceType.Crawl.getCode());
//                    resume.setResumeTag("hhh");
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
}
