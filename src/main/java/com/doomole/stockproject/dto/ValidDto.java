package com.doomole.stockproject.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class ValidDto {
    @NotEmpty
    private String name;

    @NotNull
    @Max(value = 11, message = "-를 제외한 11자리를 입력하세요.")
    @Min(value = 11, message = "-를 제외한 11자리를 입력하세요.")
    private int phoneNumber;

    @NotEmpty
    @Email
    private String email;
}
