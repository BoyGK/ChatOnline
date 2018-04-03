package com.chatonline.server.rpc;

import java.util.HashMap;
import java.util.Map;

public class RpcContext {
    private Map<Class<?>, Object> map = new HashMap<>();

    public void register(Class<?> key, Object value) {
        map.put(key, value);
    }

    public Object getImpl(Class<?> clazz) {
        return map.get(clazz);
    }
}
