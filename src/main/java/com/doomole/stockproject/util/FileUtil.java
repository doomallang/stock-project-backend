package com.doomole.stockproject.util;

import java.io.BufferedInputStream;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.detect.Detector;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;

public class FileUtil {
    public static boolean validExcelFile(BufferedInputStream bufferedInputStream) throws Exception {

        TikaInputStream tikaInputStream = TikaInputStream.get(bufferedInputStream);

        TikaConfig tikaConfig = new TikaConfig();
        Detector tikaDetector = tikaConfig.getDetector();

        MediaType mediaType = tikaDetector.detect(tikaInputStream, new Metadata());
        String[] allowedMIMETypesEquals = {
                //"application/zip",    // .zip
                //"application/pdf",    // .pdf
                //"application/msword", // .doc, .dot
                //"application/x-hwp", "applicaion/haansofthwp", "application/x-tika-msoffice", // .hwp
                "application/x-tika-ooxml"  // .xlsx, .pptx, .docx
        };
        for (int i=0; i<allowedMIMETypesEquals.length; i++) {
            if (mediaType.toString().equals(allowedMIMETypesEquals[i])) {
                return true;
            }
        }
        System.out.println(mediaType);
        String[] allowedMIMETypesStartsWith = {
                //"image",    // .png, .jpg, .jpeg, .gif, .bmp
                //"text",     // .txt, .html 등
                //"application/vnd.ms-word",          // .docx 등 워드 관련
                "application/vnd.ms-excel",         // .xls 등 엑셀 관련
                //"application/vnd.ms-powerpoint",    // .ppt 등 파워포인트 관련
                "application/vnd.openxmlformats-officedocument",    // .docx, .dotx, .xlsx, .xltx, .pptx, .potx, .ppsx
                //"applicaion/vnd.hancom"     // .hwp 관련
        };

        for (int i=0; i<allowedMIMETypesStartsWith.length; i++) {
            if (mediaType.toString().startsWith(allowedMIMETypesStartsWith[i])) {
                return true;
            }
        }
        return false;
    }
}
