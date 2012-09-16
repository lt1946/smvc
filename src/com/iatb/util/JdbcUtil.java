package com.iatb.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.log4j.Logger;
import com.iatb.bean.ColumnBean;

public class JdbcUtil {

	private final static Logger log=Logger.getLogger(JdbcUtil.class);
	private static String dirverClassName;
	private static String dbname;
	private static String url;
	private static String username;
	private static String password;
	private static Properties jdbcprop;
	private static Integer minvalue;
	private static Integer maxvalue;
	private static Connection conn;
	private static QueryRunner run;
	static{
		init();
	}
	public static void main(String[] args) {
//		String []s=queryColumns("city");
		String []s=queryTables();
		for (String string : s) {
			System.out.println(string);
		}
	}
	
	public static void init(){
		if(jdbcprop==null){
			jdbcprop=PropUtil.getConfProp("jdbc");
		}
		dirverClassName=jdbcprop.getProperty("dirverClassName");
		dbname=jdbcprop.getProperty("dbname");
		url=jdbcprop.getProperty("url");
		username=jdbcprop.getProperty("username");
		password=jdbcprop.getProperty("password");
		minvalue=Integer.parseInt(jdbcprop.getProperty("minvalue"));
		maxvalue=Integer.parseInt(jdbcprop.getProperty("maxvalue"));
		if(conn==null)conn=getConn();
		if(run==null)run=new QueryRunner();
	}
	/**
	 * 创建连接
	 * @param url   	  mysql连接地址
	 * @param user	      用户名
	 * @param password    密码
	 * @return
	 */
	public static Connection getConn(String url, String user,String password) {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	/**
	 * 创建默认连接
	 * @return
	 */
	public synchronized static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			
			return null;
		} catch (SQLException e) {
			return null;
		}
		return conn;
	}
	/**
	 * 判断sql是否存在
	 * @param sql
	 * @return	boolean
	 */
	public static boolean exists(String sql){
		int count=0;
		Object o = null;
		try {
			o = run.query(sql, new ScalarHandler(1), "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (o instanceof Integer) {
			count= (Integer) o;
		}
		if (o instanceof Long) {
			Long l = (Long) o;
			return l.intValue()>0?true:false;
		}
		String s = (String) o;
		try {
			count= Integer.parseInt(s);
		} catch (NumberFormatException e) {
			count=0;
		}
		return count>0?true:false;
	}
	/**
	 * 查看最大id
	 * @param conn
	 * @param table
	 * @return
	 */
	public static int queryMaxId(String table){
		String sql="select id from "+table+" order by id desc limit 1";
		List<Map<String, Object>> list=queryMap(sql);
		return (Integer) ((list==null||list.size()==0)?-1:list.get(0).get("id"));
	}
	/**
	 * 查询Sql
	 * @param conn
	 * @param sql
	 * @return 返回list的map集合
	 */
	public static List<Map<String, Object>> queryMap(String sql) {
		try {
			return run.query(conn, sql, new MapListHandler());
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * 查询Sql
	 * @param conn        连接类
	 * @param sql         sql
	 * @param c           返回的类
	 * @return	所有类c的集合
	 */
	@SuppressWarnings("unchecked")
	public static List query(String sql, Class c) {
		try {
			return (List) run.query(conn, sql, new BeanListHandler(c));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 查询sql
	 * @param conn    连接类
	 * @param sql     sql
	 * @param c       返回的类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List query( String sql, Class c,Object []o) {
		try {
			return (List) run.query(conn, sql, new BeanListHandler(c),o);
		} catch (SQLException e) {
			return null;
		}
	}
	/**
	 * 获取数据库中所有表
	 * @param conn
	 * @param dbname
	 * @return
	 */
	public static String[] queryTables(){
		try {
			PreparedStatement   pstmt = conn.prepareStatement("show full tables from `"+dbname+"` where table_type = 'BASE TABLE'");
			ResultSet  rs = pstmt.executeQuery();
			rs.last();
			String c[]=new String[rs.getRow()]; 
			rs.beforeFirst();
			int i=0;
			while(rs.next()){
				c[i++]=rs.getString("Tables_in_"+dbname);
			}
			rs.close();
			return c;
		} catch (SQLException e) {
			log.error("获取数据库中所有表异常！");
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 查询table中的所有字段名
	 * @param conn
	 * @param table
	 * @return
	 */
	public static String[] queryColumns(String table)  
	{   
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
				rs.close();
				return c;
		     }
		} catch (SQLException e) {
			log.error("查询"+table+"中的所有字段名异常！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取表ColumnBean集合
	 * @param table
	 * @return	 List<ColumnBean>
	 */
	public static List<ColumnBean> queryColumnList(String table)  
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
//					cb.setColumnType(rsmd.getColumnType(i));
					cb.setColumnTypeName(rsmd.getColumnTypeName(i));
					cb.setNull(rsmd.isNullable(i)==1?true:false);
					cb.setMaxlength(rsmd.getPrecision(i));
					if(rs.getString(16)==null){
						int maxi=0;
						String tn=cb.getColumnTypeName().toLowerCase();
						if(tn.equals("int"))maxi=10;
						else if(tn.equals("text"))maxi=65535;
						cb.setMaxlength(maxi);
					}else{	
						cb.setMaxlength(Integer.parseInt( rs.getString(16)));
					}
					list.add(cb);
				}
		     }
		     rs.close();
		} catch (SQLException e) {
			log.error("获取表ColumnBean集合异常！");
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 更新sql
	 * @param conn     连接类
	 * @param sql      sql字符串
	 * @return		更新影响行数
	 */
	public static int update(String sql) {
		try {
			return run.update(conn, sql);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("更新："+sql+"异常！");
			return -1;
		}
	}
	/**
	 * 更新数据
	 * @param conn    连接类
	 * @param sql     sql字符串
	 * @return
	 */
	public static int update( String sql, Object[] o) {
		try {
			return run.update(conn, sql, o);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("更新："+sql+"异常！");
			return -1;
		}
	}
	/**
	 * 批处理操作Sql
	 * @param sql
	 * @param params
	 * @param cls
	 * @return	int[]
	 */
	public static int[] batch(String sql,Object[][] params) {
		try {
			return run.batch(conn,sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 批量执行sql
	 * @param conn
	 * @param sql
	 * @param o
	 */
	public static void batch( String sql,String []o)     {
		 try {
			long start = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            sb.append(sql.substring(0,sql.indexOf(" values")+" values".length()));
            PreparedStatement mysqlPs = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (int i = 0; i < o.length; i++) {
                if(i > 0) sb.append(",");
                sb.append("("+o[i]+")");    
            }
            mysqlPs.executeUpdate(sb.toString());
            long end = System.currentTimeMillis();
            log.error("数据导入完毕,所用时间为: " + (end - start) + " ms.");
        } catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			log.error("数据出错,已进行回滚！");
        } finally {
			try {
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("连接提交异常！");
			}
        }
	}
	/**
	 * 关闭连接
	 * @param conn   连接类
	 */
	public static void close() {
		if (conn != null) { 
            try {
				conn.commit();
			} catch (SQLException e) {
				log.error("连接关闭异常！");
				e.printStackTrace();
			} finally {
				try {
					conn.close();
				} catch (SQLException e) {
					log.error("连接关闭异常！");
					e.printStackTrace();
				} 
			}
        } 
	}
}
