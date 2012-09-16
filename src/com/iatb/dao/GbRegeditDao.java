package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbRegedit;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbRegeditDao")
@SuppressWarnings("deprecation")
public class GbRegeditDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbRegeditDao.class);
	
	/**
	 * 添加并返回id
	 * @param gbRegedit
	 */
	public int addReturnId(GbRegedit gbRegedit){
		return insert("insert into gb_regedit (`siteid`,`name`,`password`,`email`,`phone`,`createTime`,`status`) values(?,?,?,?,?,?,?)", gbRegedit);
	}
	/**
	 * 添加
	 * @param gb_regedit
	 */
	public String add(GbRegedit gbRegedit){
		String sql="insert into gb_regedit (`siteid`,`name`,`password`,`email`,`phone`,`createTime`,`status`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbRegedit.getSiteid(),gbRegedit.getName(),gbRegedit.getPassword(),gbRegedit.getEmail(),gbRegedit.getPhone(),gbRegedit.getCreateTime(),gbRegedit.getStatus());	
		log.info("添加gbRegedit:"+(c>0?"成功":"失败"));
		return ("添加gbRegedit:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param gb_regedit
	 */
	public boolean save(GbRegedit gbRegedit){
		String sql="insert into gb_regedit (`siteid`,`name`,`password`,`email`,`phone`,`createTime`,`status`) values(?,?,?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbRegedit.getSiteid(),gbRegedit.getName(),gbRegedit.getPassword(),gbRegedit.getEmail(),gbRegedit.getPhone(),gbRegedit.getCreateTime(),gbRegedit.getStatus());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param gb_regedit
	 */
	public boolean update(GbRegedit gbRegedit){
		String sql="update gb_regedit set (`siteid`=?,`name`=?,`password`=?,`email`=?,`phone`=?,`createTime`=?,`status`=?) values(?,?,?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbRegedit.getSiteid(),gbRegedit.getName(),gbRegedit.getPassword(),gbRegedit.getEmail(),gbRegedit.getPhone(),gbRegedit.getCreateTime(),gbRegedit.getStatus(),gbRegedit.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<GbRegedit> listAll(){
		return listAll("gb_regedit", GbRegedit.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_regedit", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public GbRegedit load(Integer id){
		return getById("gb_regedit", id, GbRegedit.class);
	}
	
	
}
