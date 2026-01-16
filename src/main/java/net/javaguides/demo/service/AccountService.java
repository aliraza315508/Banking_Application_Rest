package net.javaguides.demo.service;

import net.javaguides.demo.dto.AccountDto;
import net.javaguides.demo.dto.TransferFundDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto) ;

    AccountDto getAccountById(Long id) ;

    AccountDto deposit(Long id , double amount) ;

    AccountDto withdraw(Long id , double amount) ;

    List<AccountDto> getAllAccounts() ;

    void transferFunds(TransferFundDto transferFundDto) ;
}
