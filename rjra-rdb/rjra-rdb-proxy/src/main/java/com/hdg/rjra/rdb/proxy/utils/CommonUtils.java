package com.hdg.rjra.rdb.proxy.utils;

import java.util.List;

/**
 * Created by Rock on 2014/10/25.
 */
public abstract class CommonUtils {
    /**
     * 将list进行逗号分隔
     *
     * @param list
     * @return
     */
    public static String array2String(List list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, len = list.size(); i < len; i++) {
            stringBuilder.append(list.get(i));
            if (i + 1 < len) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
