package com.chatonline.master.upper.dao;

import com.chatonline.master.upper.bean.User;
import org.hibernate.query.Query;

import java.util.List;

/**
 * 模拟用户数据库操作
 */
public class UserDao extends DaoImpl{

    @Override
    public void save(Object object) {
        User user = (User) object;
        session.save(user);
        transaction.commit();
    }

    @Override
    public void updata(Object object) {
        User user = (User) object;
        session.update(user);
        transaction.commit();
    }

    @Override
    public List query() {
        List list = null;
        Query q = session.createQuery("from User");
        list = q.list();
        return list;
    }

    @Override
    public void close(){
        session.close();
    }
}
