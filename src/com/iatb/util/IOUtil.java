package com.iatb.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

public class IOUtil {

	public static void main(String[] args) {
		//--------------
		/*String url="http://www.oschina.net";
		String encode=MyHtmlCleaner.getEncode(url);
		String s=readUrl(url,encode);
		System.out.println(s);*/
		//---------------
		/*List<String> list=readFile("E:\\java\\file\\51cto_java基础\\深入Java核心 探秘Java垃圾回收机制.txt", "gbk");
		for (String string : list) {
			System.out.println(string);
		}*/
		//-------------
		System.out.println(getSpace("E:\\java\\file\\51cto_java基础"));	// 25161400320 bytes 
		System.out.println(getSpace("E:\\"));	// 25161400320 bytes 
		
	}
	
	/**
	 * 读取url
	 * @param url	链接
	 * @return	String
	 * @since 2011.02.24
	 */
	public static String readUrl(String url ,String encode){
		try {
			InputStream in = new URL(url).openStream();
			try {
			   return IOUtils.toString( in,encode);
			} finally {
			   IOUtils.closeQuietly(in);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 读取文件
	 * @param filename	文件路径
	 * @param encode	编码
	 * @return	List<String> 
	 * @since 2011.02.24
	 */
	@SuppressWarnings("unchecked")
	public static List<String> readFile(File f, String encode) {
		try {
			return FileUtils.readLines(f, encode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 读取文件内容
	 * @param filename
	 * @param encode
	 * @return	String
	 */
	public static String readFileToString(String filename,String encode){
		try {
			return FileUtils.readFileToString(new File(filename), encode);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 读取文件
	 * @param filename	文件路径
	 * @param encode	编码
	 * @return	List<String> 
	 * @since 2011.02.24
	 */
	@SuppressWarnings("unchecked")
	public static List<String> readFile(String filename,String encode){
		return readFile(new File(filename), encode);
	}
	/**
	 * 返回不含..的绝对路径
	 * @param url
	 * @return String
	 * @since 2011.02.24
	 */
	public static String normalized(String url){
		 return FilenameUtils.normalize(url);
	}
	/**
	 * 返回文件空间大小："C:/"
	 * @param partion
	 * @return Long
	 * @since 2011.02.24
	 */
	@SuppressWarnings("deprecation")
	public static Long getSpace(String partion){
		try {
			return FileSystemUtils.freeSpace(partion);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
