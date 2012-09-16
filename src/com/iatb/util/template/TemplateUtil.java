package com.iatb.util.template;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.iatb.bean.ColumnBean;
import com.iatb.bean.template.ActionClass;
import com.iatb.bean.template.BeanClass;
import com.iatb.bean.template.BeanUtil;
import com.iatb.bean.template.DaoClass;
import com.iatb.bean.template.DaoImplClass;
import com.iatb.bean.template.FormBeanClass;
import com.iatb.bean.template.ServiceClass;
import com.iatb.bean.template.ServiceImplClass;
import com.iatb.util.FileUtil;
import com.iatb.util.MyFile;
import com.iatb.util.PropUtil;
import com.iatb.util.db.ConnTools;

public class TemplateUtil {
	private static final String DBName="springmvc2012";
	private static final Connection  conn=ConnTools.makeConnection(DBName);
	
	/**
	 * 一键生成bean,form,dao,daoimpl,action
	 */
	public static  void init(){
		String tableName="gb_api";
		saveBean(tableName);
		saveFormBean(tableName);
		saveDao(tableName);
		saveDaoImpl(tableName);
		saveAction(tableName);
	}
	/**
	 * 自动生成某类型表的类:Bean,DaoImpl,Serevices,saveServicesImpl
	 */
	private static void initType(String name) {
		String t[]=ConnTools.listLikeTable(conn,DBName, name);
		for (String tn : t) {
			//自动生成类时，先判断是否有改动，是:手动更新类时间.
			saveBean(tn);
			saveDaoImpl(tn);
			saveServices(tn);
			saveServicesImpl(tn);
			//TODO 添加保存Job类
		}
	}
	private static void saveAction(String tableName) {
		saveAction(new ActionClass(tableName));
	}
	private static void saveDaoImpl(String tableName) {
		saveDaoImpl(new DaoImplClass(tableName));
	}
	private static void saveDao(String tableName) {
		saveDao(new DaoClass(tableName));
	}
	private static void saveFormBean(String tableName) {
		saveFormBean(new FormBeanClass(tableName));
	}
	private static void saveBean(String tableName) {
		saveBean(new BeanClass(tableName));
	}
	private static void saveServices(String tableName) {
		saveServices(new ServiceClass(tableName));
	}
	private static void saveServicesImpl(String tableName) {
		saveServicesImpl(new ServiceImplClass(tableName));
	}
	/**
	 * 本地生成DaoImpl类
	 * @param cb
	 */
	public static String saveDaoImpl(DaoImplClass cb){
		if(cb.getTableName()==null||cb.getTableName().trim().equals("")){
			return ("表名不允许为空！"); 
		}
		String s=cb.getContent().replaceAll("_$classPackage_", cb.getClassPackage())
		.replaceAll("_$date_", cb.getDate())
		.replaceAll("_$TableBeanPackageName_", cb.getTablebeanpackageName())
		.replaceAll("_$ConnToolspackageName_", cb.getconnToolsPackageName())
		.replaceAll("_$DaoIpmlName_",cb.getClassName())
		.replaceAll("_$dbName_", cb.getDbName())
		.replaceAll("_$Tablename_", cb.getTableName())
		.replaceAll("_$TableAsila_", cb.getTableAsila())
		.replaceAll("_$TableBeanName_",cb.getTableBeanName())
		.replaceAll("_$ColumnGet_", cb.getColumnGet())
		.replaceAll("_$ColumnLength_", ""+cb.getColumnLen())
		.replaceAll("_$ColumnSwitch_", cb.getColumnSwitch())
		.replaceAll("_$ColumnList_", cb.getColumnList())
		.replaceAll("_$updateColumn_", cb.getUpdateColumn())
		.replaceAll("_$getId_", cb.getId())
		.replaceAll("_$insertColumn_", cb.getInsertColumn());
//		System.out.println(s);
		String fn=PropUtil.getConfProp("project").getProperty("class_path")+"\\"+cb.getClassPackage().replace(".","\\")+"\\"+cb.getClassName()+".java";
		MyFile.writeToFile(s, new File(fn), false);
		return "";
	}
	public static String saveServices(ServiceClass cb) {
		if(cb.getTableName()==null||cb.getTableName().trim().equals("")){
			return ("表名不允许为空！"); 
		}
		String s=cb.getContent().replaceAll("_$classPackage_", cb.getClassPackage())
		.replaceAll("_$date_", cb.getDate())
		.replaceAll("_$TableBeanPackageName_", cb.getTablebeanpackageName())
		.replaceAll("_$Tablename_", cb.getTableName())
		.replaceAll("_$TableAsila_", cb.getTableAsila())
		.replaceAll("_$TableBeanName_",cb.getTableBeanName());
		//System.out.println(s);
		String fn=PropUtil.getConfProp("project").getProperty("class_path")+"\\"+cb.getClassPackage().replace(".","\\")+"\\"+cb.getClassName()+".java";
		MyFile.writeToFile(s, new File(fn), false);
		return "";
	}
	public static String saveServicesImpl(ServiceImplClass cb) {
		if(cb.getTableName()==null||cb.getTableName().trim().equals("")){
			return ("表名不允许为空！"); 
		}
		String fn=PropUtil.getClassPath()+"\\"+cb.getClassPackage().replace(".","\\")+"\\"+cb.getClassName()+".java";
		if(!isUpdateFile(fn)){
			String s=cb.getContent().replaceAll("_$classPackage_", cb.getClassPackage())
			.replaceAll("_$date_", cb.getDate())
			.replaceAll("_$TableBeanPackageName_", cb.getTablebeanpackageName())
			.replaceAll("_$Tablename_", cb.getTableName())
			.replaceAll("_$TableAsila_", cb.getTableAsila())
			.replaceAll("_$daopackageName_", cb.getDaopackageName())
			.replaceAll("_$servicepackageName_", cb.getServicepackageName())
			.replaceAll("_$TableBeanName_",cb.getTableBeanName());
			//System.out.println(s);
			String r=MyFile.writeToFile(s, new File(fn), false);
			if(r.indexOf("成功")>=0){
				updateFileTime(fn);
			}
		}else{
			System.out.println(fn+"\n文件已经被修改放弃生成！");
		}
		return "";
	}
	/**
	 * 判断文件是否被修改
	 * @param fn	文件绝对路径
	 * @return
	 */
	private static boolean isUpdateFile(String fn) {
		if(!FileUtil.isExists(fn))return false;
		Properties p=PropUtil.getConfProp("fntime");
		if(p.get(fn)==null)return false;
		if(p.get(fn).equals(FileUtil.getModifString(new File(fn))))return false;
		else return true;
	}
	/**
	 * 写文件的修改时间
	 * (对自身不能用)
	 * @param fn	文件绝对路径
	 */
	private static void updateFileTime(String fn) {
		PropUtil.addOrUpdate(PropUtil.getConfigpath()+"fntime.properties", fn, FileUtil.getModifString(new File(fn)), "");
	}
	/**
	 * 本地生成Bean类
	 * @param bc	BeanClass
	 */
	public static String saveBean(BeanClass cb){
		if(cb.getTableName()==null||cb.getTableName().trim().equals("")){
			return ("表名不允许为空！"); 
		}
		String s=cb.getContent().replaceAll("_$classPackage_", cb.getClassPackage())
		.replaceAll("_$date_", cb.getDate())
		.replaceAll("_$className_",cb.getClassName())
		.replaceAll("_$columns_",cb.getColumns())
		.replaceAll("_$gets_",cb.getGets());
		//System.out.println(s);
		String fn=PropUtil.getConfProp("project").getProperty("class_path")+"/"+cb.getClassPackage().replace(".","/")+"/"+cb.getClassName()+".java";
		MyFile.writeToFile(s, new File(fn), false);
		return MyFile.writeToFile(s, new File(fn), false);
	}
//	/**
//	 * 本地生成Bean类
//	 * @param bc
//	 */
//	public static String saveBean(com.iatb.shs.form.BeanClass cb){
//		if(cb.getTablename()==null||cb.getTablename().trim().equals("")){
//			return ("表名不允许为空！"); 
//		}
//		String s=cb.getContent().replaceAll("_$classPackage_", cb.getClassPackage())
//		.replaceAll("_$date_", cb.getDate())
//		.replaceAll("_$className_",cb.getClassName())
//		.replaceAll("_$columns_",cb.getColumns())
//		.replaceAll("_$gets_",cb.getGets());
//		System.out.println(s);
//		String fn=PropUtil.getConfProp("project").getProperty("class_path")+"/"+cb.getClassPackage().replace(".","/")+"/"+cb.getClassName()+".java";
//		MyFile.writeToFile(s, new File(fn), false);
//		return MyFile.writeToFile(s, new File(fn), false);
//	}
	
