package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.importlog.ImportLogBo;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/3/24.
 */
public interface ImportLogService {
    public ImportLogBo company(ImportLogBo importLogBo, InputStream fileInputStream) throws IOException;

    public ImportLogBo work(ImportLogBo importLogBo, InputStream fileInputStream);

    public ImportLogBo resume(ImportLogBo importLogBo, InputStream fileInputStream);
}
