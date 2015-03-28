package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.importlog.ImportData;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Administrator on 2015/3/28.
 */
public interface HSSFReadService {

    public List<ImportData> readExcel(InputStream in) throws IOException;
}
