package com.chatonline.master.upper.dao;

import com.chatonline.master.upper.bean.User;
import com.chatonline.master.upper.util.CreateUserFactory;
import org.hibernate.query.Query;

import java.util.List;

/**
 * 模拟用户数据库操作
 */
public class UserDao extends DaoImpl {


    @Override
    public Object query(Object object) {
        return null;
    }

    @Override
    public List queryAll() {
        List list = null;
        Query q = session.createQuery("from User");
        list = q.list();
        return list;
    }

    @Override
    public boolean save(Object object) {
        User user = (User) object;
        session.save(user);
        transaction.commit();
        return true;
    }
}
