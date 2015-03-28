package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.importlog.ImportData;
import com.hdg.rjra.server.model.bo.importlog.ImportLogBo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface ImportLogService {
    public ImportLogBo company(List<ImportData> importDataList) throws IOException;

    public ImportLogBo work(List<ImportData> importDataList);

    public ImportLogBo resume(ImportLogBo importLogBo, InputStream fileInputStream);
}
