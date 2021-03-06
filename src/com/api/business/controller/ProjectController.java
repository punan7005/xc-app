package com.api.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.api.business.bean.Project;
import com.api.business.common.Result;
import com.api.business.service.IProjectService;
import com.api.tools.Page;

@Controller
public class ProjectController {
	
	@Autowired
	private IProjectService projectService;
	
	/** 
	* 方法说明 :项目创建
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	@RequestMapping("/project/create")
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response,
			Project project,
			@RequestParam(value="userId", required=true) String userId
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//创建项目
			Project rProject = this.projectService.create(project, userId);
			message.put("project", rProject);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO: handle exception
			Result.errorReturn("1", "项目创建异常", response);
		}
		return null;
	}
	
	/** 
	* 方法说明 :项目修改
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	@RequestMapping("/project/modify")
	public ModelAndView modify(HttpServletRequest request, HttpServletResponse response,
			Project project,
			@RequestParam(value="userId", required=true) String userId
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//项目修改
			Project rProject = this.projectService.modify(project, userId);
			message.put("project", rProject);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO: handle exception
			Result.errorReturn("1", "项目修改异常", response);
		}
		return null;
	}
	
	/** 
	* 方法说明 :获取单独项目信息
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	@RequestMapping("/project/get")
	public ModelAndView get(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="id", required=true) String id
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			//获取项目
			Project rProject = this.projectService.get(id);
			message.put("project", rProject);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/** 
	* 方法说明 :获取用户所有项目
	* @author  joker 
	* 创建时间：2015-12-09
	* <p>@param </p>
	*/
	@RequestMapping("/project/getsByUser")
	public ModelAndView getsByUser(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="userId", required=true) String userId,
			@RequestParam(value="pageSize", required=false) Integer pageSize,
			@RequestParam(value="pageNo", required=false) Integer pageNo
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			
			if("".equals(pageNo) || pageNo == null) pageNo = 1;
			if("".equals(pageSize) || pageSize == null) pageSize = Page.DEFAULT_PAGE_ROW_COUNT;
			
			//获取用户所有项目
			List<Project> list = this.projectService.getsByUser(userId, pageSize, pageNo);
			message.put("projects", list);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	/** 
	* 方法说明 :获取所有项目
	* @author  joker 
	* 创建时间：2015-12-08
	* <p>@param </p>
	*/
	@RequestMapping("/project/gets")
	public ModelAndView gets(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="pageSize", required=false) Integer pageSize,
			@RequestParam(value="pageNo", required=false) Integer pageNo
			){
		try {
			Map<String, Object> message = new HashMap<String, Object>();
			response.setCharacterEncoding("utf-8");
			
			if("".equals(pageNo) || pageNo == null) pageNo = 1;
			if("".equals(pageSize) || pageSize == null) pageSize = Page.DEFAULT_PAGE_ROW_COUNT;
			
			//获取所有项目
			List<Project> list = this.projectService.gets(pageSize, pageNo);
			message.put("projects", list);
			response.getWriter().write(Result.toJson(true, message, null, null));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
