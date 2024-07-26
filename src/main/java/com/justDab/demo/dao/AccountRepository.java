package com.justDab.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justDab.demo.model.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
 Account getAccountById(int id);
}