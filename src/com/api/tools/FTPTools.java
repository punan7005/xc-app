package com.api.tools;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

public class FTPTools {
	
	/** 
	 * 方法说明 :FTP put
	 * @author  joker 
	 * 创建时间：2015-04-23
	 * @param url FTP服务器hostname
	 * @param port FTP服务器端口
	 * @param username FTP登录账号
	 * @param password FTP登录密码
	 * @param path FTP服务器保存目录
	 * @param filename 上传到FTP服务器上的文件名
	 * @param input 输入流
	 * @return 成功返回true，否则返回false
	 */
	public static boolean put(String url,int port, String username, String password, String path, String filename, InputStream input,MultipartFile file){
		PropertiesTools pt = new PropertiesTools();
		if(StringUtils.isNullorBlank(url)) url = pt.getValueForKey("contect", "url");
		if(port == 0) port = Integer.valueOf(pt.getValueForKey("contect", "port"));
		if(StringUtils.isNullorBlank(username)) username = pt.getValueForKey("contect", "username");
		if(StringUtils.isNullorBlank(password)) password = pt.getValueForKey("contect", "password");
		
		boolean flag = false;
		try{
			FTPClient ftpClient = new FTPClient();
			int resultFlag;
			ftpClient.setConnectTimeout(100000);//设置超时时间
			ftpClient.setControlEncoding("utf-8");//设置字符集
			ftpClient.connect(url, port);
			ftpClient.login(username, password);//登陆
			resultFlag = ftpClient.getReplyCode();
			//判断连接状态状态
			if(!FTPReply.isPositiveCompletion(resultFlag)){
				ftpClient.disconnect();
				return flag;
			}
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.makeDirectory(path);
			ftpClient.changeWorkingDirectory(path);
			ftpClient.storeFile(filename, input);
			input.close();
			ftpClient.logout();
			flag = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
//	OutputStream out = ftpClient.appendFileStream(filename);
	
//    // 断点续传
//	byte[] bytes = new byte[1024];
//	int c;
//	while((c = input.read(bytes)) != -1){
//		out.write(bytes, 0, c);
//	}
//	out.flush();
}
