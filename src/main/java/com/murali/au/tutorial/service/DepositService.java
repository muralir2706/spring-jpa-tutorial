package com.murali.au.tutorial.service;

import org.springframework.stereotype.Service;

import com.murali.au.tutorial.model.Account;
import com.murali.au.tutorial.repo.AccountsRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DepositService {

    private final AccountsRepository accountRepository;

    public DepositService(AccountsRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account deposit(String accountNumber, double amount) {
        log.info("Depositing money");

        if(amount <= 0) {
            throw new IllegalArgumentException("Amount deposited must be greater than 0");
        }
        Account account = accountRepository.findAccountByAccountNumber(accountNumber).orElseThrow(() -> new RuntimeException(
                "Account not found"));

        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }
}
