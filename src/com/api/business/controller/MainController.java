package com.api.business.controller;

import java.util.HashMap;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.api.business.common.Result;
import com.api.business.common.SystemUtil;
import com.api.business.service.IUserService;
import com.api.tools.SendMessageTools;

@Controller
public class MainController {
	
	@Autowired
	private IUserService userService;
	
	/** 
	* 方法说明 :发送短信
	* @author  joker 
	* 创建时间：2015-12-09
	* <p>@param </p>
	*/
	@RequestMapping("/main/send")
	public ModelAndView send(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="phoneNo", required=true) String phoneNo,
			@RequestParam(value="type", required=true) int type
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//判断用户是否存在
			boolean isExist = this.userService.isExist(phoneNo);
			//注册验证
			if(isExist && type == 1){
				Result.errorReturn("1", "手机已被注册", response);
				return null;
			}
			//找回验证
			if(!isExist && type == 2){
				Result.errorReturn("2", "手机号无效", response);
			}
			String valCode = SendMessageTools.sendMessageForHY(phoneNo);
			SystemUtil.setValCode(request, valCode);
			response.getWriter().write(Result.toJson(true, null, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/** 
	* 方法说明 :验证短信
	* @author  joker 
	* 创建时间：2015-12-09
	* <p>@param </p>
	*/
	@RequestMapping("/main/valCode")
	public ModelAndView valCode(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="valCode", required=true) String valCode
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//验证输入码是否正确
			String sendCode = SystemUtil.getValCode(request);
			if(!sendCode.equals(valCode)){
				Result.errorReturn("1", "验证码输入错误", response); 
				return null;
			}
			response.getWriter().write(Result.toJson(true, null, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
}
