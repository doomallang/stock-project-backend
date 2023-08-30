package com.doomole.stockproject.controller;

import com.doomole.stockproject.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TestController {
    private TestService testService;

    @Autowired(required = false)
    public void setTestService(TestService testService) {
        this.testService = testService;
    }
    public void test() {
        testService.start();
    }

}
