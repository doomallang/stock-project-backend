package com.doomole.stockproject.dto;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class ReqRecommendType {
    private String recommendTypeName;
    private boolean isRandom;

    public boolean getIsRandom() {
        return this.isRandom;
    }

    public void setIsRandom(boolean isRandom) {
        this.isRandom = isRandom;
    }

}
