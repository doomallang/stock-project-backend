package com.doomole.stockproject.dto;

import lombok.Getter;

import javax.validation.constraints.*;

@Getter
public class ValidationDto {
    public interface Val01 {};

    public interface Val02 {};

    @NotEmpty(groups = {Val01.class, Val02.class})
    private String name;

    @NotEmpty(groups = {Val01.class, Val02.class})
    @Email
    private String email;

    @NotNull(groups = {Val01.class})
    @Max(groups = {Val01.class}, value = 11, message = "-를 제외한 11자리를 입력하세요.")
    @Min(groups = {Val01.class}, value = 11, message = "-를 제외한 11자리를 입력하세요.")
    private int phoneNumber;

    @NotNull(groups = {Val02.class})
    @Max(groups = {Val02.class}, value = 1)
    @Min(groups = {Val02.class}, value = 1)
    private int Gender;

}
