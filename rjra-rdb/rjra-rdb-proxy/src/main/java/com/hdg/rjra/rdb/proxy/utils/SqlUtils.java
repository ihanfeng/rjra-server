package com.hdg.rjra.rdb.proxy.utils;

import com.hdg.rjra.rdb.proxy.domain.enumerate.BaseMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 拼装sql的时候使用
 * Created by Rock on 2014/10/30.
 */
public abstract class SqlUtils {

    /**
     * number = 2 返回值 ?,?
     * number = 4  返回 ?,?,?,?
     *
     * @param number
     * @return
     */
    public static String appendPlaceholder(int number) {
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < number; i++) {
            if (i + 1 < number) {
                sql.append("?,");
            } else {
                sql.append("?");
            }
        }
        return sql.toString();
    }

    public static List<Object> arrayToList(Object[] objects) {
        List<Object> objectList = new ArrayList<Object>();
        if (objects == null || objects.length == 0) {
            return objectList;
        } else {
            for (Object object : objects) {
                objectList.add(object);
            }
            return objectList;
        }
    }


    public static WhereAndSqlParam buildWhereAndSqlByMapParam(Map<?, Object> param) {
        if (param == null) {
            return null;
        } else {
            WhereAndSqlParam whereAndSqlParam = new WhereAndSqlParam();
            StringBuffer sql = new StringBuffer();
            List<Object> objectList = new ArrayList<Object>();
            for (Map.Entry<?, Object> baseMappingObjectEntry : param.entrySet()) {
                Object mapping = baseMappingObjectEntry.getKey();
                Object obj = baseMappingObjectEntry.getValue();
                Class mappingClass = mapping.getClass();
                try {
                    Method fieldMethod = mappingClass.getMethod("getDbField");
                    Object field = fieldMethod.invoke(mapping);
                    Method opMethod = mappingClass.getMethod("getOp");
                    Object op = opMethod.invoke(mapping);
                    sql.append(" AND ");
                    sql.append(field);
                    sql.append(op);
                    if (String.valueOf(op).trim().equals("in")) {
                        sql.append("(-1");
                        if (obj instanceof Long[]) {
                            Long[] longs = (Long[]) obj;
                            for (int i = 0; i < longs.length; i++) {
                                Long id = longs[i];
                                sql.append(",?");
                                objectList.add(id);
                            }
                        } else if (obj instanceof List) {
                            List objList = (List) obj;
                            for (Object o : objList) {
                                sql.append(",?");
                                objectList.add(o);
                            }
                        }
                        sql.append(")");
                    } else if(String.valueOf(op).trim().equals("like")){
                        sql.append("'%");
                        sql.append(String.valueOf(obj));
                        sql.append("%'");
                    } else {
                        sql.append("?");
                        objectList.add(obj);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
            whereAndSqlParam.setSql(sql.toString());
            whereAndSqlParam.setObjects(objectList);
            return whereAndSqlParam;
        }
    }
}
