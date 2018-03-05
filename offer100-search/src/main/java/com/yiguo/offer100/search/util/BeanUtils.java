package com.yiguo.offer100.search.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {    
        if (map == null)   
            return null;    
  
        Object obj = null;
                
        try {
            obj = beanClass.newInstance();  
  
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
            for (PropertyDescriptor property : propertyDescriptors) {  
                Method setter = property.getWriteMethod();    
                if (setter != null) {  
                    setter.invoke(obj, map.get(property.getName()));   
                }  
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  
  
        return obj;  
    }    
      
    public static Map<String, Object> objectToMap(Object obj){    
        if(obj == null)  
            return new HashMap<String, Object>();      
  
        Map<String, Object> map = new HashMap<String, Object>();   
  
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
            for (PropertyDescriptor property : propertyDescriptors) {    
                String key = property.getName();    
                if (key.compareToIgnoreCase("class") == 0) {   
                    continue;  
                }  
                Method getter = property.getReadMethod();  
                Object value = getter!=null ? getter.invoke(obj) : null;  
                map.put(key, value);  
            }
        } catch (Exception e){
            e.printStackTrace();
        }    
  
        return map;  
    }    
      
}  
  