	private static String SetValue(String tablename) {
		List<ColumnBean> list=ConnTools.listColumn3(conn, tablename);
		StringBuffer sb=new StringBuffer();
		for (ColumnBean cb : list) {
			String cn=cb.getColumnName();
			if(cn.equals("id")||cn.equals("createTime"))continue;
			String CN=BeanUtil.getClassName(cn);
			String tn=cb.getColumnTypeName();
			if(tn.equals("INT")||tn.equals("BIT")){
				sb.append("\n\tif(\""+cn+"\".equals(name)){");
				sb.append("\n\t\tgb.set"+CN+"(Integer.parseInt(v.toString()));return gb;");
				sb.append("\n\t}");
			}else if(tn.equals("CHAR")||tn.equals("VARCHAR")||tn.equals("TEXT")){
				sb.append("\n\tif(\""+cn+"\".equals(name)){");
				sb.append("\n\t\tgb.set"+CN+"(v.toString());return gb;");
				sb.append("\n\t}");
			}
		}
		return sb.toString();
	}
	/**
	 * 通过bean保存FormBean
	 * @param beanName
	 * @param fullbeanPackagename
	 */
	public static String saveFormBean(FormBeanClass cb){
		if(cb.getTableName()==null||cb.getTableName().trim().equals("")){
			return ("表名不允许为空！"); 
		}
		String s=cb.getContent().replaceAll("_$classPackage_", cb.getClassPackage())
		.replaceAll("_$date_", cb.getDate())
		.replaceAll("_$className_",cb.getClassName())
		.replaceAll("_$columns_",cb.getColumns())
		.replaceAll("_$gets_",cb.getGets());
		//System.out.println(s);
		String fn=PropUtil.getConfProp("project").getProperty("class_path")+"\\"+cb.getClassPackage().replace(".","\\")+"\\"+cb.getClassName()+".java";
		MyFile.writeToFile(s, new File(fn), false);
		return MyFile.writeToFile(s, new File(fn), false);
	}
	public static String saveDao(DaoClass cb){
		if(cb.getTableName()==null||cb.getTableName().trim().equals("")){
			return ("表名不允许为空！"); 
		}
		String s=cb.getContent().replaceAll("_$ClasspackageName_", cb.getClasspackageName())
		.replaceAll("_$date_", cb.getDate())
		.replaceAll("_$TableBeanPackageName_", cb.getTablebeanpackageName())
		.replaceAll("_$ClassName_",cb.getClassName())
		.replaceAll("_$dbName_", cb.getDbName())
		.replaceAll("_$Tablename_", cb.getTableName())
		.replaceAll("_$TableAsila_", cb.getTableAsila())
		.replaceAll("_$TableBeanName_",cb.getTableBeanName());
		//System.out.println(s);
		String fn=PropUtil.getConfProp("project").getProperty("class_path")+"\\"+cb.getClasspackageName().replace(".","\\")+"\\"+cb.getClassName()+".java";
		MyFile.writeToFile(s, new File(fn), false);
		return "";
	}
	public static String saveAction(ActionClass cb){
		if(cb.getTableName()==null||cb.getTableName().trim().equals("")){
			return ("表名不允许为空！"); 
		}
		String s=cb.getContent().replaceAll("_$ClasspackageName_", cb.getClassPackage())
		.replaceAll("_$date_", cb.getDate())
		.replaceAll("_$TableBeanPackageName_", cb.getTablebeanpackageName())
		.replaceAll("_$className_",cb.getClassName())
		.replaceAll("_$daopackageName_",cb.getDaopackageName())
		.replaceAll("_$tablebeanpackageName_",cb.getTablebeanpackageName())
		.replaceAll("_$Tablename_", cb.getTableName())
		.replaceAll("_$formBeanName_", cb.getFormBeanName())
		.replaceAll("_$daoName_", cb.getDaoName())
		.replaceAll("_$TableBeanName_",cb.getTableBeanName());
		//System.out.println(s);
		String fn=PropUtil.getConfProp("project").getProperty("class_path")+"\\"+cb.getClassPackage().replace(".","\\")+"\\"+cb.getClassName()+".java";
		MyFile.writeToFile(s, new File(fn), false);
		return "";
	}
	//保存所有源文件对应的修改时间到fntime.properties
	public static void initFntimeProperties(){
		List<String> list=new ArrayList<String>();
		list.add("beanPackage");
		list.add("formBeanPackage");
		list.add("daoPackage");
		list.add("daoImplPackage");
		list.add("tableBeanPackageName");
		Properties p=PropUtil.getConfProp("template");
		for (String s : list) {
			String fndirectory=PropUtil.getClassPath()+"\\"+p.get(s).toString().replace(".", "\\");
			List<File> flist=FileUtil.getDirectorys(null, new File(fndirectory));
			for (File f: flist) {
				if(!f.isDirectory()&&f.getName().endsWith(".java")&& f.getAbsolutePath().indexOf("\\.svn")<0)
					updateFileTime(f.getAbsolutePath());
			}
		}
		
	}
	public static String updateXml(){
		
		return null;
	}
	public static String saveJs(){
		
		return null;
	}
	
