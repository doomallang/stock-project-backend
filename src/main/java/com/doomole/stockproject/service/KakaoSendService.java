package com.doomole.stockproject.service;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class KakaoSendService {
    private static CloseableHttpClient httpClient;

    private static RestTemplate restTemplate;

    public String getAccessToken() {
        getHttpClient();
        String result = "";

        String url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=40c10ebd4a1ef6068b050139c091d1c7&redirect_uri=http://example.com/oauth";
        HttpHeaders headers = new HttpHeaders();
        JSONObject jsonObject = new JSONObject();

        HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);

        try {
            result = restTemplate.postForObject(url, entity, String.class);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("HTTP REQUEST ERROR:{}" + e.getMessage());
        }

        String recommendUrl = "https://kauth.kakao.com/oauth/token";
        Map<String, Object> map = new HashMap<>();

        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        map.put("grant_type", "authorization_code");
        map.put("client_id", "40c10ebd4a1ef6068b050139c091d1c7");

        return "ff";
    }

    public String kakaoSend(String data) {
        getAccessToken();

        String recommendUrl = "https://kapi.kakao.com/v1/api/talk/memo/send";
        // Http Client 생성
        getHttpClient();

        Map<String, Object> map = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
        headers.setBearerAuth("79483dc52a767f83a1bd707af1ff303d");

        String result = "";
        map.put("object_type", "list");
        map.put("header_title", "temp");
        map.put("contents", data);

        JSONObject jsonObject = new JSONObject();

        HttpEntity<String> entity = new HttpEntity<String>(jsonObject.toString(), headers);

        try {
            result = restTemplate.postForObject(recommendUrl, entity, String.class);
        } catch (Exception e) {
            System.out.println("HTTP REQUEST ERROR:{}" + e.getMessage());
        }

        return "dd";
    }

    public static void getHttpClient() {
        if (httpClient == null) {
            HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
            httpRequestFactory.setConnectTimeout(60000);
            httpRequestFactory.setReadTimeout(60000);

            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setMaxTotal(200); //커넥션풀적용(최대 오픈되는 커넥션 수)
            //connectionManager.setDefaultMaxPerRoute(50); //커넥션풀적용(IP:포트 1쌍에 대해 수행 할 연결 수제한)
            connectionManager.closeIdleConnections(2000L, TimeUnit.MILLISECONDS); // 서버에서 keepalive시간동안 미 사용한 커넥션을 죽이는 등의 케이스 방어로 idle커넥션을 주기적으로 지움
            httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

            httpRequestFactory.setHttpClient(httpClient);
            restTemplate = new RestTemplate(httpRequestFactory);
        }
    }
}
