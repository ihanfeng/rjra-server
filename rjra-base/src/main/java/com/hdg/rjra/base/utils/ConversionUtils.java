package com.hdg.rjra.base.utils;

import com.hdg.rjra.base.annotation.DateTimeFormat;
import com.hdg.rjra.base.annotation.Exclusion;
import com.hdg.rjra.base.annotation.FiledName;
import com.hdg.rjra.base.constants.CommonConstants;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 14-7-14.
 */
public class ConversionUtils {
    /**
     * LOG日志
     */
    private static final Logger LOG = Logger.getLogger(com.hdg.rjra.base.utils.ConversionUtils.class);

    /**
     *
     * @description bo转vo
     * @author Sisi 创建时间 2014年7月15日 下午3:12:25
     * @param from
     *            from
     * @param to
     *            to
     */
    public static void conversion(Object from, Object to) {

        if (null == from || null == to) {
            return;
        }
        String inputFieldName = null;
        String datePattern = null;
        boolean isDateStr = false;
        boolean isExclusion = false;

        // 以Vo需要的字段为依据，将Bo中的字段值赋给Vo
        Class<?> outputClass = to.getClass();
        Field[] fromFields = new Field[0];
        Field[] outputFields = getFields(fromFields, outputClass);
        for (Field outputField : outputFields) {
            isDateStr = false;
            isExclusion = false;

            // 1.获取字段注解
            Annotation[] anns = outputField.getDeclaredAnnotations();

            // 2.根据注解进行不同操作
            inputFieldName = outputField.getName();
            if (null != anns && 0 < anns.length) {
                for (Annotation annotation : anns) {
                    if (annotation instanceof FiledName) {
                        inputFieldName = ((FiledName) annotation).value();
                    } else if (annotation instanceof DateTimeFormat) {
                        datePattern = ((DateTimeFormat) annotation).pattern();
                        isDateStr = true;
                    } else if (annotation instanceof Exclusion) {
                        isExclusion = true;
                    } else {
                        LOG.info("ConversionUtils->conversion:has other annotation.");
                    }
                }
            }

            // 不处理该字段
            if (isExclusion) {
                continue;
            }

            // 3.获取Bo对应的字段值
            Object result = accessBoFieldValue(from, inputFieldName);

            if (result == null) {
                continue;
            }

            // 4.转成指定格式的时间字符串
            if (isDateStr) {
                result = getDateStrByPattern(datePattern, result);
            }

            // 5.给Vo赋值
            fillVoFiledValue(to, outputClass, outputField, result);

        }
    }

    private static Field[] getFields(Field[]  oldFields, Class<?> outputClass) {
        Field[] fields = outputClass.getDeclaredFields();
        Field[] newFields = concat(oldFields, fields);
        Class superclass = outputClass.getSuperclass();
        if (null != superclass){
            return getFields(newFields, superclass);
        } else {
            return  newFields;
        }
    }

    private static Field[] concat(Field[] a, Field[] b) {
        Field[] c = new Field[a.length  + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);

        return c;
    }

    /**
     *
     * @description 根据指定模板获取时间字符串
     * @author Sisi
     * 创建时间 2014年7月18日 下午2:12:19     
     * @param datePattern 时间模板
     * @param date 时间
     * @return 时间字符串
     */
    private static String getDateStrByPattern(String datePattern, Object date) {
        String dateStr = "";

        if (date instanceof String) {
            dateStr = DateUtils.formatDate((String) date, datePattern);

        } else if (date instanceof Long) {
            dateStr = DateUtils.utcToDateStr((Long) date, datePattern);

        } else if (date instanceof Date) {
            dateStr = DateUtils.getTimeNow((Date) date, datePattern);
        }
        return dateStr;
    }

    /**
     *
     * @description 根据反射回填Vo对应的字段值
     * @author Sisi 创建时间 2014年7月15日 下午4:21:53
     * @param vo
     *            vo
     * @param outputClass
     *            outputClass
     * @param outputField
     *            outputField
     * @param result
     *            result
     */
    private static void fillVoFiledValue(Object vo, Class<?> outputClass, Field outputField, Object result) {
        StringBuilder outputMethodName = new StringBuilder();
        String firstChar = outputField.getName().substring(0, 1).toUpperCase(Locale.getDefault());
        String otherChar = outputField.getName().substring(1);
        outputMethodName.append(CommonConstants.METHOD_PREFIX_SET);
        outputMethodName.append(firstChar);
        outputMethodName.append(otherChar);
        Method outputMethod;
        try {
            outputMethod = outputClass.getMethod(outputMethodName.toString(), outputField.getType());
            if(outputField.getType().equals(result.getClass())){
                outputMethod.invoke(vo, result);
            }
        } catch (NoSuchMethodException e) {
            LOG.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage());
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage());
        }

    }

    /**
     *
     * @description 获取Bo的字段值
     * @author Sisi 创建时间 2014年7月15日 下午4:14:23
     * @param bo
     *            bo
     * @param fieldName
     *            字段名
     * @return Object
     */
    private static Object accessBoFieldValue(Object bo, String fieldName) {
        Object result = null;
        try {
            result = accessValueByFiled(bo, fieldName);
        } catch (NoSuchFieldException e) {
            LOG.error(e.getMessage());
        } catch (NoSuchMethodException e) {
            LOG.error(e.getMessage());
        } catch (IllegalAccessException e) {
            LOG.error(e.getMessage());
        } catch (InvocationTargetException e) {
            LOG.error(e.getMessage());
        }
        return result;
    }

    /**
     *
     * @description 通过字段名获取字段值
     * @author Sisi 创建时间 2014年7月15日 下午2:49:58
     * @param bo
     *            bo
     * @param fieldName
     *            fieldName
     * @return 方法值
     * @throws NoSuchFieldException
     *             NoSuchFieldException
     * @throws NoSuchMethodException
     *             NoSuchMethodException
     * @throws IllegalAccessException
     *             IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     *             InvocationTargetException
     */
    private static Object accessValueByFiled(Object bo, String fieldName) throws NoSuchFieldException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class<?> objeClass = bo.getClass();

        Field fileld = getField(objeClass, fieldName);

        if (fileld == null) {
            return null;
        }
        StringBuilder methodName = new StringBuilder();

        String firstChar = fieldName.substring(0, 1).toUpperCase(Locale.getDefault());
        String otherChar = fieldName.substring(1);
        if (fileld.getType().isAssignableFrom(boolean.class)) {
            methodName.append(CommonConstants.METHOD_PREFIX_IS);
        } else {
            methodName.append(CommonConstants.METHOD_PREFIX_GET);
        }
        methodName.append(firstChar);
        methodName.append(otherChar);

        Method inputMethod = objeClass.getMethod(methodName.toString());
        return inputMethod.invoke(bo);
    }

    private static Field getField(Class<?> objeClass, String fieldName) {
        Field[] fields = getFields(new Field[0], objeClass);
        for (Field field : fields){
            if (field.getName().equals(fieldName)){
                return field;
            }
        }

        return null;
    }


}
