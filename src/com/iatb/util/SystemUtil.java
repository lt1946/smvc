package com.iatb.util;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class SystemUtil {

	/**
	 * ��ӡϵͳ������Ϣ
	 */
	public static void printEnv(){
		Map<String, String> map=System.getenv();
		for(Map.Entry<String, String> e:map.entrySet()){
			if(!e.getKey().equals("=::"))
			System.out.println(e.getKey()+":"+e.getValue());
		}
	}
	/**
	 * ��ӡ��Ŀ������Ϣ
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
