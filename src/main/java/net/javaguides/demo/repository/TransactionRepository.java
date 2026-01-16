package net.javaguides.demo.repository;

import net.javaguides.demo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction , Long> {


    List<Transaction> findByAccountIdOrderByTimestampDesc(Long accountId) ;
}
