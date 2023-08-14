package com.doomole.stockproject.controller;

import com.doomole.stockproject.dto.ReqAccount;
import com.doomole.stockproject.service.AccountService;
import com.doomole.stockproject.service.GoogleSheetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {
    private final AccountService accountService;

    private final GoogleSheetService googleSheetService;

    @RequestMapping(value = "/api/account/signUp", method = RequestMethod.POST)
    public ResponseEntity<String> signUp(HttpServletRequest request, @RequestBody ReqAccount reqAccount) {
        String accountId = reqAccount.getAccountId();
        String password = reqAccount.getPassword();
        String name = reqAccount.getName();

        accountService.addAccount(accountId, password, name);

        return new ResponseEntity<>("가입이 완료되었습니다.", HttpStatus.OK);
    }

    @RequestMapping(value = "/api/google/sheet", method = RequestMethod.POST)
    public void signUp(HttpServletRequest request) {

    }
}
