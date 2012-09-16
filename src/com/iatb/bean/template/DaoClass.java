package com.iatb.bean.template;

import java.util.Properties;

import com.iatb.util.DateUtil;
import com.iatb.util.IOUtil;
import com.iatb.util.PropUtil;

public class DaoClass {
	private String tableName;	//表名
	private String column; 		//字段组合1
	private Properties p;

	public String getContent() {
		return IOUtil.readFileToString(PropUtil.getConfProp("template").getProperty("daoFile"),p.getProperty("encode"));
	}
	/**
	 * 判断字段值是否为整型
	 * @return
	 */
	public boolean checkInteger(){
		
		return false;
	}

	public String getClassName() {
		return getTableBeanName()+"Dao";
	}

	public DaoClass( String tablename) {
		p=PropUtil.getConfProp("template");
		this.tableName=tablename;
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

	public String getTableName() {
		return tableName;
	}

	public String getColumn() {
		return column;
	}

	public String getClasspackageName() {
		return p.getProperty("daoPackage");
	}

	public String getTablebeanpackageName() {
		return p.getProperty("tableBeanPackageName")+"."+getTableBeanName();
	}

	public String getTableBeanName() {
		return BeanUtil.getClassName(getTableName());
	}
	public String getDate() {
		return DateUtil.getPlusTimeDate();
	}
	public String getDbName() {
		return p.getProperty("dbName");
	}


}
