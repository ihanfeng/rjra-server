package com.hdg.rjra.base.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by Rock on 2015/1/2 0002.
 */
public class JsonUtils {

    /**
     * @param inputObject inputObject
     * @return String
     * @discription 对象转json
     * @author rock 创建时间 2014年1月22日 下午12:17:10
     */
    public static String objectToJson(Object inputObject) {
        return JSON.toJSONString(inputObject);
    }

    /**
     *
     * @discription 将JSON转换为对象
     * @author rock 创建时间 2014年1月25日 上午9:19:56
     * @param json
     *            JSON
     * @param obj
     *            obj
     * @param <T>
     *            T
     * @return T t
     */
    public static <T> T jsonToObject(String json, Class<T> obj) {
        return JSON.parseObject(json, obj);
    }
}
