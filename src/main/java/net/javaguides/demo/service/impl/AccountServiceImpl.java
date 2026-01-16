package net.javaguides.demo.service.impl;

import net.javaguides.demo.dto.AccountDto;
import net.javaguides.demo.dto.TransferFundDto;
import net.javaguides.demo.entity.Account;
import net.javaguides.demo.entity.Transaction;
import net.javaguides.demo.mapper.AccountMapper;
import net.javaguides.demo.repository.AccountRepository;
import net.javaguides.demo.repository.TransactionRepository;
import net.javaguides.demo.service.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    private static final String TRANSACTION_TYPE_DEPOSIT = "DEPOSIT";
    private static final String TRANSACTION_TYPE_WITHDRAW = "WITHDRAW";
    private static final String TRANSACTION_TYPE_TRANSFER = "TRANSFER";



    private TransactionRepository transactionRepository ;

    public AccountServiceImpl(AccountRepository accountRepository ,
                              TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository ;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto) ;
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount) ;
    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.
                findById(id)
                .orElseThrow(() -> new
                        RuntimeException("Account does not exists"));

        account.setBalance(account.getBalance() + amount);
        Account savedAccount = accountRepository.save(account) ;

        Transaction transaction = new Transaction();

        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_DEPOSIT);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);


        return AccountMapper.mapToAccountDto(savedAccount) ;

    }

    @Override
    public AccountDto withdraw(Long id, double amount) {

        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not exists"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient amount");
        }

        double total = account.getBalance() - amount;
        account.setBalance(total);

        Account savedAccount = accountRepository.save(account);

        Transaction transaction = new Transaction();

        transaction.setAccountId(id);
        transaction.setAmount(amount);
        transaction.setTransactionType(TRANSACTION_TYPE_WITHDRAW);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);


        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll() ;


       return accounts.stream().
                map((account) ->
                                AccountMapper.mapToAccountDto(account)
                        ).collect(Collectors.toList()) ;
    }

    @Override
    public void transferFunds(TransferFundDto transferFundDto) {
        Account fromAccount =
                accountRepository.
                        findById(transferFundDto.fromAccountId())
                        .orElseThrow(() ->
                                new RuntimeException
                                        ("Account does not exists"));



        Account toAccount =
                accountRepository.
                        findById(transferFundDto.toAccountId())
                        .orElseThrow(() ->
                                new RuntimeException
                                        ("Account does not exists"));


        fromAccount.setBalance(
                fromAccount.getBalance() - transferFundDto.amount()
        );

// Credit the amount to toAccount object
        toAccount.setBalance(
                toAccount.getBalance() + transferFundDto.amount()
        );

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();

        transaction.setAccountId(transferFundDto.fromAccountId());
        transaction.setAmount(transferFundDto.amount());
        transaction.setTransactionType(TRANSACTION_TYPE_TRANSFER);
        transaction.setTimestamp(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

}
