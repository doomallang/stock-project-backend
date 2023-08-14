package com.doomole.stockproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Account {
    private long accountIdx;
    private String accountId;
    private String password;
    private String name;
    private String createDatetime;
    private String updateDatetime;
    private String passwordChangeDatetime;
}
