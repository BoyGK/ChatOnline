package com.chatonline.master.upper.dao;

import java.util.List;

public interface Dao {

    Object query(Object object);

    List queryAll();

    boolean save(Object object);
}
