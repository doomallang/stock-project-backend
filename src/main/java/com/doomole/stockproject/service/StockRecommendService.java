package com.doomole.stockproject.service;

import com.doomole.stockproject.util.Util;
import com.doomole.stockproject.util.WebClientService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockRecommendService {
    @Autowired
    private WebClientService webClientService;

    public Map<String, Object> getStockRecommendList() {
        Map<String, Object> bodyMap = new HashMap<>();
        String url = "https://api.atstockplus.co.kr:9090";
        String uri = "/contents/recStock";

        bodyMap.put("featureStockUid", 0);
        bodyMap.put("listCnt", 0);
        bodyMap.put("pubAnnounceUid", 0);
        bodyMap.put("recStockUid", 0);
        bodyMap.put("searchKeyword", "string");
        bodyMap.put("startIndex", 0);
        bodyMap.put("themeStockUid", 0);
        bodyMap.put("userLevel", "string");

        Mono<Map> monoMap = webClientService.post(url, uri, bodyMap);

        ArrayList<HashMap<String, Object>> themeStockList = (ArrayList<HashMap<String, Object>>) monoMap.block().get("themeStockList");
        HashMap<String, Object> featureStockMap = (HashMap<String, Object>) monoMap.block().get("featureStock");
        ArrayList<HashMap<String, Object>> featureStockList = (ArrayList<HashMap<String, Object>>) featureStockMap.get("featureStockContentsList");
        for(Map<String, Object> listMap : featureStockList) {
            String temp = listMap.get("featureDetailInfo").toString().replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("&nbsp", "").replaceAll("<div>", "").replaceAll("</div>", "").replaceAll("<br>", "");
            listMap.put("featureDetailInfo", temp);
        }

        for(Map<String, Object> listMap : themeStockList) {
            String temp = listMap.get("themeInfo").toString().replaceAll("<p>", "").replaceAll("</p>", "").replaceAll("&nbsp", "").replaceAll("<div>", "").replaceAll("</div>", "").replaceAll("<br>", "");
            listMap.put("themeInfo", temp);
        }
        Map<String, Object> returnMap = new HashMap<>();

        returnMap.put("themeStockList", themeStockList);
        returnMap.put("featureStockList", featureStockList);
        return returnMap;
    }

    public Map<String, Object> getStockRankList() {
        Map<String, Object> bodyMap = new HashMap<>();
        String url = "https://api.atstockplus.co.kr:9090";
        String uriBuyHighRank = "/contents/stockRanking/buyHighRank/list";
        String uriRankingUp = "/contents/stockRanking/upStock/list";
        String uriTradingTrend = "/contents/stockRanking/tradingTrend/list";

        bodyMap.put("startIndex", 0);
        bodyMap.put("listCnt", 5);

        Mono<Map> monoMap = webClientService.post(url, uriBuyHighRank, bodyMap);

        // 외국인 매수 상위
        HashMap<String, Object> buyHighRankMap = (HashMap<String, Object>)monoMap.block().get("buyHighRank");
        String buyHighRankDate = (String) buyHighRankMap.get("baseDate");
        ArrayList<HashMap<String, Object>> buyHighRankList = (ArrayList<HashMap<String, Object>>) buyHighRankMap.get("buyHighRankContentsList");

        // 목표가 상향주
        bodyMap.put("listCnt", 1);
        monoMap = webClientService.post(url, uriRankingUp, bodyMap);
        ArrayList<HashMap<String, Object>> rankingUpListTemp = (ArrayList<HashMap<String, Object>>) monoMap.block().get("upStockList");
        String rankingUpDate = (String) rankingUpListTemp.get(0).get("baseDate");
        ArrayList<HashMap<String, Object>> rankingUpList = (ArrayList<HashMap<String, Object>>) rankingUpListTemp.get(0).get("upStockContentsList");

        // 기관 매매 동향
        bodyMap.put("listCnt", 10);
        monoMap = webClientService.post(url, uriTradingTrend, bodyMap);
        ArrayList<HashMap<String, Object>> tradingTrendList = (ArrayList<HashMap<String, Object>>) monoMap.block().get("tradingTrendList");

        Map<String, Object> returnMap = new HashMap<>();

        returnMap.put("buyHighRankDate", buyHighRankDate);
        returnMap.put("buyHighRankList", buyHighRankList);
        returnMap.put("rankingUpDate", rankingUpDate);
        returnMap.put("rankingUpList", rankingUpList);
        returnMap.put("tradingTrendList", tradingTrendList);

        return returnMap;
    }

    public Map<String, Object> getStockPortfolioList() {
        Map<String, Object> bodyMap = new HashMap<>();
        String url = "https://api.atstockplus.co.kr:9090";
        String uriPortfolioList = "/contents/invstStrtg/list";
        String uriPortfolioDetail = "/contents/invstStrtg/detail";
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> listMap = new HashMap<>();

        bodyMap.put("sort",3);
        listMap.put("userLevel", "02");

        Mono<Map> monoMap = webClientService.post(url, uriPortfolioList, bodyMap);
        ArrayList<HashMap<String, Object>> portfolioListTemp = (ArrayList<HashMap<String, Object>>) monoMap.block().get("portfolioList");

        ArrayList<ArrayList<HashMap<String, Object>>> returnList = new ArrayList<>();
        ArrayList<HashMap<String, Object>> returnDetail = new ArrayList<>();

        for(Map<String, Object> map : portfolioListTemp) {
            listMap.put("portfolioUid", map.get("portfolioUid"));
            Mono<Map> detailMap = webClientService.post(url, uriPortfolioDetail, listMap);
            ArrayList<HashMap<String, Object>> detailList = (ArrayList<HashMap<String, Object>>) detailMap.block().get("stockList");
            HashMap<String, Object> detail = (HashMap<String, Object>) detailMap.block().get("detail");

            returnList.add(detailList);
            returnDetail.add(detail);
        }

        returnMap.put("list", returnList);
        returnMap.put("detail", returnDetail);
        return returnMap;
    }
}
