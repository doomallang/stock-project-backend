package com.doomole.stockproject.controller;

import com.doomole.stockproject.dto.ValidDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TestValidController {

    @RequestMapping(value = "/test/valid", method = RequestMethod.POST)
    public void testValid(@Valid @RequestBody ValidDto validDto) {

    }
}
