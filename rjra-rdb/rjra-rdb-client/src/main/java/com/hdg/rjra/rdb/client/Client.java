package com.hdg.rjra.rdb.client;


import java.io.Closeable;
import java.io.Serializable;

/**
 * Created by Rock on 2014/10/16.
 */
public interface Client extends Closeable,Serializable {
    public <T> T invoke(String className, String method, Object[] params);
}
