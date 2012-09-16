package com.iatb.util.use;

import java.io.File;
import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import com.iatb.bean.ColumnBean;
import com.iatb.bean.template.BeanUtil;
import com.iatb.util.IOUtil;
import com.iatb.util.MyFile;
import com.iatb.util.PropUtil;
import com.iatb.util.db.ConnTools;

//已有替代类ParameterizedBeanPropertyRowMapper.newInstance(*.class)
public class createRowMap {
	private static Connection  conn=ConnTools.makeConnection("SpringMVC");
	private static  String getContent() {
		Properties p=	PropUtil.getConfProp("template");
		return IOUtil.readFileToString(PropUtil.getClassPath()+"/"+ p.getProperty("rowmap"),p.getProperty("encode"));
	}

	public static void run(String tablename){
		if(tablename==null||tablename.trim().equals("")){
			System.out.println ("表名不允许为空！"); 
		}else{
			String beanname=BeanUtil.getClassName(tablename);
			String sb=BeanUtil.getShorName(beanname);
			String s=getContent().replaceAll("[$]Javabean", beanname)
			.replaceAll("[$]JB", sb)
			.replaceAll("[$]Set", getSet(sb,tablename));
			System.out.println(s);
			String fn=PropUtil.getConfProp("project").getProperty("class_path")+"\\com\\iatb\\rowmap\\"+beanname+"RowMap.java";
			MyFile.writeToFile(s, new File(fn), false);
			System.out.println (fn+"创建完毕！"); 
		}
	}
	//获取$Set变量
	private static  String getSet(String shortname, String tablename){
		List<ColumnBean> list=ConnTools.listColumn3(conn, tablename);
		StringBuffer sb=new StringBuffer();
		// 2011-12-06 check pojo 字段是否改变。
		for (ColumnBean cb : list) {
			String cn=cb.getColumnName();
			if(cn.equals("createTime")){
				sb.append("\n\t\t"+shortname+".setCreateTime();");continue;
			}
			String CN=BeanUtil.getClassName(cn);
			String tn=cb.getColumnTypeName().toLowerCase();
			if(tn.equals("int")||tn.equals("bit")){
				sb.append("\n\t\t"+shortname+".set"+CN+"(rs.getInt(\""+cn+"\"));");
			}else if(tn.equals("char")||tn.equals("varchar")||tn.equals("text")){
				sb.append("\n\t\t"+shortname+".set"+CN+"(rs.getString(\""+cn+"\"));");
			}else if(tn.equals("decimal")||tn.equals("double")){
				sb.append("\n\t\t"+shortname+".set"+CN+"(rs.getDouble(\""+cn+"\"));");
			}else if(tn.equals("blob")){
				sb.append("\n\t\t"+shortname+".set"+CN+"(rs.getBlob(\""+cn+"\"));");
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
//		run("groupurlrole");
//		run("person");
		// 2011-12-07 初始化所有表
	}
}
