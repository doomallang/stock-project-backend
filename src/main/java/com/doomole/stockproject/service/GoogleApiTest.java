package com.at.usaf.api;

import com.doomole.stockproject.util.Credentials;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

public class GoogleApiTest {

    private static final String APPLICATION_NAME = "stockadminsheet";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException, GeneralSecurityException {
        InputStream in = Credentials.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(
                JacksonFactory.getDefaultInstance(), new InputStreamReader(in)
        );

        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(),
                clientSecrets, scopes)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                .build();

        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver())
                .authorize("당신 이름");

        return credential;
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();

        String spreadsheetId = "1ElUH1yEonKD7hrm13s41l2socBxCeKyL7IxBbDDWH2w";
        String range = "SFCONN!A8:C11"; // Sheet1의 A1부터 C3까지

        List<List<Object>> values = Arrays.asList(
                Arrays.asList("Name", "Age", "Gender"),
                Arrays.asList("Alice", 25, "Female"),
                Arrays.asList("Bob", 30, "Male"),
                Arrays.asList("choi", 32, "fmale1111111111"));
        ValueRange data = new ValueRange().setValues(values);

        service.spreadsheets().values().update(spreadsheetId, range, data)
                .setValueInputOption("USER_ENTERED")
                .execute();
    }
}