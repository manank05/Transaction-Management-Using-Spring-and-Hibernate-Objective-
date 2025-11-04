package com.example.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import com.example.model.Account;
import jakarta.transaction.Transactional;

@Repository
public class AccountDAO {

    private final SessionFactory sessionFactory;

    public AccountDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Account getAccountById(int id) {
        return sessionFactory.getCurrentSession().get(Account.class, id);
    }

    public void updateAccount(Account account) {
        sessionFactory.getCurrentSession().update(account);
    }
}
