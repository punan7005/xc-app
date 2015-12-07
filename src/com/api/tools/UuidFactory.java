package com.api.tools;

import java.util.UUID;

/** 
 * 类说明 ：生成uuid，直接调用静态方法
 * @author  zealot
 * 创建时间：2012-6-20 上午11:07:41 
 */
public class UuidFactory {
	/** 
     * 方法描述： 生成uuid本方法为静态方法
     * @param null
     * @author jokerPu
     */
	public static String getUuid(){
		UUID uuidFactory = UUID.randomUUID();
		String uuid = uuidFactory.toString().replaceAll("-", "");
		return uuid;
	}
	
	/** 
     * 方法描述： 主函数
     * @param args
     * @author jokerPu
     */ 
	public static void main(String[] args){
		System.out.println(UuidFactory.getUuid());
		String oldUUid = null;
		for(int i = 0; i < 100000; i++){
			String newUUid = UuidFactory.getUuid();
			if(oldUUid != newUUid){
				oldUUid = newUUid;
				System.out.println(oldUUid);
				System.out.println(newUUid);
				break;
			}else{
				System.out.println("wokaole");
			}
			System.out.println(i);
		}
	}
}
