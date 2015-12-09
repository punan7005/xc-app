package com.api.tools;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.portable.ApplicationException;

import com.api.business.bean.Project;
import com.api.business.bean.User;


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
	
	public static void createSqlMap(Object bean, String filePath) throws Exception{
		List<String> propertis = new ArrayList<String>();
		List<String> propertisType = new ArrayList<String>();
		Class type = bean.getClass();  
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        BeanInfo beanInfo = Introspector.getBeanInfo(type);  
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {  
        	PropertyDescriptor descriptor = propertyDescriptors[i];
        	if (!descriptor.getName().equals("class")) propertis.add(descriptor.getName());
        	if (!descriptor.getName().equals("class")) propertisType.add(descriptor.getPropertyType().getSimpleName());
        }
        
        
		StringBuffer info = new StringBuffer();
		info.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>").append("\n");
		info.append("<!DOCTYPE sqlMap PUBLIC \"-//iBATIS.com//DTD SQL Map 2.0//EN\" \"http://www.ibatis.com/dtd/sql-map-2.dtd\">").append("\n");
		info.append("\n");
		info.append("<!-- The SQL in these maps files has purposely been typed with more verbosity than is actually required.").append("\n");
		info.append(" For example, many of the selects could have just used SELECT * FROM... instead of listing all of the columns.").append("\n");
		info.append("This is to demonstrate that even complex SQL can be handled and coded and formatted the way SQL was meant").append("\n");
		info.append(" to be (i.e. not a bunch of concatenated strings in a Java class). -->").append("\n");
		info.append("\n");
		info.append("<sqlMap namespace=\"").append(bean.getClass().getCanonicalName()).append("\">").append("\n");
		info.append("\n");
		info.append("  <typeAlias alias=\"").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\" type=\"").append(bean.getClass().getCanonicalName()).append("\"/>").append("\n");
		info.append("\n");
		info.append("  <resultMap id=\"").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("Result").append("\" class=\"").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\">").append("\n");
		for(int i = 0; i < propertis.size(); i++){
			info.append("    <result property=\"").append(propertis.get(i)).append("\" column=\"").append(StringUtils.toUnderlineName(propertis.get(i))).append("\"/>").append("<!-- ").append(propertisType.get(i)).append(" -->").append("\n");
		}
		info.append("  </resultMap>").append("\n");
		info.append("\n");
		info.append("  <select id=\"selectById\" resultMap=\"").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("Result").append("\">").append("\n");
		info.append("    select * from ").append("tb_").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\n");
		info.append("		<dynamic prepend=\"where\">").append("\n");
		info.append("			<isParameterPresent>").append("\n");
		info.append("				id = #value#").append("\n");
		info.append("			</isParameterPresent>").append("\n");
		info.append("		</dynamic>").append("\n");
		info.append("  </select>").append("\n");
		info.append("\n");
		info.append("  <select id=\"selectByMap\" resultMap=\"").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("Result").append("\">").append("\n");
		info.append("    select * from ").append("tb_").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\n");
		info.append("		<dynamic prepend=\"where\">").append("\n");
		for(int i = 0; i < propertis.size(); i++){
			info.append("			<isNotNull prepend=\"and\" property=\"").append(propertis.get(i)).append("\">").append("\n");
			info.append("				").append(StringUtils.toUnderlineName(propertis.get(i))).append(" = #").append(propertis.get(i)).append("#").append("\n");
			info.append("			</isNotNull>").append("\n");
		}
		info.append("		</dynamic>").append("\n");
		info.append("		<dynamic>").append("\n");
		info.append("			<isNotNull prepend=\"order by\" property=\"orderBy\">").append("\n");
		info.append("				$orderBy$ $order$").append("\n");
		info.append("			</isNotNull>").append("\n");
		info.append("			<isNotNull prepend=\"limit\" property=\"limit\">").append("\n");
		info.append("				$start$,$end$").append("\n");
		info.append("			</isNotNull>").append("\n");
		info.append("		</dynamic>").append("\n");
		info.append("  </select>").append("\n");
		info.append("\n");
		info.append("  <insert id=\"insert\" parameterClass=\"").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\">").append("\n");
		info.append("       insert into ").append("tb_").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\n");
		info.append("				(").append("\n");
		for(int i = 0; i < propertis.size(); i++){
			if(i == propertis.size() - 1){
				info.append("				").append(StringUtils.toUnderlineName(propertis.get(i))).append("\n");
			}else{
				info.append("				").append(StringUtils.toUnderlineName(propertis.get(i))).append(",").append("\n");
			}
		}
		info.append("				)").append("\n");
		info.append("				values").append("\n");
		info.append("				(").append("\n");
		for(int i = 0; i < propertis.size(); i++){
			if(i == propertis.size() - 1){
				info.append("				#").append(propertis.get(i)).append("#").append("\n");
			}else{
				info.append("				#").append(propertis.get(i)).append("#,").append("\n");
			}
		}
		info.append("				)").append("\n");
		info.append("    <selectKey resultClass=\"java.lang.String\" keyProperty=\"uuid\">").append("\n");
		info.append("    	select last_insert_id();").append("\n");
		info.append("    </selectKey>").append("\n");
		info.append("  </insert>").append("\n");
		info.append("\n");
		info.append("  <select id=\"count\" resultClass=\"int\">").append("\n");
		info.append("  		select count(*) from ").append("tb_").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\n");
		info.append("  		<dynamic prepend=\"where\">").append("\n");
		for(int i = 0; i < propertis.size(); i++){
			info.append("			<isNotNull prepend=\"and\" property=\"").append(propertis.get(i)).append("\">").append("\n");
			info.append("				").append(StringUtils.toUnderlineName(propertis.get(i))).append(" = #").append(propertis.get(i)).append("#").append("\n");
			info.append("			</isNotNull>").append("\n");
		}
		info.append("  		</dynamic>").append("\n");
		info.append("  </select>").append("\n");
		info.append("\n");
		info.append("  <update id=\"update\" parameterClass=\"").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\">").append("\n");
		info.append("	update ").append("tb_").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\n");
		info.append("	set").append("\n");
		for(int i = 0; i < propertis.size(); i++){
			if(i == propertis.size() - 1){
				info.append("	").append(StringUtils.toUnderlineName(propertis.get(i))).append(" = #").append(propertis.get(i)).append("#").append("\n");
			}else{
				info.append("	").append(StringUtils.toUnderlineName(propertis.get(i))).append(" = #").append(propertis.get(i)).append("#,").append("\n");
			}
		}
		info.append("	where").append("\n");
		info.append("	id = #uuid#;").append("\n");
		info.append("  </update>").append("\n");
		info.append("\n");
		info.append("  <update id=\"updateByMap\">").append("\n");
		info.append("    update ").append("tb_").append(StringUtils.toUnderlineName(bean.getClass().getSimpleName())).append("\n");
		info.append("    	<dynamic prepend=\"set\">").append("\n");
		for(int i = 0; i < propertis.size(); i++){
			info.append("			<isNotNull prepend=\",\" property=\"").append(propertis.get(i)).append("\">").append("\n");
			info.append("				").append(StringUtils.toUnderlineName(propertis.get(i))).append(" = #").append(propertis.get(i)).append("#").append("\n");
			info.append("			</isNotNull>").append("\n");
		}
		info.append("    	</dynamic>").append("\n");
		info.append("    	where id = #id#").append("\n");
		info.append("  </update>").append("\n");
		info.append("</sqlMap>").append("\n");
		
		
		
		
		
		FileTools fileTools = new FileTools();
		fileTools.addTxtEnd(filePath + bean.getClass().getSimpleName() + ".sqlMap.xml", info.toString());
	}
	
	public static void main(String[] strings) throws Exception{
		User user = new User();
		Project project = new Project();
		BeanTools.createSqlMap(project, "c:\\jokerpu\\test\\");
	}
}
