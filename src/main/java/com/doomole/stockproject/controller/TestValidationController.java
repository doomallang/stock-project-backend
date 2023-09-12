package com.doomole.stockproject.controller;

import com.doomole.stockproject.dto.ValidationDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestValidationController {
    @RequestMapping(value = "/test/validation01", method = RequestMethod.POST)
    public void testValidation01(@RequestBody @Validated(ValidationDto.Val01.class) ValidationDto ValidationDto) {

    }

    @RequestMapping(value = "/test/validation02", method = RequestMethod.POST)
    public void testValidation02(@RequestBody @Validated(ValidationDto.Val02.class) ValidationDto ValidationDto) {

    }
}
