package com.api.tools;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.omg.CORBA.portable.ApplicationException;


/** 
 * 类说明 :洗实体的null变成空字符串
 * @author  joker 
 * 创建时间：2015-1-21
 */
public class BeanTools {
	/** 
	* 方法说明 :洗
	* @author  joker 
	* 创建时间：2015-03-26
	* <p>@param String src</p>
	*/
	public static void nullConverNullString(Object obj) { 
		if (obj != null) { 
			Class classz = obj.getClass(); 
			// 获取所有该对象的属性值 
			Field fields[] = classz.getDeclaredFields(); 

			// 遍历属性值，取得所有属性为 null 值的 
			for (Field field : fields) { 
				try { 
					Type t = field.getGenericType(); 
					if (!t.toString().equals("boolean") && !t.toString().equals("int") && !t.toString().equals("Integer")) { 
						Method m = classz.getMethod("get" 
								+ change(field.getName())); 
						Object name = m.invoke(obj);// 调用该字段的get方法 
						if (name == null) { 

							Method mtd = classz.getMethod("set" 
									+ change(field.getName()), 
									new Class[] { String.class });// 取得所需类的方法对象 
							mtd.invoke(obj, new Object[] { "" });// 执行相应赋值方法 
						} 
					} 
				} catch (Exception e) { 
					e.printStackTrace(); 
				} 
			} 
		} 
	} 

	/** 
	* 方法说明 :第一个字母变大写
	* @author  joker 
	* 创建时间：2015-03-26
	* <p>@param String src</p>
	*/
	public static String change(String src) { 
		if (src != null) { 
			StringBuffer sb = new StringBuffer(src); 
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0))); 
			return sb.toString(); 
		} else { 
			return null; 
		} 
	}
	
	/** 
     * 将一个 JavaBean 对象转化为一个  Map 
     * @param bean 要转化的JavaBean 对象 
     * @return 转化出来的  Map 对象 
     * @throws IntrospectionException 如果分析类属性失败 
     * @throws IllegalAccessException 如果实例化 JavaBean 失败 
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败 
     */
	public static Map<String, Object> convertBean(Object bean)  
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {  
        Class type = bean.getClass();  
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        BeanInfo beanInfo = Introspector.getBeanInfo(type);  
  
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
        for (int i = 0; i< propertyDescriptors.length; i++) {  
            PropertyDescriptor descriptor = propertyDescriptors[i];  
            String propertyName = descriptor.getName();  
            if (!propertyName.equals("class")) {  
                Method readMethod = descriptor.getReadMethod();  
                Object result = readMethod.invoke(bean, new Object[0]);  
                if (result != null) {  
                    returnMap.put(propertyName, result);  
                } else {  
                    returnMap.put(propertyName, "");  
                }  
            }  
        }  
        return returnMap;  
    }  
}
