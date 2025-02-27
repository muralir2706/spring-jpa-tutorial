package com.murali.au.tutorial.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.murali.au.tutorial.model.Account;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

   Optional<Account> findAccountByAccountNumber(String accountNumber);
}
