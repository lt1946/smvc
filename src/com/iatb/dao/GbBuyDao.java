package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbBuy;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbBuyDao")
@SuppressWarnings("deprecation")
public class GbBuyDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbBuyDao.class);
	
	/**
	 * 添加并返回id
	 * @param gbBuy
	 */
	public int addReturnId(GbBuy gbBuy){
		return insert("insert into gb_buy (`gbsiteid`,`url`,`title`,`desc`,`address`,`tel`,`bus`,`number`,`code`,`usetime`,`datetime`,`ftime`,`etime`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", gbBuy);
	}
	/**
	 * 添加
	 * @param gb_buy
	 */
	public String add(GbBuy gbBuy){
		String sql="insert into gb_buy (`gbsiteid`,`url`,`title`,`desc`,`address`,`tel`,`bus`,`number`,`code`,`usetime`,`datetime`,`ftime`,`etime`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbBuy.getGbsiteid(),gbBuy.getUrl(),gbBuy.getTitle(),gbBuy.getDesc(),gbBuy.getAddress(),gbBuy.getTel(),gbBuy.getBus(),gbBuy.getNumber(),gbBuy.getCode(),gbBuy.getUsetime(),gbBuy.getDatetime(),gbBuy.getFtime(),gbBuy.getEtime(),gbBuy.getCreateTime(),gbBuy.getStatus());	
		log.info("添加gbBuy:"+(c>0?"成功":"失败"));
		return ("添加gbBuy:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param gb_buy
	 */
	public boolean save(GbBuy gbBuy){
		String sql="insert into gb_buy (`gbsiteid`,`url`,`title`,`desc`,`address`,`tel`,`bus`,`number`,`code`,`usetime`,`datetime`,`ftime`,`etime`,`createTime`,`status`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbBuy.getGbsiteid(),gbBuy.getUrl(),gbBuy.getTitle(),gbBuy.getDesc(),gbBuy.getAddress(),gbBuy.getTel(),gbBuy.getBus(),gbBuy.getNumber(),gbBuy.getCode(),gbBuy.getUsetime(),gbBuy.getDatetime(),gbBuy.getFtime(),gbBuy.getEtime(),gbBuy.getCreateTime(),gbBuy.getStatus());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param gb_buy
	 */
	public boolean update(GbBuy gbBuy){
		String sql="update gb_buy set (`gbsiteid`=?,`url`=?,`title`=?,`desc`=?,`address`=?,`tel`=?,`bus`=?,`number`=?,`code`=?,`usetime`=?,`datetime`=?,`ftime`=?,`etime`=?,`createTime`=?,`status`=?) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbBuy.getGbsiteid(),gbBuy.getUrl(),gbBuy.getTitle(),gbBuy.getDesc(),gbBuy.getAddress(),gbBuy.getTel(),gbBuy.getBus(),gbBuy.getNumber(),gbBuy.getCode(),gbBuy.getUsetime(),gbBuy.getDatetime(),gbBuy.getFtime(),gbBuy.getEtime(),gbBuy.getCreateTime(),gbBuy.getStatus(),gbBuy.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<GbBuy> listAll(){
		return listAll("gb_buy", GbBuy.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_buy", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public GbBuy load(Integer id){
		return getById("gb_buy", id, GbBuy.class);
	}
	
	
}
