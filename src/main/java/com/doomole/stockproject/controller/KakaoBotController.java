package com.doomole.stockproject.controller;

import com.doomole.stockproject.dto.ReqRecommend;
import com.doomole.stockproject.dto.ReqRecommendType;
import com.doomole.stockproject.service.KakaoBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class KakaoBotController {

    private final KakaoBotService kakaoBotService;

    @RequestMapping(value = "/api/kakaobot/recommend", method = RequestMethod.GET)
    public ResponseEntity<String> getRecommend(HttpServletRequest req, ReqRecommend reqRecommend) {
        String recommendType = reqRecommend.getRecommendType();
        String recommendContent = kakaoBotService.getRecommend(recommendType);

        return new ResponseEntity<>(recommendContent, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/kakaobot/recommend", method = RequestMethod.POST)
    public ResponseEntity<String> addRecommend(HttpServletRequest req, ReqRecommend reqRecommend) {
        String recommendType = reqRecommend.getRecommendType();
        String recommendContent = reqRecommend.getRecommendContent();
        kakaoBotService.addRecommend(recommendType, recommendContent);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/kakaobot/recommendType", method = RequestMethod.POST)
    public ResponseEntity<String> addRecommendType(HttpServletRequest req, ReqRecommendType reqRecommendType) {
        String recommendTypeName = reqRecommendType.getRecommendTypeName();
        boolean isRandom = reqRecommendType.getIsRandom();
        System.out.println(isRandom);
        kakaoBotService.addRecommendType(recommendTypeName, isRandom);

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
