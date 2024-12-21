package com.example.wwwduongtuankietonck2cus.backend.repositories;

import com.example.wwwduongtuankietonck2cus.backend.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.balance >= ?1 AND a.balance <= ?2")
    List<Account> getAccountsBetweenBalance(double min, double max);
}
