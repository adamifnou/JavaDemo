package com.justDab.demo.service;

import com.justDab.demo.dao.AccountRepository;
import com.justDab.demo.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(int accountId) {
        return accountRepository.getAccountById(accountId);
    }

    @Override
    public void saveAccount(Account account) {
        accountRepository.save(account);
    }
}




