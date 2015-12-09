package com.api.business.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.api.business.bean.User;
import com.api.business.common.Result;
import com.api.business.service.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService userService;
	
	/** 
	* 方法说明 :用户登陆
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	@RequestMapping("/user/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="phoneNo", required=true) String phoneNo,
			@RequestParam(value="passWord", required=true) String passWord
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//判断用户是否存在
			if(!userService.isExist(phoneNo)){
				Result.errorReturn("1", "用户不存在", response);
				return null;
			}
			User rUser = userService.login(phoneNo, passWord);
			message.put("user", rUser);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/** 
	* 方法说明 :用户信息修改
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	@RequestMapping("/user/modify")
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response, User user){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//更新用户信息
			User rUser = userService.modify(user);
			message.put("user", rUser);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO: handle exception
			Result.errorReturn("1", "用户信息修改异常", response);
		}
		return null;
	}
	
	/** 
	* 方法说明 :用户注册
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	@RequestMapping("/user/regist")
	public ModelAndView regist(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="phoneNo", required=true) String phoneNo,
			@RequestParam(value="passWord", required=true) String passWord
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//判断用户是否存在
			if(userService.isExist(phoneNo)){
				Result.errorReturn("1", "用户已存在", response);
				return null;
			}
			User rUser = userService.create(phoneNo, passWord);
			message.put("user", rUser);
			response.getWriter().write(Result.toJson(true, message, null, null));
 		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/** 
	* 方法说明 :获取用户
	* @author  joker 
	* 创建时间：2015-12-09
	* <p>@param </p>
	*/
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="userId", required=true) String userId
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//获取用户信息
			User rUser = this.userService.get(userId);
			message.put("user", rUser);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
