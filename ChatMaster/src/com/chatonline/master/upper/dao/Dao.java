package com.chatonline.master.upper.dao;

import java.util.List;

public interface Dao {

    void save(Object object);
    void updata(Object object);
    List query();

    void close();
}
