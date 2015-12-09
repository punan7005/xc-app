package com.api.business.common;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.api.tools.StringUtils;



public class Result {
//	是否成功
	private boolean result;
//	数据集
	private Map<String, Object> respObject;
//	错误码
	private String errorCode;
//	错误信息
	private String errorMsg;
	
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Map<String, Object> getRespObject() {
		if(respObject == null) respObject = new HashMap<String, Object>();
		return respObject;
	}
	public void setRespObject(Map<String, Object> respObject) {
		this.respObject = respObject;
	}
	public String getErrorCode() {
		if(StringUtils.isNullorBlank(errorCode)) errorCode = "";
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		if(StringUtils.isNullorBlank(errorMsg)) errorMsg = "";
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public static String toJson(boolean result, Map<String, Object> respObject, String errorCode, String errorMsg){
		Result resultObject = new Result();
		resultObject.setResult(result);
		resultObject.setRespObject(respObject);
		resultObject.setErrorCode(errorCode);
		resultObject.setErrorMsg(errorMsg);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(resultObject);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static void errorReturn(String errorCode, String errorMsg, HttpServletResponse response){
		try {
			response.getWriter().write(Result.toJson(false, null, errorCode, errorMsg));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
