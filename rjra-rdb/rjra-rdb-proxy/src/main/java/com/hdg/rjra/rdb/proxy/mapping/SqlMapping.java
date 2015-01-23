package com.hdg.rjra.rdb.proxy.mapping;

import java.util.Map;

/**
 * Created by Rock on 2014/10/21.
 */
public interface SqlMapping<T> {

    abstract T mapping(Map<String, Object> map);
}
