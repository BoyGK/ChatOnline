package com.chatonline.master.upper.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibSessionFactory {
    private static final SessionFactory ourSessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();

        ourSessionFactory = configuration.buildSessionFactory();
    }

    public static Session getSession() {
        return ourSessionFactory.openSession();
    }
}
