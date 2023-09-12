package com.doomole.stockproject.model;

import lombok.Data;

@Data
public class Recommend {
    private long recommendIdx;
    private int recommendTypeIdx;
    private String recommendContent;
    private long readCount;
}
