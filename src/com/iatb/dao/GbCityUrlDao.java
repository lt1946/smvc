package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbCityUrl;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbCityUrlDao")
@SuppressWarnings("deprecation")
public class GbCityUrlDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbCityUrlDao.class);
	
	/**
	 * 添加并返回id
	 * @param gbCityUrl
	 */
	public int addReturnId(GbCityUrl gbCityUrl){
		return insert("insert into gb_city_url (`cityurl`,`createTime`,`status`) values(?,?,?)", gbCityUrl);
	}
	/**
	 * 添加
	 * @param gb_city_url
	 */
	public String add(GbCityUrl gbCityUrl){
		String sql="insert into gb_city_url (`cityurl`,`createTime`,`status`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbCityUrl.getCityurl(),gbCityUrl.getCreateTime(),gbCityUrl.getStatus());	
		log.info("添加gbCityUrl:"+(c>0?"成功":"失败"));
		return ("添加gbCityUrl:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param gb_city_url
	 */
	public boolean save(GbCityUrl gbCityUrl){
		String sql="insert into gb_city_url (`cityurl`,`createTime`,`status`) values(?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbCityUrl.getCityurl(),gbCityUrl.getCreateTime(),gbCityUrl.getStatus());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param gb_city_url
	 */
	public boolean update(GbCityUrl gbCityUrl){
		String sql="update gb_city_url set (`cityurl`=?,`createTime`=?,`status`=?) values(?,?,?) where gbsiteid=?";
		int c=jdbcTemplate.update(sql.toString(),gbCityUrl.getCityurl(),gbCityUrl.getCreateTime(),gbCityUrl.getStatus(),gbCityUrl.getGbsiteid());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<GbCityUrl> listAll(){
		return listAll("gb_city_url", GbCityUrl.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_city_url", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public GbCityUrl load(Integer id){
		return getById("gb_city_url", id, GbCityUrl.class);
	}
	
	
}
