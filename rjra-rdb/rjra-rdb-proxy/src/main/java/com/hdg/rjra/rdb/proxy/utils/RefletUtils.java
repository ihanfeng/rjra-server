package com.hdg.rjra.rdb.proxy.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Rock on 2014/10/20.
 */
public class RefletUtils {
    private static Logger logger = LoggerFactory.getLogger(RefletUtils.class);


    /**
     * 根据方法的描述获取对此应的方法，没有找到方法返回null
     *
     * @param clazz
     * @param methodName
     * @param paramsType
     * @return
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>[] paramsType) {
        logger.debug("clazz >>>>>" + clazz + " 查找方法 >>>> " + methodName + " paramsType >>>> " + JSON.toJSONString(paramsType));
        Method resultMethod = null;

        for (Method method : clazz.getMethods()) {
            boolean matches = true;
            if (method.getName().equals(methodName)) {
                Class[] getParameterTypes = method.getParameterTypes();
                if (getParameterTypes.length == paramsType.length) {
                    if (getParameterTypes.length != 0) {
                        for (int i = 0; i < getParameterTypes.length; i++) {
                            if (!getParameterTypes[i].isAssignableFrom(paramsType[i])) {
                                matches = false;
                                break;
                            }
                        }
                    }
                } else {
                    matches = false;
                }
            } else {
                matches = false;
            }

            if (matches) {
                resultMethod = method;
                break;
            }
        }
        return resultMethod;
    }

    public static Object invoke(Method method, Object instance, Object[] paramValue) throws Exception {
        Object obj = null;
        try {
            if (paramValue.length == 0) {
                obj = method.invoke(instance, null);
            } else {
                obj = method.invoke(instance, paramValue);
            }
        } catch (Exception e) {
            throw e;
        }
        return obj;
    }


    public static String getFieldValue(Object obj, String fieldName) {
        String value = null;
        String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method method = null;
        Object methodValue = null;
        try {
            method = obj.getClass().getMethod(methodName);
        } catch (Exception e) {

        }
        if (method != null) {
            try {
                methodValue = method.invoke(obj);
            } catch (Exception e) {
            }
            if (methodValue != null) {
                value = methodValue.toString();
            } else {
                Class<?> objSuperClass = obj.getClass().getSuperclass();
                while (true) {
                    if (objSuperClass != null) {
                        try {
                            methodValue = method.invoke(objSuperClass);
                        } catch (Exception e) {
                        }
                        if (methodValue != null) {
                            value = methodValue.toString();
                            break;
                        } else {
                            objSuperClass = objSuperClass.getSuperclass();
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return value;
    }
}
