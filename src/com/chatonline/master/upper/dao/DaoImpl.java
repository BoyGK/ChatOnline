package com.chatonline.master.upper.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class DaoImpl implements Dao {

    protected Session session;
    protected Transaction transaction;

    public DaoImpl(){
        Configuration configuration = new Configuration();
        configuration.configure();
        session = configuration.buildSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

}
