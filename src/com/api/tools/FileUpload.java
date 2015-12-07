package com.api.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	
	public static String upLoadFile(String savePath,String fileName, byte[] bytes, HttpServletRequest request){
		//服务器Url
		String ctx = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		//取文件后缀
		String extFileName = fileName.substring(fileName.lastIndexOf("."));
		//通过getRndFileName获取时间戳
		String dateFileName = getRndFileName();
		String uuid = UuidFactory.getUuid();
		//重定义文件存储路径
		String saveFilePath = savePath + File.separator + dateFileName + File.separator;
		//加载
		File file = new File(saveFilePath);
		//判断路径是否存在
		if(!file.exists()){
			//创建
			file.mkdirs();
		}
		try {
			//加载文件
			FileOutputStream fileOutputStream = new FileOutputStream(file + File.separator + uuid + extFileName);
			//输出
			fileOutputStream.write(bytes);
			//关闭
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctx + "uploadFile/" + dateFileName + "/" + uuid + extFileName;
	}
	
	//获取时间戳方法
	public static String getRndFileName(){
		//Calendar nowTime=Calendar.getInstance();
		String reqStr = "";
		Date date=new Date();
		String formatStr="yyyyMMdd";
		try {
			Random random = new Random();
			java.text.DateFormat df = new java.text.SimpleDateFormat(formatStr);
			reqStr = df.format(date);
		} catch (Exception ex) {
		}
		return reqStr;
	}
	
	//获得保存图片路径
	public static String getImgPath(HttpServletRequest request,String logoPath,String isDefault, MultipartFile file1) throws IOException{
		
		//服务器Url
		String ctx = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		String savePath = request.getSession().getServletContext().getRealPath("/uploadFile");
		if(file1.getOriginalFilename()==null || file1.getOriginalFilename().isEmpty()){
			if(logoPath==null || logoPath.isEmpty()){
				return ctx+"img/1.jpg";
			}
			if(("1").equals(isDefault)&&logoPath!=null&&!logoPath.isEmpty()){
				int index = logoPath.lastIndexOf("/");
				String dt = logoPath.substring(index-8, index); 
				String imgName = logoPath.substring(logoPath.lastIndexOf("/")+1);
				String imgPath = savePath+File.separator+dt+File.separator+imgName;
				new File(imgPath).delete();
				return ctx+"img/1.jpg";
			}
			return null;
		}
		
		if(logoPath!=null&&!logoPath.isEmpty()){
			int index = logoPath.lastIndexOf("/");
			String dt = logoPath.substring(index-8, index); 
			String imgName = logoPath.substring(logoPath.lastIndexOf("/")+1);
			String imgPath = savePath+File.separator+dt+File.separator+imgName;
			new File(imgPath).delete();
		}
		
		return upLoadFile(savePath,file1.getOriginalFilename(),file1.getBytes(),request);
	}
	
	//学校logo上传
	public static String upLoadSchoolLogo(String logoName,String schoolLogo,String isDefault, MultipartFile file1, HttpServletRequest request){
		String path = null;
		//服务器Url
		String ctx = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		String savePath = request.getSession().getServletContext().getRealPath("/uploadFile/schoolLogo");
		if(file1.getOriginalFilename()==null || file1.getOriginalFilename().isEmpty()){
			if(schoolLogo==null || schoolLogo.isEmpty()){
				return ctx+"img/1.jpg";
			}
			if(("1").equals(isDefault)&&schoolLogo!=null&&!schoolLogo.isEmpty()){
				int index = schoolLogo.lastIndexOf("/");
				String dt = schoolLogo.substring(index-8, index); 
				String imgName = schoolLogo.substring(schoolLogo.lastIndexOf("/")+1);
				String imgPath = savePath+File.separator+dt+File.separator+imgName;
				new File(imgPath).delete();
				return ctx+"img/1.jpg";
			}
			return null;
		}
		
		if(schoolLogo!=null&&!schoolLogo.isEmpty()){
			int index = schoolLogo.lastIndexOf("/");
			String dt = schoolLogo.substring(index-8, index); 
			String imgName = schoolLogo.substring(schoolLogo.lastIndexOf("."));
			String imgPath = savePath+File.separator+dt+File.separator+logoName+imgName;
			new File(imgPath).delete();
		}
		
		//取文件后缀
		String fileName = file1.getOriginalFilename();
		String extFileName = fileName.substring(fileName.lastIndexOf("."));
		//通过getRndFileName获取时间戳
		String dateFileName = getRndFileName();
		//重定义文件存储路径
		String saveFilePath = savePath + File.separator + dateFileName + File.separator;
		//加载
		File file = new File(saveFilePath);
		//判断路径是否存在
		if(!file.exists()){
			//创建
			file.mkdirs();
		}
		try {
			//加载文件
			FileOutputStream fileOutputStream = new FileOutputStream(file + File.separator + logoName + extFileName);
			//输出
			fileOutputStream.write(file1.getBytes());
			//关闭
			fileOutputStream.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ctx + "uploadFile/schoolLogo/"+ dateFileName +"/"+ logoName + extFileName;
	}
//	public void getVideoFileType() {
//        FILE_TYPE_MAP.put("ram", "2E7261FD"); // Real Audio (ram)
//        FILE_TYPE_MAP.put("rm", "2E524D46"); // Real Media (rm)
//        FILE_TYPE_MAP.put("mov", "00000014667479707174"); // Quicktime (mov)
//        // FILE_TYPE_MAP.put("rmvb", "2e524d46000000120001"); // rmvb
//        FILE_TYPE_MAP.put("avi", "41564920");
//        FILE_TYPE_MAP.put("avi", "52494646b440c02b4156");
//        FILE_TYPE_MAP.put("flv", "464C5601050000000900");
//        FILE_TYPE_MAP.put("mp4", "00000020667479706d70");
//        FILE_TYPE_MAP.put("wmv", "3026b2758e66CF11a6d9");
//        // FILE_TYPE_MAP.put("3gp", "00000014667479703367");
//        FILE_TYPE_MAP.put("mkv", "1a45dfa3010000000000");
//    }
//
//    public void getPicFileType() {
//        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
//        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
//        FILE_TYPE_MAP.put("gif", "47494638"); // GIF (gif)
//        FILE_TYPE_MAP.put("bmp", "424D"); // Windows Bitmap (bmp)
//        FILE_TYPE_MAP.put("png", "89504E470D0a1a0a0000"); // PNG (png)
//        FILE_TYPE_MAP.put("bmp", "424d228c010000000000"); // 16色位图(bmp)
//        FILE_TYPE_MAP.put("bmp", "424d8240090000000000"); // 24位位图(bmp)
//        FILE_TYPE_MAP.put("bmp", "424d8e1b030000000000"); // 256色位图(bmp
//    }
//
//    public void getAudioFileType() {
//        FILE_TYPE_MAP.put("wav", "57415645"); // Wave (wav)
//        FILE_TYPE_MAP.put("mid", "4D546864"); // MIDI (mid)
//        FILE_TYPE_MAP.put("mp3", "49443303000000002176");
//        FILE_TYPE_MAP.put("wav", "52494646e27807005741");
//        FILE_TYPE_MAP.put("aac", "fff1508003fffcda004c");
//        FILE_TYPE_MAP.put("wv", "7776706ba22100000704");
//        FILE_TYPE_MAP.put("flac", "664c6143800000221200");
//    }
//    
//    FILE_TYPE_MAP.put("ffd8ffe000104a464946", "jpg"); //JPEG (jpg)    
//    FILE_TYPE_MAP.put("89504e470d0a1a0a0000", "png"); //PNG (png)    
//    FILE_TYPE_MAP.put("47494638396126026f01", "gif"); //GIF (gif)    
//    FILE_TYPE_MAP.put("49492a00227105008037", "tif"); //TIFF (tif)    
//    FILE_TYPE_MAP.put("424d228c010000000000", "bmp"); //16色位图(bmp)    
//    FILE_TYPE_MAP.put("424d8240090000000000", "bmp"); //24位位图(bmp)    
//    FILE_TYPE_MAP.put("424d8e1b030000000000", "bmp"); //256色位图(bmp)    
//    FILE_TYPE_MAP.put("41433130313500000000", "dwg"); //CAD (dwg)    
//    FILE_TYPE_MAP.put("3c21444f435459504520", "html"); //HTML (html)
//    FILE_TYPE_MAP.put("3c21646f637479706520", "htm"); //HTM (htm)
//    FILE_TYPE_MAP.put("48544d4c207b0d0a0942", "css"); //css
//    FILE_TYPE_MAP.put("696b2e71623d696b2e71", "js"); //js
//    FILE_TYPE_MAP.put("7b5c727466315c616e73", "rtf"); //Rich Text Format (rtf)    
//    FILE_TYPE_MAP.put("38425053000100000000", "psd"); //Photoshop (psd)    
//    FILE_TYPE_MAP.put("46726f6d3a203d3f6762", "eml"); //Email [Outlook Express 6] (eml)      
//    FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "doc"); //MS Excel 注意：word、msi 和 excel的文件头一样    
//    FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "vsd"); //Visio 绘图    
//    FILE_TYPE_MAP.put("5374616E64617264204A", "mdb"); //MS Access (mdb)      
//    FILE_TYPE_MAP.put("252150532D41646F6265", "ps");    
//    FILE_TYPE_MAP.put("255044462d312e350d0a", "pdf"); //AdobeAcrobat (pdf)  
//    FILE_TYPE_MAP.put("2e524d46000000120001", "rmvb"); //rmvb/rm相同  
//    FILE_TYPE_MAP.put("464c5601050000000900", "flv"); //flv与f4v相同  
//    FILE_TYPE_MAP.put("00000020667479706d70", "mp4");
//    FILE_TYPE_MAP.put("49443303000000002176", "mp3");
//    FILE_TYPE_MAP.put("000001ba210001000180", "mpg"); //    
//    FILE_TYPE_MAP.put("3026b2758e66cf11a6d9", "wmv"); //wmv与asf相同    
//    FILE_TYPE_MAP.put("52494646e27807005741", "wav"); //Wave (wav)  
//    FILE_TYPE_MAP.put("52494646d07d60074156", "avi");  
//    FILE_TYPE_MAP.put("4d546864000000060001", "mid"); //MIDI (mid)  
//    FILE_TYPE_MAP.put("504b0304140000000800", "zip");    
//    FILE_TYPE_MAP.put("526172211a0700cf9073", "rar");  
//    FILE_TYPE_MAP.put("235468697320636f6e66", "ini");  
//    FILE_TYPE_MAP.put("504b03040a0000000000", "jar");
//    FILE_TYPE_MAP.put("4d5a9000030000000400", "exe");//可执行文件
//    FILE_TYPE_MAP.put("3c25402070616765206c", "jsp");//jsp文件
//    FILE_TYPE_MAP.put("4d616e69666573742d56", "mf");//MF文件
//    FILE_TYPE_MAP.put("3c3f786d6c2076657273", "xml");//xml文件
//    FILE_TYPE_MAP.put("494e5345525420494e54", "sql");//xml文件
//    FILE_TYPE_MAP.put("7061636b616765207765", "java");//java文件
//    FILE_TYPE_MAP.put("406563686f206f66660d", "bat");//bat文件
//    FILE_TYPE_MAP.put("1f8b0800000000000000", "gz");//gz文件
//    FILE_TYPE_MAP.put("6c6f67346a2e726f6f74", "properties");//bat文件
//    FILE_TYPE_MAP.put("cafebabe0000002e0041", "class");//bat文件
//    FILE_TYPE_MAP.put("49545346030000006000", "chm");//bat文件
//    FILE_TYPE_MAP.put("04000000010000001300", "mxp");//bat文件
//    FILE_TYPE_MAP.put("504b0304140006000800", "docx");//docx文件
//    FILE_TYPE_MAP.put("d0cf11e0a1b11ae10000", "wps");//WPS文字wps、表格et、演示dps都是一样的
//    FILE_TYPE_MAP.put("6431303a637265617465", "torrent");
//    
//      
//    FILE_TYPE_MAP.put("6D6F6F76", "mov"); //Quicktime (mov)  
//    FILE_TYPE_MAP.put("FF575043", "wpd"); //WordPerfect (wpd)  
//    FILE_TYPE_MAP.put("CFAD12FEC5FD746F", "dbx"); //Outlook Express (dbx)    
//    FILE_TYPE_MAP.put("2142444E", "pst"); //Outlook (pst)      
//    FILE_TYPE_MAP.put("AC9EBD8F", "qdf"); //Quicken (qdf)    
//    FILE_TYPE_MAP.put("E3828596", "pwl"); //Windows Password (pwl)        
//    FILE_TYPE_MAP.put("2E7261FD", "ram"); //Real Audio (ram)    
}
