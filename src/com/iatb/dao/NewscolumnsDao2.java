package com.iatb.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.iatb.pojo.Newscolumns;

/**
 * @author Administrator
 * @since  2011-12-22 15:25:53
 */
@Repository("newscolumnsDao")
@SuppressWarnings("deprecation")
public class NewscolumnsDao2  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(NewscolumnsDao2.class);
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
	
	public boolean save(Newscolumns newscolumns){
		String sql="insert into newscolumns (`ParentID`,`ColumnCode`,`ColumnName`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),newscolumns.getParentID(),newscolumns.getColumnCode(),newscolumns.getColumnName());	
		return c>0?true:false;
	}
	public boolean update(Newscolumns newscolumns){
		String sql="update newscolumns set `ParentID`=?,`ColumnCode`=?,`ColumnName`=? values(?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),newscolumns.getParentID(),newscolumns.getColumnCode(),newscolumns.getColumnName(),newscolumns.getID());	
		return c>0?true:false;
	}
	public List<Newscolumns> listAll(){
		return listAll("newscolumns", Newscolumns.class);
	}
	public boolean delete(Integer id){
		return deleteById("newscolumns", id)>0?true:false;
	}
	public Newscolumns load(Integer id){
		return getById("newscolumns", id, Newscolumns.class);
	}
	
	
}
