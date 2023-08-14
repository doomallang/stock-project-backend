package com.doomole.stockproject.repository;

import com.doomole.stockproject.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository {
    public void insertAccount(Account account);

    public int selectAccountIdExistByAccountId(String accountId);
}
