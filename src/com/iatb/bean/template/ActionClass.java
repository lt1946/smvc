package com.iatb.bean.template;

import java.sql.Connection;
import java.util.Properties;

import com.iatb.util.DateUtil;
import com.iatb.util.IOUtil;
import com.iatb.util.PropUtil;
import com.iatb.util.db.ConnTools;

@SuppressWarnings("unused")
public class ActionClass {
	private Connection  conn;
	private String classPackage;		//完整包名
	private String tablebeanpackageName;	//完整表bean包名
	private String daopackageName;	//完整dao包名
	private String className;	//Action类名
	private String daoName;		//dao名
	private String date;		//创建时间
	private String tableName; //table类名
	private String formBeanName; //form类名
	private Properties p;
	
	public String getContent() {
		return IOUtil.readFileToString(PropUtil.getConfProp("template").getProperty("actionFile"),p.getProperty("encode"));
	}

	public ActionClass(String tableName) {
		p=PropUtil.getConfProp("template");
		conn=ConnTools.makeConnection(p.getProperty("dbName"));
		this.tableName=tableName;
	}

	public String getClassName() {
		return getTableBeanName()+p.getProperty("action");
	}

	public String getTablebeanpackageName() {
		return p.getProperty("tableBeanPackageName")+"."+getFormBeanName();
	}

	public String getTableBeanName() {
		return BeanUtil.getClassName(tableName);
	}

	public String getTableName() {
		return tableName;
	}

	public String getClassPackage() {
		return p.getProperty("actionPackage");
	}

	public String getDaopackageName() {
		return p.getProperty("daoPackage")+"."+getDaoName();
	}

	public String getDaoName() {
		return getTableBeanName()+p.getProperty("dao");
	}

	public String getDate() {
		return DateUtil.getPlusTimeDate();
	}

	public String getFormBeanName() {
		return getTableBeanName();
	}
}
