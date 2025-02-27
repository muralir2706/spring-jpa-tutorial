package com.murali.au.tutorial.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.murali.au.tutorial.model.Account;
import com.murali.au.tutorial.service.DepositService;

@RestController
@RequestMapping("/accounts")
public class BankingController {


    private final DepositService depositService;

    public BankingController(DepositService depositService) {
        this.depositService = depositService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestBody Map<String, Object> request) {
        String accountNumber = (String) request.get("accountNumber");
        double amount = (double) request.get("amount");
        Account updatedAccount = depositService.deposit(accountNumber, amount);
        return ResponseEntity.ok(updatedAccount);
    }
}
