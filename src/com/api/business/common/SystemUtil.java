package com.api.business.common;

import javax.servlet.http.HttpServletRequest;

import com.api.business.bean.User;





public final class SystemUtil {
	
	public static void setLoginUser(HttpServletRequest request, User user){
		request.getSession().setAttribute(Constants.SESSION_USER_KEY, user);
	}
	
	public static User getCurrentUser(HttpServletRequest request){
		Object user = request.getSession().getAttribute(Constants.SESSION_USER_KEY);
		if(user != null) return (User)user;
		else return null;
	}
	
	public static boolean isLogin(HttpServletRequest request){
		if(request.getSession().getAttribute(Constants.SESSION_USER_KEY) == null) return false;
		else return true;
	}
	
	public static boolean clearLogin(HttpServletRequest request){
		request.getSession().removeAttribute(Constants.SESSION_USER_KEY);
		return true;
	}
	
	public static void setValCode(HttpServletRequest request, String valCode){
		request.getSession().setAttribute(Constants.SESSION_USER_VAL_CODE, valCode);
	}
	
	public static String getValCode(HttpServletRequest request){
		Object valcode = request.getSession().getAttribute(Constants.SESSION_USER_VAL_CODE);
		if(valcode != null) return (String)valcode;
		else return null;
	}
	
	/**
	 * 增加开发时候不关心登录
	 * @return
	 */
	public static boolean isDebug(){
		return true;
	}
	
}
