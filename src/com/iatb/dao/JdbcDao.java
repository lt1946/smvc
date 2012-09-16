package com.iatb.dao;

import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@SuppressWarnings({ "deprecation"})
public abstract class JdbcDao {
	
	protected SimpleJdbcTemplate jdbcTemplate;
	protected NamedParameterJdbcTemplate namedjdbctemp;
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
	    this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
	    this.namedjdbctemp=new NamedParameterJdbcTemplate(dataSource);
	}
	public int insert(String sql,Object o){
		SqlParameterSource ps=new BeanPropertySqlParameterSource(o);
		KeyHolder keyholder=new GeneratedKeyHolder();
		namedjdbctemp.update(sql, ps,keyholder);
		return keyholder.getKey().intValue();
	}
	public Long count(String sql){
		return jdbcTemplate.queryForLong(sql);
	}
	public Long count(String sql,Object[] o){
		return jdbcTemplate.queryForLong(sql,o);
	}
	
	public int execute(String sql,Object[] o){
		return jdbcTemplate.update(sql, o);
	}
	
	public int deleteById(String tablename,int id){
//		return jdbcTemplate.update("delete from "+tablename+" where id=?",id);
		return jdbcTemplate.update("update "+tablename+" set status=-1 where id=?",id);
	}
	
	
	public <T>T get(String sql,Class<T> z,Object[] o){
		try {
			return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(z),o).get(0);
		} catch (Exception e) {
			return null;
		}
	}
	public <T>T getById(String tablename,int id,Class<T> z){
		return jdbcTemplate.query("select * from ? where id=?",ParameterizedBeanPropertyRowMapper.newInstance(z),new Object[]{tablename,id}).get(0);
	}
	
	public <T> List<T> getList(String sql,Class<T> z,Object[] o){
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(z),o);
	}
	
	public <T> List<T> listAll(String tablename,Class<T> z) {
		return jdbcTemplate.query("select * from "+tablename+" order by id desc",ParameterizedBeanPropertyRowMapper.newInstance(z));
	}
	
	public <T> List<T> ListByPage(String tablename,Class<T> z,int page,int pagesize){
		return jdbcTemplate.query("select * from "+tablename+" order by id desc limit ("+page+"-1)*"+pagesize+","+pagesize,ParameterizedBeanPropertyRowMapper.newInstance(z));
	}
	
	public <T> List<T> ListByPage(String tablename,Class<T> z,int page,int pagesize,String sortname,String sorttype){
		return jdbcTemplate.query("select * from "+tablename+" order by "+sortname+" "+sorttype+" limit ("+page+"-1)*"+pagesize+","+pagesize,ParameterizedBeanPropertyRowMapper.newInstance(z));
	}
	
	public void batchUpdateNoDupl(String sql, List<Object[]> list) {
		try {
			jdbcTemplate.batchUpdate(sql, list);
		} catch (DuplicateKeyException e) {
			for (Object[] objects : list) {
				try {
					jdbcTemplate.update(sql, objects);
				} catch (DuplicateKeyException e1) {
					continue;
				}catch(Exception e2){
					e2.printStackTrace();
					continue;
				}
			}
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}
	
}
