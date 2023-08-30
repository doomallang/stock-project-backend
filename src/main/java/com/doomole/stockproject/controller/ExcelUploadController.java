package com.doomole.stockproject.controller;

import com.doomole.stockproject.exception.FailException;
import com.doomole.stockproject.service.ExcelUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ExcelUploadController {

    private ExcelUploadService excelUploadService;

    @RequestMapping(value = "/api/excel/upload", method = RequestMethod.POST)
    public ResponseEntity<Integer> excelUpload(MultipartHttpServletRequest req) {
        int updateCount = 0;
        // 파일 받기
        Iterator<String> iterator = req.getFileNames();
        String password = req.getParameter("password");

        if(iterator.hasNext()) {
            String fileName = iterator.next();
            MultipartFile mFile = req.getFile(fileName);
            updateCount = excelUploadService.excelUpload(mFile, password);
        // 파일이 없는 경우
        } else {
            throw new FailException("파일이 존재하지 않음");
        }

        return new ResponseEntity<>(updateCount, HttpStatus.OK);
    }
}
