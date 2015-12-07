package com.api.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SendMessageTools {
	
	
	/** 
	* 方法说明 :互亿无线短信接口工具类
	* @author  joker 
	* 创建时间：2015-02-27
	* 网址：http://www.ihuyi.com/
	* <p>@param SchoolBaseInfo</p>
	*/
	public static String sendMessageForHY(String phoneNo){
		
		String code = null;	
		String msg = null;	
		String smsid = null;
		
		HttpClient client = new HttpClient(); 
		PostMethod method = new PostMethod("http://106.ihuyi.cn/webservice/sms.php?method=Submit"); 
			
		//client.getParams().setContentCharset("GBK");		
		client.getParams().setContentCharset("UTF-8");
		method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

		
		int mobileCode = RandomNoTools.getRandomNo();

		//System.out.println(mobile);
		
	    String content = new String("您的验证码是：" + mobileCode + "。请不要把验证码泄露给其他人。"); 

		NameValuePair[] data = {
			    new NameValuePair("account", "cf_jokerpu"), 
			    new NameValuePair("password", "duanxin!321"), 
//			    new NameValuePair("password", MD5Util.getMD5String("duanxin!321")),
			    new NameValuePair("mobile", phoneNo), 
			    new NameValuePair("content", content),
		};
		
		method.setRequestBody(data);		
		
		
		try {
			client.executeMethod(method);	
			
			String SubmitResult =method.getResponseBodyAsString();
					
			//System.out.println(SubmitResult);

			Document doc = DocumentHelper.parseText(SubmitResult); 
			Element root = doc.getRootElement();


			code = root.elementText("code");	
			msg = root.elementText("msg");	
			smsid = root.elementText("smsid");	
						
			if(code == "2"){
				System.out.println("提交成功");
			}
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return String.valueOf(mobileCode);
	}
	
	/** 
	* 方法说明 :华兴短信接口工具类
	* @author  joker 
	* 创建时间：2015-02-27
	* 网址：http://www.ihuyi.com/
	* <p>@param SchoolBaseInfo</p>
	*/
	public static String sendMessageForExByHX(String phoneNo, int type, String parentNo, String parentName, String exTime){
		
		String strReg = "101100-WEB-HUAX-770301";
		String strPwd = "SGOIMFQM";

		//子通道号，可为空（预留参数一般为空）
		String strSourceAdd = null;
		//内容
		String strContent = null;
		//验证码
		int mobileCode = RandomNoTools.getRandomNo();
		//模板
		try {
			if(type == 1) strContent = HttpSend.paraTo16("考点变动通知,考点：" + parentName + "，考点代码：" + parentNo + "，考试时间：" + exTime + "，已经报满【66学网】");
			if(type == 2) strContent = HttpSend.paraTo16("考点变动通知,考点：" + parentName + "，考点代码：" + parentNo + "，考试时间：" + exTime + "，有考位了【66学网】");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//请求地址
		String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
		//清秋参数
		String strSmsParam = "reg=" + strReg + "&pwd=" + strPwd + "&sourceadd=" + strSourceAdd + "&phone=" + phoneNo + "&content=" + strContent;
		//返回值
		String strRes = null;
		
		//发送短信
        strRes = HttpSend.postSend(strSmsUrl, strSmsParam);
        String[] result = strRes.split("&");
        if("result=0".equals(result[0])) return String.valueOf(mobileCode); 
        return null;
	}
	
	public static void main(String[] args){
//		SendMessageTools.sendMessageForHX("13681109674", 1);
		sendMessageForExByHX("13581651017", 1, "STN80055D", "高等教育出版社（马甸考点）", "2016年1月24日 09:00");//yyf
		sendMessageForExByHX("13810776570", 1, "STN80055D", "高等教育出版社（马甸考点）", "2016年1月24日 09:00");//stt
//		sendMessageForExByHX("13681109674", 1, "STN80055D", "高等教育出版社（马甸考点）", "2016年1月24日 09:00");
//		Map<String, Object> message = new HashMap<String, Object>();
//		message = null;
//		message.get("nimei");
	}
	
}
