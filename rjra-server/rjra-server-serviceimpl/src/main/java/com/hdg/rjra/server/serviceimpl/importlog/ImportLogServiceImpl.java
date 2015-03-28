package com.hdg.rjra.server.serviceimpl.importlog;

import com.hdg.rjra.server.model.bo.importlog.ImportData;
import com.hdg.rjra.server.serviceimpl.importlog.BaseImportLogServiceImpl;
import org.springframework.stereotype.Service;


/**
 * Created by Administrator on 2015/3/26.
 */
@Service
public class ImportLogServiceImpl extends BaseImportLogServiceImpl {


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
}
