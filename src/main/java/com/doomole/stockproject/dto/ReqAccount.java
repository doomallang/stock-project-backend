package com.doomole.stockproject.dto;

import lombok.Data;

@Data
public class ReqAccount {
    private String accountId;
    private String password;
    private String name;
}
