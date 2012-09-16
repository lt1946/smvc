package com.iatb.bean.template;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import com.iatb.bean.ColumnBean;
import com.iatb.util.DateUtil;
import com.iatb.util.IOUtil;
import com.iatb.util.PropUtil;
import com.iatb.util.db.ConnTools;

public class BeanClass {
	private static	Connection  conn;
	private String dbName;	
	private String tableName;
	private String className;
	private	String c[];
	private Properties p;
	private List<ColumnBean> list;
	
	private String classpackageName;		//完整包名
	private String tablename;	//表名
	
	public String getContent() {
		return IOUtil.readFileToString(PropUtil.getClassPath()+"/" +PropUtil.getConfProp("template").getProperty("beanFile"),p.getProperty("encode"));
	}
	
	public BeanClass(String tableName) {
		p=PropUtil.getConfProp("template");
		conn=ConnTools.makeConnection(p.getProperty("dbName"));
		this.tableName=tableName;
		c=ConnTools.listColumn(conn, tableName);
		list=ConnTools.listColumn2(conn,getTableName());
	}

	public BeanClass() {
		super();
	}
	/**
	 * 判断字段值是否为整型
	 * @return
	 */
	public boolean checkInteger(){
		
		return false;
	}

	public String getClassName() {
		return BeanUtil.getClassName(getTableName());
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}


	public String getTableName() {
		return tableName;
	}

	public int getColumnLen() {
		return c.length;
	}

	public String getClassPackage() {
		return p.getProperty("beanPackage");
	}
	public String getDate() {
		return DateUtil.getPlusTimeDate();
	}
	public String getColumnSwitch() {
		List<ColumnBean> list=ConnTools.listColumn2(conn, getTableName());
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			int k=list.get(i).getColumnType();
			sb.append("	case "+(1+i)+":"+(k==4||k==-7?"b1=true;":"")+"name=\"`"+list.get(i).getColumnName()+"`\";break;\n");
		}
		return sb.toString();
	}

	public String getColumnList() {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < c.length; i++) {
			sb.append((1+i)+":"+c[i]+",");
		}
		return sb.toString();
	}

	public String getInsertColumn() {
		StringBuffer  sb=new StringBuffer("(");
		for (int i = 1; i < c.length; i++) {
			sb.append("'"+c[i]+"',");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") values(");
		for (int i = 1; i < c.length; i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
	}
	public String getColumns() {
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			ColumnBean cb=list.get(i);
			int k=cb.getColumnType();
			boolean b=k==4||k==-7?true:false;
			boolean bb=k==12||k==1||k==-1?true:false;
			boolean bbb=k==8?true:false;
			String s="";
			if(b){s="int";}else if(bb){s="String";}else if(bbb){s="Double";}else{s="boolean";}
			sb.append("\n\tprivate\t"+s+"\t"+cb.getColumnName()+";");
		}
		return sb.toString();
	}
	public String getGets(){
		StringBuffer sb=new StringBuffer();
		sb.append("\n");
		sb.append("\n\t@Override");
		sb.append("\n\tpublic String toString() {");
		sb.append("\n\t\treturn ");
		for (int i = 0; i < list.size(); i++) {
			ColumnBean cb=list.get(i);
			String name=cb.getColumnName();
			sb.append("\"|"+name+":\"+"+name+"+");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(";");
		sb.append("\n\t}");
		//struts1
		sb.append("\n\tpublic "+getClassName()+"() {\n\t\tsuper();\n\t}");
		//struts2
		sb.append("\n\tpublic "+getClassName()+"(");
		for (int i = 0; i < list.size(); i++) {
			ColumnBean cb=list.get(i);
			String name=cb.getColumnName();
			if(name.equals("id")||name.equals("createTime")||name.equals("status"))continue;
			int k=cb.getColumnType();
			boolean b=k==4||k==-7?true:false;
			boolean bb=k==12||k==1||k==-1?true:false;
			boolean bbb=k==8?true:false;
			String s="";
//			System.out.println(k+":"+cb.getColumnTypeName()); 
			if(b){s="int";}else if(bb){s="String";}else if(bbb){s="Double";}else{s="boolean";}
			sb.append(s+" "+name+",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")\n\t{");
		for (int i = 0; i < list.size(); i++) {
			ColumnBean cb=list.get(i);
			String name=cb.getColumnName();
			if(name.equals("id"))continue;
			else if(name.equals("createTime"))
				sb.append("\n\t\tthis.createTime = DateUtil.getPlusTimeDate();");
			else if(name.equals("status")){
				sb.append("\n\t\tthis.status = 0;");
			}else{
				sb.append("\n\t\tthis."+cb.getColumnName()+" = "+cb.getColumnName()+";");
			}
		}
		sb.append("\n\t}");
//		sb.append("\n\tpublic "+getClassName()+"(");
//		for (int i = 0; i < list.size(); i++) {
//			ColumnBean cb=list.get(i);
//			String name=cb.getColumnName();
//			int k=cb.getColumnType();
//			boolean b=k==4||k==-7?true:false;
//			boolean bb=k==12||k==1||k==-1?true:false;
//			boolean bbb=k==8?true:false;
//			String s="";
//			if(b){s="int";}else if(bb){s="String";}else if(bbb){s="Double";}else{s="boolean";}
//			sb.append(s+" "+name+",");
//		}
//		sb.deleteCharAt(sb.length()-1);
//		sb.append(")\n\t{");
//		for (int i = 0; i < list.size(); i++) {
//			ColumnBean cb=list.get(i);
//			sb.append("\n\t\tthis."+cb.getColumnName()+" = "+cb.getColumnName()+";");
//		}
//		sb.append("\n\t}");
		for (int i = 0; i < list.size(); i++) {
			ColumnBean cb=list.get(i);
			String name=cb.getColumnName();
			int k=cb.getColumnType();
			boolean b=k==4||k==-7?true:false;
			boolean bb=k==12||k==1||k==-1?true:false;
			boolean bbb=k==8?true:false;
			String s="";
			if(b){s="int";}else if(bb){s="String";}else if(bbb){s="Double";}else{s="boolean";}
			String getname=name.substring(0,1).toUpperCase()+name.substring(1);
			if(name.equals("createTime")){
				sb.append("\n\tpublic "+s+" get"+getname+"(){");
				sb.append("\n\t\treturn DateUtil.getPlusTimeDate();\n\t}");
				sb.append("\n\tpublic void set"+getname+"(){");
				sb.append("\n\t\tthis."+name+" = DateUtil.getPlusTimeDate();\n\t}");
			}else{
				sb.append("\n\tpublic "+s+" get"+getname+"(){");
				sb.append("\n\t\treturn "+name+";\n\t}");
				sb.append("\n\tpublic void set"+getname+"("+s+" "+name+"){");
				sb.append("\n\t\tthis."+name+" = "+name+";\n\t}");
			}
		}
		sb.append("\n");
		return sb.toString();
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClasspackageName() {
		return classpackageName;
	}

	public void setClasspackageName(String classpackageName) {
		this.classpackageName = classpackageName;
	}

	public String getTablename() {
		return tableName;
	}

	public void setTablename(String tablename) {
		this.tableName = tablename;
	}

}
