package com.doomole.stockproject.controller;

import com.doomole.stockproject.service.StockRecommendService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StockRecommendController {
    @Autowired
    private StockRecommendService stockRecommendService;

    @RequestMapping(value = "/api/stock/recommend/list", method = RequestMethod.GET)
    public Map<String, Object> getStockRecommendList() {
        Map<String, Object> map = stockRecommendService.getStockRecommendList();

        return map;
    }

    @RequestMapping(value = "/api/stock/rank/list", method = RequestMethod.GET)
    public Map<String, Object> getStockRankList() {
        Map<String, Object> map = stockRecommendService.getStockRankList();

        return map;
    }

    @RequestMapping(value = "/api/stock/portfolio/list", method = RequestMethod.GET)
    public Map<String, Object> getStockPortfolioList() {
        Map<String, Object> map = stockRecommendService.getStockPortfolioList();

        return map;
    }

    @RequestMapping(value = "/api/test/json", method = RequestMethod.POST)
    public String getApiTestData(HttpEntity<String> test) {
        System.out.println(test.getBody());

        JSONObject jsonObject = new JSONObject();
        JSONObject temp = new JSONObject();
        temp.put("RESULT_CODE", "200");
        temp.put("RESULT", "성공");
        temp.put("RESULT_MESSAGE", "성공했습니다.");

        jsonObject.put("HEADER", temp);
        jsonObject.put("BODY","asdj1k2b3jkbsdm,fnaddifbawsdjfb weqkjbruiasdghbjkawberljkgerobds");

        return jsonObject.toString();
    }
}
