package com.chatonline.master.upper.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

public abstract class DaoImpl implements Dao {

    protected Session session;
    protected Transaction transaction;

    public DaoImpl() {
        session = HibSessionFactory.getSession();
        transaction = session.beginTransaction();
    }
}
