package com.iatb.bean.template;

public class BeanUtil {

	/**
	 * ªÒ»°BeanClassName
	 * @param tablename
	 * @return
	 */
	public static String getClassName(String tablename){
		String s[]=tablename.split("_");
		String ss=s[0].substring(0,1).toUpperCase()+s[0].substring(1);
		if(s.length!=1){
			for (int i = 1; i < s.length; i++) {
				 ss+=s[i].substring(0,1).toUpperCase()+s[i].substring(1);
			}
		}
		return ss;
	}
	public static String getShorName(String beanname){
		if(beanname.length()<1)return null;
		if(beanname.length()<2)return beanname;
		return beanname.substring(0,1).toLowerCase()+beanname.substring(1,beanname.length());
	}
	
}
