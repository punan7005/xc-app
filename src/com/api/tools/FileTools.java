package com.api.tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class FileTools {
	public static void addTxtEnd(String fileName, String value){
		FileWriter fw = null;
		try {
		//如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File(fileName);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(value);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> readText(String fileName){
		List<String> list = new ArrayList<String>();
		int i = 0;
		try {
            String encoding = "utf-8";
            File file = new File(fileName);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    String[] tempLine = lineTxt.split(",");
                    list.add(tempLine[0]);
                    i++;
                }
                read.close();
		    }else{
		        System.out.println("找不到指定的文件");
		    }
		    } catch (Exception e) {
		        System.out.println("读取文件内容出错");
		        e.printStackTrace();
		    }
		return list;
	}
	
	public List<String> readTextForLine(String fileName){
		List<String> list = new ArrayList<String>();
		int i = 0;
		try {
            String encoding = "utf-8";
            File file = new File(fileName);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    list.add(lineTxt);
                    i++;
                }
                read.close();
		    }else{
		        System.out.println("找不到指定的文件");
		    }
		    } catch (Exception e) {
		        System.out.println("读取文件内容出错");
		        e.printStackTrace();
		    }
		return list;
	}
	
	
	public static void main(String[] args){
		FileTools fileTools = new FileTools();
		fileTools.readText("c:\\tools\\schoolinfos\\key\\state.txt");
	}
}
