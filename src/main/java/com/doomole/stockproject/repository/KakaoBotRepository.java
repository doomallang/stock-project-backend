package com.doomole.stockproject.repository;

import com.doomole.stockproject.model.Recommend;
import com.doomole.stockproject.model.RecommendType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface KakaoBotRepository {
    public RecommendType selectRecommendType(String recommendTypeName);

    public ArrayList<Recommend> selectRecommendList(int recommendTypeIdx);

    public int selectRecommendExists(int recommendTypeIdx, String recommendContent);

    public void insertRecommend(int recommendTypeIdx, String recommendContent);

    public int selectRecommendTypeLastIdx();

    public void insertRecommendType(int recommendTypeIdx, String recommendTypeName, boolean isRandom);
}
