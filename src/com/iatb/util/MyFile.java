/**
 *
 */
package com.iatb.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * @author Administrator
 *
 */
public class MyFile {
	//读取一个文件的所有内容
	public static String getText(File f)
	{
		StringBuffer sb=new StringBuffer("");
		try
		{
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String s=br.readLine();
			while(s!=null)
			{
				sb.append(s);
				s=br.readLine();
			}
			br.close();
		}
		catch(Exception e)
		{
			sb.append("");
		}

		return sb.toString();
	}
	//读取一个文件的所有内容--重载
	public static String getText(String s)
	{
		String t = "";
		try
		{
			File f = new File(s);
//			System.out.println(f.getAbsolutePath());
			t = getText(f);
		}
		catch(Exception e)
		{
			t = "";
		}

		return t;
	}
	//读取一个文件的所有内容
	public static String getText(File f,String encode)
	{
		StringBuffer sb=new StringBuffer("");
		try
		{
			BufferedReader br  = new BufferedReader(new InputStreamReader(new FileInputStream(f),encode));
			String s=br.readLine();
			while(s!=null)
			{
				sb.append(s);
				s=br.readLine();
			}
			br.close();
		}
		catch(Exception e)
		{
			sb.append("");
		}

		return sb.toString();
	}
	//读取一个文件的所有内容--重载
	public static String getText(String s,String encode)
	{
		String t = "";
		try
		{
			File f = new File(s);
			t = getText(f,encode);
		}
		catch(Exception e)
		{
			t = "";
		}

		return t;
	}
	/**
	 * 创建文件或目录
	 * @param file	要创建的文件或目录
	 */
	public static void createFile(File file,boolean isDirectory) {
			if(file==null) {
				System.out.println("文件为空值！");
			}else  {
				try {
					if(!file.exists()) {
						if(isDirectory) {
//							if(!file.getParentFile().exists()){
//								createFile(file.getParentFile(), isDirectory);
//							}else {
								file.mkdirs();
//							}
						}else {
							if(!file.getParentFile().exists()) {
								createFile(file.getParentFile(), true);
							}else {
								file.createNewFile();
							}
						}
						System.out.println("文件:"+file.toString()+"创建完毕！");
					}else {
						System.out.println("文件:"+file.toString()+"已经存在！");
					}

				} catch (IOException e) {
					System.out.println("文件:"+file.toString()+"创建异常！");
					e.printStackTrace();
				}
			}
	}
	/**
	 * 创建并测试文件。
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static void createFile(String fileName[]){
		try {
			for (String fn : fileName) {
				File dest=new File(fn);
				if (!dest.exists()) {
					dest.createNewFile();
				}
			}
		} catch (IOException e) {
		}
	}
	/**
	 * 写入文件
	 * @param sb 写入的字符串
	 * @param file 被写入的文件
	 * @param b	是否追加到文件
	 */
	public static String writeToFile(String s,File file,boolean b) {
		try {
			if(file==null) {
				return ("文件为空值！");
			}else  {
				if(!file.exists()) {
					createFile(file,false);
					System.out.println(file+"文件不存在！");
				}
				FileWriter fw=new FileWriter(file,b);
				fw.write(s);
				fw.close();
				return ("文件："+file.toString()+"写入成功！");

			}
		} catch (IOException e) {
			e.printStackTrace();
			return ("文件写入异常！");
		}
	}
	/**
	 * 写入文件
	 * @param sb 写入的字符串
	 * @param file 被写入的文件
	 * @param b	是否追加到文件
	 */
	public static void writeToFile(String s,String encode,File file,boolean b) {
		try {
			if(file==null) {
				System.out.println("文件为空值！");
			}else  {
				if(!file.exists()) {
					System.out.println(file+"文件不存在！");
					createFile(file,false);
				}
				BufferedWriter br  = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),encode));
				br.write(s);
				br.close();
				System.out.println("文件："+file.toString()+"写入成功！");

			}
		} catch (IOException e) {
			System.out.println("文件写入异常！");
			e.printStackTrace();
		}
	}
	/**
	 * 返回相似字符串的文件列表,除了目录
	 * @param path
	 * @param endLike
	 * @return
	 */
	public static List<File> getFiles(String path,String like) {
		File list[]=new File(path).listFiles();
		List<File> fl=new ArrayList<File>();
		for (File file : list) {
			if(file.isDirectory()||!MyString.getLike(file.getName(), like))continue;
			else fl.add(file);
		}
		return fl;
	}
	/**
	 * 批量重命名文件
	 * @param f
	 * @param like
	 * @param replace
	 */
	public static void renameFiles(File f,String like,String replace) {
		if(MyString.getLike(f.getName(),like)) {
			File f2=new File(f.getParent()+ f.separatorChar+f.getName().replaceAll(like	,replace));
			f.renameTo(f2);
		}
	}
	/**
	 * 清除文件中整行
	 * @param f
	 * @param Line
	 * @param encode
	 * @since 2011.02.24
	 */
	public static void cleanStartContent(File f,String startLine,String encode){
		try {
			List<String> lists=IOUtil.readFile(f, encode);
			StringBuffer d=new StringBuffer();
			for (int i = 0; i < lists.size(); i++) {
				if(lists.get(i).trim().startsWith(startLine)){
					lists.remove(i);
				}else{
					d.append(lists.get(i)+"\n");
				}
			}
			FileUtils.writeStringToFile(f, d.toString(), encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 文件字符转码
	 * @param f
	 * @param encode
	 * @since 2011.02.24
	 */
	public static void decodeContent(File f,String encode){
		try {
			String s=FileUtils.readFileToString(f);
			s=MyString.decode(s);
			FileUtils.writeStringToFile(f, s, encode);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 文件清除字符
	 * @param f
	 * @param c
	 * @param encode
	 * @since 2011.02.24
	 */
	public static void clearContent(File f,String []c,String encode){
		try{
			String s=FileUtils.readFileToString(f);
			s=MyString.clearString(s, c);
			FileUtils.writeStringToFile(f, s, encode);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public static List<File> getFiles(File path,List<File> l){
		List<File> list=new ArrayList<File>();
		if(l!=null)list=l;
 		if(path.isDirectory()){
 			File f[]=path.listFiles();
 			for (int i = 0; i < f.length; i++) {
				if(f[i].isDirectory()){
					  getFiles(f[i],list);
				}else{
					list.add(f[i]);
				}
			}
 		}
 		return list;
	}
	/**
	 * 获取所有文件名
	 * @param path
	 * @return
	 */
	public static List<String> getFilesName(File path){
		List<File> listFile=MyFile.getFiles(path, null);
		List<String> listNames=new ArrayList<String>();
		for (File f : listFile) {
			listNames.add(f.getName().substring(0,f.getName().lastIndexOf(".")));
		}
		return listNames;
	}
}
