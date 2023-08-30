package com.doomole.stockproject.service;

import com.doomole.stockproject.exception.FailException;
import com.doomole.stockproject.repository.AccountRepository;
import com.doomole.stockproject.util.ExcelUtil;
import com.doomole.stockproject.util.FileUtil;
import com.monitorjbl.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ExcelUploadService {
    private ExcelUploadService excelUploadService;

    private AccountRepository accountRepository;

    @Transactional
    public int excelUpload(MultipartFile mFile, String password) {
        File file = new File(mFile.getOriginalFilename());
        InputStream dataStream = null;

        // File 객체로 변환
        try {
            mFile.transferTo(file);
        } catch(Exception e) {
            throw new FailException("파일 전송 에러");
        }

        if(password == null || password.equals("")) {
            try {
                dataStream = new FileInputStream(file);
            } catch(Exception e) {
                throw new FailException("dataStream 생성 에러");
            }
        } else {
            try {
                POIFSFileSystem fileSystem = new POIFSFileSystem(file);
                EncryptionInfo info = new EncryptionInfo(fileSystem);
                Decryptor decryptor = Decryptor.getInstance(info);
                // 비밀번호 불일치
                if (!decryptor.verifyPassword(password)) {
                    throw new FailException("비밀번호 불일치");
                }
                dataStream = decryptor.getDataStream(fileSystem);
            } catch(Exception e) {
                throw new FailException("엑셀 파싱 실패" + e.getMessage());
            }
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(dataStream);

        try {
            if (!FileUtil.validExcelFile(bufferedInputStream)) {
                throw new FailException("파일타입이 엑셀이 아닙니다.");
            }
        } catch(Exception e) {
            throw new FailException("tika error - " + e.getMessage());
        }

        Workbook workbook = StreamingReader.builder().rowCacheSize(100).bufferSize(4096).open(bufferedInputStream);

        int updateCount = 0;
        // sheet별 반복
        for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            ArrayList<String[]> list = getUpdateAccountList(sheet);
            updateCount += excelUploadService.updateAccountDatetime(list);
        }
        return updateCount;
    }

    @Transactional
    public ArrayList getUpdateAccountList(Sheet sheet) {
        ArrayList<String[]> list = new ArrayList<>();
        int count = 0;
        //행을읽는다
        for(Row row : sheet) {
            // 가장 위는 패스
            if(count == 0) {
                count++;
                continue;
            } else {
                count++;
            }
            // 셀의 수
            int cells = row.getLastCellNum();
            String[] arr = new String[cells];
            // 셀이 빈값이라면 마지막행으로 간주하고 for문 종료
            if(row.getCell(0) == null) {
                break;
            }
            for (int columnindex = 0; columnindex <= cells; columnindex++) {
                String value = "";
                try {
                    //셀값을 읽는다
                    Cell cell = row.getCell(columnindex);
                    // 시/분/초만 있는 cell에 대한 변환
                    if(columnindex == 5) {
                        Date date = cell.getDateCellValue();
                        value = new SimpleDateFormat("HH:mm:ss").format(date);
                    } else {
                        value = ExcelUtil.getValue(cell);
                    }
                    arr[columnindex] = value;
                } catch(Exception e) {
                    throw new FailException(count + "행 파싱 실패 오류 : " + e.getMessage());
                }
            }
            list.add(arr);
        }

        return list;
    }

    public int updateAccountDatetime(ArrayList<String[]> list) {
        int count = 0;
        for(String[] arr : list) {
            String id = arr[0];
            String name = arr[1];
            String updateDatetime = arr[2] + arr[3];
            // id 존재 여부
            if(accountRepository.selectAccountIdExistByAccountId(id) > 0) {
                count += accountRepository.updateAccountDatetime(id, updateDatetime);
            }
        }

        return count;
    }
}
