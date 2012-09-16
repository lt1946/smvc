package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbUser;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbUserDao")
@SuppressWarnings("deprecation")
public class GbUserDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbUserDao.class);
	
	/**
	 * 添加并返回id
	 * @param gbUser
	 */
	public int addReturnId(GbUser gbUser){
		return insert("insert into gb_user (`name`,`password`,`tel`,`city`,`createTime`,`status`,`gbsiteid`) values(?,?,?,?,?,?,?)", gbUser);
	}
	/**
	 * 添加
	 * @param gb_user
	 */
	public String add(GbUser gbUser){
		String sql="insert into gb_user (`name`,`password`,`tel`,`city`,`createTime`,`status`,`gbsiteid`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbUser.getName(),gbUser.getPassword(),gbUser.getTel(),gbUser.getCity(),gbUser.getCreateTime(),gbUser.getStatus(),gbUser.getGbsiteid());	
		log.info("添加gbUser:"+(c>0?"成功":"失败"));
		return ("添加gbUser:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param gb_user
	 */
	public boolean save(GbUser gbUser){
		String sql="insert into gb_user (`name`,`password`,`tel`,`city`,`createTime`,`status`,`gbsiteid`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbUser.getName(),gbUser.getPassword(),gbUser.getTel(),gbUser.getCity(),gbUser.getCreateTime(),gbUser.getStatus(),gbUser.getGbsiteid());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param gb_user
	 */
	public boolean update(GbUser gbUser){
		String sql="update gb_user set (`name`=?,`password`=?,`tel`=?,`city`=?,`createTime`=?,`status`=?,`gbsiteid`=?) values(?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbUser.getName(),gbUser.getPassword(),gbUser.getTel(),gbUser.getCity(),gbUser.getCreateTime(),gbUser.getStatus(),gbUser.getGbsiteid(),gbUser.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<GbUser> listAll(){
		return listAll("gb_user", GbUser.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_user", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public GbUser load(Integer id){
		return getById("gb_user", id, GbUser.class);
	}
	
	
}
