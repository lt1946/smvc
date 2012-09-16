package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbApi;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbApiDao")
@SuppressWarnings("deprecation")
public class GbApiDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbApiDao.class);
	
	/**
	 * 添加并返回id
	 * @param gbApi
	 */
	public int addReturnId(GbApi gbApi){
		return insert("insert into gb_api (`url`,`gbsiteid`,`createTime`,`status`,`updateTime`) values(?,?,?,?,?)", gbApi);
	}
	/**
	 * 添加
	 * @param gb_api
	 */
	public String add(GbApi gbApi){
		String sql="insert into gb_api (`url`,`gbsiteid`,`createTime`,`status`,`updateTime`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbApi.getUrl(),gbApi.getGbsiteid(),gbApi.getCreateTime(),gbApi.getStatus(),gbApi.getUpdateTime());	
		log.info("添加gbApi:"+(c>0?"成功":"失败"));
		return ("添加gbApi:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param gb_api
	 */
	public boolean save(GbApi gbApi){
		String sql="insert into gb_api (`url`,`gbsiteid`,`createTime`,`status`,`updateTime`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbApi.getUrl(),gbApi.getGbsiteid(),gbApi.getCreateTime(),gbApi.getStatus(),gbApi.getUpdateTime());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param gb_api
	 */
	public boolean update(GbApi gbApi){
		String sql="update gb_api set (`url`=?,`gbsiteid`=?,`createTime`=?,`status`=?,`updateTime`=?) values(?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbApi.getUrl(),gbApi.getGbsiteid(),gbApi.getCreateTime(),gbApi.getStatus(),gbApi.getUpdateTime(),gbApi.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<GbApi> listAll(){
		return listAll("gb_api", GbApi.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_api", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public GbApi load(Integer id){
		return getById("gb_api", id, GbApi.class);
	}
	
	
}
