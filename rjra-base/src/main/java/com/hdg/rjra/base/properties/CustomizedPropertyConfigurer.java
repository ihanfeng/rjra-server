package com.hdg.rjra.base.properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Title: CustomizedPropertyConfigurer.java
 * @Copyright: Copyright © 2014
 * @Description: 
 * @Company: yeahmobi
 * @Created on 上午11:47:21
 * @author yanghao
 */

public class CustomizedPropertyConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, Object> ctxPropertiesMap;  
	  
    @Override  
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,  
            Properties props)throws BeansException {  
  
        super.processProperties(beanFactory, props);  
        //load properties to ctxPropertiesMap  
        ctxPropertiesMap = new HashMap<String, Object>();  
        for (Object key : props.keySet()) {  
            String keyStr = key.toString();  
            String value = props.getProperty(keyStr);  
            ctxPropertiesMap.put(keyStr, value);  
        }  
    }  
  
    //static method for accessing context properties  
    public static Object getContextProperty(String name) {  
        return ctxPropertiesMap.get(name);  
    }

    //static method for accessing context properties for return String
    public static String getContextPropertyForString(String name) {
        return ctxPropertiesMap.get(name).toString();
    }

    public static Long getContextPropertyForLong(String name) {
        return Long.parseLong(ctxPropertiesMap.get(name).toString());
    }

    public static Integer getContextPropertyForInteger(String name) {
        return Integer.parseInt(ctxPropertiesMap.get(name).toString());
    }
}
