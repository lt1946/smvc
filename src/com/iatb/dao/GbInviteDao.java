package com.iatb.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.iatb.pojo.GbInvite;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 * @since  2012-03-07 16:16:07
 */
@Repository("gbInviteDao")
@SuppressWarnings("deprecation")
public class GbInviteDao  extends JdbcDao{
	
	private static final Logger log=Logger.getLogger(GbInviteDao.class);
	
	/**
	 * 添加并返回id
	 * @param gbInvite
	 */
	public int addReturnId(GbInvite gbInvite){
		return insert("insert into gb_invite (`myuser`,`iuser`,`isiteid`,`createTime`,`status`) values(?,?,?,?,?)", gbInvite);
	}
	/**
	 * 添加
	 * @param gb_invite
	 */
	public String add(GbInvite gbInvite){
		String sql="insert into gb_invite (`myuser`,`iuser`,`isiteid`,`createTime`,`status`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbInvite.getMyuser(),gbInvite.getIuser(),gbInvite.getIsiteid(),gbInvite.getCreateTime(),gbInvite.getStatus());	
		log.info("添加gbInvite:"+(c>0?"成功":"失败"));
		return ("添加gbInvite:"+(c>0?"成功":"失败"));
	}
	/**
	 * 保存
	 * @param gb_invite
	 */
	public boolean save(GbInvite gbInvite){
		String sql="insert into gb_invite (`myuser`,`iuser`,`isiteid`,`createTime`,`status`) values(?,?,?,?,?)";
		int c=jdbcTemplate.update(sql.toString(),gbInvite.getMyuser(),gbInvite.getIuser(),gbInvite.getIsiteid(),gbInvite.getCreateTime(),gbInvite.getStatus());	
		return c>0?true:false;
	}
	/**
	 * 修改
	 * @param gb_invite
	 */
	public boolean update(GbInvite gbInvite){
		String sql="update gb_invite set (`myuser`=?,`iuser`=?,`isiteid`=?,`createTime`=?,`status`=?) values(?,?,?,?,?) where id=?";
		int c=jdbcTemplate.update(sql.toString(),gbInvite.getMyuser(),gbInvite.getIuser(),gbInvite.getIsiteid(),gbInvite.getCreateTime(),gbInvite.getStatus(),gbInvite.getId());	
		return c>0?true:false;
	}
	/**
	 * 查询所有
	 */
	public List<GbInvite> listAll(){
		return listAll("gb_invite", GbInvite.class);
	}
	/**
	 * 删除
	 * @param id
	 */
	public boolean delete(Integer id){
		return deleteById("gb_invite", id)>0?true:false;
	}
	/**
	 * 获取唯一
	 * @param id
	 */
	public GbInvite load(Integer id){
		return getById("gb_invite", id, GbInvite.class);
	}
	
	
}
