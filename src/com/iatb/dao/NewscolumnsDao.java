package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.Newscolumns;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2011-12-22 15:57:42
 */
@Repository("newscolumnsDao")
@SuppressWarnings("deprecation")
public class NewscolumnsDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(NewscolumnsDao.class);
	/**
	 * 添加
	 * @param newscolumns
	 */
	public String add(Newscolumns newscolumns){
		String sql="insert into newscolumns (`ParentID`,`ColumnCode`,`ColumnName`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),newscolumns.getParentID(),newscolumns.getColumnCode(),newscolumns.getColumnName());	
		log.info("添加newscolumns:"+(c>0?"成功":"失败"));
		return ("添加newscolumns:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param newscolumns
	 */
	public boolean save(Newscolumns newscolumns){
		String sql="insert into newscolumns (`ParentID`,`ColumnCode`,`ColumnName`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),newscolumns.getParentID(),newscolumns.getColumnCode(),newscolumns.getColumnName());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param newscolumns
	 */
	public boolean update(Newscolumns newscolumns){
		String sql="update newscolumns set (`ParentID`=?,`ColumnCode`=?,`ColumnName`=?) values(?,?,?) where ID=?";
		int c=jdbcTemplate.update(sql.toString(),newscolumns.getParentID(),newscolumns.getColumnCode(),newscolumns.getColumnName(),newscolumns.getID());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<Newscolumns> listAll(){
		return listAll("newscolumns", Newscolumns.class);
	}
	/**
	 * 查询sql
	 */
	public List<Newscolumns> list(String sql,Object[] o){
		return getList(sql,Newscolumns.class,o);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("newscolumns", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public Newscolumns load(Integer id){
		return getById("newscolumns", id, Newscolumns.class);
	}
	
	
}
