package com.doomole.stockproject.service;

import com.doomole.stockproject.exception.FailException;
import com.doomole.stockproject.model.Account;
import com.doomole.stockproject.repository.AccountRepository;
import com.doomole.stockproject.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    // 계정 등록
    public void addAccount(String accountId, String password, String name) {
        System.out.println(accountId);
        if(accountRepository.selectAccountIdExistByAccountId(accountId) > 0) {
            throw new FailException("이미 아이디가 존재합니다람쥐.");
        }

        Account account = new Account();
        account.setAccountId(accountId);
        account.setPassword(password);
        account.setName(name);

        String datetime = Util.newDateString();
        account.setCreateDatetime(datetime);
        account.setUpdateDatetime(datetime);
        account.setPasswordChangeDatetime(datetime);

        accountRepository.insertAccount(account);
    }

}
