package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dao.AccountDAO;
import com.example.model.Account;
import com.example.model.TransactionRecord;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class BankingService {

    private final AccountDAO accountDAO;

    @PersistenceContext
    private EntityManager entityManager;

    public BankingService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Transactional
    public void transferMoney(int fromId, int toId, double amount) {
        Account from = accountDAO.getAccountById(fromId);
        Account to = accountDAO.getAccountById(toId);

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds in sender's account!");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accountDAO.updateAccount(from);
        accountDAO.updateAccount(to);

        // Save transaction record
        entityManager.persist(new TransactionRecord(fromId, toId, amount));

        // If any line above fails, entire transaction will roll back
    }
}
