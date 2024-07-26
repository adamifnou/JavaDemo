package com.justDab.demo.service;


import com.justDab.demo.model.Account;



public interface AccountService {
    Account getAccountById(int accountId);
    void saveAccount(Account account);
}

