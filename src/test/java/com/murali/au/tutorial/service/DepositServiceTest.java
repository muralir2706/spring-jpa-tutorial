package com.murali.au.tutorial.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.murali.au.tutorial.model.Account;
import com.murali.au.tutorial.repo.AccountsRepository;

class DepositServiceTest {


    @Mock
    private AccountsRepository accountsRepository;

    @InjectMocks
    private DepositService depositService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void testDeposit_Success() {

        String accountNumber = "123";
        double amount = 100.0;
        Account account = Account.builder().accountNumber(accountNumber).balance(amount).build();

        when(accountsRepository.findAccountByAccountNumber(accountNumber)).thenReturn(Optional.of(account));
        when(accountsRepository.save(account)).thenReturn(account);

        Account updatedAccount = depositService.deposit(accountNumber, amount);
        assertNotNull(updatedAccount);
        assertEquals(amount * 2, updatedAccount.getBalance());
        verify(accountsRepository, times(1)).findAccountByAccountNumber(accountNumber);
        verify(accountsRepository, times(1)).save(account);
    }

    @Test
    public void testDeposit_AccountNotFound() {
        String accountNumber = "12345";
        double amount = 100.0;

        when(accountsRepository.findAccountByAccountNumber(accountNumber)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            depositService.deposit(accountNumber, amount);
        });

        assertEquals("Account not found", exception.getMessage());
        verify(accountsRepository, times(1)).findAccountByAccountNumber(accountNumber);
        verify(accountsRepository, never()).save(any(Account.class));
    }

    @Test
    public void testDeposit_InvalidAmount() {
        String accountNumber = "12345";
        double amount = -100.0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            depositService.deposit(accountNumber, amount);
        });

        assertEquals("Amount deposited must be greater than 0", exception.getMessage());
        verify(accountsRepository, never()).findAccountByAccountNumber(anyString());
        verify(accountsRepository, never()).save(any(Account.class));
    }
}