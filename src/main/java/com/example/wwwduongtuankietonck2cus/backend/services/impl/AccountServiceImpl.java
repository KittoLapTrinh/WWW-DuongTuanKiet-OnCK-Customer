package com.example.wwwduongtuankietonck2cus.backend.services.impl;

import com.example.wwwduongtuankietonck2cus.backend.models.Account;
import com.example.wwwduongtuankietonck2cus.backend.repositories.AccountRepository;
import com.example.wwwduongtuankietonck2cus.backend.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    public List<Account> getAccountsBetweenBalance(double min, double max) {
        return accountRepository.getAccountsBetweenBalance(min, max);
    }
}
