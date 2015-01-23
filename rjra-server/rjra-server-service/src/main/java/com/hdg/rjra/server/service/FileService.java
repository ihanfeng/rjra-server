package com.hdg.rjra.server.service;

import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by Rock on 2015/1/8 0008.
 */
public interface FileService {

    public Long upload(MultipartFile file, String type, String path, String key, String fileName, String fileType, String fileFormat) throws IOException;

    public List<AccountFileBo> findAccountFileByIds(Long[] fileIds);
}
