package com.api.business.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class MainController {
	
	@RequestMapping()
	public ModelAndView send(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="phoneNo", required=true) String phoneNo
			){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
}
