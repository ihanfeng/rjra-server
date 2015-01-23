package com.hdg.rjra.base.serializer;

import java.io.Serializable;

/**
 * 最想的序列化 和反序列化
 * 
 * @author hexin
 * 
 */
public interface Serializer extends Serializable {

    /**
     * 实例序列化
     * 
     * @param o
     * @return
     * @throws SerializerException
     */
    byte[] serialize(Object o) throws SerializerException;

    /**
     * 实例反序列化
     * 
     * @param value
     * @return
     * @throws SerializerException
     */
    Object deserialize(byte[] value) throws SerializerException;
}
