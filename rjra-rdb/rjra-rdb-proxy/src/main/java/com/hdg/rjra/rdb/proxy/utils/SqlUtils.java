package com.hdg.rjra.rdb.proxy.utils;

import com.hdg.common.utils.StringUtils;
import com.hdg.rjra.rdb.proxy.domain.BaseDomain;
import com.hdg.rjra.rdb.proxy.domain.Company;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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

    public static SqlParam buildUpdateSqlByDomain(BaseDomain domain) {
        if (domain == null) {
            return null;
        } else {
            SqlParam sqlParam = new SqlParam();
            StringBuffer sql = new StringBuffer();
            StringBuffer sqlWhere = new StringBuffer();
            List<Object> objectList = new ArrayList<Object>();
            List<Object> objectWhereList = new ArrayList<Object>();
            Class clazz = domain.getClass();
            Annotation[] clazzDeclaredAnnotations = clazz.getDeclaredAnnotations();
            boolean hasPkId = false;
            for (int i = 0; i < clazzDeclaredAnnotations.length; i++) {
                Annotation annotation = clazzDeclaredAnnotations[i];
                if (annotation instanceof DBClass) {
                    String dbTableName = ((DBClass) annotation).value();
                    if (dbTableName != null) {
                        sql.append("UPDATE ");
                        sql.append(dbTableName);
                        sql.append(" SET ");
                        Field[] fields = clazz.getDeclaredFields();
                        for (int j = 0; j < fields.length; j++) {
                            Field field = fields[j];
                            Annotation[] annotations = field.getDeclaredAnnotations();
                            for (int k = 0; k < annotations.length; k++) {
                                Annotation fieldAnnotation = annotations[k];
                                if (fieldAnnotation instanceof DBField) {
                                    DBField dbField = ((DBField) fieldAnnotation);
                                    String fieldName = field.getName();
                                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase(Locale.getDefault()) + fieldName.substring(1);
                                    try {
                                        Method method = clazz.getMethod(methodName);
                                        Object object = method.invoke(domain);
                                        if (object != null) {
                                            if (dbField.pk()) {
                                                hasPkId = true;
                                                sqlWhere.append(" WHERE ");
                                                sqlWhere.append(dbField.value());
                                                sqlWhere.append(" =?");
                                                objectWhereList.add(object);
                                            } else {
                                                sql.append(dbField.value());
                                                sql.append(" =?,");
                                                if (object instanceof Long[]){
                                                    objectList.add(StringUtils.longArrayToString((Long[]) object));
                                                }
                                                else {
                                                    objectList.add(object);
                                                }
                                            }
                                        }
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if(!hasPkId){
                return null;
            }
            String executeSql = sql.toString().substring(0,sql.length()-1) + sqlWhere.toString();
            objectList.addAll(objectWhereList);
            sqlParam.setSql(executeSql);
            sqlParam.setObjects(objectList);
            return sqlParam;
        }
    }

    public static SqlParam buildSelectSqlByDomain(BaseDomain domain) {
            SqlParam sqlParam = new SqlParam();
            StringBuffer sql = new StringBuffer();
            StringBuilder countSql = new StringBuilder("select count(1) cnt from (");
            StringBuffer innerSql = new StringBuffer();
            StringBuffer orderBy = new StringBuffer();
            List<Object> objectList = new ArrayList<Object>();
            boolean isOrder = false;
            Class clazz = domain.getClass();
            Annotation[] clazzDeclaredAnnotations = clazz.getDeclaredAnnotations();
            for (int i = 0; i < clazzDeclaredAnnotations.length; i++) {
                Annotation annotation = clazzDeclaredAnnotations[i];
                if (annotation instanceof DBClass) {
                    DBClass dbClass = (DBClass) annotation;
                    String dbTableName = dbClass.value();
                    if (dbTableName != null) {
                        sql.append("SELECT * FROM ");
                        sql.append(dbTableName);
                        sql.append(" WHERE 1 = 1 ");
                        innerSql.append("SELECT * FROM ");
                        innerSql.append(dbTableName);
                        innerSql.append(" WHERE 1 = 1 ");

                        Field[] fields = clazz.getDeclaredFields();
                        for (int j = 0; j < fields.length; j++) {
                            Field field = fields[j];
                            Annotation[] annotations = field.getDeclaredAnnotations();
                            for (int k = 0; k < annotations.length; k++) {
                                Annotation fieldAnnotation = annotations[k];
                                if (fieldAnnotation instanceof DBField) {
                                    DBField dbField = ((DBField) fieldAnnotation);
                                    String fieldName = field.getName();
                                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase(Locale.getDefault()) + fieldName.substring(1);
                                    try {
                                        Method method = clazz.getMethod(methodName);
                                        Object object = method.invoke(domain);
                                        if (object != null) {
                                            sql.append(" AND ");
                                            innerSql.append(" AND ");
                                            if (object instanceof Long[]){
                                                Long[] obj = (Long[]) object;
                                                sql.append(dbField.value());
                                                sql.append(" in (-1");
                                                innerSql.append(dbField.value());
                                                innerSql.append(" in (-1");
                                                for (int n = 0; n < obj.length; n++) {
                                                    sql.append(",?");
                                                    innerSql.append(",?");
                                                    objectList.add(obj[n]);
                                                }
                                                sql.append(")");
                                                innerSql.append(")");
                                            } else  if (object instanceof String){
                                                String obj = (String) object;
                                                sql.append(dbField.value());
                                                sql.append(" like ?");
                                                innerSql.append(dbField.value());
                                                innerSql.append(" like ?");
                                                objectList.add("%" + obj + "%");
                                            }
                                            else {
                                                sql.append(dbField.value());
                                                sql.append(" =?");
                                                innerSql.append(dbField.value());
                                                innerSql.append(" =?");
                                                objectList.add(object);
                                            }
                                        }
                                        if(dbField.order()){
                                            isOrder = isOrder || dbField.order();
                                            orderBy.append(dbField.value());
                                            orderBy.append(",");
                                        }
                                    } catch (NoSuchMethodException e) {
                                        e.printStackTrace();
                                    } catch (InvocationTargetException e) {
                                        e.printStackTrace();
                                    } catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        if(isOrder) {
                            String sortSql = orderBy.substring(0, orderBy.length() - 1);
                            sql.append(" ORDER BY ");
                            sql.append(sortSql);
                            sql.append(" ");
                            sql.append(dbClass.sortBy());
                        }
                    }
                }
            }
            countSql.append(innerSql.toString());
            countSql.append(") as pagertable");
            sqlParam.setCountSql(countSql.toString());
            sqlParam.setSql(sql.toString());
            sqlParam.setObjects(objectList);
            return sqlParam;
    }
    public static void main(String[] args) {
        Company company = new Company();
        company.setCompanyId(1L);
        company.setCompanyName("haha");
        buildSelectSqlByDomain(company);
    }

    public static SqlParam buildWhereAndSqlByMapParam(Map<?, Object> param) {
        if (param == null || param.size() == 0) {
            return null;
        } else {
            SqlParam sqlParam = new SqlParam();
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
                    } else if (String.valueOf(op).trim().equals("like")) {
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
            sqlParam.setSql(sql.toString());
            sqlParam.setObjects(objectList);
            return sqlParam;
        }
    }
}
