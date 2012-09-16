package com.iatb.bean.template;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import com.iatb.bean.ColumnBean;
import com.iatb.util.DateUtil;
import com.iatb.util.IOUtil;
import com.iatb.util.PropUtil;
import com.iatb.util.db.ConnTools;

public class DaoImplClass {
	private static	Connection  conn;
	private String tableName;	//表名
	private String column; 		//字段组合1
	private	String c[];
	private Properties p;

	public String getContent() {
		return IOUtil.readFileToString(PropUtil.getClassPath()+"/"+ PropUtil.getConfProp("template").getProperty("daoimplFile"),p.getProperty("encode"));
	}
	
	public DaoImplClass(String tableName) {
		p=PropUtil.getConfProp("template");
		conn=ConnTools.makeConnection(p.getProperty("dbName"));
		this.tableName=tableName;
		c=ConnTools.listColumn(conn, tableName);
	}
	
	/**
	 * 判断字段值是否为整型
	 * @return
	 */
	public boolean checkInteger(){
		
		return false;
	}

	public String getClassName() {
		return getTableBeanName()+p.getProperty("daoimpl");
	}

	public String getDbName() {
		return p.getProperty("dbName");
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

	public void setColumn(String column) {
		this.column = column;
	}

	public String getColumnGet() {
		String s=getTableAsila()+".get";String p="(),";
		StringBuffer sb=new StringBuffer();
		for (int i = 1; i < getColumnLen(); i++) {
			sb.append(s+c[i].substring(0, 1).toUpperCase()+c[i].substring(1)+p);
		}
		sb=sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	public int getColumnLen() {
		return c.length;
	}

	public String getClassPackage() {
		return p.getProperty("daoImplPackage");
	}

	public String getTablebeanpackageName() {
		return p.getProperty("tableBeanPackageName")+"."+getTableBeanName();
	}

	public String getconnToolsPackageName() {
		return p.getProperty("connToolsPackageName");
	}

	public String getTableBeanName() {
		return BeanUtil.getClassName(getTableName());
	}
	public String getDate() {
		return DateUtil.getPlusTimeDate();
	}

	public String getColumnSwitch() {
		List<ColumnBean> list=ConnTools.listColumn2(conn, getTableName());
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			int k=list.get(i).getColumnType();
			if(i==0)
				sb.append("case "+(1+i)+":"+(k==4||k==-7?"b1=true;":"")+"name=\"`"+list.get(i).getColumnName()+"`\";break;\n");
			else
				sb.append("\t\t\tcase "+(1+i)+":"+(k==4||k==-7?"b1=true;":"")+"name=\"`"+list.get(i).getColumnName()+"`\";break;\n");
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
			sb.append("`"+c[i]+"`,");
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
	// `ParentID`=?,`ColumnCode`=?,`ColumnName`=? values(?,?,?) where id=?
	public String getUpdateColumn() {
		StringBuffer  sb=new StringBuffer("(");
		for (int i = 1; i < c.length; i++) {
			sb.append("`"+c[i]+"`=?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") values(");
		for (int i = 1; i < c.length; i++) {
			sb.append("?,");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") where "+c[0]+"=?");
		return sb.toString();
	}
	//$getTableAsila.getID()
	public String getId() {
		return getTableAsila()+".get"+c[0].substring(0, 1).toUpperCase()+c[0].substring(1)+"()";
	}


}
