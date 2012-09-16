package com.iatb.util.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.iatb.bean.ColumnBean;

/**
 * @author Administrator
 * @since 2010-09-20
 */
public class OracleConnTools {
	private static String dirverClassName = "oracle.jdbc.driver.OracleDriver";
//	private static String url = "jdbc:mysql://127.0.0.1:3306/oa?autoReconnect=true&useUnicode=true&characterEncoding=gbk&mysqlEncoding=gbk&zeroDateTimeBehavior=convertToNull";
	private static String url = "jdbc:oracle:thin:@10.10.11.103:1521:oracle";
	private static String user = "tbiss";
	private static String password = "tbiss";

	/**
	 * 获得第一个查询第一行第一列
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Object getAnAttr(String sql, Object[] params) {
		Object s = null;
		try {
			QueryRunner run = new QueryRunner();
			s = run.query(sql, new ScalarHandler(1), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}
	/**
	 * eg: select count(1) from user
	 * @param sql
	 * @param params
	 * @return
	 */
	public static int count(String sql, Object[] params) {
		Object o = getAnAttr(sql, params);
		if (o instanceof Integer) {
			return (Integer) o;
		}
		if (o instanceof Long) {
			Long l = (Long) o;
			return l.intValue();
		}
		String s = (String) o;
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	//查看最大id
	public static int getMaxId(Connection conn, String table){
		String sql="select id from "+table+" order by id desc limit 1";
		List<Map<String, Object>> list=OracleConnTools.queryMap(conn, sql);
		return (Integer) ((list==null||list.size()==0)?-1:list.get(0).get("id"));
	}
	
	/**
	 * 创建连接类
	 * 
	 * @return
	 */
	public static Connection makeConnection() {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			return null;
		}
		return conn;
	}

	/**
	 * 创建连接类
	 * @return
	 */
	public static Connection makeCrawlJobConnection() {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
		try {
			conn = DriverManager.getConnection(url.replace("oa", "myspider"), user, password);
		} catch (SQLException e) {
			return null;
		}
		return conn;
	}
	
	/**
	 * 创建Groupbuy连接类
	 * @return
	 */
	public static Connection makeGroupBuyJobConnection() {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
		try {
			conn = DriverManager.getConnection(url.replace("oa", "groupbuy"), user, password);
		} catch (SQLException e) {
			return null;
		}
		return conn;
	}
	/**
	 * 创建连接类
	 * @return
	 */
	public static Connection makeConnection(String database) {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
		try {
			conn = DriverManager.getConnection(url.replace("oa", database), user, password);
			
		} catch (SQLException e) {
			return null;
		}
		return conn;
	}
	
