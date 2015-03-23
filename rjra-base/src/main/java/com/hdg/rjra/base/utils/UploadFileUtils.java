package com.hdg.rjra.base.utils;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2015/3/23.
 */
public class UploadFileUtils {
    public static void uploadFile(InputStream inputStream, String absolutePath, String savePath, String savePathThumbnails, int i, int j) throws IOException {
        File files = new File(savePath);
        File filesThumbnails = new File(savePathThumbnails);
        File newFile = new File(absolutePath);
        if (!newFile.exists()) {
            newFile.mkdirs();
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
        Thumbnails.of(savePath).forceSize(i,j).toFile(filesThumbnails);
    }
}
