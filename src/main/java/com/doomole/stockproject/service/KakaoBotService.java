package com.doomole.stockproject.service;

import com.doomole.stockproject.exception.FailException;
import com.doomole.stockproject.model.Recommend;
import com.doomole.stockproject.model.RecommendType;
import com.doomole.stockproject.repository.KakaoBotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KakaoBotService {

    private final KakaoBotRepository kakaoBotRepository;

    @Transactional
    public String getRecommend(String recommendTypeName) {
        String recommendContent = "";
        RecommendType recommendType = selectRecommendType(recommendTypeName);
        if(recommendType == null) {
            throw new FailException("type 없음");
        }
        int recommendTypeIdx = recommendType.getRecommendTypeIdx();

        ArrayList<Recommend> recommendList = selectRecommendList(recommendTypeIdx);
        if(recommendType.getIsRandom()) {
            Collections.shuffle(recommendList);
            recommendContent = recommendList.get(0).getRecommendContent();
        }

        return recommendContent;
    }

    @Transactional
    public void addRecommend(String recommendTypeName, String recommendContent) {
        RecommendType recommendType = selectRecommendType(recommendTypeName);
        if(recommendType == null) {
            throw new FailException("type 없음");
        }

        int recommendTypeIdx = recommendType.getRecommendTypeIdx();
        if(selectRecommendExists(recommendTypeIdx, recommendContent) > 0) {
            throw new FailException("이미 있음");
        }

        insertRecommend(recommendTypeIdx, recommendContent);
    }

    @Transactional
    public void addRecommendType(String recommendTypeName, boolean isRandom) {
        RecommendType recommendType = selectRecommendType(recommendTypeName);
        if(recommendType != null) {
            throw new FailException("이미 존재하는 Type");
        }
        int recommendTypeIdx = kakaoBotRepository.selectRecommendTypeLastIdx() + 1;
        insertRecommendType(recommendTypeIdx, recommendTypeName, isRandom);
    }

    public RecommendType selectRecommendType(String recommendTypeName) {
        return kakaoBotRepository.selectRecommendType(recommendTypeName);
    }

    public ArrayList<Recommend> selectRecommendList(int recommendTypeIdx) {
        return kakaoBotRepository.selectRecommendList(recommendTypeIdx);
    }

    public int selectRecommendExists(int recommendTypeIdx, String recommendContent) {
        return kakaoBotRepository.selectRecommendExists(recommendTypeIdx, recommendContent);
    }

    public void insertRecommend(int recommendTypeIdx, String recommendContent) {
        kakaoBotRepository.insertRecommend(recommendTypeIdx, recommendContent);
    }

    public void insertRecommendType(int recommendTypeIdx, String recommendTypeName, boolean isRandom) {
        kakaoBotRepository.insertRecommendType(recommendTypeIdx, recommendTypeName, isRandom);
    }

}
