package com.skp.usaf.common.util;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

public class GoogleSheetsApiUtil {

	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);
	//private static final List<String> SCOPES = Arrays.asList(SheetsScopes.SPREADSHEETS);
	//private static final String CREDENTIALS_FILE_PATH = "C:\\dev\\usaf-mngr-20221116\\src\\main\\webapp\\resources\\backoffice\\json\\sfconn-sheets-service.json";

	public static Sheets getSheetsService(String spreadsheetId, String credentialsFilePath) throws IOException, GeneralSecurityException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = GoogleCredential
				.fromStream(new FileInputStream(credentialsFilePath))
				.createScoped(SCOPES);
		return new Sheets.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(spreadsheetId)
				.build();
	}

}