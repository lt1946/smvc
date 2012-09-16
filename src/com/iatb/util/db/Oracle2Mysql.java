package com.iatb.util.db;

import java.sql.Connection;
import java.util.List;
import java.util.Map;


public class Oracle2Mysql {
	private static  Connection conn = OracleConnTools.makeConnection();
	private static  Connection conn2 = ConnTools.makeConnection("iatbforever_ssh2");
	
	/**
	 * oracle表中数据导入mysql表中
	 */
	public static void convert(){
		String sql2="insert into t_role (`ID`,`PARENT_ID`,`ROLE_NAME`,`ROLE_DESCRIPTION`,`DISPLAY_ORDER`,`CREATE_TIME`) values(?,?,?,?,?,?)";
		List<Map<String,Object>> list = OracleConnTools.queryMap( conn, "select * from t_role ");
		for (Map<String, Object> map : list) {
//			int i= Integer.parseInt(map.get("b").toString());
//			System.out.println(i);
			int i=ConnTools.update(conn2, sql2, new Object[]{map.get("ID"),map.get("PARENT_ID"),map.get("ROLE_NAME"),map.get("ROLE_DESCRIPTION"),map.get("DISPLAY_ORDER"),map.get("CREATE_TIME")});
			System.out.println(i);
		}
	}
	
	/**
	 * 判断oracle和mysql中的数据表异同，并更新 
	 * 
	 */
	public static void checkTable(){
		List<Map<String, Object>> s=OracleConnTools.listTable(conn,"TBISS");
		String[] s2=ConnTools.listTable(conn2,"iatbforever_ssh2");
		System.out.println("mysql tables count:"+s2.length);
		System.out.println("orcale tables count:"+s.size());
		for (String t : s2) {
			if(t==null||t.equals(""))continue;
			List<Map<String,Object>> list = OracleConnTools.queryMap( conn, "select count(1) b from "+t);
			List<Map<String,Object>> list2 = ConnTools.queryMap( conn2, "select count(1) b from "+t);
			String l1,l2="";
			if(!(l1=list.get(0).get("b").toString().trim()).equals(list2.get(0).get("b").toString().trim())){
				System.out.println("mysql table:"+t+" count:"+l1);
				System.out.println("orcale table:"+t+" count:"+l2);
				
				String[] t1=OracleConnTools.listColumn(conn, t);
				String c1="";
				String c11="";
				for (int i = 0; i < t1.length; i++) {
					c1+="`"+t1[i]+"`,";
					c11+="?,";
				}
				String sql2="insert into "+t.trim()+" ("+c1.substring(0,c1.length()-1)+") values("+c11.substring(0,c11.length()-1)+")";
				List<Map<String,Object>> l3 = OracleConnTools.queryMap( conn, "select * from "+t.trim());
				for (Map<String, Object> map : l3) {
					Object[] c111=new Object[t1.length];
					for (int i = 0; i < t1.length; i++) {
						c111[i]=map.get(t1[i]);
					}
					int i=ConnTools.update(conn2, sql2, c111);
					System.out.print(i+"\t");
				}
			}
			
		}

	}
	public static void main(String[] args) {
//		convert();
//		checkTable();
	}
}
