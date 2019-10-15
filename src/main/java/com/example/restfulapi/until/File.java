package com.example.restfulapi.until;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by Administrator on 2019/10/15 0015.
 */
public class File {

    /**
     * 追加写入文件
     * @param filePathName
     * @param text
     */
    public static void fileWrite(String filePathName, String text) {
        Date date = new Date();
        String dateStr = date.toString();

        FileWriter fw = null;
        try {
            //如果文件存在，则追加内容；如果文件不存在，则创建文件
            java.io.File f = new java.io.File(filePathName);
            if(f.exists()) {
                fw = new FileWriter(f, true);

                PrintWriter pw = new PrintWriter(fw);
                pw.println(dateStr+" >>> "+text);
                pw.flush();

                fw.flush();
                pw.close();
                fw.close();
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
