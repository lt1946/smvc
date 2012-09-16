package com.iatb.bean.template;

import java.util.Properties;

import com.iatb.util.DateUtil;
import com.iatb.util.IOUtil;
import com.iatb.util.PropUtil;

public class ServiceClass {
	private String tableName;	//±íÃû
	private Properties p;
	public String getContent() {
		return IOUtil.readFileToString(PropUtil.getClassPath()+"/"+ PropUtil.getConfProp("template").getProperty("serviceFile"),p.getProperty("encode"));
	}
	
	public String getClassName() {
		return getTableBeanName()+p.getProperty("service");
	}
	public ServiceClass( String tablename) {
		p=PropUtil.getConfProp("template");
		this.tableName=tablename;
	}
	public String getClassPackage() {
		return p.getProperty("servicePackage");
	}
	public String getTablebeanpackageName() {
		return p.getProperty("tableBeanPackageName")+"."+getTableBeanName();
	}
	public String getTableBeanName() {
		return BeanUtil.getClassName(getTableName());
	}
	public String getTableName() {
		return tableName;
	}
	public String getDate() {
		return DateUtil.getPlusTimeDate();
	}
	public String getTableAsila() {
		String s[]=getTableName().split("_");
		String ss=s[0].substring(0,1).toLowerCase()+s[0].substring(1);
		if(s.length!=1){
			for (int i = 1; i < s.length; i++) {
				 ss+=s[i].substring(0,1).toUpperCase()+s[i].substring(1);
			}
		}
		return ss;
	}
}
