package com.hdg.rjra.server.web.utils;

import com.hdg.common.properties.CustomizedPropertyConfigurer;
import com.hdg.rjra.rdb.domain.AccountFile;
import com.hdg.rjra.server.model.bo.file.AccountFileBo;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Rock on 2015/2/18 0018.
 */
public class UploadFileUtils {

    public static AccountFileBo uploadFile(InputStream inputStream, String type, String key, String fileName, String fileType, String fileFormat) throws IOException {

        String cd = CustomizedPropertyConfigurer.getContextPropertyForString("upload_file_path");
        String dir = "upload/" + type + "/" + key + "/";
        String thumbnails = "_thumbnail";
        String webPath = cd + dir + fileName + "." + fileFormat;
        String saveDbPath = dir + fileName + "." + fileFormat;
        File newfile = new File(cd + dir);
        String savePath = webPath;
        String webPathThumbnails = cd + dir + fileName + thumbnails + "." + fileFormat;
        String saveDbPathThumbnails = dir + fileName + thumbnails + "." + fileFormat;
        String savePathThumbnails = webPathThumbnails;
        File files = new File(savePath);
        File filesThumbnails = new File(savePathThumbnails);
        if (!newfile.exists()) {
            newfile.mkdirs();
            files.createNewFile();
            filesThumbnails.createNewFile();
        }

        FileOutputStream outputStream = new FileOutputStream(savePath);
        byte[] buffer = new byte[1024 * 1024];

        int c = 0;
        while ((c = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, c);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        //生成缩略图
        Thumbnails.of(savePath).forceSize(156, 109).toFile(filesThumbnails);

        AccountFileBo accountFileBo = new AccountFileBo();
        accountFileBo.setFileFormat(fileFormat);
        accountFileBo.setFileUrl(saveDbPath);
        accountFileBo.setFileThumbnailUrl(saveDbPathThumbnails);
        accountFileBo.setFileName(fileName);
        accountFileBo.setFileType(Integer.valueOf(fileType));
        return accountFileBo;
    }
}
