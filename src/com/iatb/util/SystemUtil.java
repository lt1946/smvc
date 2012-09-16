package com.iatb.util;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SystemUtil {

	/**
	 * 打印系统环境信息
	 */
	public static void printEnv(){
		Map<String, String> map=System.getenv();
		for(Map.Entry<String, String> e:map.entrySet()){
			if(!e.getKey().equals("=::"))
			System.out.println(e.getKey()+":"+e.getValue());
		}
	}
	/**
	 * 打印项目环境信息
	 */
	public static void printProjectEnv(){
		Properties sp=System.getProperties();
		Set<Object>  keys=	sp.keySet();
		for (Object o : keys) {
			System.out.println(o.toString()+":"+sp.getProperty(o.toString()));
		}
	}
	public static boolean isWindows(){
		return System.getProperty( "os.name").indexOf("Windows")>=0;
	}
	public static void main(String[] args) {
		printEnv();
		printProjectEnv();
	}
}
