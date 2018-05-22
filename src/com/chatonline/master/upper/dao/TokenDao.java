package com.chatonline.master.upper.dao;

import com.chatonline.master.upper.bean.Userbytoken;
import org.hibernate.query.Query;

import java.util.List;

public class TokenDao extends DaoImpl {
    @Override
    public Object query(Object object) {
        return null;
    }

    @Override
    public List queryAll() {
        List list = null;
        Query q = session.createQuery("from Userbytoken");
        list = q.list();
        return list;
    }

    @Override
    public boolean save(Object object) {
        Userbytoken token = (Userbytoken) object;
        session.save(token);
        transaction.commit();
        return true;
    }
}
