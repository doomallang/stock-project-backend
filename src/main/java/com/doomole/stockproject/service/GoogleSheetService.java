package com.doomole.stockproject.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleSheetService {
    // google sheets
    private static final String APPLICATION_NAME = "google-sheet-project";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private static final String CREDENTIALS_FILE_PATH = "googlesheet/google_spread_sheet_key.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        ClassLoader loader = GoogleSheetService.class.getClassLoader();
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(loader.getResource(CREDENTIALS_FILE_PATH).getFile()))
                .createScoped(SCOPES);

        return credential;
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        String spreadSheetId = "1PrjniLIPwS-eHicw9oRMpZffkL38PQQmm7N1dc59fAI";
        String range = "sheet1!A1:C4"; // Sheet1의 A1부터 C3까지

        List<List<Object>> values = Arrays.asList(
                Arrays.asList("Name", "Age", "Gender"),
                Arrays.asList("Alice", 25, "Female"),
                Arrays.asList("Bob", 30, "Male"),
                Arrays.asList("choi", 32, "fmale1111111111"));
        ValueRange data = new ValueRange().setValues(values);

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        service.spreadsheets().values().update(spreadSheetId, range, data)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }
}