	public static void main(String[] args) {
//		initType("open163");
//		saveBean("gb_api");
		
//		initFntimeProperties();
//		init();
		/*MainClass cb=new MainClass("spider2011","gb_site");
		cb.setClasspackageName("com.iatb.cmd");
		cb.setTablebeanpackageName("com.iatb.bean.GbSite");
		cb.setTabledaopackageName("com.iatb.dbutils.dao.impl.GbSiteDaoImpl");
		cb.setConnToolspackageName("com.util.db.ConnTools");
		cb.setClassName("GbSiteMain");
		cb.setDbName("spider2011");
		cb.setTablename("gb_site");
		cb.setTableBeanName("GbSite");
		cb.setColumnBeanpackageName("com.javaBean.ColumnBean");
		saveMain(cb);*/
		
		/*
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in) );
			String line="";
			line=br.readLine();
			System.out.println(line.trim().equals(""));
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
//		updateFileTime("D:\\java\\tool\\Genuitec\\workspace\\SpringMVC\\src\\config\\fntime.properties1");
		/*DaoClass cb=new DaoClass("gb_user");
		saveDao(cb);*/
		/*ActionClass cb=new ActionClass("gb_user");
		saveAction(cb);*/
	/*	FormBeanClass cb=new FormBeanClass("gb_user");
		saveFormBean(cb);*/
		/*BeanClass cb=new BeanClass();
		cb.setClassName("BeanClass");
		cb.setClasspackageName("com.iatb.bean.template");
		saveFormBean(cb);*/
	}
}
