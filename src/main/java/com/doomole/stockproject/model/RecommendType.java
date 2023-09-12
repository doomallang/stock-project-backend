package com.doomole.stockproject.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class RecommendType {
    private int recommendTypeIdx;
    private String recommendTypeName;
    @Value("isRandom")
    private boolean isRandom;
    private long readCount;
    private int parentIdx;

    public boolean getIsRandom() {
        return this.isRandom;
    }
    public void setIsRandom(boolean isRandom) {
        this.isRandom = isRandom;
    }
}
