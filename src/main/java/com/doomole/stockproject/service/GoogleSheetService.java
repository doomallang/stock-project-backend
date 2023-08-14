package com.doomole.stockproject.service;

import com.doomole.stockproject.util.Credentials;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleSheetService {
    private static final String APPLICATION_NAME = "stockadminsheet";

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        Credential credential = Credentials.authorize();
        Sheets sheetsService = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential)
                .setApplicationName(APPLICATION_NAME)
                .build();

        String spreadsheetId = "1ElUH1yEonKD7hrm13s41l2socBxCeKyL7IxBbDDWH2w";
        String range = "sheet1!A8:C11"; // Sheet1의 A1부터 C3까지

        List<List<Object>> values = Arrays.asList(
                Arrays.asList("Name", "Age", "Gender"),
                Arrays.asList("Alice", 25, "Female"),
                Arrays.asList("Bob", 30, "Male"),
                Arrays.asList("choi", 32, "fmale1111111111"));
        ValueRange data = new ValueRange().setValues(values);

        sheetsService.spreadsheets().values().update(spreadsheetId, range, data)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }
}