	/**
	 * 创建连接类
	 * 
	 * @param url
	 *            mysql连接地址
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 */
	public static Connection makeConnection(String url, String user,
			String password) {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			return null;
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			return null;
		}
		return conn;
	}

	/**
	 * 查询sql
	 * 
	 * @param conn
	 *            连接类
	 * @param sql
	 *            sql
	 * @param c
	 *            返回的类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List query(Connection conn, String sql, Class c) {
		try {
			QueryRunner qRunner = new QueryRunner();
			return (List) qRunner.query(conn, sql, new BeanListHandler(c));
		} catch (SQLException e) {
			return null;
		}
	}
	//TODO 添加获取唯一对象（3）
	/*public static Object queryUnique(Connection conn,String sql,Class c){
		QueryRunner qRunner=new QueryRunner();
		return qRunner.query(conn, sql,new BeanHandler<c>);
	}*/
	/**
	 * 查询sql
	 * 
	 * @param conn
	 *            连接类
	 * @param sql
	 *            sql
	 * @param c
	 *            返回的类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List query2(Connection conn, String sql, Class c,Object []o) {
		try {
			QueryRunner qRunner = new QueryRunner();
			return (List) qRunner.query(conn, sql, new BeanListHandler(c),o);
		} catch (SQLException e) {
			return null;
		}
	}

		
	/**
	 * 返回list的map集合
	 * 
	 * @param conn
	 * @param sql
	 * @return
	 */
	public static List<Map<String, Object>> queryMap(Connection conn, String sql) {
		try {
			QueryRunner qr = new QueryRunner();
			return qr.query(conn, sql, new MapListHandler());
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param conn
	 *            连接类
	 * @param sql
	 *            sql字符串
	 * @return
	 */
	public static int update(Connection conn, String sql, Object[] o) {
		try {
			QueryRunner qRunner = new QueryRunner();
			return qRunner.update(conn, sql, o);
		} catch (SQLException e) {
			return -1;
		}
	}
	
	/**
	 * 更新数据
	 * @param conn
	 *            连接类
	 * @param sql
	 *            sql字符串
	 * @return
	 */
	public static int update2(Connection conn, String sql) {
		try {
			QueryRunner qRunner = new QueryRunner();
			return qRunner.update(conn, sql);
		} catch (SQLException e) {
			return -1;
		}
	}

	/**
	 * 批处理操作的应用
	 * @since 2010-09-21
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws SQLException
	 */
	public static int[] executeUpdateBatch(Connection conn, String sql,
			Object[][] params) throws SQLException {
		QueryRunner run = new QueryRunner();
		return run.batch(conn,sql, params);
	}

	/**
	 * 批量执行sql
	 * @param conn
	 * @param sql
	 * @param o
	 * @throws SQLException 
	 * @throws SQLException 
	 */
	public static void batch(Connection conn, String sql,String []o)     {
		 try {
	            StringBuilder sb = new StringBuilder();
	            sb.append(sql.substring(0,sql.indexOf(" values")+" values".length()));
	            PreparedStatement mysqlPs = conn.prepareStatement(sql);
	            conn.setAutoCommit(false);
	            long start = System.currentTimeMillis();
	            for (int i = 0; i < o.length; i++) {
	                if(i > 0) sb.append(",");
	                sb.append("("+o[i]+")");    
	            }
//	            System.out.println(sb.toString());
	            mysqlPs.executeUpdate(sb.toString());
	            long end = System.currentTimeMillis();
	            System.out.println("数据导入完毕,所用时间为: " + (end - start) + " ms");
	        } catch (Exception e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					System.out.println("数据出错,已进行回滚");
	        } finally {
					try {
						conn.commit();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					OracleConnTools.close(conn);
	        }
	}
	/**
	 * 关闭连接
	 * 
	 * @param conn
	 *            连接类
	 */
	public static void close(Connection conn) {
		 if (conn != null) { 
                try {
					conn.commit();
				} catch (SQLException e) {
					
				} finally {
					try {
						conn.close();
					} catch (SQLException e) {
					} 
				}
	        } 
	}
	
	/**
	 * 获取数据库中所有表(适用orcale)
	 * @param conn
	 * @param dbname
	 * @return
	 */
	public static List<Map<String, Object>> listTable(Connection conn,String dbname){
		return queryMap(conn, "select  table_name from dba_tables   where owner='"+dbname.toUpperCase()+"'");
	}
	/**
	 * 查询table中的字段名(适用Oracle)
	 * @param conn
	 * @param table
	 * @return
	 */
	public static String[] listColumn(Connection conn,String table)  
	{   
//		queryMap(conn, "SELECT column_name FROM DBA_TAB_COLUMNS WHERE TABLE_NAME = '"+table.toUpperCase()+"'");
		try {
			PreparedStatement   pstmt = conn.prepareStatement("SELECT * FROM "+table);
			ResultSet  rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData(); //获取字段名
		     if(rsmd != null){
				int count  = rsmd.getColumnCount();
				String c[]=new String[count]; 
				for(int i=1;i<=count;i++){
					c[i-1]=rsmd.getColumnName(i);
				}
				return c;
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static List<ColumnBean> listColumn2(Connection conn,String table)  
	{   
		List<ColumnBean> list=new ArrayList<ColumnBean> ();
    	try {
			PreparedStatement   pstmt = conn.prepareStatement("SELECT * FROM "+table);
			ResultSet  rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData(); //获取字段名
		     if(rsmd != null){
				int count  = rsmd.getColumnCount();
				for(int i=1;i<=count;i++){
					ColumnBean cb=new ColumnBean ();
					cb.setColumnLabel(rsmd.getColumnLabel(i));
					cb.setColumnName(rsmd.getColumnName(i));
					cb.setColumnType(rsmd.getColumnType(i));
					cb.setColumnTypeName(rsmd.getColumnTypeName(i));
					cb.setNull(rsmd.isNullable(i)==1?true:false);
					cb.setMaxlength(rsmd.getPrecision(i));
					list.add(cb);
				}
		     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static List<ColumnBean> listColumn3(Connection con,String table) {
		List<ColumnBean> list=new ArrayList<ColumnBean> ();
		try {
		      DatabaseMetaData dbmd = con.getMetaData();
		      ResultSet rs = dbmd.getColumns(null,null,table,null);
		      while(rs.next()) {
					ColumnBean cb=new ColumnBean ();
					cb.setColumnLabel(rs.getString(4));
					cb.setColumnName(rs.getString(4));
//					cb.setColumnType(Integer.parseInt( rs.getString(7)));
					cb.setColumnTypeName(rs.getString(6));
					cb.setNull(rs.getString(18)=="null"?true:false);
					if(rs.getString(16)==null){
						int i=0;
						String tn=cb.getColumnTypeName().toLowerCase();
						if(tn.equals("int"))i=10;
						else if(tn.equals("text"))i=65535;
						cb.setMaxlength(i);
					}else{	
						cb.setMaxlength(Integer.parseInt( rs.getString(16)));
					}
					cb.setDefaultString(rs.getString(13));
					cb.setComments(rs.getString(12));
					list.add(cb);
		      }
		      rs.close();
		   } 
		   catch (Exception e) {
		      e.printStackTrace();
		   }finally{
			   return list;
		   }
		}
	public static void main(String[] args) {
		Connection  conn=OracleConnTools.makeConnection("spider2011");
		listColumn2(conn, "content");
//		listTable(conn, "spider2011");
		List<ColumnBean> list=listColumn3(conn, "content");
		for (ColumnBean columnBean : list) {
			System.out.println(columnBean);
		}
	}
}