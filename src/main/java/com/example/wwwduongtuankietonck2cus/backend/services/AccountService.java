package com.example.wwwduongtuankietonck2cus.backend.services;

import com.example.wwwduongtuankietonck2cus.backend.models.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAccountsBetweenBalance(double min, double max);
}
