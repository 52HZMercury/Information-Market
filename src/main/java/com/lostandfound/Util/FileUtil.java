package com.lostandfound.Util;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @program: LostandFound
 * @description:
 * @author: Mr.Like
 * @create: 2021-12-10 21:34
 **/
public class FileUtil {
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
